CREATE TABLE IF NOT EXISTS ticket_communication(
    id UUID NOT NULL,

    ticket_id UUID UNIQUE NOT NULL,

    PRIMARY KEY(id),
    FOREIGN KEY(ticket_id) REFERENCES ticket(id)
);

CREATE TABLE IF NOT EXISTS ticket_message (
    ticket_communication_id UUID NOT NULL,
    send_at TIMESTAMP NOT NULL,
    content TEXT NOT NULL,

    PRIMARY KEY(ticket_communication_id),
    FOREIGN KEY(ticket_communication_id) REFERENCES ticket_communication(id)
);
