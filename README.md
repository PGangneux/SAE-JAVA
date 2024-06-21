# SAE-JAVA
## Rappel des commandes (pour les développeurs) : 
`mvn compile` : compile le projet ==> succès si pas d'érreurs <br>
`mvn exec:java` : compilation + execution de la classe principale (définit dans le pom.xml) <br>
`mvn clean test` ou `mvn test` : execution des classe test avec ou sans nétoyage des fichier temporaires. <br>
`mvn clean` : nétoyage fichiers temporaires (./target) <br>
note : le ficher pom.xml doit etre à la racine pour executer les commandes

## Pour lancher le projet :
### Base de données
ce placer a la racine du projet vous devez y voir le repertoir mud.
Lancez les commande : 
Assurez  vous d'avoir creer un utilisateur 'nathan' disposant de tous le droit dans votre base de données distant ou local
`sh DBinstallServer.sh` pour creer la base de données depuis un utilisateur local
`sh DBinstallServerOnline.sh` pour creer la base de données depuis un utilisateur distant connecter au reseau local

### Base de données
`sh ApplicationSetup.sh`


