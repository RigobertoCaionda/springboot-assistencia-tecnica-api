package app.vercel.rigobertocaionda.assistencia_tecnica.controller.mapper;

import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.EquipamentoDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.EquipamentoResponseDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Equipamento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EquipamentoMapper {
    Equipamento toEntity(EquipamentoDTO dto);
    EquipamentoResponseDTO toDTO(Equipamento equipamento);
}
