package app.vercel.rigobertocaionda.assistencia_tecnica.service;

import app.vercel.rigobertocaionda.assistencia_tecnica.exceptions.NotFoundException;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Peca;
import app.vercel.rigobertocaionda.assistencia_tecnica.repository.PecaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class PecaService {
    private final PecaRepository repository;

    public Peca findOne(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Peça não encontrada."));
    }
}
