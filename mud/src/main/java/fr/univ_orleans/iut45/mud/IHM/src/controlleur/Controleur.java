package fr.univ_orleans.iut45.mud.IHM.src.controlleur;

import java.io.IOException;

import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

import fr.univ_orleans.iut45.mud.IHM.src.*;

public class Controleur {
    @FXML
    private Button btnCo;

    @FXML
    private Button btnAccueil;
    private JeuxOlympique vue;

    @FXML
    private void init(){}

    public Controleur(JeuxOlympique vue){
        this.vue = vue;
    }

    @FXML
    private void handleConnexion(ActionEvent event) throws IOException{
        this.vue.modeParticipant();
        System.out.println("Affichage fenetre Participants");
    }

    @FXML
    private void handleDeconnexion(ActionEvent event) throws IOException{
        this.vue.modeConnexion();
        System.out.println("Affichage fenete Connexion");
    }

    

    @FXML
    private void handleParticipant(ActionEvent event) throws IOException{
        this.vue.modeParticipant();
    }

    @FXML
    private void handleCompetition(ActionEvent event) throws IOException{
        this.vue.modeCompetition();
    }


    @FXML
    private void handleCompetitionLiEp(ActionEvent event) throws IOException{
        this.vue.modeCompetitionLiEp();
    }

    @FXML
    private void handleCompetitionClassement(ActionEvent event) throws IOException{
        this.vue.modeCompetitionClassement();
    }

    @FXML
    private void handlePays(ActionEvent event) throws IOException{
        this.vue.modePays();
    }

    @FXML
    private void handleParamAffichage(ActionEvent event) throws IOException{
        this.vue.modeParamAffichage();
    }

    @FXML
    private void handleParamAudio(ActionEvent event) throws IOException{
        this.vue.modeParamAudio();
    }

    @FXML
    private void handleParamPref(ActionEvent event) throws IOException{
        this.vue.modeParamPref();
    }

    @FXML
    private void handleAppliquer(ActionEvent event){
        System.out.println("Appliqué");
    }

}
