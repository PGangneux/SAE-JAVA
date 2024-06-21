package fr.univ_orleans.iut45.mud.IHM.src.controlleur;

import java.io.IOException;

import java.util.List;
import fr.univ_orleans.iut45.mud.IHM.src.*;
import fr.univ_orleans.iut45.mud.competition.CompetCoop;
import fr.univ_orleans.iut45.mud.competition.CompetInd;
import fr.univ_orleans.iut45.mud.epreuve.EpreuveCoop;
import fr.univ_orleans.iut45.mud.epreuve.EpreuveCoopFem;
import fr.univ_orleans.iut45.mud.epreuve.EpreuveCoopMasc;
import fr.univ_orleans.iut45.mud.epreuve.EpreuveInd;
import fr.univ_orleans.iut45.mud.epreuve.EpreuveIndFem;
import fr.univ_orleans.iut45.mud.epreuve.EpreuveIndMasc;

import java.sql.SQLException;
import java.util.Set;

import javax.swing.Action;

import fr.univ_orleans.iut45.mud.IHM.src.*;
import fr.univ_orleans.iut45.mud.app.App;
import fr.univ_orleans.iut45.mud.items.*;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;
public class Controleur {
    

    private JeuxOlympique vue;
    private App model;
    //private ImportData model;

    @FXML
    private TextField identifiant;

    @FXML
    private PasswordField mdp;

    @FXML
    private VBox leftVboxCompet;

    @FXML
    private TextField PopupCompetNom;
    
    @FXML
    private ScrollPane ScrolEditEp;

    private boolean themeClair;

    @FXML
    private void init(){}


    public Controleur(JeuxOlympique vue, App model2 ){
        this.vue = vue;
        this.model = model2;
        App.alwaysConnectTrue = false; //a modifier pour se connecter quand on veut
        this.themeClair = true;
        System.out.println(this.model);    
    }


    @FXML
    private void handleConnexion(ActionEvent event) throws IOException, ClassNotFoundException, SQLException{
        String login;
        String password;
        if (App.alwaysConnectTrue) {
            this.vue.getStage().setMaximized(true);
            this.vue.modeParticipant();
            return;
        }
        try {
            login = this.identifiant.getText();
            password = this.mdp.getText();
            boolean state = this.model.getConnexion(login, password);
            if (state) {
                this.vue.getStage().setMaximized(true);
                this.vue.modeParticipant();
                // this.model.dataBaseInit(); // Init Base de donnée avec Athlete,Sport,Pays
            }else {
                throw new SQLException();
            }   
            System.out.println(this.model.getStatusCompte());
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Compte inexistant");
            alert.setHeaderText("Identifiant ou mot de passe incorrect");
            alert.setContentText("Les informations de connexion saisi ne correspondent à aucun de nos comptes enregistrés");
            alert.showAndWait();
            this.mdp.setText("");      
        } 
        catch (IOException e) {
            throw new IOException();
        }
        // this.vue.getStage().setMaximized(true);
        // this.vue.modeParticipant();

        // System.out.println("Affichage fenetre Participants");

        // System.out.println("bof");

    }

    @FXML
    private void handleDeconnexion(ActionEvent event) throws IOException, SQLException{
        if (App.alwaysConnectTrue) this.vue.modeConnexion();

        try {
            boolean state = this.model.closeDBConnection();
            if (state) {
               this.vue.modeConnexion();
               System.out.println("Affichage fenete Connexion");
           }
            System.out.println("Déconnection echoué");
        } catch (Exception e) {
               System.out.println("a check");
        } 
    }

    

    @FXML
    private void handleParticipant(ActionEvent event) throws IOException{
        System.out.println("pageParticipant");
        this.vue.modeParticipant();
    }

    @FXML
    private void handleCompetition(ActionEvent event) throws IOException{
        this.vue.modeCompetition();
    }


    @FXML
    private void handleCompetitionClassement(ActionEvent event) throws IOException{
        System.out.println("vplus");
        CompetCoop competCoop = this.recupCompetCoop();
        CompetInd competInd = this.recupCompetInd();
        if (competCoop == null){
            System.out.println("1");
            this.vue.modeCompetitionClassement(competInd);
        }
        else{
            System.out.println("2");
            this.vue.modeCompetitionClassement(competCoop);
        }
    }

