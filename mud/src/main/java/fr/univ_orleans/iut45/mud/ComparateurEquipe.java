package fr.univ_orleans.iut45.mud;

import java.util.Comparator;

public class ComparateurEquipe implements Comparator<Equipe> {

    private EpreuveCoop epreuve;

    public ComparateurEquipe(EpreuveCoop epreuve) {
        this.epreuve = epreuve;
    }

    @Override
    public int compare(Equipe e1, Equipe e2){
        if((epreuve.getScore(e1) == null) && (epreuve.getScore(e2) == null)){
            return 0;
        }
        if((epreuve.getScore(e1) == null) ){
            return 1;
        }
        if((epreuve.getScore(e1) == null) ){
            return -1;
        }
        return epreuve.getScore(e1)-epreuve.getScore(e2);

    }

    



}
