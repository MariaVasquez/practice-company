CREATE TABLE db_company.application (
	app_code varchar(100) NOT NULL,
	app_name varchar(100) NOT NULL,
	app_description varchar(100) NOT NULL,
	app_id int auto_increment NOT NULL PRIMARY KEY
)

CREATE TABLE db_company.company (
	id_company INT auto_increment NOT NULL PRIMARY KEY,
	codigo_company varchar(100) NOT NULL,
	name_company varchar(100) NOT NULL,
	description_company varchar(100) NOT NULL
)

CREATE TABLE db_company.version (
	version_id INT auto_increment NOT NULL PRIMARY KEY,
	app_id INT NOT NULL,
	version varchar(100) NOT NULL,
	version_description varchar(100) NOT NULL
)

ALTER TABLE db_company.version ADD CONSTRAINT version_application_FK FOREIGN KEY (`app_ id`) REFERENCES db_company.application(app_id);


CREATE TABLE db_company.version_company (
	version_company_id INT NOT NULL,
	company_id INT NOT NULL,
	version_id INT NOT NULL,
	version_company_description varchar(100) NOT NULL,
	CONSTRAINT version_company_pk PRIMARY KEY (version_company_id),
	CONSTRAINT version_company_version_FK FOREIGN KEY (version_id) REFERENCES db_company.version(version_id),
	CONSTRAINT version_company_company_FK FOREIGN KEY (company_id) REFERENCES db_company.company(id_company)
)

ALTER TABLE db_company.version_company
MODIFY COLUMN version_company_id INT NOT NULL AUTO_INCREMENT;

CREATE TABLE db_company.TMP_LLENAR_CAMPOS (
id_company INT,
codigo_company varchar(100),
name_company varchar(100),
description_company varchar(100),
version varchar(100),
version_description varchar(100),
version_company_id INT,
company_id INT,
version_id INT,
version_company_description varchar(100),
app_id INT,app_code varchar(100),
app_name varchar(100),
app_description varchar(100) );

ALTER TABLE version
ADD CONSTRAINT fk_version_application
FOREIGN KEY (app_id)
REFERENCES application(app_id);