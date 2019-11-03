package com.anime.proxy.v3;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author 陌上丶天琊
 * @date 2019-11-02 11:40
 * 描述：
 */
public class LogInterceptor implements MethodInterceptor {

    /**
     * @param o           代理对象，即main方法中的tank
     * @param method      被代理对象接口的方法，即tank的move()
     * @param objects     方法参数
     * @param methodProxy 方法的代理类
     * @return 方法返回值
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println(o.getClass().getSuperclass().getName());
        System.out.println("intercept before");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("intercept after");
        return result;
    }
}
