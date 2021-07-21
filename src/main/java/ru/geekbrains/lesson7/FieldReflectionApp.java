package ru.geekbrains.lesson7;

import java.lang.reflect.Field;

public class FieldReflectionApp {
    public static void main(String[] args) {
        Class bikeClass = Bike.class;

        Field[] publicFields = bikeClass.getFields();
        for (Field publicField : publicFields) {
            System.out.println(publicField.getName() + " " + publicField.getType().getName());
        }
        System.out.println();
        Field[] allFields = bikeClass.getDeclaredFields();
        for (Field allField : allFields) {
            System.out.println(allField.getName() + " " + allField.getType().getName());
        }

        System.out.println();

        try {
            Field field = bikeClass.getField("name");
            System.out.println(field.getModifiers());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        System.out.println();
        Bike bike = new Bike();
        System.out.println(bike);
        try {
            Field nameField = bikeClass.getField("name");
            nameField.set(bike, "Specialized");
            Field serialNumberField = bikeClass.getDeclaredField("serialNo");
            serialNumberField.setAccessible(true);
            serialNumberField.set(bike, "11111");
            System.out.println(bike);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    static class Bike {
        public String name;
        public int year;
        private String serialNo;

        public Bike(String name, int year, String serialNo) {
            this.name = name;
            this.year = year;
            this.serialNo = serialNo;
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
}
