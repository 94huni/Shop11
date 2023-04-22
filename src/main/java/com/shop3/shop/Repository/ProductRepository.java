package com.shop3.shop.Repository;

import com.shop3.shop.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    //SELECT * FROM product WHERE name = ?
    Product findByName(String name);

    //SELECT * FROM product WHERE company = ?
    List<Product> findByCompany(String Company);

    //SELECT * FROM product ORDER BY start DESC
    Page<Product> findProductByNameContaining(String searchKeyword, Pageable pageable);


}
