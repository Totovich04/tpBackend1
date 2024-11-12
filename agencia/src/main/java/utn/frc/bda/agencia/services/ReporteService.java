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

    private  final ExternalApisService externalApisService;
    private final VehiculoRepository vehiculoRepository;
    private final PosicionRepository posicionRepository;
    private final PruebaRepository pruebaRepository;

    @Autowired
    public ReporteService(ExternalApisService exService, VehiculoRepository veRepo, PosicionRepository poRepo, PruebaRepository prRepo) {
        this.externalApisService = exService;
        this.vehiculoRepository = veRepo;
        this.posicionRepository = poRepo;
        this.pruebaRepository = prRepo;
    }

    public DistanciaVehiculoResponse calcularDistanciaVehiculo(Integer idVehiculo, Date fechaDesde, Date fechaHasta) {
        List<PosicionEntity> posicionEntities = posicionRepository.findByVehiculoAndFechaHoraBetween(idVehiculo, fechaDesde, fechaHasta);
        VehiculoEntity vehiculo = vehiculoRepository.findById(idVehiculo).orElse(null);
        Double distanciaTotal = 0.0;

        if (posicionEntities.isEmpty()){
            DistanciaVehiculoResponse response =new DistanciaVehiculoResponse(vehiculo, fechaDesde, fechaHasta, distanciaTotal);
        }

        for (int i = 0; i < posicionEntities.size() - 1; i++) {
            PosicionEntity posicionActual = posicionEntities.get(i);
            PosicionEntity posicionSiguiente = posicionEntities.get(i + 1);
            distanciaTotal += calcularDistancia(posicionActual.getLatitud(), posicionActual.getLongitud(), posicionSiguiente.getLatitud(), posicionSiguiente.getLongitud());
        }
        return new DistanciaVehiculoResponse(vehiculo, fechaDesde, fechaHasta, distanciaTotal);
    }

    private Double calcularDistancia(Double latitud, Double longitud, Double latitud1, Double longitud1) {
            Double dX = latitud1 - latitud;
            Double dY = longitud1 - longitud;
            return Math.sqrt(dX * dX + dY * dY);
    }

    public List<PruebaDto> getPruebasVehiculo(Integer idVehiculo) {
        VehiculoEntity vehiculo = vehiculoRepository.findById(idVehiculo).orElse(null);

        return vehiculo.getPruebaEntities().stream().map(pruebaEntity -> new PruebaDto(pruebaEntity)).toList();
    }

    public List<PruebaDto> obtenerIncidentes(){
        List<NotificacionRadioExcedidoDto> notificaciones = externalApisService.getNotificacionesRadioExcedido();

        return notificaciones.stream().map(this::buscarPruebaDeNotificacion).collect(Collectors.toList());
    }

    private PruebaDto buscarPruebaDeNotificacion(NotificacionRadioExcedidoDto notificacion){
        PruebaEntity prueba = pruebaRepository.existsByVehiculoIdAndFechaNotificacion(notificacion.getIdVehiculo(), notificacion.getFecha());
        return new PruebaDto(prueba);
    }

    public List<PruebaDto> obtenerIncidentesPorEmpleado(Integer idEmpleado){
        List<NotificacionRadioExcedidoDto> notificaciones = externalApisService.getNotificacionesRadioExcedido();

        return notificaciones.stream().map(notificacion -> buscarPruebaDeNotificacionPorEmpleado(notificacion, idEmpleado)).collect(Collectors.toList());
    }

    private PruebaDto buscarPruebaDeNotificacionPorEmpleado(NotificacionRadioExcedidoDto notificacion, Integer idEmpleado){
        PruebaEntity prueba = pruebaRepository.existsByVehiculoIdAndFechaNotificacionAndEmpleado(notificacion.getIdVehiculo(), notificacion.getFecha(), idEmpleado);
        return new PruebaDto(prueba);
    }

    public Iterable<PruebaEntity> getAll(){
        return  pruebaRepository.findAll();
    }
}
