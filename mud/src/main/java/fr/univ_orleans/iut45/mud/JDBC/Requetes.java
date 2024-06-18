package fr.univ_orleans.iut45.mud.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import fr.univ_orleans.iut45.mud.competition.*;
import fr.univ_orleans.iut45.mud.epreuve.*;
import fr.univ_orleans.iut45.mud.items.*;


public class Requetes {

    Connexion laConnexion;
	Statement st;

    public Requetes(Connexion laConnexion){
        this.laConnexion=laConnexion;
    }

    void ajouterAthlete(Athlete a) throws  SQLException{
        st=laConnexion.createStatement();
     	String requete = "insert into ATHLETE values("+this.getPlusGrandIdAthlete()+1+",'"+a.getNom()+"','"+a.getPrenom()+"','"+a.getSexe()+"',"+a.getForce()+", "+a.getEndurance()+", "+a.getAgilite()+", "+this.getIdPays(a.getPays())+ ")";

		System.out.println(requete);
		st.executeUpdate(requete);
    }


    public int getIdPays(Pays pays) throws  SQLException{
        int nb =0;
        st=laConnexion.createStatement();
        String requete = "select idPays from PAYS where nomPays='"+pays.getNom()+"'";
        ResultSet rs = st.executeQuery(requete);
        while(rs.next()){
			nb=rs.getInt("idPays");			// récupération du nombre
		}
        return nb;
    }

    public int getPlusGrandIdPays() throws  SQLException{
        int nb = 0;
        st=laConnexion.createStatement(); 
        String requete = "select max(idPays) as nb from PAYS";
        ResultSet rs = st.executeQuery(requete);
        while(rs.next()){
			nb=rs.getInt("nb");			// récupération du nombre
		}
        return nb;
        
    }

    public int getPlusGrandIdAthlete() throws  SQLException{
        int nb = 0;
        st=laConnexion.createStatement();
        String requete = "select max(idAthlete) as nb from ATHLETE";
        ResultSet rs = st.executeQuery(requete);
        while(rs.next()){
			nb=rs.getInt("nb");			// récupération du nombre
		}
        return nb;
    }


    public void ajouterCompetition(Competition compet)  throws  SQLException{
        int indiv = 1;
        st=laConnexion.createStatement();
        if(compet instanceof CompetCoop){
            indiv = 0;
        }
     	String requete = "insert into COMPETITION values("+this.getPlusGrandIdCompetition()+1+",'"+compet.getNom()+"',"+this.idSport(compet.getSport())+","+indiv+")";

		System.out.println(requete);
		st.executeUpdate(requete);
         

    }


    public int getPlusGrandIdCompetition() throws  SQLException{
        int nb = 0;
        st=laConnexion.createStatement();
        String requete = "select max(idCompet) as nb from COMPETITION";
        ResultSet rs = st.executeQuery(requete);
        while(rs.next()){
			nb=rs.getInt("nb");			// récupération du nombre
		}
        return nb;
    }

    public int idSport(Sport s) throws SQLException{
        int nb = 0;
        st=laConnexion.createStatement();
        String requete = "select idSport from SPORT where nomSport='"+s.getNom()+"'";
        ResultSet rs = st.executeQuery(requete);
        while(rs.next()){
			nb=rs.getInt("idSport");			// récupération du nombre
		}
        return nb;
    }

    public void ajouterSport(Sport s) throws SQLException {
        st=laConnexion.createStatement();
     	String requete = "insert into SPORT values("+this.getPlusGrandIdSport()+1+",'"+s.getNom()+"')";

		System.out.println(requete);
		st.executeUpdate(requete);
    }

    public int getPlusGrandIdSport() throws  SQLException{
        int nb = 0;
        st=laConnexion.createStatement();
        String requete = "select max(idSport) as nb from SPORT";
        ResultSet rs = st.executeQuery(requete);
        while(rs.next()){
			nb=rs.getInt("nb");	 // récupération du nombre
		}
        return nb;
    }

    public void ajouterPays(Pays p) throws SQLException {
        st=laConnexion.createStatement();
     	String requete = "insert into PAYS values("+this.getPlusGrandIdPays()+1+",'"+p.getNom()+"')";

		System.out.println(requete);
		st.executeUpdate(requete);
    }

    



}
