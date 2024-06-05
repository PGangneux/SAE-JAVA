package fr.univ_orleans.iut45.mud;
import java.util.ArrayList;
import java.util.List;

public class Equipe implements Participant{
    private String nom;
    private String sexe;
    private List<Athlete> liAthlete;

    public Equipe(String nom, String sexe){
        this.nom = nom;
        this.sexe = sexe;
        this.liAthlete = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSexe() {
        return sexe;
    }

    public boolean verifSexe(Athlete athlete) {
        return athlete.getSexe()==this.sexe;
    }

    public List<Athlete> getLiAthlete() {
        return liAthlete;
    }

    public void ajouteAthlete(Athlete athlete){
        if (this.verifSexe(athlete)){
            this.liAthlete.add(athlete);
        }
    }

    public void supAthlete(Athlete athlete){
        this.liAthlete.remove(athlete);
    }
}

