package fr.univ_orleans.iut45.mud.IHM.src.controlleur;

import java.util.Set;

import fr.univ_orleans.iut45.mud.IHM.src.JeuxOlympique;
import fr.univ_orleans.iut45.mud.app.App;
import fr.univ_orleans.iut45.mud.competition.CompetCoop;
import fr.univ_orleans.iut45.mud.competition.CompetInd;
import fr.univ_orleans.iut45.mud.competition.Competition;
import fr.univ_orleans.iut45.mud.epreuve.Epreuve;
import fr.univ_orleans.iut45.mud.items.Athlete;
import fr.univ_orleans.iut45.mud.items.Equipe;
import fr.univ_orleans.iut45.mud.items.ImportData;
import fr.univ_orleans.iut45.mud.items.Participant;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

public class ControlleurRadioButtonCompetition implements EventHandler<ActionEvent> {
    private JeuxOlympique vue;
    //private App model;
    private App model;
    public ControlleurRadioButtonCompetition(JeuxOlympique vue, App model){
        this.vue = vue;
        this.model = model;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        
        RadioButton radioButton = (RadioButton) actionEvent.getSource();
        String premier = "1er ";
        String second = "2nd ";
        String troisieme = "3ème ";
        
        
        Set<CompetCoop> ensCompetitionsCoop = this.model.getEnsCompetitionsCoop();
        for(CompetCoop compet:ensCompetitionsCoop){
            if(compet.getNom().equals(radioButton.getText())){
                List<Equipe> clasement = compet.classement();
                try{
                    premier += clasement.get(0).getPays().getNom();
                    second += clasement.get(1).getPays().getNom();
                    troisieme += clasement.get(2).getPays().getNom();
                }
                catch(IndexOutOfBoundsException e){}
                this.vue.majCompet(premier,second,troisieme, compet);
            }
        }
        
       
        Set<CompetInd> ensCompetitionsInd= this.model.getEnsCompetitionsInd();
        for(CompetInd compet:ensCompetitionsInd){
            if(compet.getNom().equals(radioButton.getText())){
                List<Athlete> clasement = compet.classement();
                try{
                    premier += clasement.get(0).getNom() + " " + clasement.get(0).getPrenom();
                    second += clasement.get(1).getNom()+ " " + clasement.get(1).getPrenom();
                    troisieme += clasement.get(2).getNom()+ " " + clasement.get(2).getPrenom(); 
                }
                catch(IndexOutOfBoundsException e){}
                this.vue.majCompet(premier,second,troisieme, compet);
            }
        }
        
       

    }
}
