package app.vercel.rigobertocaionda.assistencia_tecnica.validator;

import app.vercel.rigobertocaionda.assistencia_tecnica.exceptions.RegistoDuplicadoException;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Cliente;
import app.vercel.rigobertocaionda.assistencia_tecnica.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor

public class ClienteValidator {
    private final ClienteRepository clienteRepository;

    public void validar(Cliente cliente) {
        if (this.isEmailDuplicated(cliente)) {
            throw new RegistoDuplicadoException("Este email já existe.");
        }

        if (this.isTelefoneDuplicated(cliente)) {
            throw  new RegistoDuplicadoException("Este telefone já existe.");
        }
    }

    private boolean isEmailDuplicated(Cliente cliente) {
        if (cliente.getId() == null) {
            return clienteRepository.existsByEmail(cliente.getEmail());
        }

        return clienteRepository.existsByEmailAndIdNot(cliente.getEmail(), cliente.getId());
    }

    private boolean isTelefoneDuplicated(Cliente cliente) {
        if (cliente.getId() == null) {
            return clienteRepository.existsByTelefone(cliente.getTelefone());
        }

        return clienteRepository.existsByTelefoneAndIdNot(cliente.getTelefone(), cliente.getId());
    }
}
