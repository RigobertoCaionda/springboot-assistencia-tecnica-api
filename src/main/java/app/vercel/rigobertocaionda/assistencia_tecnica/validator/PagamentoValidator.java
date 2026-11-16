package app.vercel.rigobertocaionda.assistencia_tecnica.validator;

import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.PagamentoDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.exceptions.BusinessException;
import app.vercel.rigobertocaionda.assistencia_tecnica.exceptions.RegistoDuplicadoException;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Orcamento;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Pagamento;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Tecnico;
import app.vercel.rigobertocaionda.assistencia_tecnica.service.OrcamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class PagamentoValidator {
    private final OrcamentoService orcamentoService;

    public void validar(PagamentoDTO dto) {
        if (this.isPagamentoSuperior(dto)) {
            throw new BusinessException("Este pagamento é superior ao valor em dívida.");
        }
    }

    private boolean isPagamentoSuperior(PagamentoDTO dto) {
        Orcamento orcamento = orcamentoService.findOne(dto.orcamentoId());
        if () {

        }
    }
}
