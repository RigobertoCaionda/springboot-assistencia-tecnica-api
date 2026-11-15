package app.vercel.rigobertocaionda.assistencia_tecnica.repository;

import app.vercel.rigobertocaionda.assistencia_tecnica.model.OrdemServico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
    Page<OrdemServico> findByTecnicoId(Long tecnicoId, Pageable pageable);
    Page<OrdemServico> findByEquipamentoClienteId(
            Long clienteId,
            Pageable pageable
    );
}
