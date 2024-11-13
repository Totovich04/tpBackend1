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

    public PruebaDto createPrueba(PruebaDto dto) {
        PruebaEntity nuevaPrueba = crearPruebaDto(dto);
        PruebaEntity prueba = pruebaRepository.save(nuevaPrueba);
        return  new PruebaDto(prueba);
    }

    public PruebaDto buscarPorId(Integer id){
        return pruebaRepository.findById(id).map(PruebaDto::new).orElseThrow(() -> new ServiceException("No se encontro la prueba con id: " + id));
    }

    public Iterable<PruebaDto> findAllPruebas() {
        Iterable<PruebaEntity> pruebas = pruebaRepository.findAll();
        return StreamSupport.stream(pruebas.spliterator(), false).map(PruebaDto::new).toList();
    }

    public List<PruebaDto> getPruebasEnCurso(){
        return pruebaRepository.findByFechaHoraFinIsNull().stream().map(PruebaDto::new).toList();
    }

    public PruebaDto updatePrueba(Integer id, PruebaDto dto){
        PruebaEntity prueba = pruebaRepository.findById(id).orElseThrow(() -> new ServiceException("No se encontro la prueba con id: " + id));

        prueba.setId(id);
        prueba.setFechaHoraInicio(dto.getFechaHoraInicio());

        VehiculoEntity vehiculo = vehiculoRepository.findById(dto.getVehiculo().getId()).orElseThrow(
                () -> new ServiceException("No se encontro el vehiculo con id: " + dto.getVehiculo().getId()));
        prueba.setVehiculo(vehiculo);

        EmpleadoEntity empleado = empleadoRepository.findById(dto.getEmpleado().getLegajo()).orElseThrow(
                () -> new ServiceException("No se encontro el empleado con legajo: " + dto.getEmpleado().getLegajo()));
        prueba.setEmpleado(empleado);

        InteresadoEntity interesado = interesadoRepository.findById(dto.getInteresado().getId()).orElseThrow(
                () -> new ServiceException("No se encontro el interesado con id: " + dto.getInteresado().getId()));
        prueba.setInteresado(interesado);

        PruebaEntity pruebaActualizada = pruebaRepository.save(prueba);

        return new PruebaDto(pruebaActualizada);
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

    private VehiculoEntity validarVehiculoDisponible(Integer id){
        VehiculoEntity vehiculo = vehiculoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No se encontro el vehiculo"));

        if (pruebaRepository.existsByVehiculoIdAndFechaHoraFinIsNull(id)){
            throw new IllegalArgumentException("El vehiculo no esta disponible en el momento.");
        }
        return vehiculo;
    }

    private InteresadoEntity validarInteresado(Integer id){
        InteresadoEntity interesado = interesadoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No se encontro el interesado"));

        if (interesado.getFechaVtoLicencia().before(new Date())){
            throw new IllegalArgumentException("La licencia del interesado esta vencida");
        }

        if (interesado.getRestringido()){
            throw new IllegalArgumentException("El interesado no puede realizar pruebas");
        }

        return interesado;
    }

    private PruebaEntity crearPruebaDto(PruebaDto dto){
        VehiculoEntity vehiculo = validarVehiculoDisponible(dto.getVehiculo().getId());
        InteresadoEntity interesado = validarInteresado(dto.getInteresado().getId());
        EmpleadoEntity empleado = empleadoRepository.findById(dto.getEmpleado().getLegajo()).orElseThrow(() -> new IllegalArgumentException("No se encontro el empleado"));

        PruebaEntity prueba = new PruebaEntity();
        prueba.setVehiculo(vehiculo);
        prueba.setInteresado(interesado);
        prueba.setEmpleado(empleado);
        prueba.setFechaHoraInicio(new Date());

        return prueba;
    }

    public void deletePrueba(Integer id){
        PruebaEntity prueba = pruebaRepository.findById(id).orElseThrow(() -> new ServiceException("No se encontro la prueba con id: " + id));
        pruebaRepository.delete(prueba);
    }
}
