package fr.univ_orleans.iut45.mud;

public class EpreuveIndFem extends EpreuveInd {

    public EpreuveIndFem(String nom, CompetInd competition){
        super(nom, competition);
        for(Participant a : competition.getParticipant()){
            Athlete athlete = (Athlete)a;
            if(a.getSexe().equals("F")){
                this.scores.put(athlete, null);
            }
        }
        competition.ajoutEpreuve((Epreuve) this);
    }

}
