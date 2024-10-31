package utn.frc.bda.agencia.repositories;

import org.springframework.data.repository.CrudRepository;
import utn.frc.bda.agencia.entities.EmpleadoEntity;

public interface EmpleadoRepository extends CrudRepository<EmpleadoEntity, Integer> {
}
