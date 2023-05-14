CREATE DATABASE `peliculasData`;
CREATE DATABASE `usuariosData`;

INSERT INTO `peliculasData`.`pelicula` (`anio`, `direccion`, `duracion`, `genero`, `pais`, `portada`, `sinopsis`, `titulo`) VALUES ('2022', 'Azahara Ballester', '120', 'Comedia', 'España', '1188874.jpg', 'Elegante film cinematográfico con el que pasar un buen rato en familia', 'Un show'); 

INSERT INTO `peliculasData`.`pelicula` (`anio`, `direccion`, `duracion`, `genero`, `pais`, `portada`, `sinopsis`, `titulo`) VALUES ('2035', 'Patricia Gomez', '136', 'Drama', 'Francia', '1188879.jpg', 'La historia que emocionó a Steven Spielberg', 'La mejor'); 

 

INSERT INTO `peliculasData`.`actor` (`fecha_nac`, `nacionalidad`, `nombre`) VALUES ('2000-01-29', 'Española', 'Azahara Ballester'); 

INSERT INTO `peliculasData`.`actor` (`fecha_nac`, `nacionalidad`, `nombre`) VALUES ('2001-05-18', 'Española', 'Patricia Gomez'); 

 

INSERT INTO `peliculasData`.pelicula_actor (pelicula_id, actor_id) VALUES (1,1); 

INSERT INTO `peliculasData`.pelicula_actor (pelicula_id, actor_id) VALUES (2,1); 

INSERT INTO `peliculasData`.pelicula_actor (pelicula_id, actor_id) VALUES (2,2); 


INSERT INTO `usuariosData`.`usuarios` (`password`, `correo`, `enable`, `username`) VALUES ('contrasenia', 'usuario@email.com',0, 'usuario');
INSERT INTO `usuariosData`.`usuarios` (`password`, `correo`, `enable`, `username`) VALUES ('admin', 'admin@admin.com', 1, 'admin');


INSERT INTO `usuariosData`.roles (rol) VALUES ('admin'); 
INSERT INTO `usuariosData`.roles (rol) VALUES ('user'); 


INSERT INTO `usuariosData`.usuarios_roles (usuarios_id_usuario, roles_id_rol) VALUES (2,1); 

INSERT INTO `usuariosData`.usuarios_roles (usuarios_id_usuario, roles_id_rol) VALUES (1,2); 


INSERT INTO `usuariosData`.`criticas` (`fecha`, `id_pelicula`, `puntuacion`, `usuarios_id_usuario`) VALUES ('2000-01-29', 1, '7.4', 1);
INSERT INTO `usuariosData`.`criticas` (`fecha`, `id_pelicula`, `puntuacion`, `usuarios_id_usuario`) VALUES ('2000-05-18', 2, '8.9', 1);
INSERT INTO `usuariosData`.`criticas` (`fecha`, `id_pelicula`, `puntuacion`, `usuarios_id_usuario`) VALUES ('2000-01-29', 2, '9.0', 2);

