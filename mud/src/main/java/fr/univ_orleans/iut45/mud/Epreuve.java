package fr.univ_orleans.iut45.mud;

import java.util.Map;


public interface Epreuve<T> {

    public String classementEpreuve();
    public Integer getScore(T o);
    public void setScore(T o, Integer valeur);
    public Integer getScoreTheorique(T o);
    public String getNom();
    public String classementTheorique();
    public Map<Integer,T> getDonneesClassement();

}

