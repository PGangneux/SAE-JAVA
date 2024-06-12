package fr.univ_orleans.iut45.mud;

public class EpreuveCoopFem extends EpreuveCoop {
    public EpreuveCoopFem(String nom, CompetCoop competition){
        super(nom, competition);
        for(Participant e : competition.getParticipant()){
            Equipe equipe = (Equipe)e;
            if(e.getSexe().equals("F")){
                this.scores.put(equipe, null);
            }
        }
    }

}

