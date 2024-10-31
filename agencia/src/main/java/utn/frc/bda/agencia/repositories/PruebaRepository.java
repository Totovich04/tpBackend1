package utn.frc.bda.agencia.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import utn.frc.bda.agencia.entities.PruebaEntity;

import java.util.List;

public interface PruebaRepository extends CrudRepository<PruebaEntity, Integer> {
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END " +
            "FROM PruebaEntity p WHERE p.vehiculo.id = ?1 AND p.fechaHoraFin IS NULL")
    boolean existsByVehiculoIdAndFechaHoraFinIsNull(@Param("idVehiculo") Integer idVehiculo);
    List<PruebaEntity> findByFechaHoraFinIsNull();
}
