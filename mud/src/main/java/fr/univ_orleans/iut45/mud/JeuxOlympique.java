package fr.univ_orleans.iut45.mud;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
        Set<CompetCoop> ensCompetitionsCoop = donnees.getEnsCompetitionsCoop();
        Set<CompetInd> ensCompetitionsInd = donnees.getEnsCompetitionsInd();
        List<Athlete> liAthletes = donnees.getListAthletes();  
        List<Equipe> liEquipes = donnees.getListEquipes();

        //// ajouts manuels ////
        // ajout d'un pays 
        Pays pologne = new Pays("Pologne");
        System.out.println("ajout de la "+pologne.getNom());
        ensPays.add(pologne);

        // ajout d'un Sport
        Sport football = new Sport("Football");
        ensSports.add(football);
        System.out.println("ajout du " +football.getNom());

        //ajout competitons collective
        CompetCoop competFootCoop = new CompetCoop("competFootCoopFem", "F", football, 11);
        System.out.println("ajout de la compétition Coop"+competFootCoop.getNom()+" "+competFootCoop.getSexe()+" "+competFootCoop.getSport().getNom());
        System.out.println("voici les compétitions collective du sport " + football.getNom() +" "+ football.getLiCompetCoop()+" (doit pas etre vide)"); // ajout automatique de la compet dans le sport

        //ajout competitons collective
        CompetInd competFootSolo = new CompetInd("competFootSoloMasc", "H", football);
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

    
        //// Simulation des Jeux Olympique /////

         // ajout de une épreuve à chaque compétition
        for (CompetCoop competition : ensCompetitionsCoop){
            if (competition.getSexe().equals("F")){
                String nom = "Epreuve de "+competition.getSport().getNom() + " feminin"; 
                EpreuveCoop epreuve = new EpreuveCoopFem(nom, (CompetCoop) competition);
                    
            }
            else{
                String nom = "Epreuve de "+competition.getSport().getNom() + " masculin"; 
                EpreuveCoopMasc epreuve = new EpreuveCoopMasc(nom, (CompetCoop) competition);

            }
            }
        for (CompetInd competition : ensCompetitionsInd){
            if (competition.getSexe().equals("F")){
                String nom = "Epreuve de "+competition.getSport().getNom() + " feminin"; 
                EpreuveIndFem epreuve = new EpreuveIndFem(nom, (CompetInd) competition);
                    
                }
            else{
                String nom = "Epreuve de "+competition.getSport().getNom() + " masculin"; 
                EpreuveIndMasc epreuve = new EpreuveIndMasc(nom, (CompetInd) competition);
                
            }

        }

        

        
        // execution de l'epreuve dans chaque compétition
        for (CompetCoop competition : ensCompetitionsCoop){
            for(Epreuve<Equipe> ep : competition.getLiEpreuves()){
                for(Equipe equipe : competition.getParticipant()){
                    Random random = new Random();
                    int randomNumber = random.nextInt(10);
                    ep.setScore(equipe, randomNumber);
                }
            }
            competition.attribuerMedaille();
        }
        for (CompetInd competition : ensCompetitionsInd){
            for(Epreuve<Athlete> ep : competition.getLiEpreuves()){
                for(Athlete athlete : competition.getParticipant()){
                    Random random = new Random();
                    int randomNumber = random.nextInt(10);
                    ep.setScore(athlete, randomNumber);
                }
            }
            competition.attribuerMedaille();
        }
        List<CompetCoop> listeCompet = new ArrayList<>(ensCompetitionsCoop);
        CompetCoop compet = listeCompet.get(0);
        System.out.println("affichage du classsment de l'épreuve " + compet.getLiEpreuves().get(0).getNom() + "\n" + compet.getLiEpreuves().get(0).classementEpreuve()+"\n");
        System.out.println("affichage du classsment de la compétition " + compet.getNom() + "\n" + compet.classement()+"\n");

        List<Pays> liMedaille = Pays.classementPaysMedaille(ensPays);
        System.out.println("classment des Pays par nombre de médaille\n");
        for (Pays pays : liMedaille){
            System.out.println(pays.getCompteurMedaille() + " " + pays.getNom());
        }
        System.out.println("\n classment des Pays par nombre de médaille d'or\n");
        List<Pays> liMedailleOr = Pays.classementPaysMedailleOr(ensPays);
        for (Pays pays : liMedailleOr){
            System.out.println(pays.getCompteurMedailleOr() + " " + pays.getNom());
        }

        
    
    }
}
