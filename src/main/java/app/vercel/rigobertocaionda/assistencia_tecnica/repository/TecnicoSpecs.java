package app.vercel.rigobertocaionda.assistencia_tecnica.repository;
import app.vercel.rigobertocaionda.assistencia_tecnica.model.Tecnico;
import org.springframework.data.jpa.domain.Specification;

public class TecnicoSpecs {
    public static Specification<Tecnico> isEspecialidadeEqual(String especialidade) {
        return (root, query, cb) -> cb.equal(root.get("especialidade"), especialidade);
    }

    public static Specification<Tecnico> isAtivoEqual(Boolean ativo) {
        return (root, query, cb) -> cb.equal(root.get("ativo"), ativo);
    }
}
