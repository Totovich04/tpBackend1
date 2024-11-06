package utn.frc.bda.notificacion.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.frc.bda.notificacion.dtos.NotificacionPromocionDto;
import utn.frc.bda.notificacion.service.NotificacionService;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {
    private final NotificacionService notificacionService;

    public NotificacionController(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    @PostMapping("/promocion/new")
    public ResponseEntity<?> notificacionPromocion(
            @RequestBody NotificacionPromocionDto promo){
        return ResponseEntity.ok(notificacionService.createPromocion(promo));
    }


    @GetMapping("/promocion")
    public ResponseEntity<?> getAllPromociones() {
        return ResponseEntity.ok(notificacionService.getAllPromociones());
    }

    //Obtener notificacion de radio excedido
    @GetMapping("/seguridad/radio-excedido")
    public ResponseEntity<?> getAllRadiosExcedidos() {
        return ResponseEntity.ok(notificacionService.getAllRadiosExcedidos());
    }

    //Obtener notificacion de zona peligrosa
    @GetMapping("/seguridad/zona-peligrosa")
    public ResponseEntity<?> getAllZonasPeligrosas() {
        return ResponseEntity.ok(notificacionService.getAllZonasPeligrosas());
    }
}
