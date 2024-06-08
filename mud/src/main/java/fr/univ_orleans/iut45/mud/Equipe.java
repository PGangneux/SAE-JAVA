package fr.univ_orleans.iut45.mud;
import java.util.ArrayList;
import java.util.List;

public class Equipe implements Participant{
    private String nom;
    private String sexe;
    private Pays pays;
    private Sport sport;
    private List<Athlete> liAthlete;
    

    public Equipe(String nom, String sexe, Pays pays, Sport sport){
        this.nom = nom;
        this.sexe = sexe;
        this.pays = pays;
        this.sport = sport;
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

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public List<Athlete> getLiAthlete() {
        return liAthlete;
    }

    public void ajouteAthlete(Athlete athlete){
        if (athlete.getSexe().equals(this.getSexe())){
            if(athlete.getPays().equals(this.getPays())){
                if(athlete.getSport().equals(this.getSport())){
                    this.liAthlete.add(athlete);
                }
                else{System.out.println("Le sport de l'athlète ne correspond pas avec le sport de l'équipe");}
            }
            else{System.out.println("Le pays de l'athlète ne correspond pas avec le pays de l'équipe");}
        }
        else{System.out.println("Le sexe de l'athlète ne correspond pas avec le sexe de l'équipe");}
    }

    public void supAthlete(Athlete athlete){
        this.liAthlete.remove(athlete);
    }
}

