CREATE TABLE IF NOT EXISTS ticket (
    id UUID PRIMARY KEY NOT NULL,
    number BIGSERIAL UNIQUE NOT NULL,
    status VARCHAR(9) NOT NULL,
    title VARCHAR(50) NOT NULL,
    description TEXT NOT NULL,
    priority VARCHAR(5),
    opened_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    closed_at TIMESTAMP,


    parent_ticket_id UUID,
    service_desk_id UUID NOT NULL,
    customer_id UUID NOT NULL,
    user_model_id UUID NOT NULL, --requester

    FOREIGN KEY(parent_ticket_id) REFERENCES ticket(id),
    FOREIGN KEY(service_desk_id) REFERENCES service_desk(id),
    FOREIGN KEY(customer_id) REFERENCES customer(id),
    FOREIGN KEY(user_model_id) REFERENCES user_model(id)
);