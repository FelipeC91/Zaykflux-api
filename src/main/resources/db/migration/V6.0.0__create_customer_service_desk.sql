CREATE TABLE customer_service_desk (
    customer_id UUID NOT NULL,
    service_desk_id UUID NOT NULL,

    PRIMARY KEY(customer_id, service_desk_id),
    FOREIGN KEY(customer_id) REFERENCES customer(id),
    FOREIGN KEY(service_desk_id) REFERENCES service_desk(id)
);