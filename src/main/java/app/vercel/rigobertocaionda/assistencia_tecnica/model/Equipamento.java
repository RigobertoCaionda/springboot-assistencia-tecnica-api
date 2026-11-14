package app.vercel.rigobertocaionda.assistencia_tecnica.model;

import app.vercel.rigobertocaionda.assistencia_tecnica.enums.TipoEquipamento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "equipamentos")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Equipamento {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "marca", length = 150, nullable = false)
    private String marca;

    @Column(length = 150, nullable = false)
    private String modelo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoEquipamento tipoEquipamento;

    @Column(unique = true, length = 20, nullable = false)
    private String numeroSerie;

    @Column(nullable = false, updatable = false) // updatable false quer dizer que essa data não vai aceitar alteração depois que for criada
    @CreatedDate
    private LocalDateTime dataCadastro;

    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime dataAtualizacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "equipamento")
    private List<OrdemServico> ordemServicos;
}
