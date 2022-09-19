create sequence log.seq_ocorrencia start 1 increment 1;

create table log.ocorrencia (
    id int8 not null,
    data_registro timestamp not null,
    descricao varchar(200) not null,
    entrega_id int8 not null,
    primary key (id)
);

alter table if exists log.ocorrencia 
    add constraint fk_entrega
    foreign key (entrega_id) 
    references log.entrega;