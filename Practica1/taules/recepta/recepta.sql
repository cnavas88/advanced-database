CREATE TABLE recepta(
	id_recepta SERIAL PRIMARY KEY,
	nom VARCHAR(32) NOT NULL,
	temps TIME NOT NULL,
	dificultat INTEGER NOT NULL,
	elaboracio TEXT,
	id_chef INTEGER NOT NULL,
	id_plat INTEGER NOT NULL,
	id_tipusDeMenjar INTEGER NOT NULL,
	FOREIGN KEY(id_chef) REFERENCES chef(id_chef),
	FOREIGN KEY(id_plat) REFERENCES plat(id_plat),
	FOREIGN KEY(id_tipusDeMenjar) REFERENCES tipusDeMenjar(id_tipusDeMenjar)
);