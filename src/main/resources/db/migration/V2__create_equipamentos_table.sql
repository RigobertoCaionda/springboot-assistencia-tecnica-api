CREATE TABLE equipamentos (
          id BIGINT AUTO_INCREMENT PRIMARY KEY,
          marca VARCHAR(150) NOT NULL,
          modelo VARCHAR(150) NOT NULL,
          numero_serie VARCHAR(20) NOT NULL UNIQUE,
          tipo_equipamento ENUM(
            'COMPUTADOR',
            'IMPRESSORA',
            'TELEMOVEL',
            'TABLET',
            'MONITOR',
            'ROUTER'
        ) NOT NULL,
          cliente_id BIGINT NOT NULL,
          data_cadastro DATETIME NOT NULL,
          data_atualizacao DATETIME NOT NULL,
          CONSTRAINT fk_equipamentos_clientes
              FOREIGN KEY (cliente_id)
                  REFERENCES clientes(id)
);