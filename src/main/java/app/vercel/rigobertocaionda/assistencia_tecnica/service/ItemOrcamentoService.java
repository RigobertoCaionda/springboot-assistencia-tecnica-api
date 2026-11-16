package app.vercel.rigobertocaionda.assistencia_tecnica.service;

import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.ItemOrcamentoDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.controller.mapper.ItemOrcamentoMapper;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.ItemOrcamento;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Orcamento;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Peca;
import app.vercel.rigobertocaionda.assistencia_tecnica.repository.ItemOrcamentoRepository;
import app.vercel.rigobertocaionda.assistencia_tecnica.validator.ItemOrcamentoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor

public class ItemOrcamentoService {
    private final ItemOrcamentoRepository repository;
    private final ItemOrcamentoMapper mapper;
    private final ItemOrcamentoValidator validator;
    private final OrcamentoService orcamentoService;
    private final PecaService pecaService;

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

        List<ItemOrcamento> itemOrcamentos = repository.findByOrcamentoId(orcamentoId);

        BigDecimal valorPecas = itemOrcamentos.stream()
                .map(item -> item.getPrecoUnitario()
                        .multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        orcamento.setValorPecas(valorPecas);

        itemOrcamento.setOrcamento(orcamento);

        return repository.save(itemOrcamento);
    }
}
