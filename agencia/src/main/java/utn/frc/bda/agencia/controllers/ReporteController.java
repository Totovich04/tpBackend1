package utn.frc.bda.agencia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.frc.bda.agencia.dtos.ErrorResponse;
import utn.frc.bda.agencia.dtos.PruebaDto;
import utn.frc.bda.agencia.dtos.report.DistanciaVehiculoResponse;
import utn.frc.bda.agencia.dtos.report.IncidentesXEmpleado;
import utn.frc.bda.agencia.dtos.report.IncidentesResponse;
import utn.frc.bda.agencia.dtos.report.IncidentesXEmpleado;
import utn.frc.bda.agencia.dtos.report.PruebaResponse;
import utn.frc.bda.agencia.services.ReporteService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReporteController {
    private final ReporteService service;
    private final ReporteService reporteService;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    public ReporteController(ReporteService reporteService, ReporteService service) {
        this.reporteService = reporteService;
        this.service = service;
    }

    @GetMapping(value = "/kilometros-vehiculo/{idVehiculo}")
    public ResponseEntity<?> getKilometrosVehiculo(
            @PathVariable Integer idVehiculo,
            @RequestParam("fechaDesde") String fechaDesde,
            @RequestParam("fechaHasta") String fechaHasta) {
        try {
            Date desde = dateFormat.parse(fechaDesde);
            Date hasta = dateFormat.parse(fechaHasta);

            DistanciaVehiculoResponse response = reporteService.calcularDistanciaRecorrida(idVehiculo, desde, hasta);
            return ResponseEntity.ok(response);
        } catch (ParseException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    "Bad Request",
                    "Error al parsear la fecha: " + e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    "Bad Request",
                    "Error al calcular la distancia: " + e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
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

    @GetMapping("/detalle-pruebas/{idVehiculo}")
    public ResponseEntity<?> obtenerPruebasVehiculo(@PathVariable Integer idVehiculo) {
        try {
            List<PruebaDto> pruebas = reporteService.obtenerPruebasVehiculo(idVehiculo);
            PruebaResponse response = new PruebaResponse(pruebas);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    "Bad Request",
                    e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @GetMapping("/incidentes/{idEmpleado}")
    public ResponseEntity<?> obtenerIncidentesEmpleado(@PathVariable Integer idEmpleado) {
        try {
            List<PruebaDto> pruebas = reporteService.obtenerIncidentesEmpleado(idEmpleado);
            return ResponseEntity.ok(pruebas);
        } catch (RuntimeException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    "Bad Request",
                    "Error al obtener el reporte de incidentes del empleado con el ID: " + idEmpleado + " " +  e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
}
