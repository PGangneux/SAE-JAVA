package fr.univ_orleans.iut45.mud;
import java.util.List;

public interface Competition {

    public String getNom();
    public List<Object> getParticipant();
    public String classement();
    public void participer();
    public void suppParticipant();
    public void setNom();
    public boolean participantPresent();
    public double getScore();
    
}