/*DROP DATABASE IF EXISTS cuinaUB;
CREATE DATABASE cuinaUB;
USE cuinaUB;*/

DROP TABLE IF EXISTS "chef";
CREATE TABLE chef(
	id_chef INTEGER PRIMARY KEY AUTOINCREMENT,
	nom VARCHAR(32) NOT NULL,
	estrelles INTEGER NOT NULL
);

DROP TABLE IF EXISTS "plat";
CREATE TABLE plat(
	id_plat INTEGER PRIMARY KEY AUTOINCREMENT,
	nom VARCHAR(32) NOT NULL,
	descripcio TEXT	NOT NULL
);

DROP TABLE IF EXISTS "tipusDeMenjar";
CREATE TABLE tipusDeMenjar(
	id_tipusDeMenjar INTEGER PRIMARY KEY AUTOINCREMENT,
	nom VARCHAR(32) NOT NULL,
	descripcio TEXT NOT NULL
);

DROP TABLE IF EXISTS "familiaIngredient";
CREATE TABLE familiaIngredient(
	id_familiaIngredient INTEGER PRIMARY KEY AUTOINCREMENT,
	nom VARCHAR(32) NOT NULL,
	descripcio TEXT NOT NULL
);

DROP TABLE IF EXISTS "ingredient";
CREATE TABLE ingredient(
	id_ingredient INTEGER PRIMARY KEY AUTOINCREMENT,
	nom VARCHAR(32) NOT NULL,
	refrigeracio BOOLEAN NOT NULL,
	id_familiaIngredient INTEGER NOT NULL,
	FOREIGN KEY(id_familiaIngredient) REFERENCES familiaIngredient(id_familiaIngredient)
);

DROP TABLE IF EXISTS "recepta";
CREATE TABLE recepta(
	id_recepta INTEGER PRIMARY KEY AUTOINCREMENT,
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

DROP TABLE IF EXISTS "conte";
CREATE TABLE conte(
	id_recepta INTEGER NOT NULL,
	id_ingredient INTEGER NOT NULL,
	FOREIGN KEY (id_recepta) REFERENCES recepta(id_recepta),
	FOREIGN KEY (id_ingredient) REFERENCES ingredient(id_ingredient)
);


