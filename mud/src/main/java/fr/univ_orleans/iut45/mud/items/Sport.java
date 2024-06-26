package fr.univ_orleans.iut45.mud.items;

import java.util.ArrayList;
import java.util.List;

import fr.univ_orleans.iut45.mud.competition.CompetCoop;
import fr.univ_orleans.iut45.mud.competition.CompetInd;


/**
 * La classe Sport représente un sport.
 */
public class Sport {
    private String nom;
    private List<CompetInd> liCompetInd;
    private List<CompetCoop> liCompetCoop;


    /**
     * Constructeur pour créer un sport avec un nom spécifié.
     *
     * @param nom Le nom du sport.
     */
    public Sport(String nom) {
        this.nom = nom;
        this.liCompetCoop  = new ArrayList<>();
        this.liCompetInd = new ArrayList<>();
    }


    /**
     * Retourne le nom du sport.
     *
     * @return Le nom du sport.
     */
    public String getNom() {
        return nom;
    }


    /**
     * Retourne la liste des compétitions individuelles associées à ce sport.
     *
     * @return La liste des compétitions individuelles.
     */
    public List<CompetInd> getLiCompetInd() {
        return liCompetInd;
    }


    /**
     * Retourne la liste des compétitions coopératives associées à ce sport.
     *
     * @return La liste des compétitions coopératives.
     */
    public List<CompetCoop> getLiCompetCoop() {
        return liCompetCoop;
    }


    /**
     * Ajoute une compétition individuelle à la liste des compétitions individuelles.
     *
     * @param compet La compétition individuelle à ajouter.
     */
    public void ajouteCompetInd(CompetInd compet){
        this.liCompetInd.add(compet);
    }


     /**
     * Ajoute une compétition coopérative à la liste des compétitions coopératives.
     *
     * @param compet La compétition coopérative à ajouter.
     */
    public void ajouteCompetCoop(CompetCoop compet){
        this.liCompetCoop.add(compet);
    }


    /**
     * Compare ce Sport à un autre objet. Ils sont égaux si l'objet est un Sport et qu'ils portent le meme nom. 
     * 
     * @param object L'objet à comparer avec ce Sport.
     * @return true si l'objet donné représente un Sport équivalent à ce Sport, false sinon.
     */
    @Override
    public boolean equals(Object object){
        if (object == null){return false;}
        if(object == this){return true;}
        if(!(object instanceof Sport)){return false;}
        Sport tmp = (Sport) object;
        return tmp.getNom().equals(this.getNom());
    }

    /**
     * Retourne un code de hachage pour ce Sport.
     *
     * @return Un code de hachage pour ce Sport.
     */
    @Override
    public int hashCode(){
        return this.getNom().hashCode();
    }
    
}
