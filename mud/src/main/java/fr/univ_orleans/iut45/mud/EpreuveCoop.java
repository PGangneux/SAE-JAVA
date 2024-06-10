package fr.univ_orleans.iut45.mud;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
        int score = 0;
        for(Athlete a : equipe.getLiAthlete()){
            score += a.getAgilite()+a.getEndurance()+a.getForce();
        }
        return score;
    }

    @Override
    public String classementEpreuve(){
        return "";
    }

    @Override
    public String classementTheorique(){
        String texte="Place | Equipe | ScoreThéorique \n";
        List<Equipe> liste = new ArrayList<>();
        for(Equipe e : this.scores.keySet()){
            liste.add(e);
        }
        ComparateurEquipeTheorique comparateur = new ComparateurEquipeTheorique(this);
        Collections.sort(liste, comparateur);
        for(Equipe e : liste){
            texte += (liste.indexOf(e)+1)+" | "+e.getNom()+" | "+this.getScoreTheorique(e);
        }

        return texte;

    }

}
