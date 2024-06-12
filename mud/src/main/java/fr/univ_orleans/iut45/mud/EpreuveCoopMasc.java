package fr.univ_orleans.iut45.mud;

public class EpreuveCoopMasc extends EpreuveCoop{
    public EpreuveCoopMasc(String nom, CompetCoop competition){
        super(nom, competition);
        for(Participant e : competition.getParticipant()){
            Equipe equipe = (Equipe)e;
            if(e.getSexe().equals("H") || (e.getSexe().equals("M"))){
                this.scores.put(equipe, null);
            }
        }
        competition.ajoutEpreuve((Epreuve) this);
    }
}
