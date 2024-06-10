package fr.univ_orleans.iut45.mud;

import java.util.Set;

public class Pays {
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

    public static Set<Pays> ()
}
