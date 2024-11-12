package utn.frc.bda.notificacion.notificacionController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.frc.bda.notificacion.dtos.NotificacionPromocionDto;
import utn.frc.bda.notificacion.service.NotificacionService;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {
    private final NotificacionService notificacionService;

    @Autowired
    public NotificacionController(NotificacionService service) {this.notificacionService = service;}
    @PostMapping("/promocion/new")
    public ResponseEntity<?> notificarPromocion(
            @RequestBody NotificacionPromocionDto promocion) {
        return ResponseEntity.ok(notificacionService.createPromocion(promocion));
    }

    //Obtener notificacion de promocion
    @GetMapping("/promocion")
    public ResponseEntity<?> getAllPromociones() {
        return ResponseEntity.ok(notificacionService.getAllPromociones());
    }

    //Obtener notificacion de radio excedido
    @GetMapping("/seguridad/radio")
    public ResponseEntity<?> getAllRadios() {
        return ResponseEntity.ok(notificacionService.getAllRadios());
    }

    //Obtener notificacion de zona peligrosa
    @GetMapping("/seguridad/zona")
    public ResponseEntity<?> getAllZonas() {
        return ResponseEntity.ok(notificacionService.getAllZonas());
    }
}
