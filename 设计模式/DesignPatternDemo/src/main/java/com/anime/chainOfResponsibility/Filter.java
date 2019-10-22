package com.anime.chainOfResponsibility;

/**
 * @author 陌上丶天琊
 * @date 2019-10-22 22:36
 * 描述：过滤器接口，用于过滤消息
 */
public interface Filter {
    void doFilter(Message msg);
}
