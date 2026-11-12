CREATE TABLE clientes (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        nome VARCHAR(150) NOT NULL,
        email VARCHAR(150) NOT NULL UNIQUE,
        telefone VARCHAR(20) UNIQUE,
        data_cadastro DATETIME NOT NULL,
        data_atualizacao DATETIME NOT NULL
);