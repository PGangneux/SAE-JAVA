package fr.univ_orleans.iut45.mud.comparator;

import java.util.Comparator;

import fr.univ_orleans.iut45.mud.epreuve.EpreuveInd;
import fr.univ_orleans.iut45.mud.items.Athlete;


/**
 * La classe ComparateurAthleteTheorique implémente l'interface Comparator pour comparer deux athlètes
 * en fonction de leur score théorique dans une épreuve individuelle donnée.
 */
public class ComparateurAthleteTheorique implements Comparator<Athlete>{
    


    /**
     * L'épreuve individuelle utilisée pour obtenir les scores des athlètes.
     */
    private EpreuveInd epreuve;


    /**
     * Constructeur pour créer un comparateur d'athlètes avec une épreuve individuelle spécifiée.
     *
     * @param epreuve L'épreuve individuelle pour laquelle les athlètes seront comparés.
     */
    public ComparateurAthleteTheorique(EpreuveInd epreuve){
        this.epreuve = epreuve;
    }


    /**
     * Compare deux athlètes en fonction de leur score dans l'épreuve individuelle.
     * Si l'un des athlètes n'a pas de score, il est considéré comme ayant un score inférieur.
     *
     * @param arg0 Le premier athlète à comparer.
     * @param arg1 Le deuxième athlète à comparer.
     * @return une valeur négative si arg0 a un score inférieur à arg1,
     *         zéro s'ils ont le même score, une valeur positive sinon.
     */
    @Override
    public int compare(Athlete arg0, Athlete arg1) {
        if((epreuve.getScore(arg0) == null) && (epreuve.getScore(arg1) == null)){
            return 0;
        }
        if((epreuve.getScore(arg0) == null) ){
            return 1;
        }
        if((epreuve.getScore(arg1) == null) ){
            return -1;
        }
        return epreuve.getScore(arg0)-epreuve.getScore(arg1);
    }
}
