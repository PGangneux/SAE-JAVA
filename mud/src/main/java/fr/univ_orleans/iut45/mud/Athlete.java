package fr.univ_orleans.iut45.mud;

import java.util.Objects;

/**
 * Classe représentant un athlète participant.
 */
public class Athlete implements Participant, Comparable<Athlete>{
    /**
     * nom de l'athlète
     */
    private String nom;
    /**
     * c
     */
    private String prenom;
    /**
     * sexe de l'athlète
     */
    private String sexe;
    /**
     * pays de l'athlète
     */
    private Pays pays;
    /**
     * sport pratiquer par l'athlète
     */
    private Sport sport;
    /**
     * points de force de l'athlète
     */
    private Integer force;
    /**
     * points d'agilité de l'athlète
     */
    private Integer agilite;
    /**
     * points d'endurance de l'athlète
     */
    private Integer endurance;
    
    /**
     * 
     * @param nom nom de l'athlète
     * @param prenom nom de l'athlète
     * @param sexe sexe de l'athlète
     * @param pays pays de l'athlète
     * @param sport sport pratiqué par l'athlète
     * @param force points de force de l'athlète
     * @param agilite points d'agilité de l'athlète
     * @param endurance  points d'endurance de l'athlète
     */
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

    /**
     * Retourne le nom de l'athlète
     * @return le nom de l'athlète
     */
    @Override
    public String getNom() {
        return this.nom;
    }

    /**
     * Retourne le prenom de l'athlète
     * @return le prenom de l'athlète
     */
    public String getPrenom() {
        return this.prenom;
    }

    /**
     * Retourne le sexe de l'athlète "F" ou "M"
     * @return le sexe de l'athlète "F" ou "M"
     */
    @Override
    public String getSexe() {
        return this.sexe;
    }

    /**
     * Retourne le pays de l'athlète 
     * @return le pays de l'athlète 
     */
    @Override
    public Pays getPays() {
        return this.pays;
    }

    
    /**
     * Retourne le sport de l'athlète 
     * @return le sport de l'athlète 
     */
    @Override
    public Sport getSport(){
        return this.sport;
    }


    /**
     * Retourne la force de l'athlète.
     * @return la force de l'athlète.
     */
    public Integer getForce() {
        return this.force;
    }

    /**
     * Retourne l'agilité de l'athlète
     * @return l'agilité de l'athlète
     */
    public Integer getAgilite() {
        return this.agilite;
    }

    /**
     * Retourne l'endurance de l'athlète.
     * @return l'endurance de l'athlète.
     */
    public Integer getEndurance() {
        return this.endurance;
    }

    /**
     * Définit le nom de l'athlète.
     *
     * @param nom le nouveau nom de l'athlète.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Définit le prénom de l'athlète.
     *
     * @param prenom le nouveau prénom de l'athlète.
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Définit le sexe de l'athlète.
     *
     * @param sexe le nouveau sexe de l'athlète.
     */
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    /**
     * Définit le pays de l'athlète.
     *
     * @param pays le nouveau pays de l'athlète.
     */
    public void setPays(Pays pays) {
        this.pays = pays;
    }

    /**
     * Définit le sport pratiqué par l'athlète.
     *
     * @param sport le nouveau sport de l'athlète.
     */
    public void setSport(Sport sport) {
        this.sport = sport;
    }

    /**
     * Définit la force de l'athlète.
     *
     * @param force les nouveaux points de force de l'athlète.
     */
    public void setForce(Integer force) {
        this.force = force;
    }

    /**
     * Définit l'agilité de l'athlète.
     *
     * @param agilite les nouveaux points d'agilité de l'athlète.
     */
    public void setAgilite(Integer agilite) {
        this.agilite = agilite;
    }

    /**
     * Définit l'endurance de l'athlète.
     *
     * @param endurance les nouveaux points d'endurance de l'athlète.
     */
    public void setEndurance(Integer endurance) {
        this.endurance = endurance;
    }

    /**
     * Vérifie si deux athlètes sont égaux en se basant sur leurs attributs.
     *
     * @param o l'objet à comparer.
     * @return true si les athlètes sont égaux, sinon false.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof Athlete)) {
            return false;
        }
        Athlete tmp = (Athlete) o;
        return tmp.getNom().equals(this.getNom()) && tmp.getPrenom().equals(this.getPrenom()) && tmp.getSexe().equals(this.getSexe()) && tmp.getPays().equals(this.getPays()) && tmp.getSport().equals(this.getSport());
    }

    /**
     * Calcule le code de hachage pour l'athlète en se basant sur ses attributs.
     *
     * @return le code de hachage de l'athlète.
     */
    @Override
    public int hashCode() {
        return Objects.hash(nom, prenom, sexe, pays, sport);
    }


    /**
     * Compare l'athlète actuel à un autre athlète en fonction de la somme de leurs points d'agilité, de force et d'endurance.
     *
     * @param athlete L'athlète à comparer.
     * @return une valeur négative si l'athlète actuel a une somme de points supérieure, zéro s'ils ont la même somme, une valeur positive sinon.
     */
    @Override
    public int compareTo(Athlete athlete){
        return (athlete.getAgilite()+athlete.getForce()+athlete.getEndurance()) - (this.getAgilite()+this.getForce()+this.getEndurance());
    }
    
}