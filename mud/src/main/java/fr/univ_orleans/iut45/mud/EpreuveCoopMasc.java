package fr.univ_orleans.iut45.mud;


/**
 * La classe EpreuveCoopMasc représente une épreuve coopérative spécifiquement pour les équipes masculines.
 */
public class EpreuveCoopMasc extends EpreuveCoop{
    /**
     * Constructeur pour créer une épreuve coopérative masculine.
     * Les équipes masculines de la compétition sont ajoutées à l'épreuve avec un score initial de null.
     * L'epreuve est automatiquement ajouter à la liste de la Compétition en paramètre.
     *
     * @param nom Le nom de l'épreuve.
     * @param competition La compétition associée.
     */
    public EpreuveCoopMasc(String nom, CompetCoop competition){
        super(nom, competition);
        for(Participant e : competition.getParticipant()){
            Equipe equipe = (Equipe)e;
            if(e.getSexe().equals("H") || (e.getSexe().equals("M"))){
                this.scores.put(equipe, null);
            }
        }
        competition.ajoutEpreuve(this);
    }
}
