package fr.univ_orleans.iut45.mud.epreuve;

import fr.univ_orleans.iut45.mud.competition.CompetInd;
import fr.univ_orleans.iut45.mud.items.Athlete;
import fr.univ_orleans.iut45.mud.items.Participant;

/**
 * La classe EpreuveIndMasc représente une épreuve individuelle spécifiquement pour les athlètes masculins.
 */
public class EpreuveIndMasc extends EpreuveInd {
    
    /**
     * Constructeur pour créer une épreuve individuelle masculine avec un nom et une compétition associée.
     * Les athlètes masculins de la compétition sont ajoutés à l'épreuve avec un score initial de null.
     * L'epreuve est automatiquement ajouter à la liste de la Compétition en paramètre.
     *
     * @param nom Le nom de l'épreuve.
     * @param competition La compétition individuelle associée.
     */
    public EpreuveIndMasc(String nom, CompetInd competition){
        super(nom, competition);
        for(Participant a : competition.getParticipant()){
            Athlete athlete = (Athlete)a;
            if(a.getSexe().equals("H") || a.getSexe().equals("M")){
                this.scores.put(athlete, null);
            }
        }
        competition.ajoutEpreuve(this);
    }
}
