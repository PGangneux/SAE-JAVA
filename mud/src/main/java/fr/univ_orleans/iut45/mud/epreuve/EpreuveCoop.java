package fr.univ_orleans.iut45.mud.epreuve;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.univ_orleans.iut45.mud.comparator.ComparateurEquipe;
import fr.univ_orleans.iut45.mud.comparator.ComparateurEquipeTheorique;
import fr.univ_orleans.iut45.mud.competition.CompetCoop;
import fr.univ_orleans.iut45.mud.items.Athlete;
import fr.univ_orleans.iut45.mud.items.Equipe;


/**
 * la classe abstraite EpreuveCoop représente une épreuve coopérative dans une compétitions
 */
public abstract class EpreuveCoop implements Epreuve<Equipe> {

    /**
     * Le nom de l'épreuve.
     */
    protected String nom;

    /**
     * Un map qui stocke les scores des équipes participantes. 
     * La clé est l'équipe et la valeur est le score de l'équipe.
     */
    protected Map<Equipe, Integer> scores;

    /**
     * La compétition coopérative à laquelle cette épreuve est associée.
     */
    protected CompetCoop competition;


    /**
     * Constructeur pour créer une épreuve coopérative avec un nom et une compétition associée.
     *
     * @param nom Le nom de l'épreuve.
     * @param competition La compétition associée.
     */
    public EpreuveCoop(String nom, CompetCoop competition){
        this.nom=nom;
        this.scores = new HashMap<>();
        this.competition = competition;
    }

    /**
     * Retourne le score d'une équipe pour cette épreuve.
     *
     * @param equipe L'équipe dont on souhaite obtenir le score.
     * @return Le score de l'équipe.
     */
    @Override
    public Integer getScore(Equipe equipe){
        return this.scores.get(equipe);
    }


    /**
     * Définit le score d'une équipe pour cette épreuve.
     *
     * @param equipe L'équipe dont on souhaite définir le score.
     * @param valeur Le score à attribuer à l'équipe.
     */
    @Override
    public void setScore(Equipe equipe, Integer valeur){
        if( this.scores.keySet().contains(equipe)){
            this.scores.put(equipe, valeur);
        }
    }


    /**
     * Retourne le nom de l'épreuve.
     *
     * @return Le nom de l'épreuve.
     */
    @Override
    public String getNom(){
        return this.nom;
    }


     /**
     * Retourne le score théorique d'une équipe pour cette épreuve.
     *
     * @param equipe L'équipe dont on souhaite obtenir le score théorique.
     * @return Le score théorique de l'équipe.
     */
    @Override
    public Integer getScoreTheorique(Equipe equipe){
        int nbJoueursMax = this.competition.getNbJoueursMax();
        List<Athlete> joueurTerrain = new ArrayList<>();
        Collections.sort(equipe.getLiAthlete());
        //création d'une liste de joueur les plus fort de l'équipe
        for(int i=0; i<nbJoueursMax; ++i){
            if (i< equipe.getLiAthlete().size()){
                joueurTerrain.add(equipe.getLiAthlete().get(i));
            }
            else{i=nbJoueursMax;}
        }
        // calcule de score
        int score = 0;
        for(Athlete a : joueurTerrain){
            score += a.getAgilite()+a.getEndurance()+a.getForce();
        }
        return score;
    }


    /**
     * Retourne le classement des équipes pour cette épreuve sous forme de chaîne de caractères.
     *
     * @return Le classement des équipes pour cette épreuve.
     */
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


    /**
     * Retourne le classement théorique des équipes pour cette épreuve sous forme de chaîne de caractères.
     *
     * @return Le classement théorique des équipes pour cette épreuve.
     */
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


    /**
     * Retourne les données du classement sous forme de map, où la clé est la position dans le classement
     * et la valeur est l'équipe correspondante.
     *
     * @return Les données du classement.
     */
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
