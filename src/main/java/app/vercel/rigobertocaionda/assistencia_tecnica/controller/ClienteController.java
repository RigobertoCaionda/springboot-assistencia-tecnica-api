package app.vercel.rigobertocaionda.assistencia_tecnica.controller;

import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.ClienteDTO;
import app.vercel.rigobertocaionda.assistencia_tecnica.controller.mapper.ClienteMapper;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Cliente;
import app.vercel.rigobertocaionda.assistencia_tecnica.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")

public class ClienteController implements GenericController {
    private final ClienteService service;
    private final ClienteMapper mapper;

    @PostMapping
    public ResponseEntity<ClienteDTO> store(@RequestBody @Valid ClienteDTO dto) {
        Cliente cliente = mapper.toEntity(dto);

        Cliente clienteResultado = service.execute(cliente);
        ClienteDTO clienteDTO = mapper.toDTO(clienteResultado);

        URI location = gerarHeaderLocation(cliente.getId());
        return ResponseEntity.created(location).body(clienteDTO);
    }
}
