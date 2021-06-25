package ru.netology.repository;

import ru.netology.domain.Product;
import ru.netology.exception.NonFoundException;

public class ProductRepository {

  private Product[] storage = new Product[0];

  public int size() {
    return storage.length;
  }

  public void save(Product item) {
    if (item != null && findById(item.getId()) == null ) {
      Product[] tmp = new Product[storage.length + 1];
      System.arraycopy(storage, 0, tmp, 0, storage.length);
      tmp[tmp.length - 1] = item;
      storage = tmp;
    }
  }

  public Product[] findAll() {
    return storage;
  }

  public Product findById(int id) {
    for (Product item : storage) {
      if (item.getId() == id) {
        return item;
      }
    }
    return null;
  }

  public void removeById(int id) throws NonFoundException {
    if (findById(id) != null) {
      Product[] tmp = new Product[storage.length - 1];
      int index = 0;
      for (Product item : storage) {
        if (item.getId() != id) {
          tmp[index] = item;
          index++;
        }
      }
      storage = tmp;
    }
    else
      throw new NonFoundException("Product with id " + id + " not found.");
  }

  // for tests:
  public void setStorage(Product[] storage) {
    this.storage = storage;
  }

}
