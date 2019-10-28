package com.anime.chainOfResponsibility.filterDemo;

import com.anime.chainOfResponsibility.filterDemo.impl.FaceFilterImpl;
import com.anime.chainOfResponsibility.filterDemo.impl.HTMLFilterImpl;
import com.anime.chainOfResponsibility.filterDemo.impl.SensitiveFilterImpl;
import com.anime.chainOfResponsibility.filterDemo.impl.URLFilterImpl;

import java.time.LocalDateTime;

/**
 * @author 陌上丶天琊
 * @date 2019-10-21 19:21
 * 使用场景：在论坛中发表文章，后台要经过处理才可以发表或者进入数据库
 */
public class Main {

    public static void main(String[] args) {
        Message msg = new Message();
        msg.setName("anime");
        msg.setMemo("HELLO WORLD. Welcome to <p>anime.com</p> :). I am a java programer.");
        msg.setUpdateTime(LocalDateTime.now());

        FilterChain chain = new FilterChain();
        chain.add(new HTMLFilterImpl()).add(new SensitiveFilterImpl());

        FilterChain chain1 = new FilterChain();
        chain1.add(new FaceFilterImpl()).add(new URLFilterImpl());

        chain.add(chain1);
        chain.doFilter(msg);

        System.out.println(msg);
    }
}
