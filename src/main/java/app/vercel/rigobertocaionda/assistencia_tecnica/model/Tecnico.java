package app.vercel.rigobertocaionda.assistencia_tecnica.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tecnicos")
@Getter
@Setter

public class Tecnico {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String nome;
    @Column(length = 150, nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 100)
    private String especialidade;

    @Column(nullable = false)
    private Boolean ativo = true;

    @OneToMany(mappedBy = "tecnico")
    private List<OrdemServico> ordemServicos;
}
