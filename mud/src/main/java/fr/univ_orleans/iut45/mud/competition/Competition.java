package fr.univ_orleans.iut45.mud.competition;
import java.util.List;

import fr.univ_orleans.iut45.mud.items.Sport;


/**
 * L'interface Competition représente une compétition générique pour des participants et des épreuves.
 *
 * @param <T> Le type des participants à la compétition (e.g., Athlete, Equipe).
 * @param <E> Le type des épreuves de la compétition (e.g., EpreuveInd, EpreuveCoop).
 */
public interface Competition<T, E> {

    /**
     * Retourne le nom de la compétition.
     *
     * @return Le nom de la compétition.
     */
    public String getNom();

    /**
     * Retourne le sexe des participants.
     *
     * @return Le sexe des participants.
     */
    public String getSexe();

    /**
     * Retourne le sport associé à la compétition.
     *
     * @return Le sport associé à la compétition.
     */
    public Sport getSport();

    /**
     * Retourne la liste des participants à la compétition.
     *
     * @return La liste des participants.
     */
    public List<T> getParticipant();

    /**
     * Retourne le classement des participants dans la compétition sous forme de liste de Participant.
     *
     * @return Le classement des participants.
     */
    public List<T> classement();

    /**
     * Ajoute un participant à la compétition.
     *
     * @param participant Le participant à ajouter.
     * @return Le score théorique c'est à dire agilité + endurance + force.
     */
    public Integer participer(T participant);

    /**
     * Supprime un participant de la compétition.
     *
     * @param participant Le participant à supprimer.
     */
    public void suppParticipant(T participant);

    /**
     * Définit un nouveau nom pour la compétition.
     *
     * @param nom Le nouveau nom de la compétition.
     */
    public void setNom(String nom);

    /**
     * Vérifie si un participant est présent dans la liste des participants.
     *
     * @param participant Le participant à vérifier.
     * @return true si le participant est présent, false sinon.
     */
    public boolean participantPresent(T participant);

    /**
     * Ajoute une épreuve à la liste des épreuves de la compétition.
     *
     * @param epreuve L'épreuve à ajouter.
     */
    public void ajoutEpreuve(E epreuve);

    /**
     * Retourne la liste des épreuves de la compétition.
     *
     * @return La liste des épreuves.
     */
    public List<E> getLiEpreuves();

    /**
     * Attribue des médailles aux meilleurs participants de la compétition.
     */
    public void attribuerMedaille();
}