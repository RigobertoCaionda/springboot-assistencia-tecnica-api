package app.vercel.rigobertocaionda.assistencia_tecnica.controller.mapper;

import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.OrdemServicoDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.OrdemServicoResponseDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.OrdemServico;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrdemServicoMapper {
    OrdemServico toEntity(OrdemServicoDTO dto);
    OrdemServicoResponseDTO toDTO(OrdemServico ordemServico);
}
