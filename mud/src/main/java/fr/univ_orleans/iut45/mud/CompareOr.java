package fr.univ_orleans.iut45.mud;

import java.util.Comparator;

/**
 * La classe CompareOr implémente l'interface Comparator pour comparer deux pays
 * en fonction de leur nombre de médailles d'or.
 */
public class CompareOr implements Comparator<Pays>{

    /**
     * Compare deux pays en fonction de leur nombre de médailles d'or.
     *
     * @param p1 Le premier pays à comparer.
     * @param p2 Le deuxième pays à comparer.
     * @return une valeur négative si p2 a plus de médailles d'or que p1,
     *         zéro s'ils en ont autant, une valeur positive sinon.
     */
    @Override
    public int compare(Pays p1, Pays p2){
        return  p2.getCompteurMedailleOr()-p1.getCompteurMedailleOr();
    }
}
