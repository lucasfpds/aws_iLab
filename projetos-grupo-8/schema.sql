create table clientes (
  id serial primary key,
  nome varchar(100) not null,
  email varchar(50) not null unique,
  telefone varchar(20) not null
); 
 create table pedidos(
   id serial primary key,
   valor_total double precision,
   data date not null,
   id_cliente int not null,
   
   constraint fk_ped_cliente foreign key (id_cliente) references clientes(id)
);

  create table produtos(
  	id serial primary key,
    nome varchar not null,
    data_cadastro timestamp,
    descricao varchar not null,
    valor double precision not null
  );

create table pedidos_produtos(
  id serial primary key, 
  id_pedido integer not null,
  id_produto integer not null, 
  qtd_produto integer not null,
  preco_unit double precision not null,
  
  constraint fk_pedido_produto foreign key(id_pedido) references pedidos(id),
  constraint fk_produto foreign key(id_produto) references produtos(id)
  );