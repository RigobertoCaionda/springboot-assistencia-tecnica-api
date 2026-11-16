package app.vercel.rigobertocaionda.assistencia_tecnica.controller;

import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.ItemOrcamentoDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.ItemOrcamentoResponseDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.OrcamentoResponseDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.controller.mapper.ItemOrcamentoMapper;
import app.vercel.rigobertocaionda.assistencia_tecnica.controller.mapper.OrcamentoMapper;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.ItemOrcamento;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Orcamento;
import app.vercel.rigobertocaionda.assistencia_tecnica.service.ItemOrcamentoService;
import app.vercel.rigobertocaionda.assistencia_tecnica.service.OrcamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("orcamentos")
@RequiredArgsConstructor

public class OrcamentoController {
    private final OrcamentoService service;
    private final OrcamentoMapper mapper;
    private final ItemOrcamentoService itemOrcamentoService;
    private final ItemOrcamentoMapper itemOrcamentoMapper;

    @GetMapping("{id}")
    public ResponseEntity<OrcamentoResponseDTO> show(@PathVariable("id") Long id) {
        Orcamento orcamento = service.findOne(id);
        return ResponseEntity.ok(mapper.toDTO(orcamento));
    }

    @PatchMapping("{id}/aprovar")
    public ResponseEntity<OrcamentoResponseDTO> aprovar(@PathVariable("id") Long id) {
        Orcamento orcamento = service.aprovarOrcamento(id);
        return ResponseEntity.ok(mapper.toDTO(orcamento));
    }

    @PatchMapping("{id}/rejeitar")
    public ResponseEntity<OrcamentoResponseDTO> rejeitar(@PathVariable("id") Long id) {
        Orcamento orcamento = service.rejeitarOrcamento(id);
        return ResponseEntity.ok(mapper.toDTO(orcamento));
    }

    @PostMapping("{id}/itens")
    public ResponseEntity<ItemOrcamentoResponseDTO> adicionarpecas(@PathVariable("id") Long id, @RequestBody @Valid ItemOrcamentoDTO dto) {
        ItemOrcamento itemOrcamento = itemOrcamentoService.execute(id, dto);
        return ResponseEntity.ok(itemOrcamentoMapper.toDTO(itemOrcamento));
    }

    @DeleteMapping("{id}/itens")
    public ResponseEntity<Void> deletarPecas(@PathVariable("id") Long id, @RequestParam Long itemId) {
        itemOrcamentoService.delete(itemId, id);
        return ResponseEntity.noContent().build();
    }
}
