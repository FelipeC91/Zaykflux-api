CREATE TABLE IF NOT EXISTS estado ENCODI (
    id BIGINT PRIMARY KEY,
    nome VARCHAR(50) ,
    uf VARCHAR(2)
);

CREATE TABLE IF NOT EXISTS cidade (
    id BIGINT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    estado_id BIGINT NOT NULL,
    FOREIGN KEY (estado_id) REFERENCES estado(id)
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