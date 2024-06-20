package fr.univ_orleans.iut45.mud.IHM.src.controlleur;

import java.util.Set;

import fr.univ_orleans.iut45.mud.IHM.src.JeuxOlympique;
import fr.univ_orleans.iut45.mud.app.App;
import fr.univ_orleans.iut45.mud.competition.CompetCoop;
import fr.univ_orleans.iut45.mud.competition.CompetInd;
import fr.univ_orleans.iut45.mud.competition.Competition;
import fr.univ_orleans.iut45.mud.epreuve.Epreuve;
import fr.univ_orleans.iut45.mud.epreuve.EpreuveCoop;
import fr.univ_orleans.iut45.mud.epreuve.EpreuveInd;
import fr.univ_orleans.iut45.mud.epreuve.EpreuveIndMasc;
import fr.univ_orleans.iut45.mud.items.Athlete;
import fr.univ_orleans.iut45.mud.items.Equipe;
import fr.univ_orleans.iut45.mud.items.ImportData;
import fr.univ_orleans.iut45.mud.items.Participant;
import fr.univ_orleans.iut45.mud.items.Sport;

import java.util.List;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ControlleurSupprimerEpreuve implements EventHandler<ActionEvent> {
    private JeuxOlympique vue;
    private App model;
    // private ImportData model;
    private int indice;
    private String classementCompet1PLace;
    private String classementCompet2PLace;
    private String classementCompet3PLace;


    public ControlleurSupprimerEpreuve(JeuxOlympique vue, App model, int indice,String classementCompet1PLace, String classementCompet2PLace, String classementCompet3PLace){
        this.vue = vue;
        this.model = model;
        this.indice = indice;
        this.classementCompet1PLace = classementCompet1PLace;
        this.classementCompet2PLace = classementCompet2PLace;
        this.classementCompet3PLace = classementCompet3PLace;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        //pop up de confirmation
        boolean result = this.vue.alertSuppressionEpreuve();
        if (result) {
            VBox leftVboxCompet = this.vue.getLeftVboxCompet();
            
            RadioButton radioButton = new RadioButton();
            Competition competition = new CompetCoop("", "", new Sport(" "), 0);
            

            //récupération du radio bouton séléctioné
            boolean estVrai = true;
            int i = 0;
            while (estVrai) {
                Node node = leftVboxCompet.getChildren().get(i);
                i++;
                if (node instanceof RadioButton){
                    RadioButton UnRadioButton = (RadioButton) node;
                    if (UnRadioButton.isSelected()){
                        radioButton = UnRadioButton;
                        estVrai = false;
                    }
                }
            }

            // récupéartion de la compétitions séléctionné
            Set<CompetCoop> ensCompetitionsCoop = this.model.getEnsCompetitionsCoop();
            for(CompetCoop compet:ensCompetitionsCoop){
                if(compet.getNom().equals(radioButton.getText())){
                    competition = compet;
                }
            }
            
            Set<CompetInd> ensCompetitionsInd= this.model.getEnsCompetitionsInd();
            for(CompetInd compet:ensCompetitionsInd){
                if(compet.getNom().equals(radioButton.getText())){
                    competition = compet;
                }
            }

            //Supréssion de l'épreuve
            if (competition instanceof CompetCoop){
                CompetCoop competCoop = (CompetCoop) competition;
                List<EpreuveCoop> liEpreuves = competCoop.getLiEpreuves();
                ScrollPane scrollLiEpreuve = this.vue.getLiEpreuve();
                GridPane gridLiEpreuve =  (GridPane) scrollLiEpreuve.getContent();
                Label labelEpreuve = (Label) gridLiEpreuve.getChildren().get(indice*2);
                EpreuveCoop epreuveSup = liEpreuves.get(0);
                for (EpreuveCoop epreuve : liEpreuves){
                    if (epreuve.getNom().equals(labelEpreuve.getText())){
                        epreuveSup = epreuve;
                    }
                }
                competCoop.suppEpreuve(epreuveSup);
                this.vue.majCompet(this.classementCompet1PLace, this.classementCompet2PLace, this.classementCompet3PLace, competCoop);
            }
            else{
                CompetInd competInd = (CompetInd) competition;
                List<EpreuveInd> liEpreuves = competInd.getLiEpreuves();
                ScrollPane scrollLiEpreuve = this.vue.getLiEpreuve();
                GridPane gridLiEpreuve =  (GridPane) scrollLiEpreuve.getContent();
                Label labelEpreuve = (Label) gridLiEpreuve.getChildren().get(indice*2);
                EpreuveInd epreuveSup = liEpreuves.get(0);
                for (EpreuveInd epreuve : liEpreuves){
                    if (epreuve.getNom().equals(labelEpreuve.getText())){
                        epreuveSup = epreuve;
                        
                    }
                }
                competInd.suppEpreuve(epreuveSup);
                this.vue.majCompet(this.classementCompet1PLace, this.classementCompet2PLace, this.classementCompet3PLace, competInd);
            }
        
            ////update databases
        }

    }
}
