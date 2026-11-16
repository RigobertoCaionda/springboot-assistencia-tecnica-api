package app.vercel.rigobertocaionda.assistencia_tecnica.service;

import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.ItemOrcamentoDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.controller.mapper.ItemOrcamentoMapper;
import app.vercel.rigobertocaionda.assistencia_tecnica.exceptions.NotFoundException;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.ItemOrcamento;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Orcamento;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Peca;
import app.vercel.rigobertocaionda.assistencia_tecnica.repository.ItemOrcamentoRepository;
import app.vercel.rigobertocaionda.assistencia_tecnica.validator.ItemOrcamentoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor

public class ItemOrcamentoService {
    private final ItemOrcamentoRepository repository;
    private final ItemOrcamentoMapper mapper;
    private final ItemOrcamentoValidator validator;
    private final OrcamentoService orcamentoService;
    private final PecaService pecaService;

    @Transactional
    public ItemOrcamento execute(Long orcamentoId, ItemOrcamentoDTO dto) {
        ItemOrcamento itemOrcamento = mapper.toEntity(dto);

        if (itemOrcamento.getId() != null) {
            throw new IllegalArgumentException("Não pode enviar ID na criação.");
        }


        validator.validar(orcamentoId, dto);

        Peca peca = pecaService.findOne(dto.pecaId());
        itemOrcamento.setPeca(peca);
        itemOrcamento.setPrecoUnitario(peca.getPreco());

        Orcamento orcamento = orcamentoService.findOne(orcamentoId);
        itemOrcamento.setOrcamento(orcamento);

        repository.saveAndFlush(itemOrcamento); // O saveAndFlush garante que o itemOrcamento já tenha sido sincronizado na base de dados antes entrar para a conta do valorPecas abaxio

        BigDecimal valorPecas = repository.findByOrcamentoId(orcamentoId).stream()
                .map(item -> item.getPrecoUnitario()
                        .multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        orcamento.setValorPecas(valorPecas);
        orcamento.setValorTotal(orcamento.getValorMaoObras().add(valorPecas));

        return itemOrcamento;
    }

    public ItemOrcamento findOne(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Item orçamento não encontrado."));
    }

    @Transactional
    public void delete(Long itemOrcamentoId, Long orcamentoId) {
        Orcamento orcamento = orcamentoService.findOne(orcamentoId);
        ItemOrcamento itemOrcamento = this.findOne(itemOrcamentoId);

        if (!itemOrcamento.getOrcamento().getId().equals(orcamentoId)) {
            throw new IllegalArgumentException(
                    "Este item não pertence ao orçamento informado."
            );
        }

        repository.delete(itemOrcamento);
        repository.flush(); // Para sincronizar já essa eliminação

        BigDecimal valorPecas = repository.findByOrcamentoId(orcamentoId).stream()
                .map(item -> item.getPrecoUnitario()
                        .multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        orcamento.setValorPecas(valorPecas);
        orcamento.setValorTotal(orcamento.getValorMaoObras().add(valorPecas));
    }
}
