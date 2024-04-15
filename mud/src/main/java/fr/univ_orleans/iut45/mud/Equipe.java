package fr.univ_orleans.iut45.mud;
import java.util.ArrayList;
import java.util.List;

public class Equipe {
    private String nom;
    private String sexe;
    private List<Athlete> liAthlete;

    /**
     * Constructeur de Equipe
     * @param nom le nom de l'équipe
     * @param sexe le sexe de l'équipe
     */
    public Equipe(String nom, String sexe){
        this.nom = nom;
        this.sexe = sexe;
        this.liAthlete = new ArrayList<>();
    }

    /**
     * getter de nom
     * @return le nom de l'équipe
     */
    public String getNom() {
        return nom;
    }

    /**
     * setter de nom
     * @param nom un nom d'équipe
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * getter de sexe
     * @return le sexe de l'équipe
     */
    public String getSexe() {
        return sexe;
    }

    /**
     * Permet de vérifier si le sexe de l'équipe correspond au sexe de l'athlète
     * @param athlete un athlète
     * @return true si le sexe de l'équipe et de l'athlète est le même. Sinon false
     */
    public boolean verifSexe(Athlete athlete) {
        return athlete.getSexe()==this.sexe;
    }

    /**
     * Permet d'avoir les athlètes de l'équipe
     * @return la liste d'athlètes
     */
    public List<Athlete> getLiAthlete() {
        return liAthlete;
    }

    /**
     * Permet d'ajouter un athlète à l'équipe. Ne l'ajoutera pas si les sexes sont différents
     * @param athlete un athlète
     */
    public void ajouteAthlete(Athlete athlete){
        if verifSexe(athlete){
            this.liAthlete.add(athlete);
        }
    }

    /**
     * Permet de supprimer un athlète de l'équipe
     * @param athlete un athlète
     */
    public void supAthlete(Athlete athlete){
        this.liAthlete.remove(athlete);
    }
}

