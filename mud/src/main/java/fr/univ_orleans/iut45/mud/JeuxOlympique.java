package fr.univ_orleans.iut45.mud;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class JeuxOlympique 
{
    public static void main( String[] args )
    {
        // imporatation de données via un fichier csv
        String chemin = "./mud/src/main/java/fr/univ_orleans/iut45/mud/donnees.csv";
        importData donnees = new importData(chemin);
        Set<Pays> ensPays = donnees.getEnsPays();
        Set<Sport> ensSports = donnees.getEnsSports();
        Set<Competition> ensCompetitions = donnees.getEnsCompetitions();
        List<Athlete> liAthletes = donnees.getListAthletes();  
        List<Equipe> liEquipes = donnees.getListEquipes();

        /*
        // ajout d'un pays manuellement 
        Pays pologne = new Pays("Pologne");
        System.out.println("ajout de la"+pologne.getNom());
        ensPays.add(pologne);

        // ajout d'un Sport
        Sport football = new Sport("Football");
        ensSports.add(football);
        System.out.println("ajout du " +football.getNom());

        //ajout competitons collective
        Competition competFootCoop = new CompetCoop("competFootCoopFem", "F", football, 11);
        System.out.println("ajout de la compétition Coop"+competFootCoop.getNom()+" "+competFootCoop.getSexe()+" "+competFootCoop.getSport().getNom());
        System.out.println("voici les compétitions collective du sport " + football.getNom() +" "+ football.getLiCompetCoop()+" (doit pas etre vide)"); // ajout automatique de la compet dans le sport

        //ajout competitons collective
        Competition competFootSolo = new CompetInd("competFootSoloMasc", "H", football);
        System.out.println("ajout de la compétition Ind"+competFootSolo.getNom()+" "+competFootSolo.getSexe()+" "+competFootSolo.getSport().getNom());
        System.out.println("voici les compétitions individuelle du sport " + football.getNom() + football.getLiCompetInd() +" (doit pas etre vide)"); // ajout automatique de la compet dans le sport

        // ajout equipe
        Equipe equipePologneFoot = new Equipe("Equipe de Foot de Pologne", "F", pologne, football);
        System.out.println("ajout de l'équipe "+equipePologneFoot.getNom() +" "+equipePologneFoot.getSexe()+" "+equipePologneFoot.getPays().getNom()+" "+equipePologneFoot.getSport().getNom());

        //ajout Athlèthe
        Athlete athlete1 = new Athlete("nom1", "prenom1", "F", pologne, football, 1, 2, 3);
        Athlete athlete2 = new Athlete("nom2", "prenom2", "H", pologne, football, 1, 2, 3);
        equipePologneFoot.ajouteAthlete(athlete1);
        System.out.println("ajout d'un athlèthe dans l'équipe "+equipePologneFoot.getNom() + " "+equipePologneFoot.getLiAthlete().get(0).getNom());

        competFootCoop.participer(equipePologneFoot);
        System.out.println("ajout de d'une équipe dans la compétition "+ competFootCoop.getNom() + " " + competFootCoop.getParticipant().get(0).getNom());
        competFootSolo.participer(athlete2);
        System.out.println("ajout d'un athlèthe dans la compétition "+competFootSolo.getNom() + " "+competFootSolo.getParticipant().get(0).getNom());

        //ajout epreuves
        EpreuveCoopFem epreuveCoopFem = new EpreuveCoopFem("epreuveFootCoopFem", (CompetCoop) competFootCoop);
        EpreuveIndMasc epreuveIndMasc = new EpreuveIndMasc("epreuveFootSoloMasc", (CompetInd) competFootSolo);
        System.out.println("ajout d'une épreuve coop feminin "+epreuveCoopFem.getNom());
        System.out.println("ajout d'une épreuve ind mascluin "+epreuveIndMasc.getNom());

        //suppresion ajout 
        */

        // lancement d'une compétition
        //manque épreuve
         

        //epreuve
        



         // ajout de une épreuve à chaque compétition
        for (Competition competition : ensCompetitions){
            if (competition instanceof CompetCoop){
                if (competition.getSexe().equals("F")){
                    String nom = "Epreuve de "+competition.getSport().getNom() + " feminin"; 
                    EpreuveCoop epreuve = new EpreuveCoopFem(nom, (CompetCoop) competition);
                    
                }
                else{
                    String nom = "Epreuve de "+competition.getSport().getNom() + " masculin"; 
                    EpreuveCoopMasc epreuve = new EpreuveCoopMasc(nom, (CompetCoop) competition);

                }
            }
            else{
                if (competition.getSexe().equals("F")){
                    String nom = "Epreuve de "+competition.getSport().getNom() + " feminin"; 
                    EpreuveIndFem epreuve = new EpreuveIndFem(nom, (CompetInd) competition);
                    
                }
                else{
                    String nom = "Epreuve de "+competition.getSport().getNom() + " masculin"; 
                    EpreuveIndMasc epreuve = new EpreuveIndMasc(nom, (CompetInd) competition);
                
                }

            }
        }

        
        
        
        
    
    }
}
