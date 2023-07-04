--CLIENTE
CREATE TABLE IF NOT EXISTS cliente (
    id BIGINT PRIMARY KEY,
    nome_fantasia VARCHAR(60) ,
    razao_social VARCHAR(60) ,
    ativo BOOLEAN,
    cpf_cnpj VARCHAR(14),
    foto_uri VARCHAR(80)
);


--ALTER TABLE ENDERECO
ALTER TABLE endereco
    ADD COLUMN cliente_id BIGINT;

ALTER TABLE endereco
        ADD CONSTRAINT fk_customer
        FOREIGN KEY(customer_id)
        REFERENCES customers(customer_id);


--CONTATO
CREATE TABLE IF NOT EXISTS contato (
    id BIGINT PRIMARY KEY,
    nome VARCHAR(50) ,
    email VARCHAR(50),
    telefone VARCHAR(11),
    tipo_contato VARCHAR(11)
    clinte_id BIGINT,
    FOREIGN KEY(clinte_id) REFERENCES clinete(id)
);