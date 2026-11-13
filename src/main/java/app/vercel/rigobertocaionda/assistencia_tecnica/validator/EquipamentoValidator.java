package app.vercel.rigobertocaionda.assistencia_tecnica.validator;

import app.vercel.rigobertocaionda.assistencia_tecnica.exceptions.RegistoDuplicadoException;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Equipamento;
import app.vercel.rigobertocaionda.assistencia_tecnica.repository.EquipamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class EquipamentoValidator {
    private final EquipamentoRepository equipamentoRepository;

    public void validar(Equipamento equipamento) {
        if (isNumeroSerieDuplicated(equipamento)) {
            throw new RegistoDuplicadoException("Esse número de série já existe.");
        }
    }

    private boolean isNumeroSerieDuplicated(Equipamento equipamento) {
        if (equipamento.getId() == null) {
            return equipamentoRepository.existsByNumeroSerie(equipamento.getNumeroSerie());
        }

        return equipamentoRepository.existsByNumeroSerieAndIdNot(equipamento.getNumeroSerie(), equipamento.getId());
    }
}
