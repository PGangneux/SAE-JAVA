#!/bin/bash
 #cd ./mud/
#remplacer les valeur
#assurer vous d'avoir les authorization requis pour executer le script
set +e
set -x
mysql -u nathan -p'local' -h localhost<<EOFMYSQL 
source ./src/main/java/fr/univ_orleans/iut45/mud/data/DataSup.sql
source ./src/main/java/fr/univ_orleans/iut45/mud/data/UserDataSup.sql
source ./src/main/java/fr/univ_orleans/iut45/mud/data/DataBaseCreation.sql
source ./src/main/java/fr/univ_orleans/iut45/mud/data/apploginInit.sql
source ./src/main/java/fr/univ_orleans/iut45/mud/data/UserDataCreation.sql
show databases;



