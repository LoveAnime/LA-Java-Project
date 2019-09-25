package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * author Administrator
 * date 2018-09-04
 * 描述: 1、使用@ConfigurationProperties注解批量引入多个配置。
 *       2、需要增加@Component注解将GirlInfo注册到bean，与配置导入无关。
 */
@Component
@ConfigurationProperties(prefix = "girl")
//@PropertySource("classpath:application.yml")
public class GirlInfo {
    private String cupSize;
    private Integer age;

    public String getCupSize() {
        return cupSize;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
