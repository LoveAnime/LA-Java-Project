package com.anime.chainOfResponsibility.dualFilter;

import com.anime.chainOfResponsibility.dualFilter.impl.FaceFilterImpl;
import com.anime.chainOfResponsibility.dualFilter.impl.HTMLFilterImpl;

/**
 * @author 陌上丶天琊
 * @date 2019-10-27 13:43
 * 描述：双向过滤器
 * 使用场景：web请求中的过滤器，既对请求过滤，也对响应过滤
 * 其中，请求过滤器先添加先处理，后添加后处理
 * 反之，响应过滤器先添加后处理，后添加先处理
 */
public class Main {

    public static void main(String[] args) {
        Request request = new Request();
        Response response = new Response();

        FilterChain chain = new FilterChain();
        chain.add(new FaceFilterImpl()).add(new HTMLFilterImpl());
        chain.doFilter(request, response);

        System.out.println(request);
        System.out.println(response);
    }
}
