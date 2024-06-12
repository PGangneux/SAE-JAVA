package fr.univ_orleans.iut45.mud;

import java.util.Comparator;


/**
 * La classe ComparateurAthlete implémente l'interface Comparator pour comparer deux athlètes
 * en fonction de leur score dans une épreuve individuelle donnée.
 */
public class ComparateurAthlete implements Comparator<Athlete>{

    private EpreuveInd epreuve;


    /**
     * Constructeur pour créer un comparateur d'athlètes avec une épreuve individuelle spécifiée.
     *
     * @param epreuve L'épreuve individuelle pour laquelle les athlètes seront comparés.
     */
    public ComparateurAthlete(EpreuveInd epreuve){
        this.epreuve = epreuve;
    }


    /**
     * Compare deux athlètes en fonction de leur score dans l'épreuve individuelle.
     *
     * @param arg0 Le premier athlète à comparer.
     * @param arg1 Le deuxième athlète à comparer.
     * @return une valeur négative si arg0 a un score inférieur à arg1,
     *         zéro s'ils ont le même score, une valeur positive sinon.
     */
    @Override
    public int compare(Athlete arg0, Athlete arg1) {
        return this.epreuve.getScore(arg0) - this.epreuve.getScore(arg1);
    }
    
}
