package ru.demetriuzz.task;

class MountainTask {
    static String name = "Himalaya";

    static MountainTask getMountain() {
        System.out.println("Getting Name ");
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getMountain().name);

        final var x = getMountain();
        // ожидаем NPE, но его не будет
        final var y = x.name;
    }
}