package fr.univ_orleans.iut45.mud;

import java.util.Comparator;

public class ComparateurAthleteTheorique implements Comparator<Athlete>{
    
    private EpreuveInd epreuve;

    public ComparateurAthleteTheorique(EpreuveInd epreuve){
        this.epreuve = epreuve;
    }

    @Override
    public int compare(Athlete arg0, Athlete arg1) {
        if((epreuve.getScore(arg0) == null) && (epreuve.getScore(arg1) == null)){
            return 0;
        }
        if((epreuve.getScore(arg0) == null) ){
            return 1;
        }
        if((epreuve.getScore(arg0) == null) ){
            return -1;
        }
        return epreuve.getScore(arg0)-epreuve.getScore(arg1);
    }
}
