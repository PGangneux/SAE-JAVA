#!/bin/bash
if test -d ./mud;
then echo "Veillez vous replacez dans le bon répertoire"
fi
cd ./mud
mvn clean compile
cp -r ./src/main/java/fr/univ_orleans/iut45/mud/IHM/img ./target/classes/fr/univ_orleans/iut45/mud/IHM/img
cp -r ./src/main/java/fr/univ_orleans/iut45/mud/IHM/src/*.fxml ./target/classes/fr/univ_orleans/iut45/mud/IHM/src
mvn exec:java