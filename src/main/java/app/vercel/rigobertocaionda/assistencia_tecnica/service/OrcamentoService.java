package app.vercel.rigobertocaionda.assistencia_tecnica.service;

import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.OrcamentoDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.controller.mapper.OrcamentoMapper;
import app.vercel.rigobertocaionda.assistencia_tecnica.enums.OrcamentoPrioridade;
import app.vercel.rigobertocaionda.assistencia_tecnica.exceptions.NotFoundException;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Orcamento;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.OrdemServico;
import app.vercel.rigobertocaionda.assistencia_tecnica.repository.OrcamentoRepository;
import app.vercel.rigobertocaionda.assistencia_tecnica.validator.OrcamentoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class OrcamentoService {
    private final OrcamentoRepository repository;
    private final OrcamentoValidator validator;
    private final OrcamentoMapper mapper;
    private final OrdemServicoService ordemServicoService;

    @Transactional
    public Orcamento execute(OrcamentoDTO dto, Long ordemServicoId) {
        Orcamento orcamento = mapper.toEntity(dto);

        if (orcamento.getId() != null) {
            throw new IllegalArgumentException("Não pode enviar ID na criação.");
        }

        orcamento.setPrioridade(OrcamentoPrioridade.PENDENTE);

        OrdemServico ordemServico = ordemServicoService.findOne(ordemServicoId);

        orcamento.setOrdemServico(ordemServico);

        validator.validar(dto, ordemServicoId);

        return repository.save(orcamento);
    }


    public Orcamento findOne(Long id) {
        return repository.findById(id).orElseThrow(() ->  new NotFoundException("Orçamento não existe"));
    }

    @Transactional
    public Orcamento aprovarOrcamento(Long id) {
        Orcamento orcamento = this.findOne(id);
        orcamento.setPrioridade(OrcamentoPrioridade.APROVADO);
        return repository.save(orcamento);
    }

    @Transactional
    public Orcamento rejeitarOrcamento(Long id) {
        Orcamento orcamento = this.findOne(id);
        orcamento.setPrioridade(OrcamentoPrioridade.REJEITADO);
        return repository.save(orcamento);
    }
}
