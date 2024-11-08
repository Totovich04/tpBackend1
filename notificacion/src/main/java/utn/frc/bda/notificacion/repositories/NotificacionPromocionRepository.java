package utn.frc.bda.notificacion.repositories;

import org.springframework.data.repository.CrudRepository;
import utn.frc.bda.notificacion.models.NotificacionEntity;
import utn.frc.bda.notificacion.models.NotificacionPromocionEntity;

public interface NotificacionPromocionRepository extends CrudRepository<NotificacionPromocionEntity, Integer> {
}
