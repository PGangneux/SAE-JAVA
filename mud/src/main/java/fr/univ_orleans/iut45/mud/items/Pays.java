package fr.univ_orleans.iut45.mud.items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import fr.univ_orleans.iut45.mud.comparator.CompareArgent;
import fr.univ_orleans.iut45.mud.comparator.CompareBronze;
import fr.univ_orleans.iut45.mud.comparator.CompareOr;



/**
 * La classe Pays représente un pays.
 */
public class Pays implements Comparable<Pays>{
    private String nom;
    private int compteurMedaille;
    private int compteurMedailleOr;
    private int compteurMedailleArgent;
    private int compteurMedailleBronze;


    /**
     * Constructeur pour créer un pays avec un nom spécifié.
     *
     * @param nom Le nom du pays.
     */
    public Pays(String nom){
        this.nom = nom;
        this.compteurMedaille = 0;
        this.compteurMedailleOr = 0;
        this.compteurMedailleArgent=0;
        this.compteurMedailleBronze=0;
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

    /**
     * Retourne le compteur de médailles d'Argent du pays.
     *
     * @return Le compteur de médailles d'Argent.
     */
    public int getCompteurMedailleArgent() {
        return compteurMedailleArgent;
    }


    /**
     * Définit le compteur de médailles d'Argent du pays.
     *
     * @param compteurMedailleArgent Le nouveau compteur de médailles d'Argent.
     */
    public void setCompteurMedailleArgent(int compteurMedailleArgent) {
        this.compteurMedailleArgent = compteurMedailleArgent;
    }

    /**
     * Retourne le compteur de médailles de Bronze du pays.
     *
     * @return Le compteur de médailles de Bronze.
     */
    public int getCompteurMedailleBronze() {
        return compteurMedailleBronze;
    }


    /**
     * Définit le compteur de médailles de Bronze du pays.
     *
     * @param compteurMedailleBronze Le nouveau compteur de médailles de Bronze.
     */
    public void setCompteurMedailleBronze(int compteurMedailleBronze) {
        this.compteurMedailleBronze = compteurMedailleBronze;
    }

    /**
     * Vérifie l'égalité entre ce pays et un autre objet. Ils sont égaux si l'objet est un pays et qu'ils portent le meme nom.
     *
     * @param objet L'objet à comparer avec ce pays.
     * @return true si les objets sont égaux, false sinon.
     */
    @Override
    public boolean equals(Object objet){
        if (objet == null){return false;}
        if (objet == this){return true;}
        if(! (objet instanceof Pays)){return false;}
        Pays tmp = (Pays) objet;
        return tmp.getNom().equals(this.getNom());
    }


    /**
     * Retourne le code de hachage pour ce pays.
     *
     * @return Le code de hachage basé sur le nom du pays.
     */
    @Override
    public int hashCode() {
        return this.getNom().hashCode();
        
    }


    /**
     * Compare ce pays à un autre pays pour l'ordre naturel (basé sur le compteur de médailles).
     *
     * @param unPays Le pays à comparer.
     * @return une valeur négative, zéro ou une valeur positive selon que ce pays est moins que,
     *         égal à ou plus grand que le pays spécifié.
     */
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

     /**
     * Retourne une liste des pays triés par nombre de médailles d'Argent.
     *
     * @param ensPays L'ensemble des pays à trier.
     * @return La liste triée des pays par nombre de médailles d'Argent.
     */    
    public static List<Pays> classementPaysMedailleArgent(Set<Pays> ensPays){
        List<Pays> liPays = new ArrayList<>(ensPays);
        CompareArgent compareArgent = new CompareArgent();
        Collections.sort(liPays, compareArgent);
        return liPays;
    }

     /**
     * Retourne une liste des pays triés par nombre de médailles de Bronze.
     *
     * @param ensPays L'ensemble des pays à trier.
     * @return La liste triée des pays par nombre de médailles de Bronze.
     */    
    public static List<Pays> classementPaysMedailleBronze(Set<Pays> ensPays){
        List<Pays> liPays = new ArrayList<>(ensPays);
        CompareBronze compareBronze = new CompareBronze();
        Collections.sort(liPays, compareBronze);
        return liPays;
    }
}
