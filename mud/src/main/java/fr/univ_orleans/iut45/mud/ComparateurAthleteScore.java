package fr.univ_orleans.iut45.mud;

import java.util.Comparator;

public class ComparateurAthleteScore implements Comparator<Athlete>{

    private EpreuveInd epreuve;

    public ComparateurAthleteScore(EpreuveInd epreuve){
        this.epreuve = epreuve;
    }

    @Override
    public int compare(Athlete arg0, Athlete arg1) {
        return this.epreuve.getScore(arg0) - this.epreuve.getScore(arg1);
    }
    
}
