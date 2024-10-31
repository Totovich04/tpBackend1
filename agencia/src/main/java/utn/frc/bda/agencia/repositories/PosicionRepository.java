package utn.frc.bda.agencia.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import utn.frc.bda.agencia.entities.PosicionEntity;

import java.util.Date;
import java.util.List;

public interface PosicionRepository extends CrudRepository<PosicionEntity, Integer> {
    @Query("SELECT p FROM PosicionEntity  p WHERE p.vehiculo.id = :idVehiculo AND p.fechaHora BETWEEN :inicio AND :fin")
    List<PosicionEntity> findByVehiculoAndFechaHoraBetween(@Param("idVehiculo") Integer idVehiculo,
                                                           @Param("inicio")Date inicio,
                                                           @Param("fin")Date fin);
}
