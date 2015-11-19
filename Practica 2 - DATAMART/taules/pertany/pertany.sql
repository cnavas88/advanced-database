CREATE TABLE pertany(
	id_inmoble INTEGER NOT NULL,
	id_sol INTEGER NOT NULL,
	id_us INTEGER NOT NULL,
	FOREIGN KEY (id_inmoble) REFERENCES inmoble(id_inmoble),
	FOREIGN KEY (id_sol) REFERENCES sol(id_sol),
	FOREIGN KEY (id_us) REFERENCES us(id_us)
);