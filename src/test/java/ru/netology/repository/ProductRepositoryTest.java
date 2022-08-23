package ru.netology.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.exception.AlreadyExistsException;
import ru.netology.exception.NotFoundException;

public class ProductRepositoryTest {
    Product book1 = new Book(1, "book1", 100, "author1");
    Product book2 = new Book(2, "book2", 200, "author2");
    Product book3 = new Book(3, "book3", 300, "author3");
    Product smartphone1 = new Smartphone(6, "smartphone1", 2000, "A");
    Product smartphone2 = new Smartphone(8, "smartphone2", 1000, "B");

    @Test
    public void saveProductToRepository() {
        ProductRepository repo = new ProductRepository();

        repo.save(book1);

        Product[] expected = {book1};
        Product[] actual = repo.getProducts();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void getAllSavedProducts() {
        ProductRepository repo = new ProductRepository();

        repo.save(book1);
        repo.save(book2);
        repo.save(book3);
        repo.save(smartphone1);
        repo.save(smartphone2);

        Product[] expected = {book1, book2, book3, smartphone1, smartphone2};
        Product[] actual = repo.getProducts();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void removeProductById() {
        ProductRepository repo = new ProductRepository();

        repo.save(book1);
        repo.save(book2);
        repo.save(book3);
        repo.save(smartphone1);
        repo.save(smartphone2);
        repo.removeById(3);

        Product[] expected = {book1, book2, smartphone1, smartphone2};
        Product[] actual = repo.getProducts();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldGenerateNotFoundException() {
        ProductRepository repo = new ProductRepository();

        repo.save(book1);
        repo.save(book2);
        repo.save(book3);
        repo.save(smartphone1);
        repo.save(smartphone2);

        assertThrows(NotFoundException.class, () -> {
            repo.removeById(10);
        });
    }

    @Test
    public void shouldGenerateAlreadyExistsException() {
        ProductRepository repo = new ProductRepository();

        repo.save(book1);
        repo.save(book2);
        repo.save(book3);
        repo.save(smartphone1);
        repo.save(smartphone2);

        assertThrows(AlreadyExistsException.class, () -> {
            repo.save(book3);
        });
    }
}
