package com.anime.chainOfResponsibility.filter.impl;

import com.anime.chainOfResponsibility.filter.Filter;
import com.anime.chainOfResponsibility.filter.Message;

/**
 * @author 陌上丶天琊
 * @date 2019-10-22 22:42
 * 描述：大小写转化
 */
public class SensitiveFilterImpl implements Filter {
    @Override
    public void doFilter(Message msg) {
        msg.setMemo(msg.getMemo().replace("HELLO", "Hello").replace("WORLD", "World"));

    }
}
