package utn.frc.bda.notificacion.repos;

import org.springframework.data.repository.CrudRepository;
import utn.frc.bda.notificacion.models.Notificacion;

public interface NotificacionRepository extends CrudRepository<Notificacion, Integer> {
}
