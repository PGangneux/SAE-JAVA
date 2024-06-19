package fr.univ_orleans.iut45.mud.comparator;

import java.util.Comparator;

import fr.univ_orleans.iut45.mud.items.Pays;

/**
 * La classe CompareOr implémente l'interface Comparator pour comparer deux pays
 * en fonction de leur nombre de médailles de Bronze.
 */
public class CompareBronze implements Comparator<Pays>{

    /**
     * Compare deux pays en fonction de leur nombre de médailles de Bronze.
     *
     * @param p1 Le premier pays à comparer.
     * @param p2 Le deuxième pays à comparer.
     * @return une valeur négative si p2 a plus de médailles de Bronze que p1,
     *         zéro s'ils en ont autant, une valeur positive sinon.
     */
    @Override
    public int compare(Pays p1, Pays p2){
        return  p2.getCompteurMedailleBronze()-p1.getCompteurMedailleBronze();
    }
}
