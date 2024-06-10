package fr.univ_orleans.iut45.mud;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    /**
     * Permet de supprimer un athlète de l'équipe
     * @param athlete un athlète
     */
    public void supAthlete(Athlete athlete){
        this.liAthlete.remove(athlete);
    }

    @Override
    public boolean equals(Object o){
        if(o==null){return false;}
        if(o==this){return true;}
        if(!(o instanceof Equipe)){return false;}
        Equipe tmp = (Equipe) o;
        return tmp.getNom().equals(this.getNom()) && tmp.getSexe().equals(this.getSexe()) && tmp.getPays().equals(this.getPays()) && tmp.getSport().equals(this.getSport());
    }

    @Override
    public int hashCode(){
        Object nom = (Object) this.nom;
        Object sexe = (Object) this.sexe;
        Object pays = (Object) this.pays;
        Object sport = (Object) this.sport;
        return Objects.hash(nom,sexe,sport,pays);
    }
}

