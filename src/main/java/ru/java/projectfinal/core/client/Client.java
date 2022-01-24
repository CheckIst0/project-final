package ru.java.projectfinal.core.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.java.projectfinal.core.model.*;


import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class Client {
    private RestTemplate restTemplate = new RestTemplate();
    private String url;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

    public CryptoApiDto getPrice(String coin){
        Coin.Price response = null;
        url = String.format("https://min-api.cryptocompare.com/data/price?fsym=%s&tsyms=USD&api_key=0e61d947e19d6f9818af5bf2e984d959908af59fa1e929cd0a2131a9676e5218", coin);
        try {
            response = restTemplate.getForObject(new URI(url), Coin.Price.class);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (response.getPrice() == null){

        }
        CryptoApiDto crypto = new CryptoApiDto();
        crypto.setPrice(response.getPrice());
        crypto.setName(coin);
        crypto.setCurrency("USD");
        crypto.setTime(dtf.format(LocalDateTime.now()));
        return crypto;
    }

    public CryptoApiDto getHistPrice(String coin, String time){
        Coin response = null;
        Long ts = null;
        try {
            ts = dateFormat.parse(time).getTime()/1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        url = String.format("https://min-api.cryptocompare.com/data/pricehistorical?fsym=%s&tsyms=USD&ts=%s&api_key=0e61d947e19d6f9818af5bf2e984d959908af59fa1e929cd0a2131a9676e5218", coin, ts);
        try {
            response = restTemplate.getForObject(new URI(url), Coin.class);
        } catch (URISyntaxException e){
            e.printStackTrace();
        }
        CryptoApiDto cryptoDto = new CryptoApiDto();
        cryptoDto.setName(coin);
        cryptoDto.setTime(time);
        cryptoDto.setCurrency("USD");
        cryptoDto.setPrice(response.getPrice().getPrice());
        return cryptoDto;
    }

    public DataDto getListFullData(String coin, int limit){
        DataDto response = null;
        url = String.format("https://min-api.cryptocompare.com/data/v2/histoday?fsym=%s&tsym=USD&limit=%d&api_key=0e61d947e19d6f9818af5bf2e984d959908af59fa1e929cd0a2131a9676e5218", coin, limit);
        try{
            response = restTemplate.getForObject(new URI(url), DataDto.class);
        } catch (URISyntaxException e){
            e.printStackTrace();
        }
        List<CryptoApiDto> result = new ArrayList<>();
        for (CryptoApiDto it : response.getDataList().getData()){
            it.setName(coin);
            it.setTime(toDate(Long.parseLong(it.getTime())));
            it.setCurrency("USD");
            result.add(it);
        }
        response.getDataList().setData(result);
        return response;
    }

    public CryptoApiDto getFullData(String coin){
        FullData request = null;
        CryptoApiDto result = new CryptoApiDto();
        url =String.format("https://min-api.cryptocompare.com/data/generateAvg?fsym=%s&tsym=USD&e=Kraken&api_key=0e61d947e19d6f9818af5bf2e984d959908af59fa1e929cd0a2131a9676e5218", coin);
        try {
            request = restTemplate.getForObject(new URI(url), FullData.class);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        result.setOpen(request.getData().getOpen());
        result.setPrice(request.getData().getPrice());
        result.setHigh(request.getData().getHigh());
        result.setLow(request.getData().getLow());
        result.setName(request.getData().getName());
        result.setTime(toDate(Long.parseLong(request.getData().getTime())));
        result.setCurrency("USD");
        return result;
    }

    private String toDate(Long ts){
        Date date = new Date(ts*1000);
        return dateFormat.format(date);
    }
}