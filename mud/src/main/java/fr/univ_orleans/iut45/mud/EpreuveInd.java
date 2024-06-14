package fr.univ_orleans.iut45.mud;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * La classe abstraite EpreuveInd représente une épreuve individuelle dans une compétition.
 * Elle implémente l'interface Epreuve pour les athlètes.
 */
public abstract class EpreuveInd implements Epreuve<Athlete>{
    
    /**
     * Le nom de l'épreuve individuelle.
     */
    protected String nom;

    /**
     * Map associant chaque Athlete à son score dans cette épreuve.
     */
    protected Map<Athlete, Integer> scores;

    /**
     * La compétition à laquelle cette épreuve appartient.
     */
    protected CompetInd competition;

    /**
     * Constructeur pour créer une épreuve individuelle avec un nom et une compétition associée.
     *
     * @param nom Le nom de l'épreuve individuelle.
     * @param competition La compétition à laquelle cette épreuve individuelle appartient.
     */
    public EpreuveInd(String nom, CompetInd competition){
        this.nom=nom;
        this.scores = new HashMap<>();
        this.competition=competition;
    }


    /**
     * Obtient le score d'un Athlete dans cette épreuve individuelle.
     *
     * @param athlete L'athlète dont on veut obtenir le score.
     * @return Le score de l'athlète dans cette épreuve, ou null s'il n'a pas de score.
     */
    @Override
    public Integer getScore(Athlete athlete){
        return (this.scores.get(athlete));
    }


    /**
     * Définit le score d'un Athlete dans cette épreuve individuelle.
     *
     * @param athlete L'athlète dont on veut définir le score.
     * @param valeur La valeur du score à définir.
     */
    @Override
    public void setScore(Athlete athlete, Integer valeur){
        if(this.scores.keySet().contains(athlete)){
            this.scores.put(athlete, valeur);
        }
    }


    /**
     * Obtient le nom de cette épreuve individuelle.
     *
     * @return Le nom de l'épreuve individuelle.
     */
    @Override
    public String getNom(){
        return this.nom;
    }
    

    /**
     * Calcule et retourne le score théorique d'un Athlete dans cette épreuve individuelle.
     * Le score théorique est calculé en additionnant l'agilité, l'endurance et la force de l'Athlete.
     *
     * @param athlete L'athlète dont on veut calculer le score théorique.
     * @return Le score théorique de l'athlète dans cette épreuve.
     */
    @Override
    public Integer getScoreTheorique(Athlete athlete){
        return athlete.getAgilite()+athlete.getEndurance()+athlete.getForce();
    }


    /**
     * Génère et retourne le classement des Athlètes selon leur score réel dans cette épreuve individuelle.
     *
     * @return Une chaîne de caractères représentant le classement des Athlètes par score.
     */
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
            res += i + "|" + listeAthlete.get(i).getPrenom() + " " + listeAthlete.get(i).getNom() + "|" + this.scores.get(listeAthlete.get(i)) + System.lineSeparator();
        }
        return res;
    }


    /**
     * Génère et retourne le classement théorique des Athlètes selon leur score théorique dans cette épreuve individuelle.
     *
     * @return Une chaîne de caractères représentant le classement théorique des Athlètes.
     */
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
            res += i + "|" + listeAthlete.get(i).getPrenom() + " " + listeAthlete.get(i).getNom() + "|" + this.scores.get(listeAthlete.get(i)) + System.lineSeparator();
        }
        return res;
    }


    /**
     * Obtient les données de classement des Athlètes dans cette épreuve individuelle.
     * Les données sont représentées sous forme d'une Map où la clé est la place dans le classement et la valeur est l'Athlète correspondant.
     *
     * @return Les données de classement des Athlètes.
     */
    @Override
    public Map<Integer,Athlete> getDonneesClassement(){
        
        List<Athlete> liste = new ArrayList<>();
        for(Athlete a : this.scores.keySet()){
            liste.add(a);
        }
        Comparator<Athlete> comparateur = new ComparateurAthleteTheorique(this);
        Collections.sort(liste, comparateur);
        Map<Integer,Athlete> dico = new HashMap<>();
        for(Athlete a : liste){
            dico.put((liste.indexOf(a)+1), a);
        }
        return dico;
    }


}
