package fr.univ_orleans.iut45.mud.IHM.src.controlleur;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

import fr.univ_orleans.iut45.mud.IHM.src.*;
import fr.univ_orleans.iut45.mud.app.App;
import fr.univ_orleans.iut45.mud.items.*;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
public class Controleur {
    

    private JeuxOlympique vue;
    private App model;

    @FXML
    private TextField identifiant;

    @FXML
    private PasswordField mdp;

    @FXML
    private void init(){}

    public Controleur(JeuxOlympique vue, App model2){
        this.vue = vue;
        this.model = model2;
        System.out.println(this.model);
        
    }

    @FXML
    private void handleConnexion(ActionEvent event) throws IOException, ClassNotFoundException, SQLException{
        try {
            String login = this.identifiant.getText();
            String password = this.mdp.getText();
            boolean state = this.model.getConnexion(login, password);
            if (state) {
                this.vue.getStage().setMaximized(true);
                this.vue.modeParticipant();
            }else {
                System.out.println("inexistant");
            }   
            System.out.println(this.model.getStatusCompte());
        } catch (IOException e) {
            throw new IOException();
        }
        System.out.println("Affichage fenetre Participants");
    }

    @FXML
    private void handleDeconnexion(ActionEvent event) throws IOException{
        try {
            this.model.closeDBConnection();
            this.vue.modeConnexion();
            System.out.println("Affichage fenete Connexion");
        } catch (IOException e) {
            throw new IOException();
        }
        
        
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
    private void handleRetour(ActionEvent event) throws IOException{
        this.vue.modeParticipant();
    }

    @FXML
    private void handleVolume(MouseEvent event) throws IOException{
        System.out.println("Son modifié");
    }

    @FXML
    private void handleTheme(ActionEvent event) throws IOException{
        System.out.println("Theme modifié");
        RadioButton boutonTheme = (RadioButton) event.getSource();
        if (boutonTheme.getText().equals("Sombre")){
            vue.themeSombre();
        } else {
            vue.themeClair();
        }
    }

    @FXML
    private void handleCouleur(ActionEvent event) throws IOException{
        System.out.println("Couleur bouton modifié");
    }

    @FXML
    public void handleRadioBCompet(ActionEvent actionEvent) {
        Button bouton = (Button) actionEvent.getSource();
        ToggleGroup toggleGroup1;
        ToggleGroup toggleGroup2;
        if(bouton.getText().equals("Femme")){
            toggleGroup1 = this.vue.getGroupRadioBCompetFemme();
            toggleGroup2 = this.vue.getGroupRadioBCompetHomme();
        }
        else{
            toggleGroup2 = this.vue.getGroupRadioBCompetFemme();
            toggleGroup1 = this.vue.getGroupRadioBCompetHomme();
        }


        for (Toggle toggle : toggleGroup1.getToggles()) {
            RadioButton radioButton = (RadioButton) toggle;
            if (radioButton.isVisible()){
                radioButton.setVisible(false);
            }
            else{
                radioButton.setVisible(true);
            }
        }

        for (Toggle toggle : toggleGroup2.getToggles()) {
            RadioButton radioButton = (RadioButton) toggle;
            if (radioButton.isVisible()){
                radioButton.setVisible(false);
            }
            if (radioButton.isSelected()){
                radioButton.setSelected(false);
            }
        }
    }

    @FXML
    private void handleAppliquer(ActionEvent event){
        System.out.println("Appliqué");
    }

    @FXML
    private void handleTextFieldPays(KeyEvent event){
        if (event.getCode() == KeyCode.ENTER) {
            
            TextField textField = (TextField) event.getSource();
            Pays pays = new Pays(textField.getText());
            
            Set<Pays> ensPays = this.model.getEnsPays();
            if(ensPays.contains(pays)){
                this.vue.majPays(pays);
            }
            
        }
    }

}
