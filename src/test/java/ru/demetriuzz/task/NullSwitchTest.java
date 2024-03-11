package ru.demetriuzz.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NullSwitchTest {

    @Test
    void t() {
        // test for Java 17
        Assertions.assertEquals(1, ref(E.ONE));
        Assertions.assertEquals(2, ref(E.TWO));
        Assertions.assertEquals(0, ref(E.THREE));
        Assertions.assertThrows(NullPointerException.class, () -> ref(null));
    }

    private int ref(E e) {
        return switch (e) {
            case ONE -> 1;
            case TWO -> 2;
            default -> 0;
        };
    }

    private enum E {
        ONE, TWO, THREE
    }


    @Test
    void ti() {
        Integer a = 120;
        Integer b = 120;
        System.out.printf("a=%d\n", a);
        System.out.printf("b=%d\n", b);
        Assertions.assertTrue(a == b);

        Integer c = 128; // -128 ... 127 кэш
        Integer d = 128;
        System.out.printf("c=%d\n", c);
        System.out.printf("d=%d\n", d);
        Assertions.assertFalse(c == d);

        String a1 = "xxx";
        String b1 = "xxx";
        Assertions.assertTrue(a1 == b1);
    }

}
