package fr.univ_orleans.iut45.mud.IHM.src.controlleur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

import fr.univ_orleans.iut45.mud.IHM.src.*;

public class ControleurRadioButtonCompetition implements EventHandler<ActionEvent> {
    private JeuxOlympique vue;
    private ToggleGroup toggleGroup1;
    private ToggleGroup toggleGroup2;

    public ControleurRadioButtonCompetition(JeuxOlympique vue, ToggleGroup toggleGroup1, ToggleGroup toggleGroup2){
        this.vue = vue;
        this.toggleGroup1 = toggleGroup1;
        this.toggleGroup2 = toggleGroup2;
    }

    @Override
    public void handle(ActionEvent actionEvent) {

        for (Toggle toggle : this.toggleGroup1.getToggles()) {
            RadioButton radioButton = (RadioButton) toggle;
            if (radioButton.isVisible()){
                radioButton.setVisible(false);
            }
            else{
                radioButton.setVisible(true);
            }
        }

        for (Toggle toggle : this.toggleGroup2.getToggles()) {
            RadioButton radioButton = (RadioButton) toggle;
            if (radioButton.isVisible()){
                radioButton.setVisible(false);
            }
        }
    }
}


