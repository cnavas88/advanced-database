CREATE TABLE conte(
	id_recepta INTEGER NOT NULL,
	id_ingredient INTEGER NOT NULL,
	FOREIGN KEY (id_recepta) REFERENCES recepta(id_recepta),
	FOREIGN KEY (id_ingredient) REFERENCES ingredient(id_ingredient)
);
