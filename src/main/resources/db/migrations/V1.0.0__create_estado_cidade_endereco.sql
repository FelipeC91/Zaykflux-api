CREATE TABLE IF NOT EXISTS estado (
    uf VARCHAR(2) PRIMARY KEY,
    nome VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS cidade (
    id BIGINT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    estado_id VARCHAR(2) NOT NULL,
    FOREIGN KEY (estado_id) REFERENCES estado(uf)
);

CREATE TABLE IF NOT EXISTS endereco (
    id BIGINT PRIMARY KEY,
    cep VARCHAR(8) NOT NULL,
    bairro VARCHAR(50) NOT NULL,
    logradouro VARCHAR(80) NOT NULL,
    complemento VARCHAR(80),
    numero INTEGER NOT NULL,
    cidade_id BIGINT NOT NULL,
    FOREIGN KEY (cidade_id) REFERENCES cidade(id)
);