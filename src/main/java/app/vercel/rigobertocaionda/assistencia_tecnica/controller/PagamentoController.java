package app.vercel.rigobertocaionda.assistencia_tecnica.controller;

import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.PagamentoResponseDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.controller.mapper.PagamentoMapper;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Pagamento;
import app.vercel.rigobertocaionda.assistencia_tecnica.service.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pagamentos")
@RequiredArgsConstructor

public class PagamentoController {
    private final PagamentoService service;
    private final PagamentoMapper mapper;

    @GetMapping("{id}")
    public ResponseEntity<PagamentoResponseDTO> show(@PathVariable("id") Long id) {
        Pagamento pagamento = service.findOne(id);
        return ResponseEntity.ok(mapper.toDTO(pagamento));
    }
}
