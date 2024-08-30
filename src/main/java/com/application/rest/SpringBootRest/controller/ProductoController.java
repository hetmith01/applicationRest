package com.application.rest.SpringBootRest.controller;


import com.application.rest.SpringBootRest.controller.dto.ProductoDTO;
import com.application.rest.SpringBootRest.entities.Producto;
import com.application.rest.SpringBootRest.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
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


    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        List<ProductoDTO> productoDTOList = productoService.findAll()
                .stream()
                .map(producto -> ProductoDTO.builder()
                        .id(producto.getId())
                        .nombre(producto.getNombre())
                        .precio(producto.getPrecio())
                        .fabricante(producto.getFabricante())
                        .build())
                .toList();
        return ResponseEntity.ok(productoDTOList);
    }

    @PostMapping("/save")
    public  ResponseEntity<?> save(@RequestBody ProductoDTO productoDTO) throws URISyntaxException {

        if(productoDTO.getNombre().isBlank()){
            return ResponseEntity.badRequest().build();
        }

        productoService.save(Producto.builder()
                .nombre(productoDTO.getNombre())
                .precio(productoDTO.getPrecio())
                .fabricante(productoDTO.getFabricante())
                .build());

        return ResponseEntity.created(new URI("/api/producto/save")).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProductoDTO productoDTO){

        Optional<Producto> productoOptional = productoService.findById(id);

        if(productoOptional.isPresent()){
            Producto producto = productoOptional.get();
            producto.setNombre(productoDTO.getNombre());
            producto.setPrecio(productoDTO.getPrecio());
            producto.setFabricante(productoDTO.getFabricante());
            return ResponseEntity.ok("Registro actualizado!!!");
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){

        if(id != null){
            productoService.deleteById(id);
            return ResponseEntity.ok("Registro eliminado!!!");
        }

        return ResponseEntity.badRequest().build();
    }

}













