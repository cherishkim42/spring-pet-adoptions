DROP TABLE IF EXISTS pet_surrender;

CREATE TABLE pet_surrender (
  id SERIAL PRIMARY KEY,
  type VARCHAR(255) NOT NULL,
  description TEXT
);