CREATE TABLE IF NOT EXISTS state (
    id VARCHAR(2)  PRIMARY KEY,
    name VARCHAR(50) UNIQUE
);
---------------------------------------------

CREATE TABLE IF NOT EXISTS city (
    id UUID PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    state_id VARCHAR(2) NOT NULL,

    FOREIGN KEY (state_id) REFERENCES state(id)
);

