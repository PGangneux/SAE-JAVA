create database if not exists JeuxOlympique;
use JeuxOlympique;

--SET default_storage_engine = InnoDB;
create table PAYS(
    idPays int primary key,
    nomPays varchar(50)   
);

create table SPORT(
    idSport int primary key,
    nomSport varchar(50)
);

create table EPREUVE(
    idEpreuve int primary key,
    nomEpreuve varchar(100),
    idSport int 
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

create table PARTICIPE(
    idEpreuve int,
    idAthlete int,
    primary key (idEpreuve,idAthlete)
);

alter table EPREUVE add foreign key (idSport) references SPORT (idSport);
alter table ATHLETE add foreign key (idPays) references PAYS (idPays);
alter table PARTICIPE add foreign key (idEpreuve) references EPREUVE (idEpreuve);
alter table PARTICIPE add foreign key (idAthlete) references ATHLETE (idAthlete);