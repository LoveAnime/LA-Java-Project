package com.anime.chainOfResponsibility.impl;

import com.anime.chainOfResponsibility.Filter;
import com.anime.chainOfResponsibility.Message;

/**
 * @author 陌上丶天琊
 * @date 2019-10-22 22:43
 * 描述：字符串替换
 */
public class FaceFilterImpl implements Filter {
    @Override
    public void doFilter(Message msg) {
        msg.setMemo(msg.getMemo().replace(":)", "^V^"));
    }
}
