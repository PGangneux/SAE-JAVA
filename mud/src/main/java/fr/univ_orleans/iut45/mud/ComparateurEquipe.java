package fr.univ_orleans.iut45.mud;

import java.util.Comparator;

/**
 * La classe ComparateurEquipe implémente l'interface Comparator pour comparer deux équipes
 * en fonction de leur score dans une épreuve coopérative donnée.
 */
public class ComparateurEquipe implements Comparator<Equipe> {

    private EpreuveCoop epreuve;


    /**
     * Constructeur pour créer un comparateur d'équipes avec une épreuve coopérative spécifiée.
     *
     * @param epreuve L'épreuve coopérative pour laquelle les équipes seront comparées.
     */
    public ComparateurEquipe(EpreuveCoop epreuve) {
        this.epreuve = epreuve;
    }

    /**
     * Compare deux équipes en fonction de leur score dans l'épreuve coopérative.
     * Si l'une des équipes n'a pas de score, elle est considérée comme ayant un score inférieur.
     *
     * @param e1 La première équipe à comparer.
     * @param e2 La deuxième équipe à comparer.
     * @return une valeur négative si e1 a un score inférieur à e2,
     *         zéro s'ils ont le même score, une valeur positive sinon.
     */
    @Override
    public int compare(Equipe e1, Equipe e2){
        if((epreuve.getScore(e1) == null) && (epreuve.getScore(e2) == null)){
            return 0;
        }
        if((epreuve.getScore(e1) == null) ){
            return 1;
        }
        if((epreuve.getScore(e1) == null) ){
            return -1;
        }
        return epreuve.getScore(e2)-epreuve.getScore(e1);

    }

}
