package com.application.rest.SpringBootRest.service.impl;

import com.application.rest.SpringBootRest.entities.Producto;
import com.application.rest.SpringBootRest.persistence.IProductoDAO;
import com.application.rest.SpringBootRest.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    private IProductoDAO productoDAO;

    @Override
    public List<Producto> findAll() {
        return (List<Producto>) productoDAO.findAll();
    }

    @Override
    public Optional<Producto> findById(Long id) {
        return productoDAO.findById(id);
    }

    @Override
    public List<Producto> findByPriceInRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return (List<Producto>) productoDAO.findByPriceInRange(minPrice, maxPrice);
    }

    @Override
    public void save(Producto producto) {
        productoDAO.save(producto);
    }

    @Override
    public void deleteById(Long id) {
        productoDAO.deleteById(id);
    }
}
