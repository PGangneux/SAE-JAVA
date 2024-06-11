package fr.univ_orleans.iut45.mud;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class EpreuveInd implements Epreuve<Athlete>{
    
    protected String nom;
    protected Map<Athlete, Integer> scores;
    protected CompetInd competition;

    public EpreuveInd(String nom, CompetInd competition){
        this.nom=nom;
        this.scores = new HashMap<>();
        this.competition=competition;
    }

    @Override
    public Integer getScore(Athlete athlete){
        return (this.scores.get(athlete));
    }

    @Override
    public void setScore(Athlete athlete, Integer valeur){
        if(this.scores.keySet().contains(athlete)){
            this.scores.put(athlete, valeur);
        }
    }

    @Override
    public String getNom(){
        return this.nom;
    }

    @Override
    public Integer getScoreTheorique(Athlete athlete){
        return athlete.getAgilite()+athlete.getEndurance()+athlete.getForce();
    }

    @Override
    public String classementEpreuve(){
        String res = "Place | Athlète | Score" + System.lineSeparator();
        List<Athlete> listeAthlete = new ArrayList<>();
        for (Athlete a : this.scores.keySet()){
            listeAthlete.add(a);
        }
        Comparator<Athlete> comparateur = new ComparateurAthlete(this);
        Collections.sort(listeAthlete,comparateur);
        for (int i = 0; i<listeAthlete.size(); ++i){
            res += i + "|" + listeAthlete.get(i).getPrenom() + listeAthlete.get(i).getNom() + "|" + this.scores.get(listeAthlete.get(i)) + System.lineSeparator();
        }
        return res;
    }

    @Override
    public String classementTheorique(){
        String res = "Place | Athlète | Score Théorique" + System.lineSeparator();
        List<Athlete> listeAthlete = new ArrayList<>();
        for (Athlete a : this.scores.keySet()){
            listeAthlete.add(a);
        }
        Comparator<Athlete> comparateur = new ComparateurAthleteTheorique(this);
        Collections.sort(listeAthlete,comparateur);
        for (int i = 0; i<listeAthlete.size(); ++i){
            res += i + "|" + listeAthlete.get(i).getPrenom() + listeAthlete.get(i).getNom() + "|" + this.scores.get(listeAthlete.get(i)) + System.lineSeparator();
        }
        return res;
    }


}
