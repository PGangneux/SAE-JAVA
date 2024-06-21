# SAE-JAVA
## Rappel des commandes (pour les développeurs) : 
`mvn compile` : compile le projet ==> succès si pas d'érreurs <br>
`mvn exec:java` : compilation + execution de la classe principale (définit dans le pom.xml) <br>
`mvn clean test` ou `mvn test` : execution des classe test avec ou sans nétoyage des fichier temporaires. <br>
`mvn clean` : nétoyage fichiers temporaires (./target) <br>
note : le ficher pom.xml doit etre à la racine pour executer les commandes

# Ouvrez un terminal linux :
## Pour lancher le projet :
## Il est fortement recomendé d'installer la BD en executant les script a la main
### Base de données 
ce placer a la racine du projet vous devez y voir le repertoir mud.
Lancez les commande : 
Assurez  vous d'avoir creer un utilisateur 'nathan' disposant de tous le droit dans votre base de données distant ou local <br>
`sh DBinstallServer.sh` : pour creer la base de données depuis un utilisateur local <br>
`sh DBinstallServerOnline.sh` : pour creer la base de données depuis un utilisateur distant connecter au reseau local <br>

### Lancement Application
lancement de l'application avec la commande
`sh ApplicationSetup.sh` 


