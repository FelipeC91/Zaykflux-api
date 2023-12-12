---JOIN TABLE
CREATE TABLE IF NOT EXISTS customer_group_model (
    customer_id UUID  NOT NULL,
    group_model_id UUID NOT NULL,

   PRIMARY KEY(customer_id, group_model_id),
   FOREIGN KEY (customer_id) REFERENCES customer(id),
   FOREIGN KEY (group_model_id) REFERENCES group_model(id)
);