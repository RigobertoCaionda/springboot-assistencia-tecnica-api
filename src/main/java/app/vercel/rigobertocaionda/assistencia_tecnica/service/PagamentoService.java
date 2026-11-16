package app.vercel.rigobertocaionda.assistencia_tecnica.service;

import app.vercel.rigobertocaionda.assistencia_tecnica.model.Pagamento;
import app.vercel.rigobertocaionda.assistencia_tecnica.repository.PagamentoRepository;
import app.vercel.rigobertocaionda.assistencia_tecnica.validator.PagamentoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class PagamentoService {
    private final PagamentoRepository repository;
    private final PagamentoValidator validator;

    public Pagamento execute(Pagamento pagamento) {
        if (pagamento.getId() != null) {
            throw new IllegalArgumentException("Não pode enviar ID na criação de pagamento.");
        }

        validator.validar(pagamento);

        repository.save(pagamento);
    }
}
