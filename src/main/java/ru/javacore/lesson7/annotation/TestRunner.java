/*
 * Copyright (c) 2018.
 * @author Pavel Dymov
 */

package ru.javacore.lesson7.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class TestRunner {

    private static <T extends Annotation> Method getSingletonAnnotation(Method method, Class<T> annotationClass, Method targetMethod) {
        if (method.isAnnotationPresent(annotationClass)) {
            if (targetMethod == null)
                return method;
            else
                throw new RuntimeException(String.format("Annotation '%s' must be a singleton. Troublemaker: %s", annotationClass.getName(), method));
        }
        return targetMethod;
    }

    public static void run(Class<?> cls) {
        Method beforeSuite = null;
        Method afterSuite = null;
        final SortedSet<Map.Entry<Method, Integer>> sortedSet = new TreeSet<>((o1, o2) -> {
            int compare = o1.getValue().compareTo(o2.getValue());
            if (compare == 0) return o1.getKey().getName().compareTo(o2.getKey().getName());
            else return compare;
        });

        // находим методы с аннотациями
        Method[] methods = cls.getMethods();
        for (Method method : methods) {
            beforeSuite = getSingletonAnnotation(method, BeforeSuite.class, beforeSuite);
            afterSuite = getSingletonAnnotation(method, AfterSuite.class, afterSuite);

            Test test = method.getAnnotation(Test.class);
            if (test != null) sortedSet.add(new AbstractMap.SimpleEntry<>(method, test.priority()));
        }

        // запускаем методы
        try {
            Object obj = cls.newInstance();
            if (beforeSuite != null) beforeSuite.invoke(obj, (Object[]) beforeSuite.getParameters());
            sortedSet.forEach(e -> {
                try {
                    e.getKey().invoke(obj, (Object[]) e.getKey().getParameters());
                } catch (IllegalAccessException | InvocationTargetException e1) {
                    e1.printStackTrace();
                }
            });
            if (afterSuite != null) afterSuite.invoke(obj, (Object[]) afterSuite.getParameters());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}


