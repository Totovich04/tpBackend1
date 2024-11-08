package utn.frc.bda.agencia.dtos.externos;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public abstract class NotificacionDto {
    private Integer id;
    private List<String> reciverEmails;
    private LocalDateTime fecha;
    private String mensaje;

    public NotificacionDto(Integer id, List<String> reciverEmails, LocalDateTime fecha, String mensaje) {
        this.id = id;
        this.fecha = fecha;
        this.mensaje = mensaje;
    }
}
