package app.vercel.rigobertocaionda.assistencia_tecnica.validator;

import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.OrcamentoDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.controller.mapper.OrcamentoMapper;
import app.vercel.rigobertocaionda.assistencia_tecnica.exceptions.RegistoDuplicadoException;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Orcamento;
import app.vercel.rigobertocaionda.assistencia_tecnica.repository.OrcamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class OrcamentoValidator {
    private final OrcamentoRepository repository;
    private final OrcamentoMapper mapper;

    public void validar(OrcamentoDTO dto, Long ordemServicoId) {
        if (this.isOrcamentoDuplicated(dto, ordemServicoId)) {
            throw new RegistoDuplicadoException("Esta ordem de serviço já possui orçamento.");
        }
    }

    private boolean isOrcamentoDuplicated(OrcamentoDTO dto, Long ordemServicoId) {
        Orcamento orcamento = mapper.toEntity(dto);

        if (orcamento.getId() == null) {
            return repository.existsByOrdemServicoId(ordemServicoId);
        }
        return repository.existsByOrdemServicoIdAndIdNot(ordemServicoId, orcamento.getId());
    }
}
