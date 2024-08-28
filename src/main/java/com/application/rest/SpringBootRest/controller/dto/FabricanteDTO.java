package com.application.rest.SpringBootRest.controller.dto;

import com.application.rest.SpringBootRest.entities.Producto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FabricanteDTO {

    private Long id;
    private String nombre;
    private List<Producto> productoList = new ArrayList<>();
}
