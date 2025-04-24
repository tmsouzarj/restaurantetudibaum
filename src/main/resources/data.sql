insert into usuario (nome, email, login, senha, ativo, data_criacao, data_atualizacao) values ('Thiago Motta', 'thiago@restaurantetudibaum.com', 'us000001', '1234', 1, current_timestamp, null);
insert into usuario (nome, email, login, senha, ativo, data_criacao, data_atualizacao) values ('Usuario 2', 'usuario2@restaurantetudibaum.com', 'us000002', '1234', 1, current_timestamp, null);
insert into usuario (nome, email, login, senha, ativo, data_criacao, data_atualizacao) values ('Usuario 3', 'usuario3@restaurantetudibaum.com', 'us000003', '1234', 0, current_timestamp, current_timestamp);
insert into usuario (nome, email, login, senha, ativo, data_criacao, data_atualizacao) values ('Usuario 4', 'usuario4@restaurantetudibaum.com', 'us000004', '1234', 0, current_timestamp, current_timestamp);

insert into endereco (id_usuario, cidade, bairro, estado, endereco, numero, complemento, sem_numero, cep) 
values (1, 'São Gonçalo', 'Nova Cidade', 'Rio de Janeiro', 'Rua Aquidabã', '79', 'Casa 8', 1, '24455450');

insert into endereco (id_usuario, cidade, bairro, estado, endereco, numero, complemento, sem_numero, cep) 
values (2, 'São Gonçalo', 'Nova Cidade', 'Rio de Janeiro', 'Rua Aquidabã', null, null, 0, '24455450');

insert into perfil_acesso (descricao, data_criacao) values ('Administrador', current_date);
insert into perfil_acesso (descricao, data_criacao) values ('Informatica', current_date);
insert into perfil_acesso (descricao, data_criacao) values ('Supervisor', current_date);
insert into perfil_acesso (descricao, data_criacao) values ('Atendente', current_date);

insert into usuario_has_perfil_acesso (id_usuario, id_perfil_acesso, data_inclusao) values (1, 1, current_timestamp);
insert into usuario_has_perfil_acesso (id_usuario, id_perfil_acesso, data_inclusao) values (1, 2, current_timestamp);
insert into usuario_has_perfil_acesso (id_usuario, id_perfil_acesso, data_inclusao) values (2, 3, current_timestamp);
insert into usuario_has_perfil_acesso (id_usuario, id_perfil_acesso, data_inclusao) values (3, 4, current_timestamp);
