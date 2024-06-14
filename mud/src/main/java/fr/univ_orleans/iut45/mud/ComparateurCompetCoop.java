package fr.univ_orleans.iut45.mud;

import java.util.Comparator;
import java.util.Map;


/**
 * La classe ComparateurCompetCoop implémente l'interface Comparator pour comparer deux équipes
 * en fonction de leur score dans une compétition coopérative donnée.
 */
public class ComparateurCompetCoop implements Comparator<Equipe> {

    /**
     * Les données de scores des équipes.
     * La clé est un objet Equipe et la valeur est le score associé à cette équipe.
     */
    private Map<Equipe,Integer> donnees;


    /**
     * Constructeur pour créer un comparateur d'équipes avec les scores spécifiés.
     *
     * @param donnees Une map contenant les équipes et leurs scores respectifs.
     */
    public ComparateurCompetCoop(Map<Equipe,Integer> donnees){
        this.donnees = donnees;
    }


    /**
     * Compare deux équipes en fonction de leur score.
     *
     * @param e1 La première équipe à comparer.
     * @param e2 La deuxième équipe à comparer.
     * @return une valeur négative si e1 a un score inférieur à e2,
     *         zéro s'ils ont le même score, une valeur positive sinon.
     */
    @Override
    public int compare(Equipe e1, Equipe e2){
        return this.donnees.get(e1)-this.donnees.get(e2);
    }



}
