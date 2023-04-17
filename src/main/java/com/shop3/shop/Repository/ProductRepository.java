package com.shop3.shop.Repository;

import com.shop3.shop.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    //SELECT * FROM product WHERE name = ?
    Product findByName(String name);

    //SELECT * FROM product WHERE company = ?
    List<Product> findByCompany(String Company);
}
