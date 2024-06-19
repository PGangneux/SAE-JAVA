package fr.univ_orleans.iut45.mud.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RequeteLogAPI {
    Connexion laConnexion;
	Statement st;

    public RequeteLogAPI(Connexion laConnexion){
        this.laConnexion=laConnexion;
    }

    public boolean checkUser(String username, String password) throws SQLException {
        this.st = this.laConnexion.createStatement();
        ResultSet dataBasePasswordRs = this.st.executeQuery("select hashedPassword from USERACCOUNT where username="+username);
        ResultSet hashedPasswordRs = this.st.executeQuery("select SHA("+password+")");
        hashedPasswordRs.next();
        dataBasePasswordRs.next();
        String sqlPassword = dataBasePasswordRs.getString(1);
        String hashedPassword = hashedPasswordRs.getString(1);
        return sqlPassword.equals(hashedPassword);
    }

    public String getUserPrivilege(String username) throws SQLException {
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select privilegeName from ACCOUNTPRIVILEGE natural join USERACCOUNT where username="+username);
        rs.next();
        return rs.getString(1);
    }

    public int getNbUser() throws SQLException {
        this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("select count(idAcccount) id from USERACCOUNT");
        rs.next();
        return rs.getInt("id");
    }

    public void insertUser(String username, String password, String privilege) throws SQLException {
        this.st = this.laConnexion.createStatement();
        int privilegeId = 0;
        if (privilege.equals("organisateur")) privilegeId = 1;
        if (privilege.equals("administrateur")) privilegeId = 2;
        int nextId = this.getNbUser()+1;
        this.st.executeQuery(
            "insert into USERACCOUNT values("
            +nextId+","
            +username+","
            +"SHA("+password+")"+","
            +privilegeId
        );
    }
}
