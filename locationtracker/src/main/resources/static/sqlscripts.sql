CREATE EXTENSION postgis; --To add postgis extension

SELECT postgis_full_version(); --To check the whether postgis is there or not


CREATE TABLE user_location_gis(
	id  SERIAL PRIMARY KEY NOT NULL,
	name TEXT NOT NULL,
	type TEXT NOT NULL,
	location point NOT NULL
); -- To create a table user_location_gis

ALTER TABLE user_location_gis ALTER COLUMN location TYPE geometry USING geometry(location);-- to alter the type

INSERT INTO public.user_location_gis(
	name, type, location)
	VALUES ('ramesh', 'USER','POINT(67.0123 49.1230)');

SELECT name,type,ST_AsText(location), ST_Distance(location,ST_Point(37.611100,55.756926)) as distance FROM user_location_gis
WHERE type ='TRACKER'
ORDER BY location <-> ST_Point(37.611100,55.756926)
LIMIT 3

SELECT name,type,ST_AsText(location) as location FROM user_location_gis
WHERE type ='TRACKER'
ORDER BY location <-> ST_Point(37.611100,55.756926)
LIMIT 3

SELECT name,type,ST_AsText(location), ST_Distance(location,ST_Point(37.611100,55.756926)) as distance FROM user_location_gis
WHERE type ='TRACKER' and ST_Distance(location,ST_Point(37.611100,55.756926))<30
ORDER BY location <-> ST_Point(37.611100,55.756926)
LIMIT 3