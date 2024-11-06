package utn.frc.bda.agencia.controllers;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.frc.bda.agencia.dtos.PruebaDto;
import utn.frc.bda.agencia.services.PruebaService;

import java.util.List;

@RestController
@RequestMapping("/pruebas")
public class PruebaController {

    private final PruebaService pruebaService;

    @Autowired
    public PruebaController(PruebaService service){ this.pruebaService = service;}

    @GetMapping
    public ResponseEntity<Iterable<PruebaDto>> getAllPruebas(){
        return  ResponseEntity.ok(pruebaService.findAllPruebas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PruebaDto> getPruebaById(@PathVariable Integer id){
        try {
            PruebaDto pruebaDto = pruebaService.findById(id);
            return ResponseEntity.ok(pruebaDto);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/enCurso")
    public ResponseEntity<Iterable<PruebaDto>> getAllPruebasEnCurso(){
        List<PruebaDto> pruebaDtos = pruebaService.getPruebasEnCurso();
        if(pruebaDtos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pruebaDtos);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<?> crearPrueba(@RequestBody PruebaDto pruebaDto){
        try{
            PruebaDto prueba = pruebaService.createPrueba(pruebaDto);
            return  ResponseEntity.ok(prueba);
        }catch (ServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPrueba(@PathVariable Integer id, @RequestBody PruebaDto pruebaDto){
        try {
            PruebaDto prueba = pruebaService.updatePrueba(id, pruebaDto);
            return ResponseEntity.ok(prueba);
        }catch (ServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/finalizar/{id}")
    public ResponseEntity<?> finalizarPrueba(@PathVariable Integer id, @RequestBody PruebaDto pruebaDto){
        try {
            PruebaDto prueba = pruebaService.finalizarPrueba(id, pruebaDto.getComentarios());
            return ResponseEntity.ok(prueba);
        }catch (ServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPrueba(@PathVariable Integer id){
        try {
            pruebaService.deletePrueba(id);
            return ResponseEntity.ok().build();
        }catch (ServiceException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
