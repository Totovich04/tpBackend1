package utn.frc.bda.agencia.dtos.report;

import lombok.Data;
import lombok.EqualsAndHashCode;
import utn.frc.bda.agencia.dtos.PruebaDto;
import utn.frc.bda.agencia.dtos.PruebasConIncidentesDto;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
public class IncidentesPorEmpleado extends ReportResponse{
    private Integer idEmpleado;
    private Integer totalIncidentes;
    private List<PruebasConIncidentesDto> pruebasConIncidentes;

    public IncidentesPorEmpleado(Integer idEmpleado, List<PruebaDto> pruebasConIncidentes) {
        super("Incidentes por empleado", "Cantidad de incidentes por empleado");
        this.idEmpleado = idEmpleado;

        Map<Integer, Long> incidentesPorPrueba = pruebasConIncidentes.stream()
                .collect(Collectors.groupingBy(PruebaDto::getId, Collectors.counting()));

        this.totalIncidentes = incidentesPorPrueba.size();

        this.pruebasConIncidentes = incidentesPorPrueba.entrySet().stream()
                .map(entry -> new PruebasConIncidentesDto(pruebasConIncidentes.stream().filter(pruebasConIncidentesDto -> Objects.equals(pruebasConIncidentesDto.getId(), entry.getKey())).findFirst().orElse(null), entry.getValue().intValue()))
                .collect(Collectors.toList());
    }
}
