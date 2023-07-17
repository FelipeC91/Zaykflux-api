CREATE TABLE IF NOT EXISTS cliente_usuario (
    cliente_id BIGINT PRIMARY KEY NOT NULL,
    usuario_id BIGINT NOT NULL,

   FOREIGN KEY (cliente_id) REFERENCES cliente(id),
   FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);