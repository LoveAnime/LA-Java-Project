package com.example.demo.controller;/**
 * Created by 帝歌恋雪 on 2018-09-23.
 */

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author 帝歌恋雪
 * date 2018-09-23
 * 描述:
 */
@RestController
public class TestController {

    @GetMapping
    public String test(){
        return "hello";
    }
}
