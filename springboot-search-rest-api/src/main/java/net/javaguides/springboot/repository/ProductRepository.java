package net.javaguides.springboot.repository;

import net.javaguides.springboot.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// we dont have to provide @repository because spring data jpa already takes care of that
public interface ProductRepository extends JpaRepository<Product, Long> {
    // within this jpql query we r using named parameter :query to pass the value
    // filter as per name or description
    // here jpql u use class name Product
    @Query("SELECT p FROM Product p WHERE " +
            "p.name LIKE CONCAT('%',:query, '%')" +
            "Or p.description LIKE CONCAT('%',:query, '%')")
    List<Product> searchProducts(String query);

    // for native sql u use table name
    // instead of using alias p as above u use * below
    // additionally pass  nativeQuery = true
    @Query(value = "SELECT * FROM products p WHERE " +
            "p.name LIKE CONCAT('%',:query, '%')" +
            "Or p.description LIKE CONCAT('%',:query, '%')", nativeQuery = true)
    List<Product> searchProductsSQL(String query);
}
