package ru.geekbrains.lesson7;

public class ReflectionApp {
    public static void main(String[] args) {
        Class clazz = "String".getClass();
        Class integerClass = Integer.class;
        Class stringClass = String.class;
        Class intClass = int.class;

        try {
            Class forName = Class.forName("java.lang.String");
        } catch (ClassNotFoundException e) {

        }
    }
}
