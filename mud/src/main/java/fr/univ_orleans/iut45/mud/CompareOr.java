package fr.univ_orleans.iut45.mud;

import java.util.Comparator;

public class CompareOr implements Comparator<Pays>{
    @Override
    public int compare(Pays p1, Pays p2){
        return p1.getCompteurMedailleOr() - p2.getCompteurMedailleOr();
    }
}
