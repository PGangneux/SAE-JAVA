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

        Sport volley = new Sport("Volley");
        Pays pays = new Pays("France");

        Athlete athlete1 = new Athlete("Personne", "Femme1", "F", pays, volley, 10, 20,65);
        Athlete athlete2 = new Athlete("Personne", "Femme2", "F", pays, volley, 11, 21, 66);
        Athlete athlete3 = new Athlete("Personne", "Femme3", "F", pays, volley, 12, 22, 67);

        CompetInd competition = new CompetInd("Compet", "F", volley);
        competition.participer(athlete1);
        competition.participer(athlete2);
        competition.participer(athlete3);
        EpreuveIndFem epreuveIndFem = new EpreuveIndFem("EpreuveTest", competition);
        epreuveIndFem.setScore(athlete1, 1);
        epreuveIndFem.setScore(athlete2, 2);
        epreuveIndFem.setScore(athlete3, 3);
        System.out.println(epreuveIndFem.classementEpreuve());
    }
}
