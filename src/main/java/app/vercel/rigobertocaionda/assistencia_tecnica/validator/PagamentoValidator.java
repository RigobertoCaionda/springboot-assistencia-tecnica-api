package app.vercel.rigobertocaionda.assistencia_tecnica.validator;

import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.PagamentoDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.enums.Status;
import app.vercel.rigobertocaionda.assistencia_tecnica.exceptions.BusinessException;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Orcamento;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Pagamento;
import app.vercel.rigobertocaionda.assistencia_tecnica.repository.PagamentoRepository;
import app.vercel.rigobertocaionda.assistencia_tecnica.service.OrcamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor

public class PagamentoValidator {
    private final OrcamentoService orcamentoService;
    private final PagamentoRepository pagamentoRepository;

    public void validar(Long orcamentoId, PagamentoDTO dto) {
        if (this.isPagamentoSuperior(orcamentoId, dto)) {
            throw new BusinessException("Este pagamento é superior ao valor em dívida.");
        }

        if (this.isOrdemServicoCancelada(orcamentoId, dto)) {
            throw new BusinessException("Não pode efetuar pagamento para ordens canceladas.");
        }

    }

    private boolean isPagamentoSuperior(Long orcamentoId, PagamentoDTO dto) {
        Orcamento orcamento = orcamentoService.findOne(orcamentoId);

        BigDecimal valorPago = pagamentoRepository.findByOrcamentoId(orcamentoId).stream()
                .map(Pagamento::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalComNovoPagamento = valorPago.add(dto.valor());

        return totalComNovoPagamento.compareTo(orcamento.getValorTotal()) > 0;
    }

    private boolean isOrdemServicoCancelada(Long orcamentoId, PagamentoDTO dto) {
        Orcamento orcamento = orcamentoService.findOne(orcamentoId);

        return orcamento.getOrdemServico().getStatus().equals(Status.CANCELADA);
    }
}
