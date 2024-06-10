package fr.univ_orleans.iut45.mud;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Executable {
    public static void main(String[] args) {
        Set<Pays> ensPays = new HashSet<>();
        Pays france = new Pays("France");
        Pays belgique = new Pays("Belgique");
        Pays suisse = new Pays("Suisse");
        Pays finlande = new Pays("Finlande");
        ensPays.add(finlande);
        ensPays.add(france);
        ensPays.add(belgique);
        ensPays.add(suisse);

        france.setCompteurMedailleOr(5);
        belgique.setCompteurMedailleOr(2);
        suisse.setCompteurMedailleOr(1);

        List<Pays> classementMedailleOr = Pays.classementPaysMedailleOr(ensPays);

        
        List<Pays> classementMedailleOrTemoin = Arrays.asList(france,belgique,suisse,finlande);
        
        
        for(Pays p : classementMedailleOr){
            System.out.println(p.getCompteurMedailleOr());
        }
    }
}
