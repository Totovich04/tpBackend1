package utn.frc.bda.agencia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.frc.bda.agencia.dtos.PruebaDto;
import utn.frc.bda.agencia.dtos.externos.NotificacionRadioExcedidoDto;
import utn.frc.bda.agencia.dtos.report.DistanciaVehiculoResponse;
import utn.frc.bda.agencia.entities.PosicionEntity;
import utn.frc.bda.agencia.entities.PruebaEntity;
import utn.frc.bda.agencia.entities.VehiculoEntity;
import utn.frc.bda.agencia.repositories.PosicionRepository;
import utn.frc.bda.agencia.repositories.PruebaRepository;
import utn.frc.bda.agencia.repositories.VehiculoRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteService {

    private final PruebaRepository pruebaRepository;
    private final VehiculoRepository vehiculoRepository;
    private final PosicionRepository posicionRepository;
    private final RestriccionesService restriccionesService;

    @Autowired
    public ReporteService(RestriccionesService resService, VehiculoRepository veRepo, PosicionRepository poRepo, PruebaRepository prRepo) {
        this.vehiculoRepository = veRepo;
        this.posicionRepository = poRepo;
        this.pruebaRepository = prRepo;
        this.restriccionesService = resService;
    }

    public DistanciaVehiculoResponse calcularDistanciaRecorrida(Integer idVehiculo, Date fechaInicio, Date fechaFin) {
        List<PosicionEntity> posiciones = posicionRepository.findByVehiculoAndFechaHoraBetween(idVehiculo, fechaInicio, fechaFin);
        VehiculoEntity vehiculo = vehiculoRepository.findById(idVehiculo).orElseThrow(() -> new RuntimeException("No se encontro el vehiculo con id: " + idVehiculo));

        Double distancia = 0.0;

        if (posiciones.isEmpty()){
            DistanciaVehiculoResponse response = new DistanciaVehiculoResponse(vehiculo,fechaInicio,fechaFin,distancia);
        }

        for (int i = 0; i < posiciones.size() - 1; i++) {
            PosicionEntity pos1 = posiciones.get(i);
            PosicionEntity pos2 = posiciones.get(i + 1);
            distancia += calcularDistanciaEuclidea(pos1.getLatitud(),pos1.getLongitud(), pos2.getLatitud(),pos2.getLongitud());
        }
        DistanciaVehiculoResponse response = new DistanciaVehiculoResponse(vehiculo,fechaInicio,fechaFin,distancia);
        return response;
    }

    public List<PruebaDto> obtenerPruebasVehiculo(Integer idVehiculo){
        VehiculoEntity vehiculo = vehiculoRepository.findById(idVehiculo).orElseThrow(() -> new RuntimeException("No se encontro el vehiculo con id: " + idVehiculo));

        return  vehiculo.getPruebas().stream().map(pruebaEntity -> new PruebaDto(pruebaEntity)).toList();
    }

    private Double calcularDistanciaEuclidea(Double lat1, Double lon1, Double lat2, Double lon2){
        Double dX = lat2 - lat1;
        Double dY = lon2 - lon1;

        return Math.sqrt(dX*dX + dY*dY);
    }

    private PruebaDto buscarPruebaDeNotificacion(NotificacionRadioExcedidoDto notificacion){
        System.out.println(notificacion.getIdVehiculo());
        System.out.println(notificacion.getFechaNotificacion());
        PruebaEntity prueba = pruebaRepository.existsByVehiculoIdAndFechaNotificacion(notificacion.getIdVehiculo(), notificacion.getFechaNotificacion());
        System.out.println(prueba);
        return new PruebaDto(prueba);
    }

    private PruebaDto buscarPruebaNotificacionEmpleado(NotificacionRadioExcedidoDto notificacion,Integer idEmpleado){
        PruebaEntity prueba = pruebaRepository.existsByVehiculoIdAndFechaNotificacionAndEmpleado(notificacion.getIdVehiculo(), notificacion.getFechaNotificacion(), idEmpleado);
        return new PruebaDto(prueba);
    }

    public List<PruebaDto> obtenerIncidentesEmpleado(Integer idEmpleado) {
        List<NotificacionRadioExcedidoDto> notificaciones = restriccionesService.getNotificacionesRadioExcedido();

        return notificaciones.stream()
                .map(notificacion -> buscarPruebaNotificacionEmpleado(notificacion, idEmpleado))
                .collect(Collectors.toList());
    }

    public Iterable<PruebaEntity> getAll(){return pruebaRepository.findAll();}

    public List<PruebaDto> obtenerIncidentes() {
        List<NotificacionRadioExcedidoDto> notificaciones = restriccionesService.getNotificacionesRadioExcedido();

        return notificaciones.stream()
                .map(this::buscarPruebaDeNotificacion)
                .collect(Collectors.toList());
    }
}
