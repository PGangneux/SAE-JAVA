package fr.univ_orleans.iut45.mud;

import java.util.Comparator;
import java.util.Map;

public class ComparateurCompetCoop implements Comparator<Equipe> {

    private Map<Equipe,Integer> donnees;

    public ComparateurCompetCoop(Map<Equipe,Integer> donnees){
        this.donnees = donnees;
    }

    @Override
    public int compare(Equipe e1, Equipe e2){
        return this.donnees.get(e1)-this.donnees.get(e2);
    }



}
