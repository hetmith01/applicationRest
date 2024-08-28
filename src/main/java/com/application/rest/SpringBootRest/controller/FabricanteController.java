package com.application.rest.SpringBootRest.controller;


import com.application.rest.SpringBootRest.controller.dto.FabricanteDTO;
import com.application.rest.SpringBootRest.entities.Fabricante;
import com.application.rest.SpringBootRest.service.IFabricanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
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

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        List<FabricanteDTO> fabricanteList = fabricanteService.findAll()
                .stream()
                .map(fabricante -> FabricanteDTO.builder()
                        .id(fabricante.getId())
                        .nombre(fabricante.getNombre())
                        .productoList(fabricante.getProductoList())
                        .build())
                .toList();
        return ResponseEntity.ok(fabricanteList);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody FabricanteDTO fabricanteDTO)throws URISyntaxException {

        if(fabricanteDTO.getNombre().isBlank()){
            return ResponseEntity.badRequest().build();
        }

        fabricanteService.save(Fabricante.builder()
                .nombre(fabricanteDTO.getNombre())
                .build());

        return ResponseEntity.created(new URI("/api/fabricante/save")).build();
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateFabricante(@PathVariable Long id, @RequestBody FabricanteDTO fabricanteDTO){

        Optional<Fabricante> fabricanteOptional = fabricanteService.findById(id);

        if(fabricanteOptional.isPresent()){
            Fabricante fabricante = fabricanteOptional.get();
            fabricante.setNombre(fabricanteDTO.getNombre());
            fabricanteService.save(fabricante);
            return ResponseEntity.ok("Registro actualizado!!!");
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        if(id != null){
            fabricanteService.deleteById(id);
            return ResponseEntity.ok("Registro eliminado!!!");
        }

        return ResponseEntity.badRequest().build();
    }


}









