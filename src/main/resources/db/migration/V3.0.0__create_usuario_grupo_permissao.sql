CREATE TABLE IF NOT EXISTS usuario WITH ENCODING UTF8 (
    id BIGINT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    email VARCHAR(60) NOT NULL,
    senha VARCHAR(80) NOT NULL,
    ativo BOOLEAN NOT NULL,
    data_cadastro DATE
);
------------------------------------------------


CREATE TABLE IF NOT EXISTS grupo WITH ENCODING UTF8 (
    id BIGINT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
);

------------------------------------------------

CREATE TABLE IF NOT EXISTS usuario_grupo WITH ENCODING UTF8 (
    usuario_id BIGINT PRIMARY KEY NOT NULL,
    grupo_id BIGINT NOT NULL,

   FOREIGN KEY (usuario_id) REFERENCES usuario(id),
   FOREIGN KEY (grupo_id) REFERENCES grupo(id)
);

-------------------------------------------------

CREATE TABLE IF NOT EXISTS permissao (
    id BIGINT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    descricao  VARCHAR(50) NOT NULL
);

--------------------------------------------------

CREATE TABLE IF NOT EXISTS grupo_permissao (
    grupo_id BIGINT PRIMARY KEY NOT NULL,
    permissao_id BIGINT NOT NULL,

   FOREIGN KEY (grupo_id) REFERENCES grupo(id),
   FOREIGN KEY (permissao_id) REFERENCES permissao(id)
);
