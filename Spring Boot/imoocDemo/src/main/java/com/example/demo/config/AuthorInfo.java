package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * author Administrator
 * date 2018-09-04
 * 描述: 1、使用@Value注解逐个引入配置文件里的属性。
 *       2、Component注解只是为了将AuthorInfo注册到bean，与导入配置无关。
 */

@Component
public class AuthorInfo {
    @Value("${author}")
    private String author;

    @Value("${age}")
    private Integer age;

    @Value("${describe}")
    private String describe;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
