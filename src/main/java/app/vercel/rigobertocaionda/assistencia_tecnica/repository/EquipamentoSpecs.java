package app.vercel.rigobertocaionda.assistencia_tecnica.repository;

import app.vercel.rigobertocaionda.assistencia_tecnica.enums.TipoEquipamento;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Cliente;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Equipamento;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class EquipamentoSpecs {
    public static Specification<Equipamento> isMarcaEqual(String marca) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("marca"), marca));
    }

    public static Specification<Equipamento> modeloLike(String modelo) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder
                .like(criteriaBuilder.upper(root.get("modelo")), "%" + modelo.toUpperCase() + "%"));
    }

    public static Specification<Equipamento> isTipoEquipamentoEqual(TipoEquipamento tipo) {
        return ((root, query, cb) -> cb.equal(root.get("tipoEquipamento"), tipo));
    }

    public static Specification<Equipamento> nomeClienteLike(String name) { // Filtrando por um campo de outra entidade
        return ((root, query, criteriaBuilder) -> {
            Join<Equipamento, Cliente>  cliente = root.join("cliente", JoinType.INNER);
                return criteriaBuilder.like(
                        criteriaBuilder.lower(cliente.get("nome")),
                        "%" + name.trim().toLowerCase() + "%"
                );
        });
    }
}
