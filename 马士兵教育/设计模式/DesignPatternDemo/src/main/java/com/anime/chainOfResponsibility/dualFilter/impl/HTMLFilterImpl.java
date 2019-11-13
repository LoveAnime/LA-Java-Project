package com.anime.chainOfResponsibility.dualFilter.impl;

import com.anime.chainOfResponsibility.dualFilter.Filter;
import com.anime.chainOfResponsibility.dualFilter.FilterChain;
import com.anime.chainOfResponsibility.dualFilter.Request;
import com.anime.chainOfResponsibility.dualFilter.Response;
import org.apache.commons.lang3.StringUtils;

/**
 * @author 陌上丶天琊
 * @date 2019-10-27 13:43
 * 描述：字符串替换
 */
public class HTMLFilterImpl implements Filter {
    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        if (StringUtils.isBlank(request.getMemo())) {
            request.setMemo("-- HTML request ");
        } else {
            request.setMemo(request.getMemo() + "-- HTML request ");
        }

        chain.doFilter(request, response);

        if (StringUtils.isBlank(response.getMemo())) {
            response.setMemo("-- HTML response ");
        } else {
            response.setMemo(response.getMemo() + "-- HTML response ");
        }
    }
}
