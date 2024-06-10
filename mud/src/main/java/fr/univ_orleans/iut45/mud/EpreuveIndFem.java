package fr.univ_orleans.iut45.mud;

public class EpreuveIndFem extends EpreuveInd {

    public EpreuveIndFem(String nom, CompetInd competition){
        super(nom, competition);
        for(Participant a : competition.getParticipant()){
            if(a.getSexe().equals("F")){
                Athlete athlete = (Athlete)a;
                this.scores.put(athlete, null);
            }
        }
    }

}
