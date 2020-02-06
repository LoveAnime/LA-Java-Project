package com.anime.proxy.v2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author 陌上丶天琊
 * @date 2019-11-02 10:32
 * 描述：代理行为类
 * 传入被代理对象生成代理对象并返回，同时执行代理行为。
 */
public class LogHandler<T> implements InvocationHandler {

    private T proxyObj;

    public LogHandler(T proxyObj) {
        this.proxyObj = proxyObj;
    }

    /**
     * @param proxy  代理对象，即main方法中的movable
     * @param method 被代理对象接口的方法，即tank的move()
     * @param args   方法参数
     * @return 方法返回值
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("method " + method.getName() + " start");
        Object result = method.invoke(proxyObj, args);
        System.out.println("method " + method.getName() + " end");
        return result;
    }
}
