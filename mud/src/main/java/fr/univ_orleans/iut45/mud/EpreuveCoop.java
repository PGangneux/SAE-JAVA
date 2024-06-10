package fr.univ_orleans.iut45.mud;

import java.util.HashMap;
import java.util.Map;

public abstract class EpreuveCoop implements Epreuve<Equipe> {

    protected String nom;
    protected Map<Equipe, Integer> scores;
    protected CompetCoop competition;

    public EpreuveCoop(String nom, CompetCoop competition){
        this.nom=nom;
        this.scores = new HashMap<>();
        this.competition = competition;
    }

    @Override
    public Integer getScore(Equipe equipe){
        return this.scores.get(equipe);
    }

    @Override
    public void setScore(Equipe equipe, Integer valeur){
        if( this.scores.keySet().contains(equipe)){
            this.scores.put(equipe, valeur);
        }
    }

    @Override
    public String getNom(){
        return this.nom;
    }

    @Override
    public Integer getScoreTheorique(Equipe equipe){
        Integer scores = 0;
        for(Athlete a : equipe.getLiAthlete()){
            scores += a.getAgilite()+a.getEndurance()+a.getForce();
        }
        return scores;
    }

    @Override
    public String classementEpreuve(){
        return "";
    }

}
