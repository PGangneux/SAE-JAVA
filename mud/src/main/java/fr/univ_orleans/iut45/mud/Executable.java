package fr.univ_orleans.iut45.mud;

import java.util.List;
import java.util.Set;

public class Executable {
    public static void main(String[] args) {
        String chemin = "./mud/src/main/java/fr/univ_orleans/iut45/mud/donnees.csv";
        importData donnees = new importData(chemin);

        Set<Pays> ensPays = donnees.getEnsPays();
        Set<Sport> ensSports = donnees.getEnsSports();
        List<Athlete> liAthletes = donnees.getListAthletes();  

        
        
    }
}
