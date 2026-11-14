CREATE TABLE ordem_servicos
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    descricao      TEXT     NOT NULL,
    data_abertura  DATETIME NOT NULL,
    data_conclusao DATETIME,
    status         ENUM(
        'ABERTA',
        'EM_DIAGNOSTICO',
        'AGUARDANDO_APROVACAO',
        'AGUARDANDO_PECAS',
        'EM_REPARACAO',
        'CONCLUIDA',
        'CANCELADA'
    ) NOT NULL,
    prioridade     ENUM(
        'BAIXA',
        'NORMAL',
        'ALTA',
        'URGENTE'
    ) NOT NULL,
    equipamento_id BIGINT   NOT NULL,
    tecnico_id     BIGINT,

    CONSTRAINT fk_ordem_servicos_equipamentos
        FOREIGN KEY (equipamento_id)
            REFERENCES equipamentos (id),

    CONSTRAINT fk_ordem_servicos_tecnicos
        FOREIGN KEY (tecnico_id)
            REFERENCES tecnicos (id)
);