package com.anime.chainOfResponsibility.dualFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 陌上丶天琊
 * @date 2019-10-22 22:49
 * 描述：过滤器的一个责任链
 * add()用于多次添加过滤器，再进行过滤处理。
 * 不继承Filter
 */
public class FilterChain {
    private List<Filter> filters = new ArrayList<>();
    private int pos;

    public FilterChain add(Filter filter) {
        filters.add(filter);
        return this;
    }

    public void doFilter(Request request, Response response) {
        if (pos < filters.size()) {
            Filter filter = filters.get(pos);
            pos++;
            filter.doFilter(request, response, this);
        }
    }
}
