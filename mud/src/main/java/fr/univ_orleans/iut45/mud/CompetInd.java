package fr.univ_orleans.iut45.mud;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompetInd implements Competition{
    private String nom;
    private String sexe;
    private Sport sport;
    private List<Epreuve<Participant>> liEpreuve;
    private List<Participant> liAthletes;

    public CompetInd(String nom, String sexe, Sport sport){
        this.nom=nom;
        this.sexe = sexe;
        this.sport = sport;
        this.liAthletes = new ArrayList<>();
        this.liEpreuve = new ArrayList<>();
    }

    @Override
    public String getNom(){
        return this.nom;
    }

    @Override
    public String getSexe(){
        return this.sexe;
    }
    
    @Override
    public Sport getSport() {
        return this.sport;
    }

    @Override
    public List<Participant> getParticipant(){
        return  this.liAthletes;
    }

    @Override
    public List<Epreuve<Participant>> getLiEpreuves(){
        return this.liEpreuve;
    }

    @Override
    public String classement(){
        String texte="Place | Athlete" +System.lineSeparator();
        Map<Athlete, Integer> dico = new HashMap<>();
        for(Participant p : this.liAthletes){
            Athlete e = (Athlete) p;
            dico.put(e, 0);
        }

        for(Epreuve epreuve : this.liEpreuve){
            EpreuveInd epreuveCoop = (EpreuveInd) epreuve;
            Map<Integer,Athlete> donnees = epreuveCoop.getDonneesClassement();
            for(Integer i : donnees.keySet()){
                dico.put(donnees.get(i), (dico.get((donnees.get(i)))+i));
            }
        }

        List<Athlete> liste = new ArrayList<>();
        for(Participant p : this.liAthletes){
            Athlete e = (Athlete) p;
            liste.add(e);
        }

        ComparateurCompetInd comparator = new ComparateurCompetInd(dico);
        Collections.sort(liste , comparator);
        for(Athlete e : liste){
            texte += (liste.indexOf(e)+1)+" | "+e.getNom()+System.lineSeparator();
        }
        
        return texte;
    }

    @Override
    public void participer(Participant participant){
        if(participant instanceof Athlete){this.liAthletes.add(participant);}
    }

    @Override
    public void suppParticipant(Participant participant){
        this.liAthletes.remove(participant);
    }

    @Override
    public void setNom(String newNom){
        this.nom = newNom;
    }

    @Override
    public boolean participantPresent(Participant participant){
        return this.liAthletes.contains(participant);
    }

    @Override
    public void ajoutEpreuve(Epreuve<Participant> epreuve){
        this.liEpreuve.add(epreuve);
    }
    


}
