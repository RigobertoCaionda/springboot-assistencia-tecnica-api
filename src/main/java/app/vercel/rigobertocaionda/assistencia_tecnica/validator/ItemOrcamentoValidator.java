package app.vercel.rigobertocaionda.assistencia_tecnica.validator;

import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.ItemOrcamentoDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.controller.mapper.ItemOrcamentoMapper;
import app.vercel.rigobertocaionda.assistencia_tecnica.enums.OrcamentoPrioridade;
import app.vercel.rigobertocaionda.assistencia_tecnica.exceptions.BusinessException;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.ItemOrcamento;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Orcamento;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Peca;
import app.vercel.rigobertocaionda.assistencia_tecnica.service.OrcamentoService;
import app.vercel.rigobertocaionda.assistencia_tecnica.service.PecaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class ItemOrcamentoValidator {
    private final OrcamentoService orcamentoService;
    private final ItemOrcamentoMapper mapper;
    private final PecaService pecaService;

    public void validar(Long orcamentoId, ItemOrcamentoDTO dto) {
        ItemOrcamento itemOrcamento = mapper.toEntity(dto);

        if (this.isOrcamentoAprovadoOuRejeitado(orcamentoId)) {
            throw new BusinessException("Não Pode adicionar Peças a um orçamento aprovado ou rejeitado.");
        }

        if (this.isPecaInativa(dto.pecaId())) {
            throw  new BusinessException("Não pode adicionar uma peça inativa a um orçamento.");
        }

        if (this.isQuantidadeUltrapassaStock(dto.pecaId(), dto.quantidade())) {
            throw  new BusinessException("Esta quantidade ultrapassa o stock dessa peça..");
        }
    }

    private boolean isOrcamentoAprovadoOuRejeitado(Long orcamentoId) {
       Orcamento orcamento = orcamentoService.findOne(orcamentoId);

        return orcamento.getPrioridade().equals(OrcamentoPrioridade.APROVADO)
                ||
                orcamento.getPrioridade().equals(OrcamentoPrioridade.REJEITADO);
    }

    private boolean isPecaInativa(Long pecaId) {
        Peca peca = pecaService.findOne(pecaId);

        return !peca.getAtivo();
    }

    private boolean isQuantidadeUltrapassaStock(Long pecaId, Integer quantidade) {
        Peca peca = pecaService.findOne(pecaId);

        return quantidade > peca.getQuantidadeStock();
    }
}
