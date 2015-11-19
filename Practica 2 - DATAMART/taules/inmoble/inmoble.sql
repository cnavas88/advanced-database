CREATE TABLE inmoble(
	id_inmoble SERIAL PRIMARY KEY,
	referencia_catastral VARCHAR(20) NOT NULL,
	superficie INTEGER NOT NULL,
	estat VARCHAR(32) NOT NULL,
	internet BOOLEAN NOT NULL,
	planta INTEGER NOT NULL,
	num_residents INTEGER NOT NULL,
	id_subtipus_inmoble INTEGER NOT NULL,
	dni INTEGER(8) NOT NULL,
	id_cadastre INTEGER NOT NULL,
	id_geolocalitzacio INTEGER NOT NULL,
	FOREIGN KEY (id_subtipus_inmoble) REFERENCES subtipus_inmoble(id_tipus),
	FOREIGN KEY (dni) REFERENCES propietari(dni),
	FOREIGN KEY (id_cadastre) REFERENCES cadastre(id_cadastre),
	FOREIGN KEY (id_geolocalitzacio) REFERENCES geolocalitzacio(id_geolocalitzacio)
);