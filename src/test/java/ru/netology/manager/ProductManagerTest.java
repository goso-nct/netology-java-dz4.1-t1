package ru.netology.manager;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.exception.NullRepositoryException;
import ru.netology.repository.ProductRepository;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {

    ProductRepository repository = new ProductRepository();
    ProductManager testManager = new ProductManager(repository);

    Product p1 = new Product(0, "p0", 10);
    Book b1 = new Book(1, "first book mr. First", 111, "mr. First");
    Book b2 = new Book(2, "first book mr. Second", 222, "mr. Second!!");
    Book b3 = new Book(3, "2nd book mr. Second", 333, "mr. Second");
    Smartphone s1 = new Smartphone(4, "m20", 11, "Samsung");
    Smartphone s2 = new Smartphone(5, "s21", 22, "Samsung!!");
    Smartphone s3 = new Smartphone(6, "a40!!", 33, "Samsung");
    Smartphone s4 = new Smartphone(7, "iphone 2", 44, "Apple");
    Smartphone s5 = new Smartphone(8, "iphone 11 pro", 55, "Apple!!");
    Smartphone s6 = new Smartphone(9, "iphone sx", 22, "Apple");

    ProductManagerTest() throws NullRepositoryException {
    }

    @Test
    @Disabled
    void ProductManager_shouldNotBeCreated() throws NullRepositoryException {
        ProductManager manager = new ProductManager(null);
    }

    @Test
    void ProductManager_nullShouldGenerateException() {
        assertThrows(NullRepositoryException.class, ()-> {
                    ProductManager manager = new ProductManager(null);
                });
    }

    @Test
    void searchBy_shouldFind() {
        testManager.add(b1); testManager.add(b2); testManager.add(b3);
        testManager.add(s1); testManager.add(s2); testManager.add(s3);
        testManager.add(s4); testManager.add(s5); testManager.add(s6);
        Product[] expected = new Product[]{ b3, s1, s2, s4 };
        Product[] actual = testManager.searchBy("2");
        assertArrayEquals(expected, actual);
        expected = new Product[]{ b2, s2, s3, s5 };
        actual = testManager.searchBy("!!");
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchBy_shouldNotFind() {
        testManager.add(b1); testManager.add(b2);
        testManager.add(s1); testManager.add(s2);
        Product[] actual = testManager.searchBy("*");
        assertNull(actual);
    }

    @Test
    void matches() throws Exception {
        ProductManager testManager = new ProductManager(repository);
        Method method = ProductManager.class.getDeclaredMethod("matches", Product.class, String.class);
        method.setAccessible(true);
        boolean b = (boolean) method.invoke(testManager,p1, "p0");
        assertTrue(b);
    }

}