package com.fpt.mobile.solutions.utils;

/**
 * Created by ThaoHQSE60963 on 5/30/14.
 */

import java.lang.reflect.InvocationTargetException;

/**
 * Using Java Reflection APIUtils
 * helping invoke method by name (and support add parameters and values)
 * Notes : this Java APIUtils is extremely slowly on Android Platform.
 */
public class ReflectionUtils {
    public static Object tryInvoke(Object target, String methodName, Object... args) {
        Class<?>[] argTypes = new Class<?>[args.length];
        for (int i = 0; i < args.length; i++) {
            argTypes[i] = args[i].getClass();
        }

        return tryInvoke(target, methodName, argTypes, args);
    }

    public static Object tryInvoke(Object target, String methodName, Class<?>[] argTypes,
                                   Object... args) {
        try {
            return target.getClass().getMethod(methodName, argTypes).invoke(target, args);
        } catch (NoSuchMethodException ignored) {
        } catch (IllegalAccessException ignored) {
        } catch (InvocationTargetException ignored) {
        }

        return null;
    }

    public static <E> E callWithDefault(Object target, String methodName, E defaultValue) {
        try {
            //noinspection unchecked
            return (E) target.getClass().getMethod(methodName, (Class[]) null).invoke(target);
        } catch (NoSuchMethodException ignored) {
        } catch (IllegalAccessException ignored) {
        } catch (InvocationTargetException ignored) {
        }

        return defaultValue;
    }
}
