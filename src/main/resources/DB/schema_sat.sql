/*
use master;

DROP DATABASE BD_SAT;

CREATE DATABASE BD_SAT
GO
*/


CREATE TABLE menu (
	id_menu char(4),
	contenedor_menu char(4),
	orden_aparicion_menu numeric(4,0),
	nombre_opcion_menu varchar(100),
	formulario_asociado_menu varchar(150),
	estado_registro_menu char(1),
	CONSTRAINT pk_menu PRIMARY KEY (id_menu),
	CONSTRAINT fk_menu_menu FOREIGN KEY (contenedor_menu) REFERENCES menu(id_menu)
);

CREATE TABLE puesto (
	id_puesto char(4),
	descripcion_puesto varchar(80),
	descripcion_corta_puesto varchar(40),
	estado_puesto char(1),
	CONSTRAINT pk_puesto PRIMARY KEY (id_puesto)
);

CREATE TABLE gerencia_central (
	id_gerencia_c char(4),
	codigo_gerencia_c_propio char(4),
	descripcion_gerencia_c varchar(80),
	descripcion_corta_gerencia_c varchar(40),
	estado_gerencia_c char(1),
	CONSTRAINT pk_gerenciacentral PRIMARY KEY (id_gerencia_c)
);

CREATE TABLE gerencia (
	id_gerencia_c char(4),
	id_gerencia char(4),
	codigo_gerencia_propio char(4),
	descripcion_gerencia varchar(80),
	descripcion_corta_gerencia varchar(40),
	estado_gerencia char(1),
	CONSTRAINT pk_gerencia PRIMARY KEY (id_gerencia_c, id_gerencia),
	CONSTRAINT fk_gerencia_gerenciacentral FOREIGN KEY (id_gerencia_c) REFERENCES gerencia_central(id_gerencia_c)
);

CREATE TABLE area_funcional (
	id_gerencia_c char(4),
	id_gerencia char(4),
	id_area_funcional char(4),
	codigo_area_func_centro_costo_propio char(4),
	descripcion_area_func varchar(80),
	descripcion_corta_area_func varchar(40),
	estado_area_func char(1),
	CONSTRAINT pk_areafuncional PRIMARY KEY (id_gerencia_c, id_gerencia, id_area_funcional),
	CONSTRAINT fk_areafuncional_gerencia FOREIGN KEY (id_gerencia_c, id_gerencia) REFERENCES gerencia(id_gerencia_c, id_gerencia)
);

CREATE TABLE perfil (
	id_perfil char(4),
	descripcion_perfil varchar(80),
	descripcion_corta_perfil varchar(40),
	estado_perfil char(1),
	CONSTRAINT pk_perfil PRIMARY KEY (id_perfil)
);

CREATE TABLE r_perfil_menu (
	id_perfil char(4),
	id_menu char(4),
	CONSTRAINT pk_rperfilmenu PRIMARY KEY (id_perfil, id_menu),
	CONSTRAINT fk_rperfilmenu_perfil FOREIGN KEY (id_perfil) REFERENCES perfil(id_perfil),
	CONSTRAINT fk_rperfilmenu_menu FOREIGN KEY (id_menu) REFERENCES menu(id_menu)
);

CREATE TABLE usuario (
	id_usuario char(5),
	codigo_usuario varchar(15),
	nombre_usuario varchar(100),
	palabra_clave_usuario varchar(15),
	cuenta_correo_usuario varchar(100),
	gerencia_c_pertenece_usuario char(4),
	gerencia_pertenece_usuario char(4),
	area_func_pertenece_usuario char(4),
	id_perfil_usuario char(4),
	id_puesto_usuario char(4),
	estado_usuario char(1),
	CONSTRAINT pk_usuario PRIMARY KEY (id_usuario),
	CONSTRAINT fk_usuario_areafuncional FOREIGN KEY (gerencia_c_pertenece_usuario, gerencia_pertenece_usuario, area_func_pertenece_usuario) REFERENCES area_funcional(id_gerencia_c, id_gerencia, id_area_funcional),
	CONSTRAINT fk_usuario_perfil FOREIGN KEY (id_perfil_usuario) REFERENCES perfil(id_perfil),
	CONSTRAINT fk_usuario_puesto FOREIGN KEY (id_puesto_usuario) REFERENCES puesto(id_puesto)
);
CREATE TABLE r_usuario_ambito_responsabilidad (
	id_usuario_ambito char(5),
	gerencia_c_ambito char(4),
	gerencia_ambito char(4),
	area_func_ambito char(4),
	CONSTRAINT pk_rusuarioambitoresponsabilidad PRIMARY KEY (id_usuario_ambito, gerencia_c_ambito, gerencia_ambito, area_func_ambito),
	CONSTRAINT fk_rusuarioambitoresponsabilidad_usuario FOREIGN KEY (id_usuario_ambito) REFERENCES usuario(id_usuario),
	CONSTRAINT fk_rusuarioambitoresponsabilidad_areafuncional FOREIGN KEY (gerencia_c_ambito, gerencia_ambito, area_func_ambito) REFERENCES area_funcional(id_gerencia_c, id_gerencia, id_area_funcional)
);

CREATE TABLE general_sistema(
id_gen_sis char(5),
nombre_grupo_gen_sis varchar(40),
nivel_gen_sis char(1),
orden_gen_sis char(4),
valor_1_gen_sis varchar(40),
valor_2_gen_sis varchar(40),
valor_3_gen_sis varchar(40),
valor_4_gen_sis varchar(40),
valor_5_gen_sis varchar(40),
valor_6_gen_sis varchar(40),
CONSTRAINT pk_general_sistema PRIMARY KEY (id_gen_sis)
);

CREATE TABLE tipo_tema (
id_tipo_tema char(1),
nombre_tipo_tema char(80),
siglas_tipo_tema char(2),
CONSTRAINT pk_tipo_tema PRIMARY KEY (id_tipo_tema)
);

CREATE TABLE tema(
id_tema char(10),
nombre_tema varchar(150),
general_central_involucrada char(4),
naturaleza_tema char(1),
fecha_inicio_tema DATE,
fecha_fin_tema DATE,
dias_utiles_tema char(4),
avance_completado_tema numeric(5,2),
avance_planeado_tema numeric(5,2),
estado_tema char(1),
id_tipo_tema char(1),
CONSTRAINT pk_id_tema PRIMARY KEY (id_tema),
CONSTRAINT fk_gerencia_central FOREIGN KEY (general_central_involucrada) REFERENCES gerencia_central(id_gerencia_c),
CONSTRAINT fk_id_tipo_tema FOREIGN KEY (id_tipo_tema) REFERENCES tipo_tema(id_tipo_tema)
);