create table usuario (
	id int not null auto_increment,
	nome varchar(150) not null,
	email varchar(70) not null,
	login varchar(8) not null,
	senha varchar(10) not null,
	ativo tinyint(1) not null default 1,
	data_criacao datetime not null,
	data_atualizacao datetime null,
	primary key(id)
);

create table endereco (
	id_usuario int not null,
	cidade varchar(100) not null,
	bairro varchar(100) not null,
	estado varchar(100) not null,
	endereco varchar(150) not null,
	numero int null,
	complemento varchar(150) null,
	sem_numero tinyint(1) not null default 1,
	cep varchar(100) not null,
	primary key(id_usuario),
	foreign key(id_usuario) references usuario(id)
);

create table perfil_acesso (
	id int not null auto_increment,
	descricao varchar(50) not null,
	data_criacao date not null,
	data_inativacao date null,
	primary key(id)
);

create table usuario_has_perfil_acesso (
	id_usuario int not null,
	id_perfil_acesso int not null,
	data_inclusao datetime not null,
	data_remocao datetime null,
	primary key(id_usuario, id_perfil_acesso),
	foreign key(id_usuario) references usuario(id),
	foreign key(id_perfil_acesso) references perfil_acesso(id)
);