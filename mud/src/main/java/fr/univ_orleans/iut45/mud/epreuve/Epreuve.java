package fr.univ_orleans.iut45.mud.epreuve;

import java.util.Map;


/**
 * L'interface Epreuve représente une épreuve générique pour des participants.
 *
 * @param <T> Le type des participants à l'épreuve.
 */
public interface Epreuve<T> {

    /**
     * Retourne le classement des participants pour l'épreuve sous forme de chaîne de caractères.
     *
     * @return Le classement des participants pour l'épreuve.
     */
    public String classementEpreuve();

    /**
     * Retourne le score d'un participant donné.
     *
     * @param o Le participant dont on souhaite obtenir le score.
     * @return Le score du participant.
     */
    public Integer getScore(T o);

    /**
     * Définit le score d'un participant donné.
     *
     * @param o Le participant dont on souhaite définir le score.
     * @param valeur Le score à attribuer au participant.
     */
    public void setScore(T o, Integer valeur);

    /**
     * Retourne le score théorique d'un participant donné.
     *
     * @param o Le participant dont on souhaite obtenir le score théorique.
     * @return Le score théorique du participant.
     */
    public Integer getScoreTheorique(T o);

    /**
     * Retourne le nom de l'épreuve.
     *
     * @return Le nom de l'épreuve.
     */
    public String getNom();

    /**
     * Retourne le classement théorique des participants pour l'épreuve sous forme de chaîne de caractères.
     *
     * @return Le classement théorique des participants pour l'épreuve.
     */
    public String classementTheorique();

    /**
     * Retourne les données du classement sous forme de map, où la clé est la position dans le classement
     * et la valeur est le participant correspondant.
     *
     * @return Les données du classement.
     */
    public Map<Integer, T> getDonneesClassement();
}

