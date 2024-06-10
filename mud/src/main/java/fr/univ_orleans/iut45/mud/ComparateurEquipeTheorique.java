package fr.univ_orleans.iut45.mud;

import java.util.Comparator;

public class ComparateurEquipeTheorique implements Comparator<Equipe> {

    private EpreuveCoop epreuve;

    public ComparateurEquipeTheorique(EpreuveCoop e){
        this.epreuve=e;
    }

    @Override
    public int compare(Equipe e1, Equipe e2){
        return this.epreuve.getScore(e1)-this.epreuve.getScore(e2);
    }

}
