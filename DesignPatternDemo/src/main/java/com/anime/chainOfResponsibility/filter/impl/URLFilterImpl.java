package com.anime.chainOfResponsibility.filter.impl;

import com.anime.chainOfResponsibility.filter.Filter;
import com.anime.chainOfResponsibility.filter.Message;

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
