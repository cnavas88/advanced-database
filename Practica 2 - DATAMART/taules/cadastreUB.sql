\echo Descripcio: Script principal per la base de dades cadastreUB, que genera les taules necessaries i fa els inserts pertinets.
\echo Autors:     Carlos Navas & Jacint Moya.
\echo -------------------------------------------

\c postgres
DROP DATABASE IF EXISTS cadastreUB;
CREATE DATABASE cadastreUB;
\c cadastreUB

\echo DROPS:
\i 'drop.sql'



\i 'cadastre\\cadastre.sql'
\i 'geolocalitzacio\\geolocalitzacio.sql'
\i 'inmoble\\inmoble.sql'
\i 'propietari\\propietari.sql'
\i 'sol\\sol.sql'
\i 'subtipus_inmoble\\subtipus_inmoble.sql'
\i 'tipus_inmoble\\tipus_inmoble.sql'
\i 'tipus_sol\\tipus_sol.sql'
\i 'us\\us.sql'
\i 'pertany\\pertany.sql'

\i 'inserts.sql'
