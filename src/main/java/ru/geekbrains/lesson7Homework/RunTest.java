package ru.geekbrains.lesson7Homework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class RunTest {

    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        start(WorkClassTest.class);
    }

    static void start(Class testClass) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        Method[] methods = testClass.getDeclaredMethods();
        Object obj = testClass.newInstance();
        Map<Integer, List<String>> orderedTests = new TreeMap<>();

        Method beforeSuite = null;
        Method afterSuite = null;
        for (Method method : methods) {
            if (method.getAnnotation(BeforeSuite.class) != null) {
                if (beforeSuite != null) {
                    throw new RuntimeException("Multiple @BeforeSuite Annotations");
                }
                beforeSuite = method;
            }
            if (method.getAnnotation(AfterSuite.class) != null) {
                if (afterSuite != null) {
                    throw new RuntimeException("Multiple @AfterSuite Annotations");
                }
                afterSuite = method;
            }
            if (method.getAnnotation(Test.class) != null) {
                int methodPriority = method.getAnnotation(Test.class).value();
                if (orderedTests.containsKey(methodPriority)) {
                    orderedTests.get(methodPriority).add(method.getName());
                } else {
                    orderedTests.put(method.getAnnotation(Test.class).value(), new ArrayList<>(Arrays.asList(method.getName())));
                }
            }
        }

        if (beforeSuite != null) {
            beforeSuite.invoke(obj);
        }

        for (Map.Entry<Integer, List<String>> entry : orderedTests.entrySet()) {
            Method method;
            for (String methodName: entry.getValue()) {
                try {
                    method = testClass.getDeclaredMethod(methodName);
                    System.out.print(method.getAnnotation(DisplayName.class).value() + " -> ");
                    method.invoke(obj);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }

        if (afterSuite != null) {
            afterSuite.invoke(obj);
        }
    }
}
