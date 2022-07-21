package com.semantro.demo.repository;

import com.semantro.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategory_Id(int id);

    @Query("select p from Product p where p.category.name like %?1%")
    List<Product> findByCategoryName(String name);

    @Query("select p.category.name from Product p ")
    Set<String> findProductName();
}
