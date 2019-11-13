package com.anime.chainOfResponsibility.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 陌上丶天琊
 * @date 2019-10-22 22:49
 * 描述：过滤器的一个责任链
 * 1、add()用于多次添加过滤器，再进行过滤处理
 * 2、继承Filter表明该责任链也是一个过滤器，允许往该责任链添加另一条责任链
 */
public class FilterChain implements Filter {
    private List<Filter> filters = new ArrayList<>();

    public FilterChain add(Filter filter) {
        filters.add(filter);
        return this;
    }

    @Override
    public void doFilter(Message msg) {
        for (Filter f : filters) {
            f.doFilter(msg);
        }
    }
}
