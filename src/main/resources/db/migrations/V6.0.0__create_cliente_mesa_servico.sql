CREATE TABLE cliente_mesa_servico (
    cliente_id BIGINT PRIMARY KEY NOT NULL,
    mesa_servico_id BIGINT NOT NULL,

    FOREIGN KEY(cliente_id) REFERENCES cliente(id),
    FOREIGN KEY(mesa_servico_id) REFERENCES mesa_servico(id)
);