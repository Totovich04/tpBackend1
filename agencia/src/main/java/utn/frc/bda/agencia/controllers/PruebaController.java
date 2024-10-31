package utn.frc.bda.agencia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utn.frc.bda.agencia.dtos.PruebaDto;
import utn.frc.bda.agencia.services.PruebaService;

@RestController
@RequestMapping("/pruebas")
public class PruebaController {

    private final PruebaService pruebaService;

    @Autowired
    public PruebaController(PruebaService service){ this.pruebaService = service;}

    @GetMapping
    public ResponseEntity<Iterable<PruebaDto>> getAllPruebas(){
        return  ResponseEntity.ok(pruebaService.findAll());
    }
}
