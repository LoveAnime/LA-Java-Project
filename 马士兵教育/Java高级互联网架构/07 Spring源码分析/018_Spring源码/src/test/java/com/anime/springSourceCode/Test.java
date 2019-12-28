package com.anime.springSourceCode;

import java.lang.reflect.Field;
import java.util.stream.Stream;

public class Test {

    @org.junit.Test
    public void testField() throws NoSuchFieldException, IllegalAccessException {
        UserController userController = new UserController();
        Class<? extends UserController> clazz = userController.getClass();

        Field field = clazz.getDeclaredField("userService");
        UserService userService = new UserService();
        field.setAccessible(true);
        field.set(userController, userService);
        System.out.println(userController.getUserService());
    }
}
