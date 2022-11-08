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



CREATE TABLE user_location_track_one(
	id  SERIAL PRIMARY KEY NOT NULL,
	userName TEXT,
	userEmail TEXT,
	tenantId TEXT,
	mobileNumber TEXT,
	updatedTimeStamp TEXT NOT NULL,
	location geometry NOT NULL
);

SELECT *,ST_AsText(location), ST_Distance(location,ST_Point(37.611100,55.756926)) as distance FROM user_location_track_one
WHERE ST_Distance(location,ST_Point(37.611100,55.756926))<30
ORDER BY location <-> ST_Point(37.611100,55.756926)
LIMIT 3

UPDATE public.user_location_track_one
	SET location='POINT(61.0123 45.123)'
	WHERE id=1;

SELECT *,ST_AsText(location), ST_DistanceSphere(location,ST_Point(67.0128,49.1234))/1000 as distance FROM user_location_track_one
--WHERE ST_DistanceSphere(location,ST_Point(67.0128,49.1234))<100*1000
ORDER BY location <-> ST_Point(67.0128,49.1234)
--LIMIT 3

--Geography table
CREATE TABLE user_location_track_two AS
SELECT
id,
username,
useremail,
tenantid,
mobilenumber,
updatedtimestamp ,
ST_Transform(ST_SetSRID(location,25832),4326)::geography AS location
FROM user_location_track_one;
 --Must be in POINT(longitude, latitude)

--POINT
 INSERT INTO public.user_location_track_three (username,useremail,tenantid,mobilenumber,updatedtimestamp,"location") VALUES
 	 ('hari','hari@nslhub.com','paas3',NULL,'1667815481000',POINT (67.0128, 49.1234)),
 	 ('sonu','sonu@nslhub.com','paas3',NULL,'1667815515000',POINT (69.0128, 48.1234)),
 	 ('hitesh','hitesh@nslhub.com','paas3',NULL,'1667815552000',POINT (68.0128, 49.2234)),
 	 ('kaushik','kaushik@nslhub.com','paas3',NULL,'1667815598000',POINT (61.0128 ,40.2234)),
 	 ('vipin','vipin@nslhub.com','paas3',NULL,'1667815638000',POINT (51.0128, 42.2234)),
 	 ('rai','rai@nslhub.com','paas3',NULL,'1667815680000',POINT (49.0128, 48.2234)),
 	 ('yerra','yerra@nslhub.com','paas3',NULL,'1667815716000',POINT (51.0128, 45.2234)),
 	 ('shiva','shiva@nslhub.com','paas3',NULL,'1667815415000',POINT (61.0123, 45.123));