    @FXML
    public void handleAjoutEpreuve(ActionEvent event) throws IOException, ClassNotFoundException, SQLException{
        CompetCoop competCoop = this.recupCompetCoop();
        CompetInd competInd = this.recupCompetInd();
        this.vue.setCompetCoop(competCoop);
        this.vue.setCompetInd(competInd);  
        this.vue.PageAjoutEpreuve(); 
    }

    @FXML
    public void handlePopupRetourAjouteEpreuve(ActionEvent event){
        this.vue.hidePopup(this.vue.getPopupCompet());
    }

    @FXML
    public void handlePopupRetourEditEp(ActionEvent event){
        this.vue.hidePopup(this.vue.getPopupEditEp());
    }


    @FXML
    public void handlePopupAjouterEpreuve(ActionEvent event){
        
        String nom = this.PopupCompetNom.getText();
        if (this.vue.getCompetCoop() == null){
            String sexe = this.vue.getCompetInd().getSexe();
            if(sexe.equals("F")){
                new EpreuveIndFem(nom, this.vue.getCompetInd());
            }
            else{
                new EpreuveIndMasc(nom, this.vue.getCompetInd());
            }
            List<Athlete> lAthletes = this.vue.getCompetInd().classement();
            String premier ="1er";
            String deuxième ="2nd";
            String troisieme ="3ème";
            try{
                premier += lAthletes.get(0).getNom() + " " + lAthletes.get(0).getPrenom();
                deuxième += lAthletes.get(1).getNom()+ " " + lAthletes.get(1).getPrenom();
                troisieme += lAthletes.get(2).getNom()+ " " + lAthletes.get(2).getPrenom();
            }
            catch(IndexOutOfBoundsException e){}
            this.vue.hidePopup(this.vue.getPopupCompet());
            this.vue.majCompet(premier, deuxième, troisieme, this.vue.getCompetInd());
            
        }
        else{
            String sexe = this.vue.getCompetCoop().getSexe();
            if(sexe.equals("F")){
                new EpreuveCoopFem(nom, this.vue.getCompetCoop());
            }
            else{
                new EpreuveCoopMasc(nom, this.vue.getCompetCoop());
            }
            List<Equipe> lEquipes = this.vue.getCompetCoop().classement();
            String premier ="1er ";
            String deuxième ="2nd ";
            String troisieme ="3ème ";
            try{
                premier += lEquipes.get(0).getPays().getNom();
                deuxième += lEquipes.get(1).getPays().getNom();
                troisieme += lEquipes.get(2).getPays().getNom();
            }
            catch(IndexOutOfBoundsException e){}
            this.vue.hidePopup(this.vue.getPopupCompet());
            this.vue.majCompet(premier, deuxième, troisieme, this.vue.getCompetCoop());
        }
    }


    @FXML
    public void handleAppliquerEdit(ActionEvent event){
        GridPane gridEdit = (GridPane) this.ScrolEditEp.getContent();
        EpreuveCoop epreuveCoop = this.vue.getEpreuveCoop();
        EpreuveInd epreuveInd = this.vue.getEpreuveInd();
        if (epreuveCoop == null){
            CompetInd competInd = this.vue.getCompetInd();
            int i =0;
            for(Athlete athlete: competInd.getParticipant()){
                TextField text = (TextField) gridEdit.getChildren().get(i*2+1);
                int point = 0;
                try{
                    point = Integer.valueOf(text.getText());
                }
                catch(NumberFormatException e){}
                if(point!=0){
                    epreuveInd.setScore(athlete, point);
                }
                else{
                    epreuveInd.setScore(athlete, null);
                }
                ++i;
            }
            List<Athlete> lAthletes = this.vue.getCompetInd().classement();
            String premier ="1er ";
            String deuxième ="2nd ";
            String troisieme ="3ème ";
            try{
                premier += lAthletes.get(0).getNom() + " " + lAthletes.get(0).getPrenom();
                deuxième += lAthletes.get(1).getNom()+ " " + lAthletes.get(1).getPrenom();
                troisieme += lAthletes.get(2).getNom()+ " " + lAthletes.get(2).getPrenom();
            }
            catch(IndexOutOfBoundsException e){}
            this.vue.hidePopup(this.vue.getPopupEditEp());
            this.vue.majCompet(premier, deuxième, troisieme, competInd);
            this.vue.setCompetInd(null);
            
        }
        else{
            CompetCoop competCoop = this.vue.getCompetCoop();
            int i =0;
            for(Equipe equipe: competCoop.getParticipant()){
                
                TextField text = (TextField) gridEdit.getChildren().get(i*2+1);
                int point = 0;
                try{
                    point = Integer.valueOf(text.getText());
                }
                catch(NumberFormatException e){}
                if(point!=0){
                    epreuveCoop.setScore(equipe, point);
                }
                else{
                    epreuveCoop.setScore(equipe, null);
                }
                
                i++;
            }
            List<Equipe> lEquipes = competCoop.classement();
            
            String premier ="1er ";
            String deuxième ="2nd ";
            String troisieme ="3ème ";
            try{
                premier += lEquipes.get(0).getPays().getNom();
                System.out.println(premier);
                deuxième += lEquipes.get(1).getPays().getNom();
                troisieme += lEquipes.get(2).getPays().getNom();
            }
            catch(IndexOutOfBoundsException e){}
            this.vue.hidePopup(this.vue.getPopupEditEp());
            this.vue.majCompet(premier, deuxième, troisieme, competCoop);
            this.vue.setEpreuveCoop(null);
        }
        
        
    }


