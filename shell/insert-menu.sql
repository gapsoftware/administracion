SELECT * FROM gmsysadmin.module;
SELECT * FROM gmsysadmin.menu;
SELECT * FROM gmsysadmin.role;

INSERT INTO gmsysadmin.module VALUES (null,1,'ADMINISTRADOR',1);
INSERT INTO gmsysadmin.module VALUES (null,1,'CONTABILIDAD',1);
INSERT INTO gmsysadmin.module VALUES (null,3,'IDDAM',1);
COMMIT;

USE gmsysadmin;
INSERT INTO `menu` (`menu_id`,`active`,`sortorder`,`name`) VALUES (null,1,1,'Administrar Roles');
INSERT INTO `menu` (`menu_id`,`active`,`sortorder`,`name`) VALUES (null,1,2,'Página en Mantención');
INSERT INTO `menu` (`menu_id`,`active`,`sortorder`,`name`) VALUES (null,1,3,'Respaldo');

INSERT INTO `menu` (`menu_id`,`active`,`sortorder`,`name`) VALUES (null,1,1,'Libro Mayor');
INSERT INTO `menu` (`menu_id`,`active`,`sortorder`,`name`) VALUES (null,1,2,'Libro Diario');
INSERT INTO `menu` (`menu_id`,`active`,`sortorder`,`name`) VALUES (null,1,3,'Comprobantes');

INSERT INTO `menu` (`menu_id`,`active`,`sortorder`,`name`) VALUES (null,1,1,'Datos Personales');
INSERT INTO `menu` (`menu_id`,`active`,`sortorder`,`name`) VALUES (null,1,2,'Datos asociados');
COMMIT;

INSERT INTO module_menu (module_id, menu_id) VALUE (1,1);
INSERT INTO module_menu (module_id, menu_id) VALUE (1,2);
INSERT INTO module_menu (module_id, menu_id) VALUE (1,3);

INSERT INTO module_menu (module_id, menu_id) VALUE (2,4);
INSERT INTO module_menu (module_id, menu_id) VALUE (2,5);
INSERT INTO module_menu (module_id, menu_id) VALUE (2,6);

INSERT INTO module_menu (module_id, menu_id) VALUE (3,7);
INSERT INTO module_menu (module_id, menu_id) VALUE (3,8);
commit;

INSERT INTO gmsysadmin.menu_role VALUES (1,2);
INSERT INTO gmsysadmin.menu_role VALUES (2,2);
INSERT INTO gmsysadmin.menu_role VALUES (3,2);
INSERT INTO gmsysadmin.menu_role VALUES (4,4);
INSERT INTO gmsysadmin.menu_role VALUES (5,4);
INSERT INTO gmsysadmin.menu_role VALUES (6,4);
COMMIT;
