package fr.univ_orleans.iut45.mud.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.univ_orleans.iut45.mud.competition.CompetCoop;
import fr.univ_orleans.iut45.mud.competition.CompetInd;
import fr.univ_orleans.iut45.mud.competition.Competition;
import fr.univ_orleans.iut45.mud.epreuve.Epreuve;
import fr.univ_orleans.iut45.mud.epreuve.EpreuveCoopFem;
import fr.univ_orleans.iut45.mud.epreuve.EpreuveCoopMasc;
import fr.univ_orleans.iut45.mud.epreuve.EpreuveIndFem;
import fr.univ_orleans.iut45.mud.epreuve.EpreuveIndMasc;
import fr.univ_orleans.iut45.mud.items.Athlete;
import fr.univ_orleans.iut45.mud.items.Equipe;
import fr.univ_orleans.iut45.mud.items.Participant;
import fr.univ_orleans.iut45.mud.items.Pays;
import fr.univ_orleans.iut45.mud.items.Sport;


public class Requetes {

    Connexion laConnexion;
	Statement st;
    private List<Pays> listePays;

    public Requetes(Connexion laConnexion){
        this.laConnexion=laConnexion;
        this.listePays=new ArrayList<>();
    }

    public void ajouterAthlete(Athlete a) throws  SQLException{
        st=laConnexion.createStatement();
     	String requete = "insert into ATHLETE values("
            +(this.getPlusGrandIdAth()+1)
            +",'"+a.getNom()
            +"','"+a.getPrenom()
            +"','"+a.getSexe()+"',"
            +a.getForce()+", "
            +a.getEndurance()+", "
            +a.getAgilite()+", "
            +this.getIdPays(a.getPays())+","
            +this.getSportId(a.getSport())+
            
            ")";

		System.out.println(requete);
		st.executeUpdate(requete);
    }


