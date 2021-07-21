package ru.geekbrains.lesson7Homework;

public class WorkClassTest {

    WorkClass classInstance;

    @BeforeSuite
    void init() {
        System.out.println("Init Method");
        classInstance = new WorkClass();
    }

    @AfterSuite
    void terminate() {
        System.out.println("Terminate method");
    }

    @Test
    @DisplayName("Testing add method priority 5")
    void addTest() {
        System.out.println(classInstance.add(5, 7) == 12 ? "Passed" : "Failed");
    }

    @Test(value = 4)
    @DisplayName("Testing add method priority 4")
    void addTest2() {
        System.out.println(classInstance.add(4, 7) == 12 ? "Passed" : "Failed");
    }

    @Test(value = 6)
    @DisplayName("Testing add method priority 6")
    void addTes3() {
        System.out.println(classInstance.add(5, 6) == 11 ? "Passed" : "Failed");
    }

    @Test()
    @DisplayName("Testing subtract method default priority")
    void subtractTest() {
        System.out.println(classInstance.subtract(5, 6) == -1 ? "Passed" : "Failed");
    }

    @Test()
    @DisplayName("Testing multiply method default priority")
    void multiplyTest() {
        System.out.println(classInstance.multiply(5, 6) == 30 ? "Passed" : "Failed");
    }

    @Test(value = 8)
    @DisplayName("Testing multiply method priority 8")
    void multiplyTest2() {
        System.out.println(classInstance.multiply(4, 6) == 23 ? "Passed" : "Failed");
    }

}
