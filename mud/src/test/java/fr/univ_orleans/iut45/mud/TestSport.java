package fr.univ_orleans.iut45.mud;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestSport {
    Sport natation = new Sport("Volley");
    
    @Test
    public void TestName() {
        Sport volley = new Sport("Volley");
        Sport natation = new Sport("Natation");
        Assertions.assertEquals("Volley", volley.getNom());
        Assertions.assertEquals("Natation", natation.getNom());
    } 
    
}
