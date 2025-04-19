-- Insertar Clientes
INSERT INTO cliente (id, tipo_identificacion, numero_identificacion, nombres, correo, celular)
VALUES (1, 'DNI', '0505555555', 'Daniel Sandoval', 'dani@test.com', '0999999999');

INSERT INTO cliente (id, tipo_identificacion, numero_identificacion, nombres, correo, celular)
VALUES (2, 'RUC', '050555555501', 'Marcelo Sandoval', 'marcelo@test.com', '0999999999');

-- Insertar Direcciones
INSERT INTO direccion (id, provincia, ciudad, direccion, es_matriz, cliente_id)
VALUES (1, 'Cotopaxi', 'Salcedo', 'Calle Ana paredes', true, 1);

INSERT INTO direccion (id, provincia, ciudad, direccion, es_matriz, cliente_id)
VALUES (2, 'Cotopaxi', 'Latacunga', 'el salto', true, 2);
