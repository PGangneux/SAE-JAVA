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
        this.liEquipe.add(equipe);
    }

    @Override
    public void suppParticipant(Participant participant){
        this.liEquipe.remove(equipe);
    }

    @Override
    public void setNom(String newNom){
        this.nom = newNom;
    }

    @Override
    public boolean participantPresent(Participant participant){
        this.liEquipe.contains(equipe);
    }

    @Override
    public double getScore(){
        return 22;
    }


}
