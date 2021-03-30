INSERT INTO Niveis_acesso(role) VALUES ('ROLE_USER');
INSERT INTO Usuarios (email,senha,instante_cadastro) VALUES('email@email','123456',CURRENT_TIMESTAMP);
INSERT INTO usuarios_niveis_de_acesso (usuario_id, niveis_de_acesso_role) values (1, 'ROLE_USER');
