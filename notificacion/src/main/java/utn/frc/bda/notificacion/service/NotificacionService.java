package utn.frc.bda.notificacion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.frc.bda.notificacion.dtos.NotificacionPromocionDto;
import utn.frc.bda.notificacion.dtos.NotificacionRadioExcedidoDto;
import utn.frc.bda.notificacion.dtos.NotificacionZonaPeligrosaDto;
import utn.frc.bda.notificacion.dtos.PosicionDto;
import utn.frc.bda.notificacion.models.NotificacionEntity;
import utn.frc.bda.notificacion.models.NotificacionPromocionEntity;
import utn.frc.bda.notificacion.models.NotificacionRadioExcedidoEntity;
import utn.frc.bda.notificacion.models.NotificacionZonaPeligrosaEntity;
import utn.frc.bda.notificacion.repositories.NotificacionPromocionRepository;
import utn.frc.bda.notificacion.repositories.NotificacionRadioExcedidoRepository;
import utn.frc.bda.notificacion.repositories.NotificacionZonaPeligrosaRepository;

import java.time.LocalDateTime;
import java.util.stream.StreamSupport;

@Service
public class NotificacionService {
    private final NotificacionPromocionRepository promocionRepository;
    private final NotificacionRadioExcedidoRepository radioExcedidoRepository;
    private final NotificacionZonaPeligrosaRepository zonaPeligrosaRepository;

    @Autowired
    public NotificacionService(NotificacionPromocionRepository promocionRepository, NotificacionRadioExcedidoRepository radioExcedidoRepository, NotificacionZonaPeligrosaRepository zonaPeligrosaRepository) {
        this.promocionRepository = promocionRepository;
        this.radioExcedidoRepository = radioExcedidoRepository;
        this.zonaPeligrosaRepository = zonaPeligrosaRepository;
    }

    public NotificacionPromocionEntity createPromocion(NotificacionPromocionDto promo) {
        NotificacionPromocionEntity newPromo = buildNotificacionPromocionFromDto(promo);
        return promocionRepository.save(newPromo);
    }

    public NotificacionZonaPeligrosaEntity createZonaPeligrosa(PosicionDto pos) {
        NotificacionZonaPeligrosaEntity nuevaZonaPeligrosa = buildNotificacionZonaPeligrosaFromDto(pos);
        return zonaPeligrosaRepository.save(nuevaZonaPeligrosa);
    }

    public NotificacionRadioExcedidoEntity createRadioExcedido(PosicionDto posicion) {
        NotificacionRadioExcedidoEntity nuevoRadioExcedido = buildNotificacionRadioExcedidoFromDto(posicion);
        return radioExcedidoRepository.save(nuevoRadioExcedido);
    }

    public Iterable<NotificacionPromocionDto> getAllPromociones() {
        Iterable<NotificacionPromocionEntity> promociones =  promocionRepository.findAll();
        return StreamSupport.stream(promociones.spliterator(), false).map(NotificacionPromocionDto::new).toList();
    }

    public Iterable<NotificacionRadioExcedidoDto> getAllRadiosExcedidos() {
        Iterable<NotificacionRadioExcedidoEntity> radios = radioExcedidoRepository.findAll();
        return StreamSupport.stream(radios.spliterator(), false).map(NotificacionRadioExcedidoDto::new).toList();
    }

    public Iterable<NotificacionZonaPeligrosaDto> getAllZonasPeligrosas() {
        Iterable<NotificacionZonaPeligrosaEntity> zonas = zonaPeligrosaRepository.findAll();
        return StreamSupport.stream(zonas.spliterator(), false).map(NotificacionZonaPeligrosaDto::new).toList();
    }

    private NotificacionPromocionEntity buildNotificacionPromocionFromDto(NotificacionPromocionDto promocionDto) {
        NotificacionPromocionEntity promocion = new NotificacionPromocionEntity();
        promocion.setCodigoPromocion(promocionDto.getCodigoPromocion());
        promocion.setFechaNotificacion(promocionDto.getFechaNotificacion());
        promocion.setMensaje(promocionDto.getMensaje());
        promocion.setFechaExpiracion(promocionDto.getFechaExpiracion());
        return promocion;
    }

    private NotificacionRadioExcedidoEntity buildNotificacionRadioExcedidoFromDto(PosicionDto pos) {
        NotificacionRadioExcedidoEntity radioExcedido = new NotificacionRadioExcedidoEntity();
        radioExcedido.setFechaNotificacion(LocalDateTime.now());
        radioExcedido.setLatitudActual(pos.getCoord().getLat());
        radioExcedido.setLongitudActual(pos.getCoord().getLon());
        radioExcedido.setIdVehiculo(pos.getVehiculo().getId());
        radioExcedido.setMensaje(pos.getMessage());
        return radioExcedido;
    }

    private NotificacionZonaPeligrosaEntity buildNotificacionZonaPeligrosaFromDto(PosicionDto pos) {
        NotificacionZonaPeligrosaEntity zonaPeligrosa = new NotificacionZonaPeligrosaEntity();
        zonaPeligrosa.setFechaNotificacion(LocalDateTime.now());
        zonaPeligrosa.setNivelPeligro("ALTO");
        zonaPeligrosa.setLatitudActual(pos.getCoord().getLat());
        zonaPeligrosa.setLongitudActual(pos.getCoord().getLon());
        zonaPeligrosa.setIdVehiculo(pos.getVehiculo().getId());
        zonaPeligrosa.setMensaje(pos.getMessage());
        return zonaPeligrosa;
    }


}