    public int getIdPays(Pays pays) throws SQLException{
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

    public int getPlusGrandIdAth() throws  SQLException{
        int nb = 0;
        st=laConnexion.createStatement();
        String requete = "select max(idAth) as nb from ATHLETE";
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
     	String requete = "insert into COMPETITION values("
            +(this.getPlusGrandIdCompetition()+1)+","
            +this.getSportId(compet.getSport())+",'"
            +compet.getNom()+"','"
            +compet.getSexe()+"',"
            +indiv+")";
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
     	String requete = "insert into SPORT values("+(this.getPlusGrandIdSport()+1)+",'"+s.getNom()+"')";
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
     	String requete = "insert into PAYS values("+(this.getPlusGrandIdPays()+1)+",'"+p.getNom()+"',"+p.getCompteurMedailleOr()+","+p.getCompteurMedailleBronze()+","+p.getCompteurMedailleArgent()+")";
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
        String requete = "select nomAth, prenomAth, sexeAth, idSport, idPays, forceAth, enduranceAth, agiliteAth from ATHLETE where idAth="+athlete;
        ResultSet rs = st.executeQuery(requete);
        while(rs.next()){
            a = new Athlete(rs.getString("nomAth"), rs.getString("prenomAth"), rs.getString("sexeAth"), this.getPays(rs.getInt("idPays")), this.getSport(rs.getInt("idSport")), rs.getInt("forceAth"), rs.getInt("agiliteAth"), rs.getInt("enduranceAth"));
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
        String requete = "select nomPays from PAYS where idPays="+pays;
        ResultSet rs = st.executeQuery(requete);
        Pays p = null;
        while (rs.next()){
            p = new Pays(rs.getString("nomPays"));            
        }
        if(this.listePays.contains(p)){
            return this.listePays.get(this.listePays.indexOf(p));
        }
        else{
            this.listePays.add(p);
        }
        return p;
    }


    public List<Athlete> getAthleteEquipe(int equipe) throws SQLException{
        List<Athlete> listeAthlete = new ArrayList<>();
        st=laConnexion.createStatement();
        String requete = "select idAth from ATHLETE where idEquipe="+equipe;
        ResultSet rs = st.executeQuery(requete);
        while (rs.next()) {
            
            listeAthlete.add(this.getAthlete(rs.getInt("idAth")));

        }
        return listeAthlete;
    }

    public List<Epreuve> getEpreuvesCompetition(int compet, Competition c) throws SQLException{
        List<Epreuve> listeEpreuves = new ArrayList<>();
        st=laConnexion.createStatement();
        String requete = "select idEpreuve from EPREUVE where idCompet="+compet;
        ResultSet rs = st.executeQuery(requete);
        while (rs.next()) {
            listeEpreuves.add(this.getEpreuve(rs.getInt("idEpreuve"),c));
        }
        return listeEpreuves;
    }

    public Epreuve getEpreuve(int epreuve, Competition c) throws SQLException{
        st=laConnexion.createStatement();
        String requete = "select nomEpruve from EPREUVE where idEpreuve="+epreuve;
        ResultSet rs = st.executeQuery(requete);
        Epreuve e = null;
        while(rs.next()){
            if(c instanceof CompetCoop){
                CompetCoop compet = (CompetCoop) c;
                if(c.getSexe().equals("M")){
                    e = new EpreuveCoopMasc(rs.getString("nomEpreuve"),  compet);
                }
                else{
                    e = new EpreuveCoopFem(rs.getString("nomEpreuve"), compet);
                }
            }
            else{
                CompetInd compet = (CompetInd) c;
                if(c.getSexe().equals("M")){
                    e = new EpreuveIndMasc(rs.getString("nomEpreuve"), compet);
                }
                else{
                    e = new EpreuveIndFem(rs.getString("nomEpreuve"), compet);
                }
            }
        }
        return e;
    }

    public Competition getCompetition(int compet) throws SQLException{
        st=laConnexion.createStatement();
        String requete = "select nomCompet, sexeCompet, individuelle, idSport from COMPETITION where idCompet="+compet;
        ResultSet rs = st.executeQuery(requete);
        Competition c = null;
        while(rs.next()){
             if (rs.getInt("individuelle") == 0){
                Sport sport = this.getSport(rs.getInt("idSport"));
                if(sport.getNom().equals("Natation")){
                    c =(new CompetCoop(rs.getString("nomCompet"),rs.getString("sexeCompet"),sport,4));
                }
                else if(sport.getNom().equals("Volley-Ball")){
                    c=(new CompetCoop(rs.getString("nomCompet"),rs.getString("sexeCompet"), sport,6));
                }
                else if(sport.getNom().equals("Athlétisme")){
                    c=(new CompetCoop(rs.getString("nomCompet"),rs.getString("sexeCompet"), sport,4));
                }
                else if(sport.getNom().equals("Handball")){
                    c=(new CompetCoop(rs.getString("nomCompet"),rs.getString("sexeCompet"), sport,7));
                }
                for(Equipe e : this.getListeEquipe(compet)){
                    c.participer(e);
                }
                for(Epreuve epreuve : this.getEpreuvesCompetition(compet, c)){
                    c.ajoutEpreuve(epreuve);
                }

             }
             else {
                Sport sport = this.getSport(rs.getInt("idSport"));
                c = new CompetInd(rs.getString("nomCompet"),rs.getString("sexeCompet"), sport);
                for(Athlete a : this.getListeAthletes(compet)){
                    c.participer(a);
                }
                for(Epreuve epreuve : this.getEpreuvesCompetition(compet, c)){
                    c.ajoutEpreuve(epreuve);
                }
            }
        }
        return c;
    }

    public List<Athlete> getListeAthletes(int compet) throws SQLException{
        List<Athlete> listeAthletes = new ArrayList<>();
        st=laConnexion.createStatement();
        String requete = "select idAth from PARTICIPE where idCompet="+compet;
        ResultSet rs = st.executeQuery(requete);
        while(rs.next()){
            listeAthletes.add(getAthlete(rs.getInt("idAth")));
        }
        return listeAthletes;
    }

    public Set<CompetInd> getEnsembleCompetInd() throws SQLException{
        Set<CompetInd> ensembleInd= new HashSet<>();
        st=laConnexion.createStatement();
        String requete = "select idCompet from COMPETITION where individuelle=1";
        ResultSet rs = st.executeQuery(requete);
        while(rs.next()){
            ensembleInd.add((CompetInd) this.getCompetition(rs.getInt("idCompet")));
        }
        return ensembleInd;
    }
    
    public Set<CompetCoop> getEnsembleCompetCoop() throws SQLException{
        Set<CompetCoop> ensembleCoop= new HashSet<>();
        st=laConnexion.createStatement();
        String requete = "select idCompet from COMPETITION where individuelle=0";
        ResultSet rs = st.executeQuery(requete);
        while(rs.next()){
            ensembleCoop.add((CompetCoop) this.getCompetition(rs.getInt("idCompet")));
        }
        return ensembleCoop;
    }

    public int getSportId(Sport sport) throws SQLException {
        this.st = laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select idSport from SPORT where nomSport='"+sport.getNom()+"'");
        int id=0;
        while (rs.next()) {
            id = rs.getInt("idSport");
        }
        return id;
    }

    public int getEquipeId(Equipe eq) throws SQLException {
        this.st = laConnexion.createStatement();
        String requete = "select idEquipe from EQUIPE where idPays="+this.getIdPays(eq.getPays())+" and  nomEquipe='"+eq.getNom()+"' and idSport="+this.getSportId(eq.getSport());
        ResultSet rs = this.st.executeQuery(requete);
        int id = 0;
        while (rs.next()) {
            id = rs.getInt("idEquipe");
        }
        return id; //doit retourner l'ID d'une certaine equipe dans la Base
    }

    public int getCompetitionId(Competition compet) throws SQLException {
        int individuelle = 0;
        int id = 0;
        if (compet instanceof CompetInd) individuelle = 1; 
        this.st = laConnexion.createStatement();
            String requete = 
            "select idCompet from COMPETITION where nomCompet='"+compet.getNom()
                +"' and idSport="+this.getSportId(compet.getSport())
                +" and individuelle="+individuelle
                +" and sexeCompet='"+compet.getSexe()+"'";
            // this.st.executeQuery(requete);
        ResultSet rs = this.st.executeQuery(requete);
        while (rs.next()) {
            id = rs.getInt("idCompet");
        }
        System.out.println("success");
        return id; //doit retourner l'Id d'une certaine compet de la base
    }

    public int getAthleteId(Athlete ath) throws SQLException {
        // this.st = laConnexion.createStatement();
        // String requete = "select idAth from ATHLETE where nomAth='"+ath.getNom()+"' and prenomAth='"+ath.getPrenom()+"' and idPays="+this.getIdPays(ath.getPays())+" and idSport="+getSportId(ath.getSport()) ;
        // System.out.println(requete);
        // ResultSet rs = this.st.executeQuery(requete);
        int id=0;
        String sql = "SELECT idAth,nomAth,prenomAth FROM ATHLETE WHERE idPays=? AND idSport=? and nomAth='"+ath.getNom()+"' and prenomAth='"+ath.getPrenom()+"'";
        PreparedStatement pstmt = laConnexion.prepareStatement(sql);
        pstmt.setInt(1, this.getIdPays(ath.getPays()));
        pstmt.setInt(2, this.getSportId(ath.getSport()));
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()) {
            //if (rs.getString("nomAth").equals(ath.getNom()) && rs.getString("prenomAth").equals(ath.getPrenom())) {
                id = rs.getInt("idAth");
            //}
            System.out.println(id);
        }
        return id;
    }

    public int getPlusGrandIdEquipe() throws SQLException {
        this.st = laConnexion.createStatement();
        ResultSet rs =  this.st.executeQuery("select max(idEquipe) nb from EQUIPE");
        int id=0;
        while (rs.next()) {
            id = rs.getInt("nb");
        }
        return id;
    }

    public int getPlusGrandIdEpreuve() throws SQLException {
        this.st = laConnexion.createStatement();
        ResultSet rs =  this.st.executeQuery("select max(idEpreuve) nb from EPREUVE");
        int id=0;
        while (rs.next()) {
            id = rs.getInt("nb");
        }
        return id;
    }

    public void ajouterEquipe(Equipe eq) throws SQLException {
        this.st = laConnexion.createStatement();
        String requete = 
            "insert into EQUIPE values ("
            +(this.getPlusGrandIdEquipe()+1)+","
            +"'"+eq.getNom()+"',"
            +this.getIdPays(eq.getPays())+","
            +this.getSportId(eq.getSport())+
            ")";
        this.st.executeUpdate(requete);
    }

    public void ajouterParticipation(Competition compet, Participant participant) throws SQLException {
        System.out.println("1");
        if (compet instanceof CompetInd && participant instanceof Athlete ) {
            String requete = "insert into PARTICIPE values (?,?)";
            PreparedStatement pstmt = laConnexion.prepareStatement(requete);
            pstmt.setInt(1, this.getCompetitionId(compet));
            pstmt.setInt(2, this.getAthleteId( (Athlete) participant));
            pstmt.executeUpdate();
            System.out.println("2");
        }else if (compet instanceof CompetCoop && participant instanceof Equipe) {
            String requete = "insert into DISPUTE values (?,?)";
            PreparedStatement pstmt = laConnexion.prepareStatement(requete);
            pstmt.setInt(1, this.getCompetitionId(compet));
            pstmt.setInt(2, this.getEquipeId( (Equipe) participant));
            pstmt.executeUpdate();
            // String requete = "insert into DISPUTE values ("+this.getCompetitionId(compet)+","+this.getEquipeId( (Equipe) participant)+")";
        }
    }

    public void ajouterLienAthleteEquipe(Equipe eq, Athlete ath) throws SQLException {
        this.st = laConnexion.createStatement();
        String requete = "insert into APPARTENIR values ("
            +this.getEquipeId(eq)+","
            +this.getAthleteId(ath)+")";
        this.st.executeUpdate(requete);
    }

    public void ajouterEpreuve(Epreuve ep,Competition compet) throws SQLException {
        this.st = laConnexion.createStatement();
        String requete = "insert into EPREUVE values ("
            +(this.getPlusGrandIdEpreuve()+1)+","
            +"'"+ep.getNom()+"',"
            +this.getCompetitionId(compet)+")";
        this.st.executeUpdate(requete);
    }





}
