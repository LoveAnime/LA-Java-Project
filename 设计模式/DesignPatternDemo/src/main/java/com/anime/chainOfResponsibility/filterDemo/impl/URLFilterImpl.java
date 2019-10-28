package com.anime.chainOfResponsibility.filterDemo.impl;

import com.anime.chainOfResponsibility.filterDemo.Filter;
import com.anime.chainOfResponsibility.filterDemo.Message;

/**
 * @author 陌上丶天琊
 * @date 2019-10-22 22:43
 * 描述：补全网址
 */
public class URLFilterImpl implements Filter {
    @Override
    public void doFilter(Message msg) {
        msg.setMemo(msg.getMemo().replace("anime.com", "http://www.anime.com"));
    }
}
