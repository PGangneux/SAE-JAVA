package fr.univ_orleans.iut45.mud.JDBC;

import java.sql.*;



public class Connexion {
	private Connection mysql;
	private boolean connecte=false;

	public Connexion() throws ClassNotFoundException{
		this.mysql=null;
		this.connecte=false;
		Class.forName("com.mysql.cj.jdbc.Driver");
	}

	public void connecter(String nomServeur, String nomBase, String nomLogin, String motDePasse) throws SQLException {
		this.mysql=null;
		this.connecte=false;
		this.mysql = DriverManager.getConnection("jdbc:mysql://"+nomServeur+":3306/"+nomBase, nomLogin, motDePasse);
		this.connecte=true;
	}
	public void close() throws SQLException {
		this.mysql.close();
		this.connecte=false;
	}

    public boolean isConnecte() { return this.connecte;}
    public Blob createBlob()throws SQLException{
		return this.mysql.createBlob();
	}
	public Statement createStatement() throws SQLException {
		return this.mysql.createStatement();
	}

	public PreparedStatement prepareStatement(String requete) throws SQLException{
		return this.mysql.prepareStatement(requete);
	}
	
}