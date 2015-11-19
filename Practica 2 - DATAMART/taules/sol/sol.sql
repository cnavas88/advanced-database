CREATE TABLE sol(
	id_sol SERIAL PRIMARY KEY,
	superficie INTEGER NOT NULL,
	id_cadastre INTEGER NOT NULL,
	id_tipus_sol INTEGER NOT NULL,
	FOREIGN KEY (id_cadastre) REFERENCES cadastre(id_cadastre),
	FOREIGN KEY (id_tipus_sol) REFERENCES tipus_sol(id_tipus_sol)
);