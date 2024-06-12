package fr.univ_orleans.iut45.mud;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompetCoop implements Competition<Equipe, EpreuveCoop>{
    private String nom;
    private String sexe;
    private Sport sport;
    private List<EpreuveCoop> liEpreuve;
    private List<Equipe> liEquipe;
    private int nbJoueursMax;

    public CompetCoop(String nom, String sexe, Sport sport, int nbJoueursMax){
        this.nom=nom;
        this.sexe = sexe;
        this.sport = sport;
        this.liEquipe = new ArrayList<>();
        this.liEpreuve = new ArrayList<>();
        this.nbJoueursMax = nbJoueursMax;
    }


    

    public int getNbJoueursMax() {
        return nbJoueursMax;
    }




    public void setNbJoueursMax(int nbJoueursMax) {
        this.nbJoueursMax = nbJoueursMax;
    }




   @Override
    public List<EpreuveCoop> getLiEpreuves(){
        return this.liEpreuve;
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
    public List<Equipe> getParticipant(){
        return this.liEquipe;
    }

    @Override
    public Sport getSport() {
        return sport;
    }

    @Override
    public String classement(){
        String texte="Place | Equipe" +System.lineSeparator();
        Map<Equipe, Integer> dico = new HashMap<>();
        for(Equipe e : this.liEquipe){
            dico.put(e, 0);
        }

        for(EpreuveCoop epreuve : this.liEpreuve){
            Map<Integer,Equipe> donnees = epreuve.getDonneesClassement();
            for(Integer i : donnees.keySet()){
                dico.put(donnees.get(i), (dico.get((donnees.get(i)))+i));
            }
        }

        List<Equipe> liste = new ArrayList<>();
        for(Equipe e : this.liEquipe){
            liste.add(e);
        }

        ComparateurCompetCoop comparator = new ComparateurCompetCoop(dico);
        Collections.sort(liste , comparator);
        for(Equipe e : liste){
            texte += (liste.indexOf(e)+1)+" | "+e.getNom()+System.lineSeparator();
        }
        
        return texte;
    }

    @Override
    public void participer(Equipe participant){
        this.liEquipe.add((Equipe) participant);
        
    }

    @Override
    public void suppParticipant(Equipe participant){
        this.liEquipe.remove(participant);
    }

    @Override
    public void setNom(String newNom){
        this.nom = newNom;
    }

    @Override
    public boolean participantPresent(Equipe participant){
        return this.liEquipe.contains(participant);
    }

    @Override
    public void ajoutEpreuve(EpreuveCoop epreuve){
        this.liEpreuve.add(epreuve);
    }


}
