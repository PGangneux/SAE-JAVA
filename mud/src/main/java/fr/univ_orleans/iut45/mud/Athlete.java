package fr.univ_orleans.iut45.mud;

public class Athlete implements Participant{
    private String nom;
    private String prenom;
    private String sexe;
    private Pays pays;
    private Sport sport;
    private Integer force;
    private Integer agilite;
    private Interger endurance;
    
    public Athlete(String nom, String prenom, String sexe, Pays pays, Sport sport, Integer force, Integer agilite, Integer endurence){
        this.nom = nom;
        this.prenom = prenom;
        this.sexe =sexe;
        this.pays = pays;
        this.sport = sport;
        this.force = force;
        this.agilite = agilite;
        this.endurance = endurence;
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


    public Integer getForce() {
        return force;
    }

    public Integer getAgilite() {
        return agilite;
    }

    public Interger getEndurance() {
        return endurance;
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

    public void setForce(Integer force) {
        this.force = force;
    }

    public void setAgilite(Integer agilite) {
        this.agilite = agilite;
    }

    public void setEndurance(Interger endurance) {
        this.endurance = endurance;
    }

    

}
