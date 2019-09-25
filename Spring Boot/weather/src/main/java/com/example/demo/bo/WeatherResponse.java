package com.example.demo.bo;/**
 * Created by 帝歌恋雪 on 2018-09-23.
 */

import java.io.Serializable;

/**
 * author 帝歌恋雪
 * date 2018-09-23
 * 描述:
 */
public class WeatherResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private Weather weather;
    private String status;
    private String desc;
}
