import java.util.List;

public interface Competition {

    public String getNom();
    public List<Object> getParticipant();
    public String classement();
    public void participer(Object object);
    public void suppParticipant(Object object);
    public void setNom(String nom);
    public boolean participantPresent(Object object);
    public double getScore();
    
}