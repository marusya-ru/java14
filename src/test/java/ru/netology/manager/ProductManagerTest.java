package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;
import static org.junit.jupiter.api.Assertions.*;

public class ProductManagerTest {

    ProductRepository repo = new ProductRepository();

    Product book1 = new Book(1, "золушка", 100, "Братья Гримм");
    Product book2 = new Book(2, "Сказка о золотом петушке", 200, "Александр Пушкин");
    Product book3 = new Book(3, "Незнайка на луне", 300, "Николай Носов");
    Product smartphone1 = new Smartphone(6,"Samsung", 2000, "A");
    Product smartphone2 = new Smartphone(8,"Phone", 1000, "B");

    @Test
    public void addProductToRepository() {
        ProductManager manager = new ProductManager(repo);

        manager.add(book2);

        Product[] expected = { book2 };
        Product[] actual = manager.getProducts();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchByNameSeveralProduct() {
        ProductManager manager = new ProductManager(repo);

        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smartphone1);
        manager.add(smartphone2);

        Product[] expected = { book1, book2 };
        Product[] actual = manager.searchBy("зол");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchByNameOneProduct() {
        ProductManager manager = new ProductManager(repo);

        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smartphone1);
        manager.add(smartphone2);

        Product[] expected = { smartphone2 };
        Product[] actual = manager.searchBy("Phone");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchByNotExistingName() {
        ProductManager manager = new ProductManager(repo);

        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smartphone1);
        manager.add(smartphone2);

        Product[] expected = { };
        Product[] actual = manager.searchBy("ъ");

        assertArrayEquals(expected, actual);
    }
}