    @FXML
    private void handlePays(ActionEvent event) throws IOException{
        System.out.println("pagePays");
        this.vue.modePays();
    }

    @FXML
    private void handleParamAffichage(ActionEvent event) throws IOException{
        System.out.println("pageParam ");
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
            this.themeClair = false;
            // vue.themeSombre();
        } else {
            this.themeClair = true;
            // vue.themeClair();
        }
    }

    @FXML
    private void handleCouleur(ActionEvent event) throws IOException{
        ColorPicker colorPicker = (ColorPicker)event.getSource();
        this.vue.setCouleur(colorPicker.getValue());
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
        if (this.themeClair){
            this.vue.themeClair();
        } else {
            this.vue.themeSombre();
        }
        try {
            String hex = Integer.toHexString(this.vue.getCouleur().hashCode());
        } catch (Exception e) {
            System.err.println("Couleur pas sélectionné");
        }
        
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

    @FXML
    private void handleRechercheE(KeyEvent event){
        if (event.getCode() == KeyCode.ENTER){
            for (Equipe e : this.model.getListEquipes()){
                String nom = e.getNom().toUpperCase();
                System.out.println(nom);
                TextField textField = (TextField) event.getSource();
                String contenuTF = textField.getText().toUpperCase();
                System.out.println(contenuTF);
                if (contenuTF.equals(nom)){
                    this.vue.majEquipe(e);
                }    
            }
            System.out.println("Equipe affiché");
        }
    }

    @FXML
    private void handleRechercheA(KeyEvent event){
        if (event.getCode() == KeyCode.ENTER){
            for (Athlete a : this.model.getListAthletes()){
                String nom = a.getPrenom().toUpperCase() + " " + a.getNom().toUpperCase();
                System.out.println(nom);
                TextField textField = (TextField) event.getSource();
                String contenuTF = textField.getText().toUpperCase();
                System.out.println(contenuTF);
                if (contenuTF.equals(nom)){
                    this.vue.majAthlete(a);
                }
            }
            System.out.println("Athlete affiché");
        }
    }

    @FXML
    private void handleAddAthlete(ActionEvent event){
        System.out.println(event.getSource());
    }

    @FXML
    private void handleAddEquipe(ActionEvent event){
        System.out.println(event.getSource());
    }

    public CompetCoop recupCompetCoop(){
        for (Node node: this.leftVboxCompet.getChildren()){
            if (node instanceof RadioButton){
                RadioButton radioButton = (RadioButton) node;
                if(radioButton.isSelected()){
                    Set<CompetCoop> ensCompetitionsCoop = this.model.getEnsCompetitionsCoop();
                    for(CompetCoop compet:ensCompetitionsCoop){
                        if(compet.getNom().equals(radioButton.getText())){
                            return compet; 
                        }
                    }
                }
            }
        }
        return null;
    }

    public CompetInd recupCompetInd(){
        for (Node node: this.leftVboxCompet.getChildren()){
            if (node instanceof RadioButton){
                RadioButton radioButton = (RadioButton) node;
                if(radioButton.isSelected()){
                    Set<CompetInd> ensCompetitionsInd= this.model.getEnsCompetitionsInd();
                    for(CompetInd compet:ensCompetitionsInd){
                        if(compet.getNom().equals(radioButton.getText())){
                            return compet;
                        }
                    }
                }
            }
        }
        return null;
    }
    

}
