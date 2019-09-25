package com.example.demo.controller;

import com.example.demo.config.AuthorInfo;
import com.example.demo.config.GirlInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Test {
    @Autowired
    AuthorInfo authorInfo;

    @Autowired
    GirlInfo girlInfo;

    @RequestMapping(value = "/")
    public String hello() {
        return "hello";
    }


    @RequestMapping(value = "/value")
    public String test1() {
        return "hello " + authorInfo.getDescribe();
    }

    @RequestMapping(value = "/girl")
    public String test2() {
        return "hello " + girlInfo.getCupSize();
    }

    @RequestMapping(value = "/PathVariable/{id}")
    public Integer test3(@PathVariable("id") Integer myId) {
        // PathVariable以斜杆分隔url
        return myId;
    }

    @RequestMapping(value = "/RequestParam")
    public String test4(@RequestParam(value = "id", defaultValue = "0") Integer myId) {
        // 以"?id="的方式拼接url，不传或写不全均使用默认值
        return "id " + myId.toString();
    }
}
