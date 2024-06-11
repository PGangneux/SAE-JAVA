package fr.univ_orleans.iut45.mud;

public class EpreuveIndMasc extends EpreuveInd {
    
    
    public EpreuveIndMasc(String nom, CompetInd competition){
        super(nom, competition);
        for(Participant a : competition.getParticipant()){
            Athlete athlete = (Athlete)a;
            if(a.getSexe().equals("H")){
                this.scores.put(athlete, null);
            }
        }
    }
}