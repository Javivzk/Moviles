--Consulta de Insercion. Inserta un movil nuevo
CREATE OR REPLACE PROCEDURE insertar_movil(
referencia IN MOVIL.REFERENCIA%type,
marca IN MOVIL.MARCA%type,
modelo IN MOVIL.MODELO%type,
color IN MOVIL.COLOR%type,
precioBase IN MOVIL.PRECIOBASE%type)
IS
BEGIN
    INSERT INTO MOVIL(REFERENCIA, MARCA, MODELO, COLOR, PRECIOBASE) 
    VALUES (referencia,marca,modelo,color,precioBase);
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
    ROLLBACK;
END insertar_movil;
/

EXECUTE INSERTAR_MOVIL('364986585278876','Poco','F3','Negro',250);

--Consulta de modificacion. Modifica el MOVIL por la referencia.
CREATE OR REPLACE PROCEDURE modifica_movil(
nreferencia IN MOVIL.REFERENCIA%type,
nmarca IN MOVIL.MARCA%type,
nmodelo IN MOVIL.MODELO%type,
ncolor IN MOVIL.COLOR%type,
nprecioBase IN MOVIL.PRECIOBASE%type)
IS
BEGIN
    UPDATE MOVIL 
    SET MARCA = nmarca, MODELO = nmodelo, COLOR = ncolor, PRECIOBASE = nprecioBase
    WHERE REFERENCIA = nreferencia;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
    ROLLBACK;
END modifica_movil;
/ 

EXECUTE MODIFICA_MOVIL('364986585278876','PocoUPDATE','F3','Negro',250);

--Consulta de listado agrupado. Muestra el numero de moviles por marca.
SELECT COUNT(MOVIL.MARCA) as NumeroMoviles, MOVIL.MARCA FROM MOVIL GROUP BY MOVIL.MARCA;

--Crear una consulta que muestre un listado de registros que contengan una cierta cadena.
SELECT MARCA, MODELO, COLOR FROM MOVIL WHERE UPPER(MARCA) LIKE UPPER('%apple%');

--Para finalizar, crea un procedimiento almacenado que realice el borrado de parte de la información almacenada en una tabla secundaria, donde el/los criterios de borrado se obtengan de una tabla principal.
CREATE OR REPLACE PROCEDURE borrar_citas (DNICLIENTE IN VARCHAR) AS
BEGIN
    DELETE FROM CITAS
    WHERE ID_USUARIO = (SELECT CLIENTE.ID_USUARIO FROM CLIENTE
        JOIN USUARIO ON CLIENTE.ID_USUARIO = USUARIO.ID_USUARIO
        JOIN CITAS ON USUARIO.ID_USUARIO = CITAS.ID_USUARIO
        WHERE CLIENTE.DNI = DNICLIENTE
        );
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN 
        ROLLBACK;
END;
/

EXECUTE BORRAR_CITAS('43786604D');