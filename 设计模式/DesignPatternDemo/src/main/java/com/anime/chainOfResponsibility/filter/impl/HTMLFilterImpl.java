package com.anime.chainOfResponsibility.filter.impl;

import com.anime.chainOfResponsibility.filter.Filter;
import com.anime.chainOfResponsibility.filter.Message;

/**
 * @author 陌上丶天琊
 * @date 2019-10-22 22:41
 * 描述：字符串替换
 */
public class HTMLFilterImpl implements Filter {
    @Override
    public void doFilter(Message msg) {
        msg.setMemo(msg.getMemo().replace("<", "[").replace(">", "]"));
    }
}
