package fr.univ_orleans.iut45.mud;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;



/**
 * La classe Pays représente un pays.
 */
public class Pays implements Comparable<Pays>{
    private String nom;
    private int compteurMedaille;
    private int compteurMedailleOr;


    /**
     * Constructeur pour créer un pays avec un nom spécifié.
     *
     * @param nom Le nom du pays.
     */
    public Pays(String nom){
        this.nom = nom;
        this.compteurMedaille = 0;
        this.compteurMedailleOr = 0;
    }


    /**
     * Retourne le nom du pays.
     *
     * @return Le nom du pays.
     */
    public String getNom() {
        return this.nom;
    }
    

    /**
     * Retourne le compteur de médailles du pays.
     *
     * @return Le compteur de médailles.
     */
    public int getCompteurMedaille() {
        return compteurMedaille;
    }


    /**
     * Définit le compteur de médailles du pays.
     *
     * @param compteurMedaille Le nouveau compteur de médailles.
     */
    public void setCompteurMedaille(int compteurMedaille) {
        this.compteurMedaille = compteurMedaille;
    }


    /**
     * Retourne le compteur de médailles d'or du pays.
     *
     * @return Le compteur de médailles d'or.
     */
    public int getCompteurMedailleOr() {
        return compteurMedailleOr;
    }


    /**
     * Définit le compteur de médailles d'or du pays.
     *
     * @param compteurMedailleOr Le nouveau compteur de médailles d'or.
     */
    public void setCompteurMedailleOr(int compteurMedailleOr) {
        this.compteurMedailleOr = compteurMedailleOr;
    }

    @Override
    public boolean equals(Object objet){
        if (objet == null){return false;}
        if (objet == this){return true;}
        if(! (objet instanceof Pays)){return false;}
        Pays tmp = (Pays) objet;
        return tmp.getNom().equals(this.getNom());
    }

    @Override
    public int hashCode() {
        return this.getNom().hashCode();
        
    }

    @Override
    public int compareTo(Pays unPays){
        return  unPays.getCompteurMedaille() - this.compteurMedaille;
    }


    /**
     * Retourne une liste des pays triés par nombre de médailles.
     *
     * @param ensPays L'ensemble des pays à trier.
     * @return La liste triée des pays par nombre de médailles.
     */
    public static List<Pays> classementPaysMedaille(Set<Pays> ensPays){
        List<Pays> liPays = new ArrayList<>(ensPays);
        Collections.sort(liPays);
        System.out.println(liPays);
        return liPays;
    }


     /**
     * Retourne une liste des pays triés par nombre de médailles d'or.
     *
     * @param ensPays L'ensemble des pays à trier.
     * @return La liste triée des pays par nombre de médailles d'or.
     */    
    public static List<Pays> classementPaysMedailleOr(Set<Pays> ensPays){
        List<Pays> liPays = new ArrayList<>(ensPays);
        CompareOr compareOr = new CompareOr();
        Collections.sort(liPays, compareOr);
        return liPays;
    }
}
