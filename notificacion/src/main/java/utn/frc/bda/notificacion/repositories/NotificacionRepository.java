package utn.frc.bda.notificacion.repositories;

import org.springframework.data.repository.CrudRepository;
import utn.frc.bda.notificacion.models.NotificacionEntity;

public interface NotificacionRepository extends CrudRepository<NotificacionEntity, Integer> {
}
