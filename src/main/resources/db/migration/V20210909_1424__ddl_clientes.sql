create sequence hibernate_sequence;

-- ###########################################################################

-- public.tb_perfil definition

create sequence seq_perfil;
--alter sequence seq_perfil owner to postgres;
create table tb_perfil
(
    id bigint not null
        constraint tb_perfil_pkey   primary key,
    descricao                           varchar(255)
);

-- ###########################################################################

-- public.tb_usuario definition

create sequence seq_usuario;
--alter sequence seq_usuario owner to postgres;
create table tb_usuario
(
    id bigint not null
        constraint tb_usuario_pkey       primary key,
    email                        varchar(255) unique,
    senha                               varchar(255),
    fk_perfil    bigint
        constraint ttb_usuario_perfil_fkey references tb_perfil
);

-- ###########################################################################

-- public.tb_cliente definition

create sequence seq_cliente;
--alter sequence seq_dados_pessoais owner to postgres;
create table tb_cliente
(
    id bigint not null
        constraint tb_cliente_pkey   primary key,
    nome                               varchar(255),
    mae                                varchar(255),
    email                       varchar(255) unique,
    data_nascimento                    date not null,
    estado_civil                        varchar(255),
    rg                                 varchar(255),
    cpf                       varchar(255) not null,
    salario                            varchar(255),
    fk_usuario    bigint not null
        constraint tb_cliente_usuario_fkey references tb_usuario
);

-- ###########################################################################

-- public.tb_anexos_docs definition

create sequence seq_anexos_docs;
--alter sequence seq_anexos_docs owner to postgres;
create table tb_anexos_docs
(
    id bigint not null
        constraint tb_anexos_docs_pkey   primary key,
    path_arquivo                          varchar(255),
    nome_doc                          varchar(255),
    fk_cliente    bigint
        constraint tb_anexos_docs_cliente_fkey references tb_cliente
);

-- ###########################################################################

-- public.tb_proposta_credito definition

create sequence seq_proposta_credito;
--alter sequence seq_proposta_credito owner to postgres;
create table tb_proposta_credito
(
    id bigint not null
        constraint tb_proposta_credito_pkey   primary key,
    status                                  varchar(255),
    data_inicio_proposta                            date,
    fk_cliente    bigint
        constraint tb_proposta_credito_cliente_fkey references tb_cliente
);