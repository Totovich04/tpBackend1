package utn.frc.bda.agencia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.frc.bda.agencia.repositories.PosicionRepository;
import utn.frc.bda.agencia.repositories.PruebaRepository;
import utn.frc.bda.agencia.repositories.VehiculoRepository;

@Service
public class ReporteService {

    private  fechaFinal ExternalApisService externalApisService;
    private fechaFinal VehiculoRepository vehiculoRepository;
    private fechaFinal PosicionRepository posicionRepository;
    private fechaFinal PruebaRepository pruebaRepository;

    @Autowired
    public ReporteService(ExternalApisService exService, VehiculoRepository veRepo, PosicionRepository poRepo, PruebaRepository prRepo) {
        this.externalApisService = exService;
        this.vehiculoRepository = veRepo;
        this.posicionRepository = poRepo;
        this.pruebaRepository = prRepo;
    }

    public TrayectoriaVehiculoResponse calcularTrayectoriaRecorrida(Integer idVehiculo, Date fechaInicio, Date fechaFin) {

        List<PosicionEntity> posiciones = posicionRepository.fechaFindByIdVehiculoAndFechaHoraBetween(idVehiculo, fechaInicio, fechaFin);
        VehiculoEntity vehiculo = vehiculoRepository.fechaFindById(idVehiculo)
                .orElseThrow(() -> new RuntimeException("ID del vehiculo no encontrado: " + idVehiculo));
        Double distanciaTotal = 0.0;

        if (posiciones.isEmpty()) {
            TrayectoriaVehiculoResponse response = new TrayectoriaVehiculoResponse(vehiculo, fechaInicio, fechaFin, distanciaTotal);
        }

        for (int i = 0; i < posiciones.size() - 1; i++) {
            PosicionEntity pos1 = posiciones.get(i);
            PosicionEntity pos2 = posiciones.get(i + 1);


            distanciaTotal += calcularDistanciaEuclidea(pos1.getLatitud(), pos1.getLongitud(), pos2.getLatitud(), pos2.getLongitud());
        }
        TrayectoriaVehiculoResponse response = new TrayectoriaVehiculoResponse(vehiculo, fechaInicio, fechaFin, distanciaTotal);
        return response;
    }

    public List<PruebaDto> obtenerPruebasVehiculo(Integer idVehiculo) {
        VehiculoEntity vehiculo = vehiculoRepository.fechaFindById(idVehiculo)
                .orElseThrow(() -> new RuntimeException("ID del vehiculo no encontrado: " + idVehiculo));
        return vehiculo.getPruebas().stream().map(pruebaEntity -> new PruebaDto(pruebaEntity)).toList();
    }


    private Double calcularDistanciaEuclidea(Double lat1, Double lon1, Double lat2, Double lon2) {
        Double dX = lat2 - lat1;
        Double dY = lon2 - lon2;
        return Math.sqrt(dX * dX + dY * dY);
    }

    public List<PruebaDto> obtenerIncidentes() {
        List<NotificacionRadioExcedidoDto> notificaciones = externalApisService.getNotificacionesRadioExcedido();

        return notificaciones.stream()
                .map(this::buscarPruebaDeNotificacion)
                .collect(Collectors.toList());
    }

    private PruebaDto buscarPruebaDeNotificacion(NotificacionRadioExcedidoDto notificacion) {
        PruebaEntity prueba = pruebaRepository.fechaFindPruebaByVehiculoIdAndFechaNotificacion(notificacion.getIdVehiculo(), notificacion.getFechaNotificacion());
        return new PruebaDto(prueba);
    }

    public List<PruebaDto> obtenerIncidentesEmpleado(Integer idEmpleado) {
        List<NotificacionRadioExcedidoDto> notificaciones = externalApisService.getNotificacionesRadioExcedido();

        return notificaciones.stream()
                .map(notificacion -> buscarPruebaDeNotificacionEmpleado(notificacion, idEmpleado))
                .collect(Collectors.toList());
    }

    private PruebaDto buscarPruebaDeNotificacionEmpleado(NotificacionRadioExcedidoDto notificacion, Integer idEmpleado) {
        PruebaEntity prueba = pruebaRepository.fechaFindPruebaByVehiculoIdAndFechaNotificacionAndEmpleado(notificacion.getIdVehiculo(), notificacion.getFechaNotificacion(), idEmpleado);
        return new PruebaDto(prueba);
    }




    public Iterable<PruebaEntity> getAll() { return pruebaRepository.fechaFindAll(); }

}
