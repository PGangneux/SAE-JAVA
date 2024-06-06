package fr.univ_orleans.iut45.mud;

import java.util.List;
import java.util.Set;

public class Executable {
    public static void main(String[] args) {
        String chemin = "./mud/src/main/java/fr/univ_orleans/iut45/mud/donnees.csv";
        importData donnees = new importData(chemin);

        Set<Pays> ensPays = donnees.getEnsPays();
        Set<Sport> ensSports = donnees.getEnsSports();
        Set<Competition> ensCompetitions = donnees.getEnsCompetitions();
        List<Athlete> liAthletes = donnees.getListAthletes();  
        List<Equipe> liEquipes = donnees.getListEquipes();



        for(Athlete athlete: liAthletes){
            //if( athlete.getNom().equals("Ishii") && athlete.getPrenom().equals("Haruto") && athlete.getSexe().equals("F")){System.out.println(athlete.getSport().getNom());}
        }

        int i = 0;
        for (Competition competition : ensCompetitions){
            System.out.println(competition.getNom() +" " +competition.getSexe()+" "+ competition.getSport().getNom()+" "+ competition.getParticipant().size());
            if (competition instanceof CompetInd){
                for(Participant Participant : competition.getParticipant()){
                    Athlete athlete = (Athlete) Participant;
                    if( athlete.getNom().equals("Ishii") && athlete.getPrenom().equals("Haruto") && athlete.getSexe().equals("F")){
                        //System.out.println(competition.getNom() +" " +competition.getSexe()+" "+ competition.getSport().getNom()+" "+ competition.getParticipant().size());
                    }
                }
            }
        }
        
    }
}
