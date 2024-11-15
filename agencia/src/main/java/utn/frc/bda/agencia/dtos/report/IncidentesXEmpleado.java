package utn.frc.bda.agencia.dtos.report;

import lombok.Data;
import lombok.EqualsAndHashCode;
import utn.frc.bda.agencia.dtos.PruebaDto;
import utn.frc.bda.agencia.dtos.PruebasConIncidentesDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
public class IncidentesXEmpleado extends ReportResponse{
    private Integer idEmpleado;
    private Integer totalIncidentes;
    private List<PruebasConIncidentesDto> pruebasConIncidentes;

    public IncidentesXEmpleado(Integer idEmpleado, List<PruebaDto> pruebasConIncidentes) {
        super("Reporte de Incidentes por Empleado", "Reporte de las pruebas para un empleado en especifico que tienen accidentes registrados.");
        this.idEmpleado = idEmpleado;

        // Agrupa las pruebas por ID y cuenta los incidentes
        Map<Integer, Long> incidentesPorPrueba = pruebasConIncidentes.stream()
                .collect(Collectors.groupingBy(PruebaDto::getId, Collectors.counting()));

        // Crea una lista de PruebaConIncidentesDto usando el conteo de incidentes
        this.pruebasConIncidentes = incidentesPorPrueba.entrySet().stream()
                .map(entry -> new PruebasConIncidentesDto(
                        pruebasConIncidentes.stream()
                                .filter(prueba -> prueba.getId() == entry.getKey())
                                .findFirst()
                                .orElse(null), // o maneja esto adecuadamente en caso de que falte algún ID
                        entry.getValue().intValue()
                ))
                .collect(Collectors.toList());

        this.totalIncidentes = pruebasConIncidentes.size();
    }
}