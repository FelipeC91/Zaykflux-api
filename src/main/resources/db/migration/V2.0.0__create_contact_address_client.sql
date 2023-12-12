
CREATE TABLE IF NOT EXISTS customer (
    id UUID PRIMARY KEY NOT NULL,
    trading_name VARCHAR(60) NOT NULL, --nome fantasia
    company_name VARCHAR(60) NOT NULL, --razao social
    active BOOLEAN NOT NULL,
    cpf_cnpj VARCHAR(14) NOT NULL,
    photo_url VARCHAR(80)
);

CREATE TABLE IF NOT EXISTS contact (
    customer_id UUID,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    phone VARCHAR(11),
    contact_type VARCHAR(11),

    PRIMARY KEY(customer_id),
    FOREIGN KEY(customer_id) REFERENCES customer(id)
);

------------------------------------------

CREATE TABLE IF NOT EXISTS address (
    customer_id UUID,
    zip_code VARCHAR(8) NOT NULL,
    neighborhood VARCHAR(50) NOT NULL,
    street VARCHAR(80) NOT NULL,
    complement VARCHAR(80),
    number INTEGER NOT NULL,

    city_id UUID NOT NULL,

    PRIMARY KEY(customer_id),
    FOREIGN KEY(customer_id) REFERENCES customer(id),
    FOREIGN KEY (city_id) REFERENCES city(id)
);

