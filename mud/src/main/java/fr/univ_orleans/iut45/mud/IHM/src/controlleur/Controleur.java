package fr.univ_orleans.iut45.mud.IHM.src.controlleur;

import java.io.IOException;
import java.util.Set;

import fr.univ_orleans.iut45.mud.IHM.src.*;
import fr.univ_orleans.iut45.mud.items.*;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
public class Controleur {
    @FXML
    private GridPane recherchePays;
    

    private JeuxOlympique vue;
    private ImportData model;

    @FXML
    private void init(){}

    public Controleur(JeuxOlympique vue, ImportData model){
        this.vue = vue;
        this.model = model;
        System.out.println(this.model);
        
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
        System.out.println(this.model);

        System.out.println(event.getCode());
        if (event.getCode() == KeyCode.ENTER) {
            
            Set<Pays> ensPays = this.model.getEnsPays();
            TextField textField = (TextField) event.getSource();
            Pays pays = new Pays(textField.getText());
            if(ensPays.contains(pays)){
                System.out.println(pays.getNom()+".png");
                
                ImageView imageView = new ImageView(new Image(getClass().getResource("/fr/univ_orleans/iut45/mud/IHM/img/flags/"+pays.getNom()+".png").toExternalForm()));
                imageView.setFitWidth(50); 
                imageView.setFitHeight(35); 
                imageView.setPreserveRatio(true);
                GridPane.setMargin(imageView, new Insets(0,0,0,10));
                Label nom = new Label(pays.getNom());

                
                Label nbMedaille = new Label(String.valueOf(pays.getCompteurMedaille()));
                Label nbMedailleOr = new Label(String.valueOf(pays.getCompteurMedailleOr()));
                Label nbMedailleArgent = new Label(String.valueOf(pays.getCompteurMedailleArgent()));
                Label nbMedailleBronze = new Label(String.valueOf(pays.getCompteurMedailleBronze()));
                this.recherchePays.add(imageView, 0, 1);
                this.recherchePays.add(nom, 1, 1);
                this.recherchePays.add(nbMedaille, 1, 2);
                this.recherchePays.add(nbMedailleOr, 1, 3);
                this.recherchePays.add(nbMedailleArgent, 1, 4);
                this.recherchePays.add(nbMedailleBronze, 1, 5);

                GridPane.setMargin(nbMedaille, new Insets(0,0,50,25));
                GridPane.setMargin(nbMedailleOr, new Insets(0,0,50,25));
                GridPane.setMargin(nbMedailleArgent, new Insets(0,0,50,25));
                GridPane.setMargin(nbMedailleBronze, new Insets(0,0,50,25));

            }
        }
    }

}
