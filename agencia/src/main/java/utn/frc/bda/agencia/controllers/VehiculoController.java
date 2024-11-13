package utn.frc.bda.agencia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utn.frc.bda.agencia.dtos.ErrorResponse;
import utn.frc.bda.agencia.dtos.PosicionDto;
import utn.frc.bda.agencia.services.VehiculoService;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    private final VehiculoService service;

    //inyeccion de dependencias
    @Autowired
    public VehiculoController(VehiculoService service) {
        this.service = service;
    }

    @PostMapping("/posicion/new")
    public ResponseEntity<?> create(@RequestBody PosicionDto posicion) {
        try {
            PosicionDto nuevaPosicion = service.procesarPosicion(posicion);
            return ResponseEntity.ok(nuevaPosicion);
        } catch (IllegalArgumentException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    "Bad Request",
                    e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}

