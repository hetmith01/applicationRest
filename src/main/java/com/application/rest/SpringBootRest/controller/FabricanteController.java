package com.application.rest.SpringBootRest.controller;


import com.application.rest.SpringBootRest.controller.dto.FabricanteDTO;
import com.application.rest.SpringBootRest.entities.Fabricante;
import com.application.rest.SpringBootRest.service.IFabricanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/fabricante")
public class FabricanteController {

    @Autowired
    private IFabricanteService fabricanteService;

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Fabricante> fabricanteOptional = fabricanteService.findById(id);

        if(fabricanteOptional.isPresent()){
            Fabricante fabricante = fabricanteOptional.get();

            FabricanteDTO fabricanteDTO = FabricanteDTO.builder()
                    .id(fabricante.getId())
                    .nombre(fabricante.getNombre())
                    .productoList(fabricante.getProductoList())
                    .build();
            return ResponseEntity.ok(fabricanteDTO);
        }
        return  ResponseEntity.notFound().build();
    }
}
