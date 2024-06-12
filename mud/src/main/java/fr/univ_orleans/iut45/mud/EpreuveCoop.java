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
        int nbJoueursMax = this.competition.getNbJoueursMax()+1;
        List<Athlete> joueurTerrain = new ArrayList<>();
        Collections.sort(equipe.getLiAthlete());
        //création d'une liste de joueur les plus fort de l'équipe
        for(int i=0; i<nbJoueursMax; ++i){
            joueurTerrain.add(equipe.getLiAthlete().get(i));
        }
        // calcule de score
        int score = 0;
        for(Athlete a : joueurTerrain){
            score += a.getAgilite()+a.getEndurance()+a.getForce();
        }
        return score;
    }

    @Override
    public String classementEpreuve(){
        String texte="Place | Equipe | Score" +System.lineSeparator();
        List<Equipe> liste = new ArrayList<>();
        for(Equipe e : this.scores.keySet()){
            liste.add(e);
        }
        ComparateurEquipe comparateur = new ComparateurEquipe(this);
        Collections.sort(liste, comparateur);
        for(Equipe e : liste){
            texte += (liste.indexOf(e)+1)+" | "+e.getNom()+" | "+this.getScore(e)+System.lineSeparator();
        }

        return texte;
    }

    @Override
    public String classementTheorique(){
        String texte="Place | Equipe | ScoreThéorique "+System.lineSeparator();
        List<Equipe> liste = new ArrayList<>();
        for(Equipe e : this.scores.keySet()){
            liste.add(e);
        }
        ComparateurEquipeTheorique comparateur = new ComparateurEquipeTheorique(this);
        Collections.sort(liste, comparateur);
        for(Equipe e : liste){
            texte += (liste.indexOf(e)+1)+" | "+e.getNom()+" | "+this.getScoreTheorique(e)+System.lineSeparator();
        }

        return texte;

    }

    @Override
    public Map<Integer,Equipe> getDonneesClassement(){
        
        List<Equipe> liste = new ArrayList<>();
        for(Equipe e : this.scores.keySet()){
            liste.add(e);
        }
        ComparateurEquipeTheorique comparateur = new ComparateurEquipeTheorique(this);
        Collections.sort(liste, comparateur);
        Map<Integer,Equipe> dico = new HashMap<>();
        for(Equipe e : liste){
            dico.put((liste.indexOf(e)+1), e);
        }
        return dico;
    }

}
