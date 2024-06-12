package fr.univ_orleans.iut45.mud;

import java.util.Comparator;
import java.util.Map;

public class ComparateurCompetInd implements Comparator<Athlete>{


    private Map<Athlete,Integer> donnees;

    public ComparateurCompetInd(Map<Athlete,Integer> donnees){
        this.donnees = donnees;
    }

    @Override
    public int compare(Athlete a1, Athlete a2){
        return this.donnees.get(a1)-this.donnees.get(a2);
    }

}
