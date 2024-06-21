set +e
set -x
mysql -u nathan -p'local' -h localhost<<EOFMYSQL 
source ./src/main/java/fr/univ_orleans/iut45/mud/data/DataBaseCreation.sql
source ./src/main/java/fr/univ_orleans/iut45/mud/data/UserDataCreation.sql
source ./src/main/java/fr/univ_orleans/iut45/mud/data/apploginInit.sql
show databases;

