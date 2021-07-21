package ru.geekbrains.lesson7;

import java.lang.reflect.Modifier;

public class ClassReflectionApp {

    public static void main(String[] args) {
        Class clazz = String.class;
        System.out.println(clazz.getName());
        System.out.println(clazz.getSimpleName());

        int modifiers = clazz.getModifiers();

        if (Modifier.isPublic(modifiers)) {
            System.out.println(clazz.getName() + " is public");
        }
        if (Modifier.isAbstract(modifiers)) {
            System.out.println(clazz.getName() + " is abstract");
        }
        if (Modifier.isFinal(modifiers)) {
            System.out.println(clazz.getName() + " is final");
        }

        Class superClass = clazz.getSuperclass();
        System.out.println(superClass.getName());

        Class[] interfaces = clazz.getInterfaces();
        for (Class anInterface : interfaces) {
            System.out.println(clazz.getName() + " implements " + anInterface.getName());
        }
    }

}
