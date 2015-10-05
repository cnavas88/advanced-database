CREATE TABLE ingredient(
	id_ingredient SERIAL PRIMARY KEY,
	nom VARCHAR(32) NOT NULL,
	refrigeracio BOOLEAN NOT NULL,
	id_familiaIngredient INTEGER NOT NULL,
	FOREIGN KEY(id_familiaIngredient) REFERENCES familiaIngredient(id_familiaIngredient)
);