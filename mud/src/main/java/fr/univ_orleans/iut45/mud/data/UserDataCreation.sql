create database if not exists SAEACCOUNT;
use SAEACCOUNT;

-- ##############################
-- # Partie comptes utilisateur #
-- ##############################
-- création des rôle pour les comptes
-- Fonction qui hash un password : SHA("mypass");


create table ACCOUNTPRIVILEGE(
    idPrivilege int primary key,
    privilegeName enum ('administrateur','journalist','organisateur'),
    constraint chk_id CHECK (idPrivilege in (0,1,2))
);

create table USERACCOUNT(
    idAcccount int primary key,
    username varchar(50) unique not null,
    hashedPassword varchar(100) not null,
    idPrivilege int references ACCOUNTPRIVILEGE.idPrivilege
);

insert into ACCOUNTPRIVILEGE values
    (0,'journalist'),
    (1,'organisateur'),
    (2,'administrateur');


