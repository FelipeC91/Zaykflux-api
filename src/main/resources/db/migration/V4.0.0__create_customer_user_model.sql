---JOIN TABLE
CREATE TABLE IF NOT EXISTS customer_user_model (
    customer_id UUID NOT NULL,
    user_model_id UUID NOT NULL,

   PRIMARY KEY(customer_id, user_model_id),
   FOREIGN KEY (customer_id) REFERENCES customer(id),
   FOREIGN KEY (user_model_id) REFERENCES user_model(id)
);