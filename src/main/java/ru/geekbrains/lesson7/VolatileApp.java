package ru.geekbrains.lesson7;

public class VolatileApp {

    static volatile boolean isAlive = true;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (isAlive) {
                System.out.println(Thread.currentThread().getName() + " alive");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();

        Thread.sleep(5000);
        isAlive = false;
    }
}
