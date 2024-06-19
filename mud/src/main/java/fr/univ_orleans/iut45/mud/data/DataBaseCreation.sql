create database if not exists SAE;
use SAE;
source ./src/main/java/fr/univ_orleans/iut45/mud/data/DataSup.sql;

-- SET default_storage_engine = InnoDB;
create table PAYS(
    idPays int primary key,
    nomPays varchar(50) unique  
);

create table SPORT(
    idSport int primary key,
    nomSport varchar(50)
);

create table ATHLETE(
    idAthlete int primary key,
    nom varchar(50),
    prenom varchar(50),
    sexe varchar(1),
    forceAth int,
    enduranceAth int,
    agiliteAth int,
    idPays int
);

create table EQUIPE (
    idEquipe int primary key,
    nomEquipe varchar(50)
);

create table COMPETITION (
    idCompet int primary key,
    idSport int references SPORT.idSport,
    nomCompet varchar(50),
    individuelle int not null
    constraint chk_ind CHECK (individuelle in (0,1))
);

create table EPREUVE(
    idEpreuve int primary key,
    nomEpreuve varchar(50),
    idCompet int references COMPETITION.idCompet
);

create table PARTICIPE (
    idCompet int references COMPETITION.idCompet,
    idAthlete int references ATHLETE.idAthlete,
    primary key (idCompet,idAthlete)
);

create table APPARTENIR (
    idEquipe int references EQUIPE.idEquipe,
    idAthlete int references ATHLETE.idAthlete,
    primary key (idEquipe,idAthlete)
);

create table DISPUTE (
    idCompet int references COMPETITION.idCompet,
    idEquipe int references EQUIPE.idEquipe,
    primary key (idEquipe,idCompet)
);

-- ####################################
-- # Partie comptes et administration #
-- ####################################
-- création des rôle pour les comptes
create role 'administrateur';
create role 'journalist';
create role 'organisateur';
flush privileges;

-- attibution des rôles
grant all privileges on SAE.* to 'administrateur' with grant option;
grant select,update,insert on SAE.* to 'organisateur';
grant select on SAE.* to 'journalist';
flush privileges;

-- creation d'un super user
create user 'admin'@'%' identified by 'admin';
grant 'administrateur' to 'admin'@'%';