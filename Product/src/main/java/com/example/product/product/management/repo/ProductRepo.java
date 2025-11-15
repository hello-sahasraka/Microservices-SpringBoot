package com.example.product.product.management.repo;

import com.example.product.product.management.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT * FROM product WHERE product_id= :product_id", nativeQuery = true)
    Product getProductByProductId(@Param("product_id") Integer product_id);
}
