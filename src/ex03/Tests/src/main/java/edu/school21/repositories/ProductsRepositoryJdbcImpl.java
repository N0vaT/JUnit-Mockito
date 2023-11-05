package edu.school21.repositories;

import edu.school21.models.Product;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository{
    private JdbcTemplate jdbcTemplate;
    private final String FIND_ALL = "SELECT * FROM table_product";
    private final String FIND_BY_ID = "SELECT * FROM table_product\n" +
            "WHERE identifier = ?";
    private final String UPDATE = "UPDATE table_product\n" +
            "SET name = ?, price = ? WHERE identifier = ?";
    private final String SAVE = "INSERT INTO table_product (name, price)\n" +
            "VALUES (?, ?)";
    private final String DELETE = "DELETE FROM table_product\n" +
            "WHERE identifier = ?";

    public ProductsRepositoryJdbcImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query(FIND_ALL, (rs, rowNum) ->
                new Product(rs.getLong("identifier"),
                        rs.getString("name"),
                        rs.getDouble("price")
                )
        );
    }

    @Override
    public Optional<Product> findById(Long id) {
        Product product;
        try{
        product = jdbcTemplate.queryForObject(FIND_BY_ID, new Object[]{id}, (rs, rowNum) ->
                new Product(rs.getLong("identifier"),
                        rs.getString("name"),
                        rs.getDouble("price")
                )
        );
        }catch (Exception e){
            return Optional.empty();
        }
        return Optional.ofNullable(product);
    }

    @Override
    public void update(Product product) {
        jdbcTemplate.update(UPDATE, product.getName(),product.getPrice(), product.getId());
    }

    @Override
    public void save(Product product) {
        jdbcTemplate.update(SAVE, product.getName(), product.getPrice());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DELETE, id);
    }
}
