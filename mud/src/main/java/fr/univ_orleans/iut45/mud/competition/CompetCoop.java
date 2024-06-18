package fr.univ_orleans.iut45.mud.competition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.univ_orleans.iut45.mud.comparator.ComparateurCompetCoop;
import fr.univ_orleans.iut45.mud.epreuve.EpreuveCoop;
import fr.univ_orleans.iut45.mud.items.Athlete;
import fr.univ_orleans.iut45.mud.items.Equipe;
import fr.univ_orleans.iut45.mud.items.Sport;

/**
 * La classe CompetInd représente une compétitions collective pour les équipes
 */
public class CompetCoop implements Competition<Equipe, EpreuveCoop>{
    /**
     * Le nom de la compétition coopérative.
     */
    private String nom;

    /**
     * Le sexe des participants à la compétition.
     */
    private String sexe;

    /**
     * Le sport associé à la compétition.
     */
    private Sport sport;

    /**
     * La liste des épreuves coopératives de la compétition.
     */
    private List<EpreuveCoop> liEpreuve;

    /**
     * La liste des équipes participantes à la compétition.
     */
    private List<Equipe> liEquipe;

    /**
     * Le nombre maximum de joueurs par équipe dans la compétition.
     */
    private int nbJoueursMax;

    /**
     * Constructeur pour initialiser une compétition coopérative.
     * Ajout automatique de la compétitions dans la liste de compétitions de Sport.
     *
     * @param nom Le nom de la compétition.
     * @param sexe Le sexe des participants ("M" ou "F").
     * @param sport Le sport associé à la compétition.
     * @param nbJoueursMax Le nombre maximum de joueurs par équipe.
     */
    public CompetCoop(String nom, String sexe, Sport sport, int nbJoueursMax){
        this.nom=nom;
        this.sexe = sexe;
        this.sport = sport;
        this.liEquipe = new ArrayList<>();
        this.liEpreuve = new ArrayList<>();
        this.nbJoueursMax = nbJoueursMax;
        this.sport.getLiCompetCoop().add(this);
    }


    
    /**
     * Retourne le nombre maximum de joueurs par équipe pour la compétition.
     *
     * @return Le nombre maximum de joueurs par équipe.
     */
    public int getNbJoueursMax() {
        return nbJoueursMax;
    }



    /**
     * Modifie le nombre maximum de joueurs par équipe pour la compétition.
     *
     * @param nbJoueursMax Le nouveau nombre maximum de joueurs par équipe.
     */
    public void setNbJoueursMax(int nbJoueursMax) {
        this.nbJoueursMax = nbJoueursMax;
    }



    /**
     * Retourne la liste des épreuves coopératives de la compétition.
     *
     * @return La liste des épreuves coopératives.
     */
   @Override
    public List<EpreuveCoop> getLiEpreuves(){
        return this.liEpreuve;
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
     * Retourne le sexe des participants à la compétition ("M" ou "F").
     *
     * @return Le sexe des participants.
     */
    @Override
    public String getSexe(){
        return this.sexe;
    }


    /**
     * Retourne la liste des équipes participantes à la compétition.
     *
     * @return La liste des équipes participantes.
     */
    @Override
    public List<Equipe> getParticipant(){
        return this.liEquipe;
    }


    /**
     * Retourne le sport associé à la compétition.
     *
     * @return Le sport associé à la compétition.
     */
    @Override
    public Sport getSport() {
        return sport;
    }


    /**
     * Génère et retourne un texte représentant le classement des équipes dans la compétition.
     * Le classement est calculé en fonction des scores obtenus dans les épreuves coopératives.
     *
     * @return Le texte représentant le classement des équipes.
     */
    @Override
    public String classement(){
        String texte="Place | Equipe" +System.lineSeparator();
        Map<Equipe, Integer> dico = new HashMap<>();
        for(Equipe e : this.liEquipe){
            dico.put(e, 0);
        }

        for(EpreuveCoop epreuve : this.liEpreuve){
            Map<Integer,Equipe> donnees = epreuve.getDonneesClassement();
            for(Integer i : donnees.keySet()){
                dico.put(donnees.get(i), (dico.get((donnees.get(i)))+i));
            }
        }

        List<Equipe> liste = new ArrayList<>();
        for(Equipe e : this.liEquipe){
            liste.add(e);
        }

        ComparateurCompetCoop comparator = new ComparateurCompetCoop(dico);
        Collections.sort(liste , comparator);
        for(Equipe e : liste){
            texte += (liste.indexOf(e)+1)+" | "+e.getNom()+System.lineSeparator();
        }
        
        return texte;
    }


    /**
     * Ajoute une équipe à la liste des participants de la compétition.
     *
     * @param participant L'équipe à ajouter.
     * @return Le score théorique total de l'équipe c'est à dire agilité + endurance + force.
     */
    @Override

    public Integer participer(Equipe participant){
        if (participant.getSexe().equals(this.getSexe()) && participant.getSport().equals(this.getSport())){
            this.liEquipe.add( participant);
            Integer scoreTheorique = 0;
            for (Athlete athlete : participant.getLiAthlete()){
                scoreTheorique += athlete.getForce() + athlete.getAgilite() + athlete.getEndurance();
            }
        }
        return 0;
    }


    /**
     * Supprime une équipe de la liste des participants de la compétition.
     *
     * @param participant L'équipe à supprimer.
     */
    @Override
    public void suppParticipant(Equipe participant){
        this.liEquipe.remove(participant);
    }


    /**
     * Modifie le nom de la compétition.
     *
     * @param newNom Le nouveau nom de la compétition.
     */
    @Override
    public void setNom(String newNom){
        this.nom = newNom;
    }


    /**
     * Vérifie si une équipe est présente parmi les participants de la compétition.
     *
     * @param participant L'équipe à vérifier.
     * @return true si l'équipe est présente, sinon false.
     */
    @Override
    public boolean participantPresent(Equipe participant){
        return this.liEquipe.contains(participant);
    }


    /**
     * Ajoute une épreuve coopérative à la liste des épreuves de la compétition.
     *
     * @param epreuve L'épreuve coopérative à ajouter.
     */
    @Override
    public void ajoutEpreuve(EpreuveCoop epreuve){
        this.liEpreuve.add(epreuve);
    }


    /**
     * Attribue les médailles aux équipes gagnantes de la compétition.
     * Les médailles sont attribuées aux trois premières équipes du classement.
     */
    @Override
    public void attribuerMedaille(){
        Map<Equipe, Integer> dico = new HashMap<>();
        for(Equipe e : this.liEquipe){
            dico.put(e, 0);
        }

        for(EpreuveCoop epreuve : this.liEpreuve){
            Map<Integer,Equipe> donnees = epreuve.getDonneesClassement();
            for(Integer i : donnees.keySet()){
                dico.put(donnees.get(i), (dico.get((donnees.get(i)))+i));
            }
        }

        List<Equipe> liste = new ArrayList<>();
        for(Equipe e : this.liEquipe){
            liste.add(e);
        }

        ComparateurCompetCoop comparator = new ComparateurCompetCoop(dico);
        Collections.sort(liste , comparator);

        for(int i=0 ; i<3; i++){
            if(i<1){
                liste.get(i).getPays().setCompteurMedailleOr(liste.get(i).getPays().getCompteurMedailleOr()+1);
            }
            liste.get(i).getPays().setCompteurMedaille(liste.get(i).getPays().getCompteurMedaille()+1);
        }
    }


}
