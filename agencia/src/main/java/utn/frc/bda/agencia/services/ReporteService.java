package utn.frc.bda.agencia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.frc.bda.agencia.repositories.PosicionRepository;
import utn.frc.bda.agencia.repositories.PruebaRepository;
import utn.frc.bda.agencia.repositories.VehiculoRepository;

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


}
