CREATE TABLE IF NOT EXISTS service_desk (
    id UUID PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description  VARCHAR(80)
);


CREATE TABLE IF NOT EXISTS service_catalog (
    id BIGINT PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    service_desk_id BIGINT NOT NULL
);


-------------------------------------------------

CREATE TABLE IF NOT EXISTS catalog_area (
    id BIGINT PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    service_catalog_id BIGINT NOT NULL,

    FOREIGN KEY(service_catalog_id) REFERENCES service_catalog(id)
);
--------------------------------------------------

CREATE TABLE IF NOT EXISTS catalog_item (
    id BIGINT PRIMARY KEY,
    name VARCHAR(80) NOT NULL,
    catalog_area_id BIGINT NOT NULL,

    FOREIGN KEY(catalog_area_id) REFERENCES catalog_area(id)
);