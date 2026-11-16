package app.vercel.rigobertocaionda.assistencia_tecnica.service;

import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.PagamentoDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.controller.mapper.PagamentoMapper;
import app.vercel.rigobertocaionda.assistencia_tecnica.enums.PagamentoStatus;
import app.vercel.rigobertocaionda.assistencia_tecnica.exceptions.NotFoundException;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Orcamento;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Pagamento;
import app.vercel.rigobertocaionda.assistencia_tecnica.repository.PagamentoRepository;
import app.vercel.rigobertocaionda.assistencia_tecnica.validator.PagamentoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor

public class PagamentoService {
    private final PagamentoRepository repository;
    private final PagamentoValidator validator;
    private final PagamentoMapper mapper;
    private final OrcamentoService orcamentoService;

    @Transactional
    public Pagamento execute(Long orcamentoId, PagamentoDTO dto) {
        Pagamento pagamento = mapper.toEntity(dto);


        if (pagamento.getId() != null) {
            throw new IllegalArgumentException("Não pode enviar ID na criação de pagamento.");
        }

        Orcamento orcamento = orcamentoService.findOne(orcamentoId);
        pagamento.setOrcamento(orcamento);
        pagamento.setStatus(PagamentoStatus.PENDENTE);

        validator.validar(orcamentoId, dto);

        return repository.save(pagamento);
    }

    public Pagamento findOne(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Pagamento não encontrado."));
    }

    public List<Pagamento> getOrdemServicoPagamentos(Long ordemServicoId) {
        return repository.findByOrcamentoOrdemServicoId(ordemServicoId);
    }
}
