create sequence log.seq_entrega start 1 increment 1;

create table log.entrega (
    id int8 not null,
    data_localizacao timestamp,
    data_pedido timestamp not null,
    destinatario_bairro varchar(155) not null,
    destinatario_complemento varchar(155) not null,
    destinatario_logradouro varchar(255) not null,
    destinatario_nome varchar(155) not null,
    destinatario_numero varchar(55) not null,
    status_entrega varchar(50) not null,
    taxa numeric(10, 2) not null,
    cliente_id int8 not null,
    primary key (id)
);

alter table if exists log.entrega 
    add constraint fk_entrega_cliente 
    foreign key (cliente_id) 
    references log.cliente;