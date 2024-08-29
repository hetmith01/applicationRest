package com.application.rest.SpringBootRest.controller;


import com.application.rest.SpringBootRest.controller.dto.ProductoDTO;
import com.application.rest.SpringBootRest.entities.Producto;
import com.application.rest.SpringBootRest.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {

    @Autowired
    private IProductoService productoService;


    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Producto> productoOptional = productoService.findById(id);

        if(productoOptional.isPresent()){
            Producto producto = productoOptional.get();

            ProductoDTO productoDTO = ProductoDTO.builder()
                    .id(producto.getId())
                    .nombre(producto.getNombre())
                    .precio(producto.getPrecio())
                    .fabricante(producto.getFabricante())
                    .build();
            return ResponseEntity.ok(productoDTO);
        }
       return ResponseEntity.notFound().build();
    }
}
