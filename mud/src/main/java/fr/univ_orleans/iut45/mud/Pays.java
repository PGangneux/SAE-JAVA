package fr.univ_orleans.iut45.mud;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;



public class Pays implements Comparable<Pays>{
    private String nom;
    private int compteurMedaille;
    private int compteurMedailleOr;

    public Pays(String nom){
        this.nom = nom;
        this.compteurMedaille = 0;
        this.compteurMedailleOr = 0;
    }

    public String getNom() {
        return this.nom;
    }
    

    

    public int getCompteurMedaille() {
        return compteurMedaille;
    }

    public void setCompteurMedaille(int compteurMedaille) {
        this.compteurMedaille = compteurMedaille;
    }

    public int getCompteurMedailleOr() {
        return compteurMedailleOr;
    }

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

    public static List<Pays> classementPaysMedaille(Set<Pays> ensPays){
        List<Pays> liPays = new ArrayList<>(ensPays);
        Collections.sort(liPays);
        System.out.println(liPays);
        return liPays;
    }

    public static List<Pays> classementPaysMedailleOr(Set<Pays> ensPays){
        List<Pays> liPays = new ArrayList<>(ensPays);
        CompareOr compareOr = new CompareOr();
        Collections.sort(liPays, compareOr);
        return liPays;
    }
}
