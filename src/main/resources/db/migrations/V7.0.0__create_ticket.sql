CREATE TABLE IF NOT EXISTS ticket (
    id BIGINT PRIMARY KEY NOT NULL,
    aberto BOOLEAN NOT NULL,
    titulo VARCHAR(50) NOT NULL,
    descricao VARCHAR(80) NOT NULL,
    prioridade VARCHAR(5),
    data_criacao TIMESTAMP NOT NULL,
    data_fechamento TIMESTAMP,


    ticket_pai_id BIGINT,
    mesa_servico_id BIGINT NOT NULL,
    cliente_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL, --solicitante

    FOREIGN KEY(ticket_pai_id) REFERENCES ticket(id),
    FOREIGN KEY(mesa_servico_id) REFERENCES mesa_servico(id),
    FOREIGN KEY(usuario_id) REFERENCES usuario(id),
    FOREIGN KEY(cliente_id) REFERENCES cliente(id)
);