package com.example.demo.service;

import com.example.demo.bo.WeatherResponse;
import com.example.demo.utils.SysVariable;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * author 帝歌恋雪
 * date 2018-09-23
 * 描述:
 */
public class WeatherDataServiceImp implements WeatherDataService {

    @Autowired
    private RestTemplate restTemplate;  // 一个Rest客户端，默认采用apache httpclient来实现

    @Override
    public WeatherResponse getDataByCityId(String cityId) {
        String url = SysVariable.WEATHER_API + "?cityKey=" + cityId;
        return doGetWeatherData(url);
    }

    @Override
    public WeatherResponse getDataByCityName(String cityName) {
        String url = SysVariable.WEATHER_API + "?city=" + cityName;
        return doGetWeatherData(url);
    }

    private WeatherResponse doGetWeatherData(String url) {
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String strBody = null;
        if (response.getStatusCodeValue() == 200) {
            strBody = response.getBody();
        }
        ObjectMapper mapper = new ObjectMapper();
        WeatherResponse weather = null;
        try {
            weather = mapper.readValue(strBody, WeatherResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weather;
    }
}
