package hu.nive.ujratervezes.jurassic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JurassicPark {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;
    private Connection conn;

    public JurassicPark(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
        this.conn = getConnection();
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (SQLException e) {
            System.err.println("Database not reachable.");
        }
        return connection;
    }

    public List<String> checkOverpopulation() {
        List<String> result = new ArrayList<>();
        String query = "SELECT breed FROM dinosaur WHERE expected < actual ORDER BY breed ASC";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
