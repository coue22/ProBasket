set serveroutput on;
exec dbms_output.enable(10000000000);
		

DBMS_OUTPUT.PUT_LINE('Dando de alta las nuevas propiedades...');
INSERT INTO CONFIGURACION ( PARAMETRO, VALOR) VALUES ('temporada', '2012');
-- .................


INSERT INTO OPERACION ( CODOPERA, DESCRIPCION) VALUES ('0000000001', 'XXXXXXXXXXXXXXXX');
-- .................


--INSERT INTO PROP_CONFIG ( ID, CLAVE, VALOR, COMENTARIO, VISIBLE ) VALUES (1, 'org.quartz.scheduler.instanceName', 'DefaultQuartzScheduler', '#Nombre del planificador', 1); 
