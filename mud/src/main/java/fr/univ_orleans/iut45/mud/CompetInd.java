package fr.univ_orleans.iut45.mud;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompetInd implements Competition<Athlete , EpreuveInd>{
    private String nom;
    private String sexe;
    private Sport sport;
    private List<EpreuveInd> liEpreuve;
    private List<Athlete> liAthletes;

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
    public List<Athlete> getParticipant(){
        return  this.liAthletes;
    }

    @Override
    public List<EpreuveInd> getLiEpreuves(){
        return this.liEpreuve;
    }

    @Override
    public String classement(){
        String texte="Place | Athlete" +System.lineSeparator();
        Map<Athlete, Integer> dico = new HashMap<>();
        for(Athlete a : this.liAthletes){
            dico.put(a, 0);
        }

        for(EpreuveInd epreuveCoop : this.liEpreuve){
            Map<Integer,Athlete> donnees = epreuveCoop.getDonneesClassement();
            for(Integer i : donnees.keySet()){
                dico.put(donnees.get(i), (dico.get((donnees.get(i)))+i));
            }
        }

        List<Athlete> liste = new ArrayList<>();
        for(Athlete a : this.liAthletes){
            liste.add(a);
        }

        ComparateurCompetInd comparator = new ComparateurCompetInd(dico);
        Collections.sort(liste , comparator);
        for(Athlete e : liste){
            texte += (liste.indexOf(e)+1)+" | "+e.getNom()+System.lineSeparator();
        }
        
        return texte;
    }

    @Override
    public void participer(Athlete participant){
        this.liAthletes.add(participant);
    }

    @Override
    public void suppParticipant(Athlete participant){
        this.liAthletes.remove(participant);
    }

    @Override
    public void setNom(String newNom){
        this.nom = newNom;
    }

    @Override
    public boolean participantPresent(Athlete participant){
        return this.liAthletes.contains(participant);
    }

    @Override
    public void ajoutEpreuve(EpreuveInd epreuve){
        this.liEpreuve.add(epreuve);
    }
    


}
