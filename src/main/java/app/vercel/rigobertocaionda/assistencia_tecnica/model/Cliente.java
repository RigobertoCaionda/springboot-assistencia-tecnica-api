package app.vercel.rigobertocaionda.assistencia_tecnica.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)

public class Cliente {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 150, nullable = false)
    private String nome;

    @Column(name = "email", length = 150, nullable = false, unique = true)
    private String email;

    @Column(name = "telefone", length = 150, unique = true)
    private String telefone;

    @Column(name = "data_cadastro", nullable = false)
    @CreatedDate
    private LocalDateTime dataCadastro;

    @Column(name = "data_atualizacao", nullable = false)
    @LastModifiedDate
    private LocalDateTime dataAtualizacao;
}
