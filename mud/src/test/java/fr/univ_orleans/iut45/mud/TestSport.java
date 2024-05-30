package fr.univ_orleans.iut45.mud;

import org.junit.*;
import static org.junit.Assert.assertEquals;

public class TestSport {
    Sport natation = new Sport("Volley");
    
    @Test
    public void TestName() {
        Sport volley = new Sport("Volley");
        Sport natation = new Sport("Natation");
        assertEquals("Volley", volley.getNom());
        assertEquals("Natation", natation.getNom());
    } 
    
}
