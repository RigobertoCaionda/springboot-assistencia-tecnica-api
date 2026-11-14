package app.vercel.rigobertocaionda.assistencia_tecnica.validator;

import app.vercel.rigobertocaionda.assistencia_tecnica.exceptions.RegistoDuplicadoException;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Tecnico;
import app.vercel.rigobertocaionda.assistencia_tecnica.repository.TecnicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class TecnicoValidator {
    private final TecnicoRepository tecnicoRepository;

    public void validar(Tecnico tecnico) {
        if (this.isEmailDuplicated(tecnico)) {
            throw new RegistoDuplicadoException("Este email já existe.");
        }
    }

    private boolean isEmailDuplicated(Tecnico tecnico) {
        if (tecnico.getId() == null) {
            return tecnicoRepository.existsByEmail(tecnico.getEmail());
        }

        return tecnicoRepository.existsByEmailAndIdNot(tecnico.getEmail(), tecnico.getId());
    }
}
