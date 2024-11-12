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
public class IncidentesResponse extends ReportResponse {
    private Integer totalIncidentes;
    private List<PruebasConIncidentesDto> pruebasConIncidentesDtos;

    public IncidentesResponse(List<PruebaDto> pruebaDtos){
        super("Incidentes en pruebas", "Incidentes en pruebas que tienen al menos un incidente");
        this.totalIncidentes = pruebaDtos.size();

        Map<Integer, Long> incidentesPorPrueba = pruebaDtos.stream()
                .collect(Collectors.groupingBy(PruebaDto::getId, Collectors.counting()));

        this.pruebasConIncidentesDtos = incidentesPorPrueba.entrySet().stream().map(entry -> new PruebasConIncidentesDto(pruebaDtos.stream().filter(pruebaDto -> pruebaDto.getId().equals(entry.getKey())).findFirst().orElse(null), entry.getValue().intValue())).collect(Collectors.toList());
    }
}
