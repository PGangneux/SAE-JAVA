package fr.univ_orleans.iut45.mud;

import java.util.ArrayList;
import java.util.List;

public class CompetInd implements Competition{
    private String nom;
    private String sexe;
    private Sport sport;
    private List<Epreuve> liEpreuve;
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
    public String classement(){
        return "t";
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
    public double getScore(Participant participant){
        return 1.2;
    }


}
