package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProductsRepositoryJdbcImplTest {
    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(new Product(1L, "Ches", 200.),
            new Product(2L, "buckwheat", 80.),
            new Product(3L, "Tea", 100.),
            new Product(4L, "Bread", 40.),
            new Product(5L, "Beer", 70.));
    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(3L,"Tea", 100.0);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(3L, "Lipton Tea", 230.);
    final Product EXPECTED_SAVE_PRODUCT = new Product(6L, "Chocolate", 250.);

    DataSource dataSource;

    @BeforeEach
    void init(){
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        dataSource = builder.generateUniqueName(true)
                .addDefaultScripts()
                .build();
    }
    @Test
    void isCorrectFindAll(){
        ProductsRepository repositoryJdbc = new ProductsRepositoryJdbcImpl(dataSource);
        List<Product> products = repositoryJdbc.findAll();
        assertIterableEquals(EXPECTED_FIND_ALL_PRODUCTS, products);
    }
    @Test
    void isCorrectFindById(){
        ProductsRepository repository = new ProductsRepositoryJdbcImpl(dataSource);
        assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, repository.findById(3L).get());
    }
    @Test
    void isNullFindById(){
        ProductsRepository repository = new ProductsRepositoryJdbcImpl(dataSource);
        assertNull(repository.findById(7L).orElse(null));
    }
    @Test
    void isCorrectUpdate(){
        ProductsRepository repository = new ProductsRepositoryJdbcImpl(dataSource);
        Product product = repository.findById(3L).orElse(null);
        assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, product);
        product.setName("Lipton Tea");
        product.setPrice(230.);
        repository.update(product);
        product = repository.findById(3L).orElse(null);
        assertEquals(EXPECTED_UPDATED_PRODUCT, product);
    }
    @Test
    void isCorrectSave(){
        ProductsRepository repository = new ProductsRepositoryJdbcImpl(dataSource);
        repository.save(new Product("Chocolate", 250.));
        assertEquals(EXPECTED_SAVE_PRODUCT, repository.findById(6L).orElse(null));
    }
}