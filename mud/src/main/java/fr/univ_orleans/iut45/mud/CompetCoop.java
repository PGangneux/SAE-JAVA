package fr.univ_orleans.iut45.mud;

import java.util.ArrayList;
import java.util.List;

public class CompetCoop implements Competition{
    private String nom;
    private List<Epreuve> liEpreuve;
    private List<Equipe> liEquipe;

    public CompetCoop(String nom){
        this.nom=nom;
        this.liEquipe = new ArrayList<>();
        this.liEpreuve = new ArrayList<>();
    }

    @Override
    public String getNom(){
        return this.nom;
    }

    @Override
    public List<Participant> getParticipant(){
        return this.liEquipe;
    }

    @Override
    public String classement(){
        return "t";
    }

    @Override
    public void participer(Participant participant){
        this.liEquipe.add(participant);
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
        this.liEquipe.contains(participant);
    }

    @Override
    public double getScore(Equipe equipe){
        double score = 0;
        for (Epreuve epreuve: this.liEpreuve){
            score += epreuve.getScore(equipe);
        }
        return score;
    }


}
