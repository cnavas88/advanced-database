CREATE TABLE recepta(
	id_recepta SERIAL PRIMARY KEY,
	nom VARCHAR(32) NOT NULL,
	temps VARCHAR(8) NOT NULL,
	dificultat INTEGER NOT NULL,
	elaboracio TEXT NOT NULL,
	id_chef INTEGER,
	id_plat INTEGER,
	id_tipusDeMenjar INTEGER,
	FOREIGN KEY(id_chef) REFERENCES chef(id_chef),
	FOREIGN KEY(id_plat) REFERENCES plat(id_plat),
	FOREIGN KEY(id_tipusDeMenjar) REFERENCES tipusDeMenjar(id_tipusDeMenjar)
);