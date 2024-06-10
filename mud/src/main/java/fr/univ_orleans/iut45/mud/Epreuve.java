package fr.univ_orleans.iut45.mud;


public interface Epreuve<T> {

    public String classementEpreuve();
    public int getScore(T o);
    public void setScore(T o, Integer valeur);
    public int getScoreTheorique(T o);
    public String getNom();

}

