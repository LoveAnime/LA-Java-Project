package com.example.demo.service;/**
 * Created by 帝歌恋雪 on 2018-09-23.
 */

import com.example.demo.bo.WeatherResponse;

/**
 * @author 帝歌恋雪
 * @date 2018-09-23
 * 描述:
 */
public interface WeatherDataService {
    WeatherResponse getDataByCityId(String cityId);

    WeatherResponse getDataByCityName(String cityName);

}
