package utn.frc.bda.agencia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utn.frc.bda.agencia.services.ReporteService;

import java.text.SimpleDateFormat;

@RestController
@RequestMapping("reportes")
public class ReporteController {
    private final ReporteService reporteService;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }


}
