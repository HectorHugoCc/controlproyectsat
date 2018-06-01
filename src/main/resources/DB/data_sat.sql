INSERT INTO menu(id_menu, contenedor_menu, orden_aparicion_menu, nombre_opcion_menu, formulario_asociado_menu, estado_registro_menu)
VALUES ('0001','0001',10,'Maestro','#','1'),
    ('0002','0001',10,'Menú del Sistema','/view/administrador/menu.xhtml','1'),
    ('0003','0001',20,'Tabla General','/view/administrador/general.xhtml','1'),
    ('0004','0001',30,'Gerencia','/view/administrador/gerencia.xhtml','1'),
    ('0005','0001',40,'Perfil','/view/administrador/perfiles.xhtml','1'),
    ('0006','0001',50,'Puesto','/view/administrador/puesto.xhtml','1'),
    ('0007','0001',60,'Usuario','/view/administrador/usuario.xhtml','1'),
    ('0008','0001',70,'Tipo Tema','/view/administrador/tipotema.xhtml','1'),
    ('0009','0001',80,'Tema','/view/administrador/tema.xhtml','1'),
    ('0010','0010',20,'Autorizaciones','#','1'),
    ('0011','0010',10,'Perfil Vs Opción Menu','/view/administrador/perfilvsmenu.xhtml','1'),
    ('0012','0012',30,'Gestión de Necesidades','#','1'),
    ('0013','0012',10,'Crear Necesidades','/view/administrador/necesidades.xhtml','1');
          	
INSERT INTO puesto(
	id_puesto, descripcion_puesto, descripcion_corta_puesto, estado_puesto)
	VALUES ('0001','Puesto 1','pu 1','1'),
          ('0002','Puesto 2','pu 2','1'),
          ('0003','Puesto 3','pu 3','1'),
          ('0004','Puesto 4','pu 4','1');
                    
INSERT INTO gerencia_central(
	id_gerencia_c, codigo_gerencia_c_propio, descripcion_gerencia_c, descripcion_corta_gerencia_c, estado_gerencia_c)
	VALUES ('0001','1111','Gerencia Central 1','GC1','1'),
          ('0002','2222','Gerencia Central 2','GC2','1'),
          ('0003','3333','Gerencia Central 3','GC3','1');
          
INSERT INTO gerencia(
	id_gerencia_c, id_gerencia, codigo_gerencia_propio, descripcion_gerencia, descripcion_corta_gerencia, estado_gerencia)
	VALUES ('0001','0001','1100','Gerencia 1','G1','1'),
          ('0002','0002','1111','Gerencia 2','G2','1'),
          ('0003','0003','1122','Gerencia 3','G3','1');
          
INSERT INTO area_funcional(
	id_gerencia_c, id_gerencia, id_area_funcional, codigo_area_func_centro_costo_propio, descripcion_area_func, descripcion_corta_area_func, estado_area_func)
	VALUES ('0001','0001','0001','2211','Area Funcional 1','AF1','1'),
          ('0002','0002','0002','2222','Area Funcional 2','AF2','1'),
          ('0003','0003','0003','2233','Area Funcional 3','AF3','1');
         
INSERT INTO perfil(
	id_perfil, descripcion_perfil, descripcion_corta_perfil, estado_perfil)
	VALUES ('0001','Administrador','admin','1'),
          ('0002','Usuario1','usu1','1'),
          ('0003','Usuario2','usu2','1'),
          ('0004','Usuario3','usu3','1'),
		  ('0005','Usuario4','usu4','1');
		  
INSERT INTO r_perfil_menu(
	id_menu, id_perfil)
	VALUES ('0001','0001'),('0002','0001'),('0003','0001'),('0004','0001'),('0005','0001'),
  ('0006','0001'),('0007','0001'),('0008','0001'),('0009','0001'),('0010','0001'),
  ('0011','0001'),('0012','0001'),('0013','0001'),
  ('0010','0002'),('0011','0002'),('0012','0002'),('0013','0002'),
  ('0010','0003'),('0011','0003'),('0012','0003'),('0013','0003'),
  ('0010','0004'),('0011','0004'),('0012','0004'),('0013','0004');
          
INSERT INTO usuario(
	id_usuario, codigo_usuario, nombre_usuario, palabra_clave_usuario, cuenta_correo_usuario, gerencia_c_pertenece_usuario, gerencia_pertenece_usuario, area_func_pertenece_usuario, id_perfil_usuario, id_puesto_usuario, estado_usuario)
	VALUES 
  ('00001','admin','Jose Carlos','123','admin@gmail.com','0001','0001','0001','0001','0001','1'),
  ('00002','user1','Ana Maria','123','user1@gmail.com','0002','0002','0002','0002','0002','1'),
  ('00003','user2','Julio andrés','123','user2@gmail.com','0003','0003','0003','0003','0003','1'),
  ('00004','user3','Pepe Lucho','123','user3@gmail.com','0003','0003','0003','0004','0004','1'),
  ('00005','user4','San Martin','123','user4@gmail.com','0002','0002','0002','0005','0004','1');
  
INSERT INTO r_usuario_ambito_responsabilidad(
	area_func_ambito, gerencia_c_ambito, gerencia_ambito, id_usuario_ambito)
	VALUES ('0001','0001','0001','00001'),
          ('0002','0002','0002','00002'),
          ('0003','0003','0003','00003'),
          ('0003','0003','0003','00004'),
          ('0002','0002','0002','00005');	

INSERT INTO general_sistema(id_gen_sis, nombre_grupo_gen_sis,nivel_gen_sis,orden_gen_sis ,valor_1_gen_sis ,valor_2_gen_sis ,valor_3_gen_sis ,valor_4_gen_sis ,valor_5_gen_sis ,valor_6_gen_sis)
VALUES 
('00001','Estado de Registro','1','0001','Codigo','Descripcion',null,null,null,null),
('00002','Estado de Registro','2','0001','0','Inactivo',null,null,null,null),
('00003','Estado de Registro','2','0002','1','Activo',null,null,null,null),
('00004','Naturaleza del Tema','1','0001','Codigo Naturaleza','Descripcion',null,null,null,null),
('00005','Naturaleza del Tema','2','0001','1','Tributario',null,null,null,null),
('00006','Naturaleza del Tema','2','0002','2','No Tributario',null,null,null,null);

INSERT INTO  tipo_tema (id_tipo_tema ,nombre_tipo_tema ,siglas_tipo_tema)
VALUES
('1','Tipo Tema 1','T1'),
('2','Tipo Tema 2','T2'),
('3','Tipo Tema 3','T3');
	
INSERT INTO tema(id_tema ,nombre_tema ,general_central_involucrada ,naturaleza_tema ,fecha_inicio_tema ,fecha_fin_tema ,dias_utiles_tema ,avance_completado_tema ,avance_planeado_tema ,estado_tema ,id_tipo_tema)
VALUES
('T120180001','Tema 1','0001','1','2018-05-26','2018-06-26','0030',50.00,70.00,'P','1'),
('T220180002','Tema 2','0002','1','2018-04-26','2018-07-26','0060',50.00,70.00,'P','2'),
('T320180003','Tema 3','0003','1','2018-05-24','2018-06-24','0030',50.00,70.00,'P','3');
	
	
	
	
	
	
	
	
	
	