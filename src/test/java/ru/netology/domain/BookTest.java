package ru.netology.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    Book b0 = new Book();
    Book b1 = new Book( 1, "One", 111, "mr. First");
    Book b2 = new Book( 2, "Two", 222, "mr. Second");
    Book b1copy = new Book( 1, "One", 111, "mr. First");

    @Test
    void testEquals() {
        assertEquals(b1, b1copy);
        assertNotEquals(b1, b2);
    }

    @Test
    void testHashCode() {
        assertEquals(b1.hashCode(), b1copy.hashCode());
        assertNotEquals(b1.hashCode(), b2.hashCode());
    }

    @Test
    void testToString() {
        String expected = "Book{id=1, name='One', price=111, author='mr. First'}";
        String actual = b1.toString();
        assertEquals(expected, actual);
    }

    @Test
    void getsetAuthor() {
        String expected = "Пушкин";
        b1.setAuthor(expected);
        String actual = b1.getAuthor();
        assertEquals(expected, actual);
    }

    @Test
    public void toString_shouldUseOverrided() {
        Product p = b1;
        assertEquals(p.toString(), b1.toString());
    }}