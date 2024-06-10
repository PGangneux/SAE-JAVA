package fr.univ_orleans.iut45.mud;

public class EpreuveIndFem extends EpreuveInd {

    public EpreuveIndFem(String nom, CompetInd competition){
        super(nom, competition);
        for(Participant a : competition.getParticipant()){
            if(a.getSexe()=="F"){
                this.scores.put(a, null);
            }
        }
    }

}
