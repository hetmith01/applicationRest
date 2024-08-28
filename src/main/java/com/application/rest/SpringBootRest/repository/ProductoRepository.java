package com.application.rest.SpringBootRest.repository;

import com.application.rest.SpringBootRest.entities.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long> {


    @Query("SELECT p FROM Producto p WHERE p.precio BETWEEN ?1 AND ?2")
    List<Producto> findProductByPRiceInRange(BigDecimal minPrice, BigDecimal maxPrice);

    List<Producto> findProductoByPrecioBetween(BigDecimal minPrice, BigDecimal maxPrice);
}
