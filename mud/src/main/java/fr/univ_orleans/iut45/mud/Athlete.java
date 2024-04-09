package fr.univ_orleans.iut45.mud;

public class Athlete {
    private String nom;
    private String prenom;
    private String sexe;
    private Pays pays;
    private Sport sport;
    
    public Athlete(String nom, String prenom, String sexe, Pays pays, Sport sport){
        this.nom = nom;
        this.prenom = prenom;
        this.sexe =sexe;
        this.pays = pays;
        this.sport = sport;
    }

    public String getNom() {
        return this.nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public String getSexe() {
        return this.sexe;
    }

    public Pays getPays() {
        return this.pays;
    }

    public Sport getSport() {
        return this.sport;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

}
