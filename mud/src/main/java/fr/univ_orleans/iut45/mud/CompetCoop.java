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
    public List<Equipe> getParticipant(){
        return this.liEquipe;
    }

    @Override
    public List<Epreuve> getEpreuve(){
        return this.liEpreuve;
    }

    @Override
    public String getSexe(){
        if (this.liEquipe.size()>0){
            for (Equipe e : this.liEquipe){
                if (e.getLiAthlete().size()>0){
                    return e.getLiAthlete().get(0).getSexe();
                }
            }
        }
        
        return "NA";
    }
        

    @Override
    public String classement(){
        return "t";
    }

    @Override
    public void participer(Equipe equipe){
        this.liEquipe.add(equipe);
    }

    @Override
    public void suppParticipant(Equipe equipe){
        this.liEquipe.remove(equipe);
    }

    @Override
    public void setNom(String newNom){
        this.nom = newNom;
    }

    @Override
    public boolean participantPresent(Equipe equipe){
        this.liEquipe.contains(equipe);
    }

    @Override
    public double getScore(){
        return 22;
    }


}
