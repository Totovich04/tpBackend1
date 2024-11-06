package utn.frc.bda.agencia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.frc.bda.agencia.dtos.PosicionDto;
import utn.frc.bda.agencia.dtos.VehiculoDto;
import utn.frc.bda.agencia.dtos.externos.RestriccionesDto;
import utn.frc.bda.agencia.entities.PosicionEntity;
import utn.frc.bda.agencia.entities.VehiculoEntity;
import utn.frc.bda.agencia.repositories.PosicionRepository;
import utn.frc.bda.agencia.repositories.PruebaRepository;
import utn.frc.bda.agencia.repositories.VehiculoRepository;

import java.util.Date;

@Service
public class VehiculoService {
    private final VehiculoRepository vehiculoRepository;
    private final PruebaRepository pruebaRepository;
    private final PosicionRepository posicionRepository;
    private final ExternalApisService externalApisService;

    @Autowired
    public VehiculoService(VehiculoRepository vrepository, PruebaRepository pruebaRepository, PosicionRepository posicionRepository, ExternalApisService externalApisService) {
        this.vehiculoRepository = vrepository;
        this.pruebaRepository = pruebaRepository;
        this.posicionRepository = posicionRepository;
        this.externalApisService = externalApisService;
    }

    public PosicionDto procesarPosicion(PosicionDto posicionDto) {
        RestriccionesDto restriccionesDto = externalApisService.getRestricciones();

        if (restriccionesDto == null) {
            throw new IllegalStateException("No se pudieron obtener las restricciones.");
        }
        PosicionEntity posicion = guardarPosicion(posicionDto);
        PosicionDto posicionDtoRespuesta = construirPosicion(posicionDto, posicion);

        if (posicionFueraRadio(posicionDtoRespuesta, restriccionesDto)){
            posicionDtoRespuesta.setMensaje("El vehiculo se encuentra fuera del radio permitido!.");
            return posicionDtoRespuesta;
        }

        if (zonaRestringida(posicionDtoRespuesta, restriccionesDto)){
            posicionDtoRespuesta.setMensaje("El vehiculo se encuentra en una Zona restringida!");
            return posicionDtoRespuesta;
        }

        posicionDtoRespuesta.setMensaje("Se registro la posicion del vehiculo.");
        return posicionDtoRespuesta;
    }


    private boolean posicionFueraRadio(PosicionDto posicionDto, RestriccionesDto restriccionesDto) {
        double distance = Math.sqrt(Math.pow(posicionDto.getCoordenadas().getLat() - restriccionesDto.getCoordenadas().getLat(), 2)
                + Math.pow(posicionDto.getCoordenadas().getLon() - restriccionesDto.getCoordenadas().getLon(), 2));

        return distance > restriccionesDto.getRadioAdmitido();
    }

    private boolean zonaRestringida(PosicionDto posicionDto, RestriccionesDto restriccionesDto) {
        return restriccionesDto.getZonaRestringidas().stream().anyMatch(zona -> {
            double latVehiculo = posicionDto.getCoordenadas().getLat();
            double lonVehiculo = posicionDto.getCoordenadas().getLon();
            double latNoroeste = zona.getNoroeste().getLat();
            double lonNoroeste = zona.getNoroeste().getLon();
            double latSureste = zona.getSureste().getLat();
            double lonSureste = zona.getSureste().getLon();

            return latVehiculo <= latNoroeste && latVehiculo >= latSureste
                    && lonVehiculo >= lonNoroeste && lonVehiculo <= lonSureste;
        });
    }
    private PosicionDto construirPosicion(PosicionDto posicionDto, PosicionEntity posicion) {
        posicionDto.setId(posicion.getId());
        posicionDto.getIdVehiculo().setPatente(posicion.getVehiculo().getPatente());
        posicionDto.getIdVehiculo().setAnio(posicion.getVehiculo().getAnio());
        posicionDto.getIdVehiculo().setIdModelo(posicion.getVehiculo().getModelo().getId());
        return posicionDto;
    }

    private VehiculoEntity validarVehiculo(Integer id) {
        VehiculoEntity vehiculo = vehiculoRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("No se encontro el vehiculo"));

        if (pruebaRepository.existsByVehiculoIdAndFechaHoraFinIsNull(id)){
            return vehiculo;
        } throw new IllegalArgumentException("El vehiculo no esta disponible en el momento.Inicie una prueba");
    }

    private PosicionEntity crearPosicionDto(PosicionDto posicionDto) {
        VehiculoEntity vehiculo = validarVehiculo(posicionDto.getIdVehiculo().getId());

        PosicionEntity posicion = new PosicionEntity();
        posicion.setVehiculo(vehiculo);
        posicion.setLatitud(posicionDto.getCoordenadas().getLat());
        posicion.setLongitud(posicionDto.getCoordenadas().getLon());
        posicion.setFechaHora(new Date());
        return posicion;
    }

    private PosicionEntity guardarPosicion(PosicionDto posicionDto) {
        PosicionEntity posicion = this.crearPosicionDto(posicionDto);

        return posicionRepository.save(posicion);
    }
}
