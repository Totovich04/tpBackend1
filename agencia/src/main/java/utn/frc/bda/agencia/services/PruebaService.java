package utn.frc.bda.agencia.services;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.frc.bda.agencia.dtos.PruebaDto;
import utn.frc.bda.agencia.entities.EmpleadoEntity;
import utn.frc.bda.agencia.entities.InteresadoEntity;
import utn.frc.bda.agencia.entities.PruebaEntity;
import utn.frc.bda.agencia.entities.VehiculoEntity;
import utn.frc.bda.agencia.repositories.EmpleadoRepository;
import utn.frc.bda.agencia.repositories.InteresadoRepository;
import utn.frc.bda.agencia.repositories.PruebaRepository;
import utn.frc.bda.agencia.repositories.VehiculoRepository;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class PruebaService {
    private final PruebaRepository pruebaRepository;
    private final EmpleadoRepository empleadoRepository;
    private final VehiculoRepository vehiculoRepository;
    private final InteresadoRepository interesadoRepository;

    @Autowired
    public PruebaService(PruebaRepository repository, EmpleadoRepository empleado, VehiculoRepository vehiculo, InteresadoRepository interesado) {
        this.pruebaRepository = repository;
        this.empleadoRepository = empleado;
        this.vehiculoRepository = vehiculo;
        this.interesadoRepository = interesado;
    }
    //terminar esto

    public PruebaDto createPrueba(PruebaDto prueba) {
        PruebaEntity nuevaPrueba = buildPruebaFromDto(prueba);
        PruebaEntity savedPrueba = pruebaRepository.save(nuevaPrueba);
        return new PruebaDto(savedPrueba);
    }


    public PruebaDto findById(Integer id) throws ServiceException {
        return pruebaRepository.findById(id).map(PruebaDto::new).orElseThrow(() ->
                new ServiceException("Prueba no encontrada")
        );
    }

    public Iterable<PruebaDto> findAll() {
        Iterable<PruebaEntity> pruebas = pruebaRepository.findAll();
        return StreamSupport.stream(pruebas.spliterator(), false).map(PruebaDto::new).toList();
    }

    public List<PruebaDto> getPruebasEnCurso() {
        return pruebaRepository.findByFechaHoraFinIsNull().stream().map(PruebaDto::new).toList();
    }


    public PruebaDto updatePrueba(Integer id, PruebaDto pruebaDto) {
        PruebaEntity existingPrueba = pruebaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Prueba no encontrada"));

        existingPrueba.setId(id);
        existingPrueba.setFechaHoraInicio(pruebaDto.getFechaHoraInicio());

        // actualizar relaciones
        VehiculoEntity vehiculo = vehiculoRepository.findById(pruebaDto.getVehiculo().getId())
                .orElseThrow(() -> new IllegalArgumentException("Vehículo no encontrado"));
        existingPrueba.setVehiculo(vehiculo);

        EmpleadoEntity empleado = empleadoRepository.findById(pruebaDto.getEmpleado().getLegajo())
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));
        existingPrueba.setEmpleado(empleado);

        InteresadoEntity interesado = interesadoRepository.findById(pruebaDto.getInteresado().getId())
                .orElseThrow(() -> new IllegalArgumentException("Interesado no encontrado"));
        existingPrueba.setInteresado(interesado);

        PruebaEntity updatedPrueba = pruebaRepository.save(existingPrueba);

        return new PruebaDto(updatedPrueba);
    }

    public PruebaEntity finalizarPrueba(Integer id, String comentario){
        PruebaEntity prueba = pruebaRepository.findById(id).orElseThrow(() -> new ServiceException("No se encontro la prueba con el id: " + id));

        if (prueba.getFechaHoraFin() != null){
            throw new ServiceException("La prueba ya fue finalizada");
        }

        prueba.setFechaHoraFin(new Date());
        prueba.setComentarios(comentario);

        return pruebaRepository.save(prueba);
    }

    private VehiculoEntity validarVehiculoDisponible(Integer idVehiculo) {
        VehiculoEntity vehiculo = vehiculoRepository.findById(idVehiculo)
                .orElseThrow(() -> new IllegalArgumentException("Vehículo no encontrado"));
        // todos los vehiculos se asumen patentados por lo que no es necesario validar la patente
        if (pruebaRepository.existsByVehiculoIdAndFechaHoraFinIsNull(idVehiculo)) {
            throw new IllegalArgumentException("El vehículo está siendo probado.");
        }
        return vehiculo;
    }

    private InteresadoEntity validarInteresado(Integer idInteresado) {
        InteresadoEntity interesado = interesadoRepository.findById(idInteresado)
                .orElseThrow(() -> new IllegalArgumentException("Interesado no encontrado"));
        if (interesado.getFechaVtoLicencia().before(new Date())) {
            throw new IllegalArgumentException("La licencia del interesado está vencida.");
        }
        if (interesado.getRestringido()) {
            throw new IllegalArgumentException("El interesado está restringido para probar vehículos.");
        }
        return interesado;
    }

    private PruebaEntity buildPruebaFromDto(PruebaDto pruebaDto) {
        VehiculoEntity vehiculo = validarVehiculoDisponible(pruebaDto.getVehiculo().getId());
        InteresadoEntity interesado = validarInteresado(pruebaDto.getInteresado().getId());

        EmpleadoEntity empleado = empleadoRepository.findById(pruebaDto.getEmpleado().getLegajo())
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));

        PruebaEntity prueba = new PruebaEntity();
        prueba.setVehiculo(vehiculo);
        prueba.setEmpleado(empleado);
        prueba.setInteresado(interesado);
        prueba.setFechaHoraInicio(new Date());

        return prueba;
    }

    public void deletePrueba(Integer id) {
        PruebaEntity existingPrueba = pruebaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Prueba no encontrada"));
        pruebaRepository.delete(existingPrueba);
    }
}
