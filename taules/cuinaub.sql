\echo Descripcio: Script principal per la base de dades cuinaUB, que genera les taules necessaries i fa els inserts pertinets.
\echo Autors:     Carlos Navas & Jacint Moya.
\echo -------------------------------------------

\c postgres
DROP DATABASE IF EXISTS cuinaub;
CREATE DATABASE cuinaub;
\c cuinaub

\echo DROPS:
\i 'drop.sql'



\i 'chef\\chef.sql'
\i 'plat\\plat.sql'
\i 'tipusDeMenjar\\tipusDeMenjar.sql'
\i 'familiaIngredient\\familiaIngredient.sql'
\i 'ingredient\\ingredient.sql'
\i 'recepta\\recepta.sql'
\i 'conte\\conte.sql'
\i 'usuari\\usuari.sql'

\i 'inserts.sql'
