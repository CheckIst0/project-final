package ru.java.projectfinal.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.java.projectfinal.core.client.Client;
import ru.java.projectfinal.core.errorhadler.CoinNotFoundException;
import ru.java.projectfinal.core.mapper.CryptoDBMapper;
import ru.java.projectfinal.core.model.*;
import ru.java.projectfinal.db.entity.Crypto;
import ru.java.projectfinal.db.repository.CryptoDBRepository;

import java.time.format.DateTimeFormatter;

@Service
public class CryptoApiService {

    @Autowired
    private Client client;

    @Autowired
    private CryptoDBMapper mapper;

    @Autowired
    private CryptoDBRepository repository;

    public CryptoApiDto getById(Long id){
        Crypto crypto = repository.getById(id);
        return mapper.map(crypto, CryptoApiDto.class);
    }

    public CryptoApiDto getPrice(String coin){
        CryptoApiDto result = client.getPrice(coin);
        if (result.getPrice() == null){
            throw new CoinNotFoundException(coin);
        }
        repository.save(mapper.map(result, Crypto.class));
        return result;
    }

    public CryptoApiDto getHistPrice(String coin, String time){
        Crypto crypto = repository.findByNameAndTime(coin, time);
        CryptoApiDto result;
        if (crypto == null){
            result = client.getHistPrice(coin, time);
            repository.save(mapper.map(result, Crypto.class));
            return result;
        }
        else {
            if (crypto.getPrice() == null) {
                if (crypto.getClose() == null) {
                    result = client.getHistPrice(coin, time);
                    repository.save(mapper.map(result, Crypto.class));
                } else {
                    result = mapper.map(crypto, CryptoApiDto.class);
                    result.setPrice(crypto.getClose());
                    result.setClose(null);
                    result.setOpen(null);
                    result.setHigh(null);
                    result.setLow(null);
                }
                return result;
            } else {
                return mapper.map(crypto, CryptoApiDto.class);
            }
        }
    }

    public DataDto getListFullData(String coin, int limit){
        return client.getListFullData(coin, limit);
    }

    public CryptoApiDto getFullData(String coin){
        CryptoApiDto result = client.getFullData(coin);
        repository.save(mapper.map(result, Crypto.class));
        return result;
    }

    public CryptoApiDto putData(Long id){
        CryptoApiDto result = mapper.map(repository.getById(id), CryptoApiDto.class);
        CryptoApiDto inter = client.getFullData(result.getName());
        result.setName(inter.getName());
        result.setPrice(inter.getPrice());
        result.setHigh(inter.getHigh());
        result.setLow(inter.getLow());
        result.setOpen(inter.getOpen());
        result.setClose(inter.getClose());
        result.setTime(inter.getTime());
        repository.save(mapper.map(result, Crypto.class));
        return result;
    }

    public void deleteCrypto(Long id){
        repository.deleteById(id);
    }
}