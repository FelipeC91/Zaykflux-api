CREATE TABLE IF NOT EXISTS user_model (
    id UUID PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(60) NOT NULL,
    role_model VARCHAR(9)
);
------------------------------------------------


CREATE TABLE IF NOT EXISTS group_model (
    id UUID PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

---JOIN TABLE
CREATE TABLE IF NOT EXISTS user_model_group_model (
    user_model_id UUID NOT NULL,
    group_model_id UUID NOT NULL,

   PRIMARY KEY(user_model_id, group_model_id),
   FOREIGN KEY (user_model_id) REFERENCES user_model(id),
   FOREIGN KEY (group_model_id) REFERENCES group_model(id)
);

-------------------------------------------------

