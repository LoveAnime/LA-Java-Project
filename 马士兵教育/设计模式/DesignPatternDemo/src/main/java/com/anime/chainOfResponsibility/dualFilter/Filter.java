package com.anime.chainOfResponsibility.dualFilter;

/**
 * @author 陌上丶天琊
 * @date 2019-10-27 13:43
 * 描述：过滤器接口，用于过滤消息
 */
public interface Filter {
    void doFilter(Request request, Response response, FilterChain chain);
}
