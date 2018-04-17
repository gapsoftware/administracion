SELECT * FROM gmsysadmin.menu;

USE gmsysadmin;
INSERT INTO `menu` (`menu_id`,`menu_active`,`menu_order`,`menu_name`) VALUES (null,1,2,'Administrar Menús');
INSERT INTO `menu` (`menu_id`,`menu_active`,`menu_order`,`menu_name`) VALUES (null,1,3,'Página en Mantención');
INSERT INTO `menu` (`menu_id`,`menu_active`,`menu_order`,`menu_name`) VALUES (null,1,4,'Respaldo');

INSERT INTO `menu` (`menu_id`,`menu_active`,`menu_order`,`menu_name`) VALUES (null,1,1,'Libro Mayor');
INSERT INTO `menu` (`menu_id`,`menu_active`,`menu_order`,`menu_name`) VALUES (null,1,2,'Libro Diario');
INSERT INTO `menu` (`menu_id`,`menu_active`,`menu_order`,`menu_name`) VALUES (null,1,3,'Comprobantes');

INSERT INTO `menu` (`menu_id`,`menu_active`,`menu_order`,`menu_name`) VALUES (null,1,1,'Datos Personales');
INSERT INTO `menu` (`menu_id`,`menu_active`,`menu_order`,`menu_name`) VALUES (null,1,2,'Datos asociados');
COMMIT;

INSERT INTO module_menu (module_id, menu_id) VALUE (23,1);
INSERT INTO module_menu (module_id, menu_id) VALUE (23,2);
INSERT INTO module_menu (module_id, menu_id) VALUE (23,3);
INSERT INTO module_menu (module_id, menu_id) VALUE (23,4);

INSERT INTO module_menu (module_id, menu_id) VALUE (22,5);
INSERT INTO module_menu (module_id, menu_id) VALUE (22,6);
INSERT INTO module_menu (module_id, menu_id) VALUE (22,7);

INSERT INTO module_menu (module_id, menu_id) VALUE (25,8);
INSERT INTO module_menu (module_id, menu_id) VALUE (25,9);
commit;