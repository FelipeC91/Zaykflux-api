---JOIN TABLE
CREATE TABLE IF NOT EXISTS service_desk_service_catalog (
    service_desk_id UUID,
    service_catalog_id BIGINT,

    PRIMARY KEY(service_desk_id, service_catalog_id),
    FOREIGN KEY(service_desk_id) REFERENCES service_desk(id),
    FOREIGN KEY(service_catalog_id) REFERENCES service_catalog(id)

);