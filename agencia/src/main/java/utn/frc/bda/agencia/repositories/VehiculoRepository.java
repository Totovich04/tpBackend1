package utn.frc.bda.agencia.repositories;

import org.springframework.data.repository.CrudRepository;
import utn.frc.bda.agencia.entities.VehiculoEntity;

public interface VehiculoRepository extends CrudRepository<VehiculoEntity, Integer> {
}
