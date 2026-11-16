package app.vercel.rigobertocaionda.assistencia_tecnica.controller.mapper;

import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.ItemOrcamentoDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.ItemOrcamentoResponseDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.ItemOrcamento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemOrcamentoMapper {
    ItemOrcamento toEntity(ItemOrcamentoDTO dto);
    ItemOrcamentoResponseDTO toDTO(ItemOrcamento itemOrcamento);
}
