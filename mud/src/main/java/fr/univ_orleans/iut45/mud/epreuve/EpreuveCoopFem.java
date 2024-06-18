package fr.univ_orleans.iut45.mud.epreuve;

import fr.univ_orleans.iut45.mud.competition.CompetCoop;
import fr.univ_orleans.iut45.mud.items.Equipe;
import fr.univ_orleans.iut45.mud.items.Participant;

/**
 * La classe EpreuveCoopFem représente une épreuve coopérative spécifiquement pour les équipes féminines.
 */
public class EpreuveCoopFem extends EpreuveCoop {

    /**
     * Constructeur pour créer une épreuve coopérative féminine.
     * Les équipes féminines de la compétition sont ajoutées à l'épreuve avec un score initial de null.
     * L'epreuve est automatiquement ajouter à la liste de la Compétition en paramètre.
     *
     * @param nom Le nom de l'épreuve.
     * @param competition La compétition associée.
     */
    public EpreuveCoopFem(String nom, CompetCoop competition){
        super(nom, competition);
        for(Participant e : competition.getParticipant()){
            Equipe equipe = (Equipe)e;
            if(e.getSexe().equals("F")){
                this.scores.put(equipe, null);
            }
        }
        competition.ajoutEpreuve(this);
    }

}

