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
    public List<Athlete> getParticipant(){
        return this.liAthletes;
    }

    @Override
    public List<Epreuve> getEpreuve(){
        return this.liEpreuve;
    }

    @Override
    public String getSexe(){
        if (this.liAthletes.size()>0){
            return this.liAthletes.get(0).getSexe();
        }
        else{
            return "NA";
        }
    }

    @Override
    public String classement(){
        return "t";
    }

    @Override
    public void participer(Athlete athlete){
        this.liAthletes.add(athlete);
    }

    @Override
    public void suppParticipant(Athlete athlete){
        this.liAthletes.remove(athlete);
    }

    @Override
    public void setNom(String newNom){
        this.nom = newNom;
    }

    @Override
    public boolean participantPresent(Athlete athlete){
        return this.liAthletes.contains(athlete);
    }

    @Override
    public double getScore(){
        return 22;
    }

    


}
