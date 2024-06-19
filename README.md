Base de datos de desk

create table Usuarios (
    uuid VARCHAR2(50) not null,
    nombreUsuario VARCHAR2(50) not null unique,
    contraseñaUsuario VARCHAR2(100) not null,
    constraint pk_usuarios primary key (uuid)
);

create table Tickets (
    uuid VARCHAR2(50) not null,
    numeroTicket VARCHAR2(5) not null,
    tituloTicket VARCHAR2(50) not null,
    descripcionTicket VARCHAR2(120) not null,
    autorTicket VARCHAR2(20) not null,
    emailAutor VARCHAR2(50) not null,
    estadoTicket VARCHAR2(20) not null,
    constraint pk_tickets primary key (uuid)
);


select * from Usuarios

select * from tickets
