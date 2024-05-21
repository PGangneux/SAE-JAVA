package fr.univ_orleans.iut45.mud;

public interface Epreuve {

    public String classementEpreuve();
    public int getScore(Object o);
    public void setScore(Object o, Integer valeur);
    public String getNom();

}