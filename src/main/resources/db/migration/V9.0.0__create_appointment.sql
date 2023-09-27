CREATE TABLE IF NOT EXISTS appointment (
    ticket_id UUID,
    appointment_date DATE NOT NULL,
    beginning_at TIMESTAMP NOT NULL,
    ending_at TIMESTAMP NOT NULL,
    description TEXT NOT NULL,

    user_model_id UUID NOT NULL, --accountable

    PRIMARY KEY(ticket_id),
    FOREIGN KEY(ticket_id) REFERENCES ticket(id),
    FOREIGN KEY(user_model_id) REFERENCES user_model(id)
);
