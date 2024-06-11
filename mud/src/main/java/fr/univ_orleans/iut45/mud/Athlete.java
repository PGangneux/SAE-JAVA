package fr.univ_orleans.iut45.mud;

import java.util.Objects;

public class Athlete implements Participant, Comparable<Athlete>{
    private String nom;
    private String prenom;
    private String sexe;
    private Pays pays;
    private Sport sport;
    private Integer force;
    private Integer agilite;
    private Integer endurance;
    
    public Athlete(String nom, String prenom, String sexe, Pays pays, Sport sport, Integer force, Integer agilite, Integer endurance){
        this.nom = nom;
        this.prenom = prenom;
        this.sexe =sexe;
        this.pays = pays;
        this.sport = sport;
        this.force = force;
        this.agilite = agilite;
        this.endurance = endurance;
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

    public Integer getEndurance() {
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

    public void setEndurance(Integer endurance) {
        this.endurance = endurance;
    }

    @Override
    public boolean equals(Object o){
        if(o==null){return false;}
        if(o==this){return true;}
        if(!(o instanceof Athlete)){return false;}
        Athlete tmp = (Athlete) o;
        return tmp.getNom().equals(this.getNom()) && tmp.getPrenom().equals(this.getPrenom()) && tmp.getSexe().equals(this.getSexe()) && tmp.getPays().equals(this.getPays()) && tmp.getSport().equals(this.getSport());
    }

    @Override
    public int hashCode(){
        Object nom = (Object) this.nom;
        Object prenom = (Object) this.prenom;
        Object sexe = (Object) this.sexe;
        Object pays = (Object) this.pays;
        Object sport = (Object) this.sport;
        return Objects.hash(nom,prenom,sexe,sport,pays);
    }

    @Override
    public int compareTo(Athlete athlete){
        return (athlete.getAgilite()+athlete.getForce()+athlete.getEndurance()) - (this.getAgilite()+this.getForce()+this.getEndurance());
    }
    

}