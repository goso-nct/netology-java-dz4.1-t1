package ru.netology.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmartphoneTest {

    Smartphone s0 = new Smartphone();
    Smartphone s1 = new Smartphone(1, "one", 111, "Samsung" );
    Smartphone s2 = new Smartphone(2, "two", 222, "Apple" );
    Smartphone s1copy = new Smartphone(1, "one", 111, "Samsung" );

    @Test
    void testEquals() {
        assertEquals(s1, s1copy);
        assertNotEquals(s1, s2);
    }

    @Test
    void testHashCode() {
        assertEquals(s1.hashCode(), s1copy.hashCode());
        assertNotEquals(s1.hashCode(), s2.hashCode());
    }

    @Test
    void testToString() {
        String expected = "Smartphone{id=1, name='one', price=111, producer='Samsung'}";
        String actual = s1.toString();
        assertEquals(expected, actual);
    }

    @Test
    void getsetProducer() {
        String expected = "Xiaomi";
        s1.setProducer(expected);
        String actual = s1.getProducer();
        assertEquals(expected, actual);
    }
}