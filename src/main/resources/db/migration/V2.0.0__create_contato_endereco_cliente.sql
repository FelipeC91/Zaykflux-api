--CLIENTE
CREATE TABLE IF NOT EXISTS cliente (
    id BIGINT PRIMARY KEY,
    nome_fantasia VARCHAR(60) ,
    razao_social VARCHAR(60) ,
    ativo BOOLEAN NOT NULL,
    cpf_cnpj VARCHAR(14) NOT NULL,
    foto_url VARCHAR(80)
);


--ALTER TABLE ENDERECO
ALTER TABLE endereco
    ADD COLUMN cliente_id BIGINT;

ALTER TABLE endereco
        ADD CONSTRAINT fk_cliente FOREIGN KEY(cliente_id) REFERENCES cliente(id);


--CONTATO
CREATE TABLE IF NOT EXISTS contato (
    id BIGINT PRIMARY KEY,
    nome VARCHAR(50) ,
    email VARCHAR(50),
    telefone VARCHAR(11),
    tipo_contato VARCHAR(11),
    cliente_id BIGINT,
    FOREIGN KEY(cliente_id) REFERENCES cliente(id)
);
