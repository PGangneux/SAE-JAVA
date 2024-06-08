package fr.univ_orleans.iut45.mud;

import java.util.ArrayList;
import java.util.List;

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
        return "t";
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
