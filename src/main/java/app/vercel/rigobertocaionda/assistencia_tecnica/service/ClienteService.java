package app.vercel.rigobertocaionda.assistencia_tecnica.service;

import app.vercel.rigobertocaionda.assistencia_tecnica.model.Cliente;
import app.vercel.rigobertocaionda.assistencia_tecnica.repository.ClienteRepository;
import app.vercel.rigobertocaionda.assistencia_tecnica.validator.ClienteValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class ClienteService {
    private final ClienteRepository repository;
    private final ClienteValidator validator;

    public Cliente execute(Cliente cliente) {
        validator.validar(cliente);
        return repository.save(cliente);
    }
}
