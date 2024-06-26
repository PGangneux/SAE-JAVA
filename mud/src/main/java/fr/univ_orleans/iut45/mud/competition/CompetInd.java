package fr.univ_orleans.iut45.mud.competition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.univ_orleans.iut45.mud.comparator.ComparateurCompetInd;
import fr.univ_orleans.iut45.mud.epreuve.EpreuveInd;
import fr.univ_orleans.iut45.mud.items.Athlete;
import fr.univ_orleans.iut45.mud.items.Sport;


/**
 * La classe CompetInd représente une compétition individuelle pour les athlètes.
 */
public class CompetInd implements Competition<Athlete , EpreuveInd>{


    /**
     * Le nom de la compétition.
     */
    private String nom;
    
    /**
     * Le sexe des participants à la compétition ("M" ou "F").
     */
    private String sexe;
    
    /**
     * Le sport associé à la compétition.
     */
    private Sport sport;
    
    /**
     * La liste des épreuves individuelles de la compétition.
     */
    private List<EpreuveInd> liEpreuve;
    
    /**
     * La liste des athlètes participant à la compétition.
     */
    private List<Athlete> liAthletes;

    /**
     * Constructeur pour créer une compétition individuelle.
     * Ajout automatique de la compétitions dans la liste de compétitions de Sport.
     *
     * @param nom Le nom de la compétition.
     * @param sexe Le sexe des participants ("M" ou "F").
     * @param sport Le sport associé à la compétition.
     */
    public CompetInd(String nom, String sexe, Sport sport){
        this.nom=nom;
        this.sexe = sexe;
        this.sport = sport;
        this.liAthletes = new ArrayList<>();
        this.liEpreuve = new ArrayList<>();
        this.sport.getLiCompetInd().add(this);
    }


    /**
     * Retourne le nom de la compétition.
     *
     * @return Le nom de la compétition.
     */
    @Override
    public String getNom(){
        return this.nom;
    }


    /**
     * Retourne le sexe des participants.
     *
     * @return Le sexe des participants.
     */
    @Override
    public String getSexe(){
        return this.sexe;
    }
    

    /**
     * Retourne le sport associé à la compétition.
     *
     * @return Le sport associé à la compétition.
     */
    @Override
    public Sport getSport() {
        return this.sport;
    }


    /**
     * Retourne la liste des participants (athlètes) de la compétition.
     *
     * @return La liste des participants.
     */
    @Override
    public List<Athlete> getParticipant(){
        return  this.liAthletes;
    }


    /**
     * Retourne la liste des épreuves individuelles de la compétition.
     *
     * @return La liste des épreuves individuelles.
     */
    @Override
    public List<EpreuveInd> getLiEpreuves(){
        return this.liEpreuve;
    }

    @Override
    public List<Athlete> classement(){
        //String texte="Place | Athlete" +System.lineSeparator();
        Map<Athlete, Integer> dico = new HashMap<>();
        for(Athlete a : this.liAthletes){
            dico.put(a, 0);
        }

        for(EpreuveInd epreuveCoop : this.liEpreuve){
            Map<Integer,Athlete> donnees = epreuveCoop.getDonneesClassement();
            for(Integer i : donnees.keySet()){
                dico.put(donnees.get(i), (dico.get((donnees.get(i)))+i));
            }
        }

        List<Athlete> liste = new ArrayList<>();
        for(Athlete a : this.liAthletes){
            liste.add(a);
        }

        ComparateurCompetInd comparator = new ComparateurCompetInd(dico);
        
        Collections.sort(liste , comparator);
        /*
        for(Athlete e : liste){
            texte += (liste.indexOf(e)+1)+" | "+e.getNom()+System.lineSeparator();
        }
        */
        
        return liste;
    }


    /**
     * Ajoute un athlète à la compétition si son sexe et son sport correspondent à ceux de la compétition.
     *
     * @param participant L'athlète participant à ajouter.
     * @return Le score théorique de l'athlète c'est à dire agilité + endurance + force.
     */
    @Override
    public Integer participer(Athlete participant){
        Integer scoreTheorique = 0; 
        if (participant.getSexe().equals(this.getSexe()) && participant.getSport().equals(this.getSport())){
            this.liAthletes.add(participant);
            scoreTheorique = participant.getForce() + participant.getAgilite() + participant.getEndurance();
            return scoreTheorique;
        }
        return 0;
    }


    /**
     * Supprime un athlète de la compétition.
     *
     * @param participant L'athlète participant à supprimer.
     */
    @Override
    public void suppParticipant(Athlete participant){
        this.liAthletes.remove(participant);
    }


    /**
     * Définit un nouveau nom pour la compétition.
     *
     * @param newNom Le nouveau nom de la compétition.
     */
    @Override
    public void setNom(String newNom){
        this.nom = newNom;
    }


    /**
     * Vérifie si un athlète est présent dans la liste des participants.
     *
     * @param participant L'athlète à vérifier.
     * @return true si l'athlète est présent, false sinon.
     */
    @Override
    public boolean participantPresent(Athlete participant){
        return this.liAthletes.contains(participant);
    }


    /**
     * Ajoute une épreuve individuelle à la liste des épreuves de la compétition.
     *
     * @param epreuve L'épreuve individuelle à ajouter.
     */
    @Override
    public void ajoutEpreuve(EpreuveInd epreuve){
        this.liEpreuve.add(epreuve);
    }

    @Override 
    public void suppEpreuve(EpreuveInd epreuve){
        this.liEpreuve.remove(epreuve);
    }

    


    /**
     * Attribue des médailles aux trois premiers athlètes de la compétition.
     * L'athlète en première place reçoit une médaille d'or.
     * Les trois premiers athlètes reçoivent chacun une médaille.
     * Ces Médailles sont ajouter aux compteur de leurs pays
     */
    @Override
    public void attribuerMedaille(){
        Map<Athlete, Integer> dico = new HashMap<>();
        for(Athlete a : this.liAthletes){
            dico.put(a, 0);
        }

        for(EpreuveInd epreuveCoop : this.liEpreuve){
            Map<Integer,Athlete> donnees = epreuveCoop.getDonneesClassement();
            for(Integer i : donnees.keySet()){
                dico.put(donnees.get(i), (dico.get((donnees.get(i)))+i));
            }
        }

        List<Athlete> liste = new ArrayList<>();
        for(Athlete a : this.liAthletes){
            liste.add(a);
        }

        ComparateurCompetInd comparator = new ComparateurCompetInd(dico);
        Collections.sort(liste , comparator);

        for(int i=0 ; i<3; i++){
            if(i==0){
                liste.get(i).getPays().setCompteurMedailleOr(liste.get(i).getPays().getCompteurMedailleOr()+1);
            }
            else if(i==1){
                liste.get(i).getPays().setCompteurMedailleArgent(liste.get(i).getPays().getCompteurMedailleArgent()+1);
            }
            else if(i==2){
                liste.get(i).getPays().setCompteurMedailleBronze(liste.get(i).getPays().getCompteurMedailleBronze()+1);
            }
            liste.get(i).getPays().setCompteurMedaille(liste.get(i).getPays().getCompteurMedaille()+1);
        }
    }
    


}