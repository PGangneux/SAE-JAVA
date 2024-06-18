package fr.univ_orleans.iut45.mud.items;

/**
 * L'interface Participant définit les méthodes nécessaires pour représenter un participant à une compétition.
 */
public interface Participant {

    /**
     * Retourne le nom du participant.
     *
     * @return Le nom du participant.
     */
    public String getNom();

    /**
     * Retourne le sexe du participant.
     *
     * @return Le sexe du participant.
     */
    public String getSexe();

    /**
     * Retourne le sport auquel le participant participe.
     *
     * @return Le sport du participant.
     */
    public Sport getSport();

    /**
     * Retourne le pays que le participant représente.
     *
     * @return Le pays du participant.
     */
    public Pays getPays();
    
}