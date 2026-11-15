package app.vercel.rigobertocaionda.assistencia_tecnica.controller.mapper;

import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.OrcamentoDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.OrcamentoResponseDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Orcamento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrcamentoMapper {
    Orcamento toEntity(OrcamentoDTO dto);
    OrcamentoResponseDTO toDTO(Orcamento orcamento);
}
