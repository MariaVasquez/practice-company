USE db_company;

DROP PROCEDURE IF EXISTS insertar_datos_desde_tmp;

DELIMITER $$

CREATE PROCEDURE insertar_datos_desde_tmp()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE codigo VARCHAR(50);
    DECLARE nombre_company VARCHAR(100);
    DECLARE descripcion_company VARCHAR(255);
    DECLARE versionname VARCHAR(50);
    DECLARE versiondescription VARCHAR(100);
    DECLARE company_id INT;
    DECLARE version_id INT;
    DECLARE appid INT;
    DECLARE versioncompanydescription VARCHAR(100);
    DECLARE appcode VARCHAR(255);
    DECLARE appname VARCHAR(50);
    DECLARE appdescription VARCHAR(100);

    DECLARE cTemp CURSOR FOR 
        SELECT codigo_company, name_company, description_company,
        version, version_description,
        company_id, version_id, version_company_description,
        app_id, app_code, app_name, app_description
		FROM TMP_LLENAR_CAMPOS;
        
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
     DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        ROLLBACK;
    END;
    
    START TRANSACTION;

    OPEN cTemp;

    read_loop: LOOP
        FETCH cTemp INTO codigo, nombre_company, descripcion_company,
        versionname, versiondescription,
        company_id, version_id, versioncompanydescription,
        appid, appcode, appname, appdescription;
        IF done THEN
            LEAVE read_loop;
        END IF;

        INSERT INTO application(app_code,app_name,app_description)
		VALUES(appcode,appname,appdescription);
        
        SET appid = LAST_INSERT_ID();
		
        INSERT INTO version (app_id,version,version_description)
		VALUES(appid,versionname,versiondescription);
        
        SET version_id = LAST_INSERT_ID();
        
        INSERT INTO company(codigo_company, name_company, description_company)
        VALUES (codigo, nombre_company, descripcion_company);
        
        SET company_id = LAST_INSERT_ID();
        
        INSERT INTO version_company (company_id,version_id,version_company_description)
		VALUES(company_id,version_id,versioncompanydescription);
		
    END LOOP;

    CLOSE cTemp;
    
    COMMIT;

END$$

DELIMITER ;

CALL insertar_datos_desde_tmp()
