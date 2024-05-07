package fr.univ_orleans.iut45.mud;

import java.util.ArrayList;
import java.util.List;

public class CompetInd implements Competition{
    private String nom;
    private List<Epreuve> liEpreuve;
    private List<Athlete> liAthletes;

    public CompetCoop(String nom){
        this.nom=nom;
        this.liAthletes = new ArrayList<>();
        this.liEpreuve = new ArrayList<>();
    }

    @Override
    public String getNom(){
        return this.nom;
    }

    @Override
    public List<Participant> getParticipant(){
        return this.liAthletes;
    }

    @Override
    public String classement(){
        return "t";
    }

    @Override
    public void participer(Participant participant){
        this.liAthletes.add(athlete);
    }

    @Override
    public void suppParticipant(Participant participant){
        this.liAthletes.remove(athlete);
    }

    @Override
    public void setNom(String newNom){
        this.nom = newNom;
    }

    @Override
    public boolean participantPresent(Participant participant){
        return this.liAthletes.contains(athlete);
    }

    @Override
    public double getScore(){
        return 22;
    }


}
