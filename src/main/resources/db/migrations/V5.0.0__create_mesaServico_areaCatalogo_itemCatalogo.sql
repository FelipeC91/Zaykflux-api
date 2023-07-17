CREATE TABLE IF NOT EXISTS mesa_servico (
    id BIGINT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
);
-------------------------------------------------

CREATE TABLE IF NOT EXISTS area_catalogo (
    id BIGINT PRIMARY KEY,
    nome VARCHAR(60) NOT NULL,
    mesa_servico_id BIGINT NOT NULL,

    FOREIGN KEY(mesa_servico_id) REFERENCES mesa_servico(id)
);
--------------------------------------------------

CREATE TABLE IF NOT EXISTS item_catalogo (
    id BIGINT PRIMARY KEY,
    nome VARCHAR(80) NOT NULL,
    area_catalogo_id BIGINT NOT NULL,

    FOREIGN KEY(area_catalogo_id) REFERENCES area_catalogo(id)
);