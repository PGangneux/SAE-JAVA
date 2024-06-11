package fr.univ_orleans.iut45.mud;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe représentant une équipe participant.
 */
public class Equipe implements Participant {
    /**
     * Nom de l'équipe.
     */
    private String nom;
    
    /**
     * Sexe de l'équipe ("M" pour masculin, "F" pour féminin).
     */
    private String sexe;
    
    /**
     * Pays de l'équipe.
     */
    private Pays pays;
    
    /**
     * Sport pratiqué par l'équipe.
     */
    private Sport sport;
    
    /**
     * Liste des athlètes de l'équipe.
     */
    private List<Athlete> liAthlete;

    /**
     * Constructeur pour initialiser une équipe avec les détails donnés.
     *
     * @param nom   Nom de l'équipe.
     * @param sexe  Sexe de l'équipe.
     * @param pays  Pays de l'équipe.
     * @param sport Sport pratiqué par l'équipe.
     */
    public Equipe(String nom, String sexe, Pays pays, Sport sport) {
        this.nom = nom;
        this.sexe = sexe;
        this.pays = pays;
        this.sport = sport;
        this.liAthlete = new ArrayList<>();
    }

    /**
     * @return le nom de l'équipe.
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Définit le nom de l'équipe.
     *
     * @param nom un nom d'équipe.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return le sexe de l'équipe.
     */
    public String getSexe() {
        return this.sexe;
    }

    /**
     * @return le pays de l'équipe.
     */
    public Pays getPays() {
        return this.pays;
    }

    /**
     * Définit le pays de l'équipe.
     *
     * @param pays le nouveau pays de l'équipe.
     */
    public void setPays(Pays pays) {
        this.pays = pays;
    }

    /**
     * @return le sport pratiqué par l'équipe.
     */
    public Sport getSport() {
        return this.sport;
    }

    /**
     * Définit le sport pratiqué par l'équipe.
     *
     * @param sport le nouveau sport de l'équipe.
     */
    public void setSport(Sport sport) {
        this.sport = sport;
    }

    /**
     * @return la liste des athlètes de l'équipe.
     */
    public List<Athlete> getLiAthlete() {
        return this.liAthlete;
    }

    /**
     * Ajoute un athlète à l'équipe. Ne l'ajoutera pas si les sexes, les pays ou les sports sont différents.
     *
     * @param athlete un athlète.
     */
    public void ajouteAthlete(Athlete athlete) {
        if (athlete.getSexe().equals(this.getSexe())) {
            if (athlete.getPays().equals(this.getPays())) {
                if (athlete.getSport().equals(this.getSport())) {
                    this.liAthlete.add(athlete);
                } else {
                    System.out.println("Le sport de l'athlète ne correspond pas avec le sport de l'équipe");
                }
            } else {
                System.out.println("Le pays de l'athlète ne correspond pas avec le pays de l'équipe");
            }
        } else {
            System.out.println("Le sexe de l'athlète ne correspond pas avec le sexe de l'équipe");
        }
    }

    /**
     * Supprime un athlète de l'équipe.
     *
     * @param athlete un athlète.
     */
    public void supAthlete(Athlete athlete) {
        this.liAthlete.remove(athlete);
    }

    /**
     * Vérifie si deux équipes sont égales en se basant sur leurs attributs.
     *
     * @param o l'objet à comparer.
     * @return true si les équipes sont égales, sinon false.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof Equipe)) {
            return false;
        }
        Equipe tmp = (Equipe) o;
        return tmp.getNom().equals(this.getNom()) && tmp.getSexe().equals(this.getSexe()) && tmp.getPays().equals(this.getPays()) && tmp.getSport().equals(this.getSport());
    }

    /**
     * Calcule le code de hachage pour l'équipe en se basant sur ses attributs.
     *
     * @return le code de hachage de l'équipe.
     */
    @Override
    public int hashCode() {
        return Objects.hash(nom, sexe, pays, sport);
    }
}

