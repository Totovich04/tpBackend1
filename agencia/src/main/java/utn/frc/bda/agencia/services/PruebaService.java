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

    public PruebaDto createPrueba(PruebaDto dto) {
        PruebaEntity nuevaPrueba = crearPruebaDto(dto);
        PruebaEntity prueba = pruebaRepository.save(nuevaPrueba);
        return  new PruebaDto(prueba);
    }


    private PruebaEntity crearPruebaDto(PruebaDto dto) {
        VehiculoEntity vehiculo = validarVehiculoDisponible(dto.getIdVehiculo().getId());
        InteresadoEntity interesado = validarInteresado(dto.getIdInteresado().getId());

        EmpleadoEntity empleado = empleadoRepository.findById(dto.getIdEmpleado().getLegajo()).orElseThrow(()-> new IllegalArgumentException("Empleado no encontrado"));

        PruebaEntity prueba = new PruebaEntity();
        prueba.setVehiculo(vehiculo);
        prueba.setInteresado(interesado);
        prueba.setEmpleado(empleado);
        prueba.setFechaHoraInicio(new Date());

        return prueba;
    }

    public PruebaDto findById(Integer id) throws ServiceException {
        return pruebaRepository.findById(id).map(PruebaDto::new).orElseThrow(() ->
                new ServiceException("Prueba no encontrada")
        );
    }

    public Iterable<PruebaDto> findAllPruebas()throws ServiceException {
        Iterable<PruebaEntity> pruebas = pruebaRepository.findAll();
        return StreamSupport.stream(pruebas.spliterator(),false).map(PruebaDto::new).toList();
    }

    public List<PruebaDto> getPruebasEnCurso(){
        return pruebaRepository.findByFechaHoraFinIsNull().stream().map(PruebaDto::new).toList();
    }

    public PruebaDto updatePrueba(Integer id, PruebaDto dto)throws ServiceException {
        PruebaEntity prueba = pruebaRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Prueba no encontrada"));

        prueba.setId(id);
        prueba.setFechaHoraInicio(dto.getFechaHoraInicio());

        VehiculoEntity vehiculo = vehiculoRepository.findById(dto.getIdVehiculo().getId()).orElseThrow(()-> new IllegalArgumentException("Vehiculo no encontrado"));
        prueba.setVehiculo(vehiculo);

        EmpleadoEntity empleado = empleadoRepository.findById(dto.getIdEmpleado().getLegajo()).orElseThrow(()-> new IllegalArgumentException("Empleado no encontrado"));
        prueba.setEmpleado(empleado);

        InteresadoEntity interesado = interesadoRepository.findById(dto.getIdInteresado().getId()).orElseThrow(()-> new IllegalArgumentException("Interesado no encontrado"));
        prueba.setInteresado(interesado);

        PruebaEntity updatedPrueba = pruebaRepository.save(prueba);

        return  new PruebaDto(updatedPrueba);
    }

    public PruebaDto finalizarPrueba(Integer id, String comentario){
        PruebaEntity prueba = pruebaRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Prueba no encontrada"));

        if (prueba.getFechaHoraFin() != null) {
            throw new IllegalStateException("La prueba ya finalizó.");
        }
        prueba.setFechaHoraFin(new Date());
        prueba.setComentarios(comentario);

        return new PruebaDto(pruebaRepository.save(prueba));
    }

    public void deletePrueba(Integer id){
        PruebaEntity prueba = pruebaRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Prueba no encontrada"));

        pruebaRepository.delete(prueba);
    }

    private VehiculoEntity validarVehiculoDisponible(Integer id) {
        VehiculoEntity vehiculo = vehiculoRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Vehiculo no encontrado"));

        if (pruebaRepository.existsByVehiculoIdAndFechaHoraFinIsNull(id)){
            throw new IllegalArgumentException("Vehiculo no disponible");
        }
        return vehiculo;
    }

    private InteresadoEntity validarInteresado(Integer id) {
        InteresadoEntity interesado = interesadoRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Interesado no encontrado"));

        if (interesado.getFechaVtoLicencia().before(new Date())){
            throw new IllegalArgumentException("Interesado no habilitado.");
        }
        if (interesado.getRestringido()){
            throw new IllegalArgumentException("Interesado restringido.");
        }
        return interesado;
    }
}
