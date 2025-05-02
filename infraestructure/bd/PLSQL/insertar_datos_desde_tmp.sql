
USE db_company;

DROP PROCEDURE IF EXISTS insertar_datos_desde_tmp;

DELIMITER $$

CREATE PROCEDURE insertar_datos_desde_tmp()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE codigo_company VARCHAR(50);
    DECLARE nombre_company VARCHAR(100);
    DECLARE descripcion_company VARCHAR(255);

    DECLARE cTemp CURSOR FOR
        SELECT codigo_company, nombre_company, descripcion_company
        FROM TMP_LLENAR_CAMPOS;

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    OPEN cTemp;

    read_loop: LOOP
        FETCH cTemp INTO codigo_company, nombre_company, descripcion_company;
        IF done THEN
            LEAVE read_loop;
        END IF;

        INSERT INTO company(codigo_company, name_company, description_company)
        VALUES (codigo_company, nombre_company, descripcion_company);

    END LOOP;

    CLOSE cTemp;
END$$

DELIMITER ;

SHOW PROCEDURE STATUS WHERE Db = 'db_company';

CALL insertar_datos_desde_tmp();