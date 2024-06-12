package fr.univ_orleans.iut45.mud;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompetCoop implements Competition{
    private String nom;
    private String sexe;
    private Sport sport;
    private List<Epreuve> liEpreuve;
    private List<Participant> liEquipe;

    public CompetCoop(String nom, String sexe, Sport sport){
        this.nom=nom;
        this.sexe = sexe;
        this.sport = sport;
        this.liEquipe = new ArrayList<>();
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
    public List<Participant> getParticipant(){
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
        for(Participant p : this.liEquipe){
            Equipe e = (Equipe) p;
            dico.put(e, 0);
        }

        for(Epreuve epreuve : this.liEpreuve){
            EpreuveCoop epreuveCoop = (EpreuveCoop) epreuve;
            Map<Integer,Equipe> donnees = epreuveCoop.getDonneesClassement();
            for(Integer i : donnees.keySet()){
                dico.put(donnees.get(i), (dico.get((donnees.get(i)))+i));
            }
        }

        List<Equipe> liste = new ArrayList<>();
        for(Participant p : this.liEquipe){
            Equipe e = (Equipe) p;
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
    public void participer(Participant participant){
        if(participant instanceof Equipe){this.liEquipe.add((Equipe) participant);}
        
    }

    @Override
    public void suppParticipant(Participant participant){
        this.liEquipe.remove(participant);
    }

    @Override
    public void setNom(String newNom){
        this.nom = newNom;
    }

    @Override
    public boolean participantPresent(Participant participant){
        return this.liEquipe.contains(participant);
    }

    @Override
    public double getScore(Participant participant){
        return 22;
    }


}
