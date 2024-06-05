package fr.univ_orleans.iut45.mud;

import java.util.ArrayList;
import java.util.List;

public class Sport {
    private String nom;
    private List<CompetInd> liCompetInd;
    private List<CompetCoop> liCompetCoop;
    private boolean collectif;

    public Sport(String nom, boolean collectif) {
        this.nom = nom;
        this.collectif = collectif;
        if (this.collectif){this.liCompetCoop  = new ArrayList<>();}
        
        this.liCompetInd = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public boolean isCollectif() {
        return collectif;
    }

    public void setCollectif(boolean collectif) {
        this.collectif = collectif;
    }

    public List<CompetInd> getLiCompetInd() {
        return liCompetInd;
    }

    public List<CompetCoop> getLiCompetCoop() {
        return liCompetCoop;
    }

    public void ajouteCompetInd(CompetInd compet){
        this.liCompetInd.add(compet);
    }



    public void ajouteCompetCoop(CompetCoop compet){
        if (this.collectif){this.liCompetCoop.add(compet);}  
    }

    @Override
    public boolean equals(Object object){
        if (object == null){return false;}
        if(object == this){return true;}
        if(!(object instanceof Sport)){return false;}
        Sport tmp = (Sport) object;
        return tmp.getNom().equals(tmp.getNom());
    }

    
    @Override
    public int hashCode(){
        return this.getNom().hashCode();
    }
    
}
