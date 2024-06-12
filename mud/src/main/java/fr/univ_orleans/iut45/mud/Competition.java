package fr.univ_orleans.iut45.mud;
import java.util.List;

public interface Competition<T , E> {

    public String getNom();
    public String getSexe();
    public Sport getSport();
    public List<T> getParticipant();
    public String classement();
    public void participer(T participant);
    public void suppParticipant(T participant);
    public void setNom(String nom);
    public boolean participantPresent(T participant);
    public void ajoutEpreuve(E epreuve);
    public List<E> getLiEpreuves();
    public void attribuerMedaille();
    
}
