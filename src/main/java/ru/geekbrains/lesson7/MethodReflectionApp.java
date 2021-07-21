package ru.geekbrains.lesson7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class MethodReflectionApp {
    static class Bike {
        public String name;
        public int year;
        private String serialNo;

        public Bike(String name, int year, String serialNo) {
            this.name = name;
            this.year = year;
            this.serialNo = serialNo;
        }

        @MyAnnotation
        public String getName() {
            return name;
        }

        public int getYear() {
            return year;
        }

        public String getSerialNo() {
            return serialNo;
        }

        @MyValueAnnotation(value = 10)
        public void info() {
            System.out.println(this);
        }

        public void info(String s, int times) {
            for (int i = 0; i < times; i++) {
                System.out.println(s);
            }

        }

        public Bike() {
        }

        @Override
        public String toString() {
            return "Bike{" +
                    "name='" + name + '\'' +
                    ", year=" + year +
                    ", serialNo='" + serialNo + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        Class bikeClass = Bike.class;
        Bike bike = new Bike("Specialize", 2021, "123123");
        Method[] methods = bikeClass.getMethods();
        for (Method method : methods) {
            if(method.getAnnotation(MyAnnotation.class) == null) {
                continue;
            }
            System.out.println(method.getName() + " " + Arrays.toString(method.getParameterTypes()));
        }
        System.out.println();
        System.out.println();

        try {
            Method method = bikeClass.getMethod("info", String.class, int.class);
            method.invoke(bike, "text test", 5);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println();

        try {
            Method infoMethod = bikeClass.getDeclaredMethod("info");
            MyValueAnnotation annotation = infoMethod.getAnnotation(MyValueAnnotation.class);
            System.out.println(annotation);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
