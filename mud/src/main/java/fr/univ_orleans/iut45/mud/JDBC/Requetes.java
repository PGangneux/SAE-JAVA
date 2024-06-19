package fr.univ_orleans.iut45.mud.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.univ_orleans.iut45.mud.competition.CompetCoop;
import fr.univ_orleans.iut45.mud.competition.Competition;
import fr.univ_orleans.iut45.mud.items.Athlete;
import fr.univ_orleans.iut45.mud.items.Equipe;
import fr.univ_orleans.iut45.mud.items.Pays;
import fr.univ_orleans.iut45.mud.items.Sport;


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

    public List<Equipe> getListeEquipe(int compet) throws SQLException{
        List<Equipe> listeEquipe = new ArrayList<>();
        st=laConnexion.createStatement();
        String requete = "select idEquipe from DISPUTE where idCompet="+compet;
        ResultSet rs = st.executeQuery(requete);
        while (rs.next()) {
            
            listeEquipe.add(this.getEquipe(rs.getInt("idEquipe")));

        }
        return listeEquipe;
    }

    public Equipe getEquipe(int equipe) throws SQLException{
        st=laConnexion.createStatement();
        String requete = "select nomEquipe, sexe, idSport, idPays from Equipe where idEquipe="+equipe;
        ResultSet rs = st.executeQuery(requete);
        Equipe e = null;
        while(rs.next()){
            e = new Equipe(rs.getString("nomEquipe"), rs.getString("sexe"), this.getPays(rs.getInt("idPays")), this.getSport(rs.getInt("idSport")));
            List<Athlete> listeAthlete = this.getAthleteEquipe(equipe);
            for (Athlete a : listeAthlete){
                e.ajouteAthlete(a);
            }
        }
        return e;
    }

    public Athlete getAthlete(int athlete) throws SQLException{
        Athlete a = null;
        st=laConnexion.createStatement();
        String requete = "select nomAth, prenomAth, sexeAth, idSport, idPays, force, endurance, agilite from ATHLETE where idAthlete="+athlete;
        ResultSet rs = st.executeQuery(requete);
        while(rs.next()){
            a = new Athlete(rs.getString("nomAth"), rs.getString("prenomAth"), rs.getString("sexeAth"), this.getPays(rs.getInt("idPays")), this.getSport(rs.getInt("idSport")), rs.getInt("force"), rs.getInt("agilite"), rs.getInt("endurance"));
        }
        return a;
        
    }

    public Sport getSport(int sport) throws SQLException{
        st=laConnexion.createStatement();
        String requete = "select nomSport from SPORT where idSport="+sport;
        ResultSet rs = st.executeQuery(requete);
        Sport s = null;
        while (rs.next()) {
            s = new Sport(rs.getString("nomSport"));            
        }
        return s;
    }

    public Pays getPays(int pays) throws SQLException{
        st=laConnexion.createStatement();
        String requete = "select nomPays from Pays where idPays="+pays;
        ResultSet rs = st.executeQuery(requete);
        Pays p = null;
        while (rs.next()) {
            p = new Pays(rs.getString("nomPays"));            
        }
        return p;
    }


    public List<Athlete> getAthleteEquipe(int equipe) throws SQLException{
        List<Athlete> listeAthlete = new ArrayList<>();
        st=laConnexion.createStatement();
        String requete = "select idAthlete from ATHLETE where idEquipe="+equipe;
        ResultSet rs = st.executeQuery(requete);
        while (rs.next()) {
            
            listeAthlete.add(this.getAthlete(rs.getInt("idAthlete")));

        }
        return listeAthlete;
    }
    



}
