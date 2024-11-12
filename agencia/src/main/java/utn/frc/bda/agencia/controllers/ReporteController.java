package utn.frc.bda.agencia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.frc.bda.agencia.dtos.PruebaDto;
import utn.frc.bda.agencia.dtos.report.DistanciaVehiculoResponse;
import utn.frc.bda.agencia.dtos.report.IncidentesPorEmpleado;
import utn.frc.bda.agencia.dtos.report.IncidentesResponse;
import utn.frc.bda.agencia.dtos.report.PruebaResponse;
import utn.frc.bda.agencia.services.ReporteService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("reportes")
public class ReporteController {
    private final ReporteService reporteService;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping(value = "/kilometrosVehiculo/{idVehiculo}")
    public ResponseEntity<?> getKilometrosVehiculo(
            @PathVariable Integer idVehiculo,
            @RequestParam("fechaDesde") String fechaDesde,
            @RequestParam("fechaHasta") String fechaHasta) {
        try {
            Date fechaDesdeDate = dateFormat.parse(fechaDesde);
            Date fechaHastaDate = dateFormat.parse(fechaHasta);

            DistanciaVehiculoResponse response = reporteService.calcularDistanciaVehiculo(idVehiculo, fechaDesdeDate, fechaHastaDate);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al calcular la distancia");
        }
    }

    @GetMapping("/pruebasVehiculo/{idVehiculo}")
    public ResponseEntity<?> getPruebasVehiculo(@PathVariable Integer idVehiculo) {
        try {
            List<PruebaDto> pruebaDtos = reporteService.getPruebasVehiculo(idVehiculo);
            PruebaResponse response = new PruebaResponse("Pruebas del vehiculo", "Pruebas realizadas por el vehiculo", pruebaDtos);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Error al obtener las pruebas");
        }
    }

    @GetMapping("/incidentes")
    public ResponseEntity<?> getIncidentes() {
        try {
            List<PruebaDto> pruebaDtos = reporteService.obtenerIncidentes();
            IncidentesResponse response = new IncidentesResponse(pruebaDtos);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Error al obtener las pruebas");
        }
    }

    @GetMapping("/incidentes/{idEmpleado}")
    public ResponseEntity<?> getIncidentesEmpleado(@PathVariable Integer idEmpleado) {
        try {
            List<PruebaDto> pruebaDtos = reporteService.obtenerIncidentesPorEmpleado(idEmpleado);
            IncidentesPorEmpleado response = new IncidentesPorEmpleado(idEmpleado, pruebaDtos);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Error al obtener las pruebas");
        }
    }
}
