package ru.netology.manager;

import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.exception.NullRepositoryException;
import ru.netology.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductManager {

    private ProductRepository repository;

    public ProductManager(ProductRepository repository) throws NullRepositoryException {
        if (repository == null) throw new NullRepositoryException();
        this.repository = repository;
    }

    public void add(Product product) {
        repository.save(product);
    }

    public Product[] searchBy(String text) {
        List<Product> finded = new ArrayList<>();
        for (Product item : repository.findAll()) {
            if (matches(item, text)) {
                finded.add(item);
            }
        }
        if (finded.size() == 0) return null;
        Product[] items = new Product[finded.size()];
        finded.toArray(items);
        return items;
    }

    private boolean matches(Product product, String search) {
        boolean ok = false;
        if (product instanceof Book) {
            ok = ((Book) product).getAuthor().contains(search);
        } else if (product instanceof Smartphone) {
            ok = ((Smartphone) product).getProducer().contains(search);
        }
        return ok || product.getName().contains(search);
    }

}
