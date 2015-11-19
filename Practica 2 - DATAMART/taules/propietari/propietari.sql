CREATE TABLE propietari(
	dni INTEGER(8) PRIMARY KEY,
	nom VARCHAR(15) NOT NULL,
	primer_cognom VARCHAR(15) NOT NULL,
	segon_cognom VARCHAR(15) NOT NULL,
	telefon INTEGER NOT NULL,
	direccio VARCHAR(32) NOT NULL
);