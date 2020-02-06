package com.anime.acg.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 陌上丶天琊
 * @date 2019-11-23 17:44
 * 描述: 导入yml中的自定义配置
 * 1、@Component将AuthorInfo注册到bean，与导入配置无关
 * 2、可以使用@Value注解逐个属性引入。
 * 3、也可以使用@ConfigurationProperties批量导入
 */
@Component
@ConfigurationProperties(prefix = "author")
public class AuthorInfo {
//    @Value("${author.age}")
    private String name;
    private Integer age;
    private String describe;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
