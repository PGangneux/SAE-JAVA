package fr.univ_orleans.iut45.mud.comparator;

import java.util.Comparator;

import fr.univ_orleans.iut45.mud.epreuve.EpreuveCoop;
import fr.univ_orleans.iut45.mud.items.Equipe;


/**
 * La classe ComparateurEquipeTheorique implémente l'interface Comparator pour comparer deux équipes
 * en fonction de leur score théorique dans une épreuve coopérative donnée.
 */
public class ComparateurEquipeTheorique implements Comparator<Equipe> {

    private EpreuveCoop epreuve;


    /**
     * Constructeur pour créer un comparateur d'équipes théorique avec une épreuve coopérative spécifiée.
     *
     * @param e L'épreuve coopérative pour laquelle les équipes seront comparées.
     */
    public ComparateurEquipeTheorique(EpreuveCoop e){
        this.epreuve=e;
    }


    /**
     * Compare deux équipes en fonction de leur score théorique dans l'épreuve coopérative.
     *
     * @param e1 La première équipe à comparer.
     * @param e2 La deuxième équipe à comparer.
     * @return une valeur négative si e1 a un score inférieur à e2,
     *         zéro s'ils ont le même score, une valeur positive sinon.
     */
    @Override
    public int compare(Equipe arg0, Equipe arg1){
        if((epreuve.getScore(arg0) == null) && (epreuve.getScore(arg1) == null)){
            return 0;
        }
        if((epreuve.getScore(arg0) == null) ){
            return 1;
        }
        if((epreuve.getScore(arg1) == null) ){
            return -1;
        }
        return epreuve.getScore(arg1)-epreuve.getScore(arg0);
    }
}


