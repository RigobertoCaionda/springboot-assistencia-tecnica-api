package app.vercel.rigobertocaionda.assistencia_tecnica.controller.mapper;

import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.PagamentoDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.PagamentoResponseDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Pagamento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PagamentoMapper {
    Pagamento toEntity(PagamentoDTO dto);
    PagamentoResponseDTO toDTO(Pagamento pagamento);
}
