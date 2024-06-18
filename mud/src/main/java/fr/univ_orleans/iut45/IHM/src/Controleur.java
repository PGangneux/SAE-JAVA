import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;

public class Controleur {
    @FXML
    private Button btnCo;

    @FXML
    private Button btnAccueil;

    @FXML
    private RadioButton sport1;

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
    private void handleAccueil(ActionEvent event) throws IOException{
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
    private void handleHommeEnable(ActionEvent event) throws IOException{
        if (sport1.isVisible()){
            sport1.setVisible(false);
        }
        else{
            sport1.setVisible(true);
        }
    }
        
    @FXML
    private void handlePays(ActionEvent event) throws IOException{
        this.vue.modePays();

    }

}
