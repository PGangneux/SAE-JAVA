package fr.univ_orleans.iut45.mud;

public class Pays {
    private String nom;

    public Pays(String nom){
        this.nom = nom;
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
}
