USE ciudadanias_europeas;

INSERT INTO `ciudadanias_europeas`.`tipo_documento` (`nombre`, `descripcion`) VALUES ('Partida', 'De nacimiento, matrimonio, defunción.');
INSERT INTO `ciudadanias_europeas`.`tipo_documento` (`nombre`, `descripcion`) VALUES ('Apostille', 'Documento necesario para el consulado.');
INSERT INTO `ciudadanias_europeas`.`tipo_documento` (`nombre`, `descripcion`) VALUES ('Anexo', 'Datos extra para trámites.');

INSERT INTO `ciudadanias_europeas`.`rol` (`nombre`, `descripcion`) VALUES ('operador', 'Sólo operaciones diarias.');
INSERT INTO `ciudadanias_europeas`.`rol` (`nombre`, `descripcion`) VALUES ('administrador', 'Operaciones diarias, validaciones y reportes.');

INSERT INTO `ciudadanias_europeas`.`pais` (`nombre`) VALUES ('España');
INSERT INTO `ciudadanias_europeas`.`pais` (`nombre`) VALUES ('Italia');

INSERT INTO `ciudadanias_europeas`.`consulado` (`id_pais`, `domicilio`, `ciudad`, `provincia`) VALUES (1, 'Guido 1770', 'CABA', 'Buenos Aires');
INSERT INTO `ciudadanias_europeas`.`consulado` (`id_pais`, `domicilio`, `ciudad`, `provincia`) VALUES (2, 'Reconquista 572', 'CABA', 'Buenos Aires');

INSERT INTO `ciudadanias_europeas`.`estado_tramite` (`nombre`, `descripcion`) VALUES ('Iniciado', 'El cliente se comunicó y realizó el pago inicial.');
INSERT INTO `ciudadanias_europeas`.`estado_tramite` (`nombre`, `descripcion`) VALUES ('En curso', 'El cliente presentó documentos y se están validando.');
INSERT INTO `ciudadanias_europeas`.`estado_tramite` (`nombre`, `descripcion`) VALUES ('Enviado', 'Se han enviado los documentos a los respectivos consulados');
INSERT INTO `ciudadanias_europeas`.`estado_tramite` (`nombre`, `descripcion`) VALUES ('Finalizado', 'Se recibó el pasaporte por parte del consulado');

INSERT INTO `ciudadanias_europeas`.`tipo_tramite` (`nombre`, `descripcion`) VALUES ('Rectificación', 'Solicitud de modificación de partidas.');
INSERT INTO `ciudadanias_europeas`.`tipo_tramite` (`nombre`, `descripcion`) VALUES ('Ciudadanía', 'Solicitud de ciudadanía');

INSERT INTO `ciudadanias_europeas`.`cliente` (`dni`, `nombre`, `apellido`, `email`) VALUES (12345678, 'Cliente uno', 'Pérez', 'cuperez@gmail.com');
INSERT INTO `ciudadanias_europeas`.`cliente` (`dni`, `nombre`, `apellido`, `email`) VALUES (98765432, 'Cliente Dos', 'Torres', 'cdtorres@gmail.com');

INSERT INTO `ciudadanias_europeas`.`usuario` (`dni`, `nombre`, `apellido`, `id_rol`) VALUES (11223344, 'Empleado', 'López', 1);
INSERT INTO `ciudadanias_europeas`.`usuario` (`dni`, `nombre`, `apellido`, `id_rol`) VALUES (55667788, 'Administrador', 'Gómez', 2);

INSERT INTO `ciudadanias_europeas`.`tipo_documento` (`nombre`, `descripcion`) VALUES ('Anexo', 'Anexo con informacoión extra');
INSERT INTO `ciudadanias_europeas`.`tipo_documento` (`nombre`, `descripcion`) VALUES ('Apostille', 'Apostille enviado desde el país origen.');

INSERT INTO `ciudadanias_europeas`.`consulado` (`id_pais_fk`, `domicilio`, `ciudad`, `provincia`) VALUES (1, 'Guido 1770', 'CABA', 'Buenos Aires');
INSERT INTO `ciudadanias_europeas`.`consulado` (`id_pais_fk`, `domicilio`, `ciudad`, `provincia`) VALUES (2, 'Av. Vélez Sarsfield 360', 'Córdoba', 'Córdoba');


INSERT INTO `ciudadanias_europeas`.`tramite` (`importe`, `id_consulado`, `id_tipo_tramite`) VALUES (10.00, 1, 2);

INSERT INTO `ciudadanias_europeas`.`detalle_tramite` (`fecha_inicio`, `id_estado_tramite`, `id_tramite`) VALUES (CURDATE(), 1, 1);

