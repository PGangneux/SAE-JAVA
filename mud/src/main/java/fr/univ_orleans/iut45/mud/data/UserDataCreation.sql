create database if not exists SAEACCOUNT;
use SAEACCOUNT;
source ./src/main/java/fr/univ_orleans/iut45/mud/data/UserDataSup.sql;

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
    hashedPassword varchar(100),
    idPrivilege int references ACCOUNTPRIVILEGE.idPrivilege;
);

insert into ACCOUNTPRIVILEGE values
    (0,'journalist'),
    (1,'organisateur'),
    (2,'administrateur');


