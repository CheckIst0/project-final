package ru.java.projectfinal.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.java.projectfinal.core.errorhadler.CoinNotFoundException;
import ru.java.projectfinal.core.errorhadler.IdNotFoundException;
import ru.java.projectfinal.core.errorhadler.IncorrectTimeException;
import ru.java.projectfinal.core.errorhadler.OverLimitException;
import ru.java.projectfinal.core.model.*;
import ru.java.projectfinal.core.service.CryptoApiService;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/crypto")
public class CryptoApiController {

    @Autowired
    private CryptoApiService service;

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

    @ApiOperation(
            value = "Получение информации о криптовалюте из базы данных по ID"
    )
    @GetMapping("/getById/{id}")
    public CryptoApiDto getById(@PathVariable("id") Long id){
        try {
            return service.getById(id);
        } catch (EntityNotFoundException e){
            throw new IdNotFoundException(id);
        }
    }

    @ApiOperation(
            value = "Удаление записи о криптовалюте по указанному ID"
    )
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public void deleteCrypto(@PathVariable("id") Long id){
        try {
            service.deleteCrypto(id);
        } catch (EmptyResultDataAccessException e){
            throw new IdNotFoundException(id);
        }
    }

    @ApiOperation(
            value = "Обновление записи о криптовалюте по указанному ID"
    )
    @RequestMapping(method = RequestMethod.PUT, value = "/put_data/{id}")
    public CryptoApiDto putData(@PathVariable("id") Long id){
        try {
            return service.putData(id);
        } catch (EntityNotFoundException e){
            throw new IdNotFoundException(id);
        }
    }

    @ApiOperation(
            value = "Получение текущей цены, нужной криптовалюты"
    )
    @RequestMapping(method = RequestMethod.GET, value = "/price/{coin}")
    public CryptoApiDto getPrice(@PathVariable("coin") String coin){
        CryptoApiDto result = service.getPrice(coin);
        return result;
    }

    @ApiOperation(
            value = "Получение цены криптовалюты в указанный момент времени"
    )
    @RequestMapping(method = RequestMethod.GET, value = "/hist_price/{coin}/{time}")
    public CryptoApiDto getHistPrice(@PathVariable("coin") String coin, @PathVariable("time") @DateTimeFormat(pattern = "yyyy.MM.dd HH:mm:ss") LocalDateTime time){
        LocalDateTime beginDate = LocalDateTime.of(2009, 1, 3, 0, 0, 0);
        try {
            if (time.isAfter(LocalDateTime.now()) | time.isBefore(beginDate)){
                throw new IncorrectTimeException(time);
            }
            return service.getHistPrice(coin, time.format(dtf));
        } catch (NullPointerException e) {
            throw new CoinNotFoundException(coin);
        }
    }

    @ApiOperation(
            value = "Получение полной информации о криптовалюте на текущий момент"
    )
    @RequestMapping(method = RequestMethod.GET, value = "/full_data/{coin}")
    public CryptoApiDto getFullData(@PathVariable("coin") String coin) {
        try{
            return service.getFullData(coin);
        } catch (NullPointerException e){
            throw new CoinNotFoundException(coin);
        }
    }

    @ApiOperation(
            value = "Получение информации об указанной криптовалюте за указаный промежуток времени в днях"
    )
    @RequestMapping(method = RequestMethod.GET, value = "/list_full_data/{coin}/{limit}")
    public DataDto getListFullData(@PathVariable("coin") String coin, @PathVariable("limit") int limit){
        if (limit < 1 | limit > 2000){
            throw new OverLimitException();
        }
        try{
            return service.getListFullData(coin, limit);
        } catch (NullPointerException e){
            throw new CoinNotFoundException(coin);
        }
    }
}