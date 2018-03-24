package loginapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbUtil.dbConnection;

public class LoginModel {
    Connection connection;

    public LoginModel() {
        try {
            this.connection = dbConnection.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        if (this.connection == null) {
            System.exit(1);
        }
    }

    public boolean isDatabaseConnected() {
        return this.connection != null;
    }

    public boolean isLogin(String user, String pass, String opt) throws Exception {

        PreparedStatement pr = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM login where username = ? and password = ? and division = ?";

        try {
            pr = this.connection.prepareStatement(sql);

            //Below, checking to see if the username passed in is on the db
            pr.setString(1, user);

            //Below, checking to see if the password passed in is on db
            pr.setString(2, pass);

            //Below, checking the division selected
            pr.setString(3, opt);

            rs = pr.executeQuery();

            boolean boll1;

            if (rs.next()) {
                return true;
            }

            return false;

        } catch (SQLException ex) {
            return false;
        }

        /* might be missing extra curly's below */
        finally {
            pr.close();
            rs.close();
        }
    }

}
