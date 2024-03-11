package ru.demetriuzz.task;

/**
 * Результатом компиляции будет?
 * 1. Ошибка компиляции
 * 2. Ошибка времени выполнения
 * 3. «Object»
 * 4. «FileNotFoundException»  <<<---
 * 5. «IOException»
 */
public class OverloadTask {
    public void method(Object o) {
        System.out.println("Object");
    }

    public void method(java.io.FileNotFoundException f) {
        System.out.println("FileNotFoundException");
    }

    public void method(java.io.IOException i) {
        System.out.println("IOException");
    }

    public static void main(String args[]) {
        OverloadTask test = new OverloadTask();
        test.method(null);
    }
}

// Будет вызван перегруженный метод тот, в котором входной параметр объекта с самым дальним наследованием:
// если смотреть сверху в них от родительского класса Object.
// Это вопрос по иерархии классов.