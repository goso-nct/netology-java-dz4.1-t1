package ru.netology.manager;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.exception.NullRepositoryException;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {

    ProductRepository repository = new ProductRepository();

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

    @Test
    @Disabled
    void ProductManager_shouldNotBeCreated() throws NullRepositoryException {
        ProductManager manager = new ProductManager(null);
    }

    @Test
    void ProductManager_shouldGenerateException() {
        assertThrows(NullRepositoryException.class, ()-> {
                    ProductManager manager = new ProductManager(null);
                });
    }

    @Test
    @SneakyThrows
    void add_shouldAdd() {
        ProductManager manager = new ProductManager(repository);
        manager.add(b1);
        manager.add(b2);
        manager.add(b3);
        manager.add(s1);
        manager.add(s2);
        assertEquals(5, manager.getRepository().size());
    }

    @Test
    @SneakyThrows
    void add_shouldDontAdd() {
        repository.setStorage(new Product[]{ b1, b2, b3, s1, s2 });
        ProductManager manager = new ProductManager(repository);
        manager.add(b3);
        assertEquals(5, manager.getRepository().size());
        manager.add(null);
        assertEquals(5, manager.getRepository().size());
    }

    @Test
    @SneakyThrows
    void searchBy_shouldFind() {
        repository.setStorage(new Product[]{ b1, b2, b3, s1, s2, s3, s4, s5, s6 });
        ProductManager manager = new ProductManager(repository);
        Product[] expected = new Product[]{ b3, s1, s2, s4 };
        Product[] actual = manager.searchBy("2");
        assertArrayEquals(expected, actual);
        expected = new Product[]{ b2, s2, s3, s5 };
        actual = manager.searchBy("!!");
        assertArrayEquals(expected, actual);
    }

    @Test
    @SneakyThrows
    void searchBy_shouldDontFind() {
        repository.setStorage(new Product[]{ b1, b2, s1, s2, s3, p1 });
        ProductManager manager = new ProductManager(repository);
        Product[] actual = manager.searchBy("*");
        assertNull(actual);
    }

}