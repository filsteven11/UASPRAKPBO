/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamesdb.Controler;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author KATANA
 */
public class Controller {
    
    private DatabaseHandler conn; 
    public Controller() {
        conn = new DatabaseHandler(); 
    }

    public boolean inputGameToDB(String name, String genre, int price) {
        conn.connect();
        String query = "INSERT INTO games (name, genre, price) VALUES (?, ?, ?)";
        PreparedStatement stmt;
        try {
            stmt = conn.con.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, genre);
            stmt.setInt(3, price);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

   public ArrayList<Controller> getAllGames() {
    ArrayList<Controller> listGames = new ArrayList<>();
    try (Statement stmt = conn.con.createStatement()) {
        ResultSet rs = stmt.executeQuery("SELECT * FROM games");
        while (rs.next()) {
            Controller game = new Controller();
            game.setId(rs.getInt("id"));
            game.setName(rs.getString("name"));
            game.setGenre(rs.getString("genre"));
            game.setPrice(rs.getInt("price"));
            listGames.add(game);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Atau tindakan lainnya seperti menampilkan pesan kesalahan
    }
    return listGames;
}

public Controller getGameById(int gameId) {
    Controller game = new Controller();
    try (PreparedStatement pstmt = conn.con.prepareStatement("SELECT * FROM games WHERE id = ?")) {
        pstmt.setInt(1, gameId);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            game.setId(rs.getInt("id"));
            game.setName(rs.getString("name"));
            game.setGenre(rs.getString("genre"));
            game.setPrice(rs.getInt("price"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Atau tindakan lainnya seperti menampilkan pesan kesalahan
    }
    return game;
}

    public DatabaseHandler getConn() {
        return conn;
    }

    public void setConn(DatabaseHandler conn) {
        this.conn = conn;
    }

    private void setId(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    private void setName(String string) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    private void setGenre(String string) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    private void setPrice(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    

public static void main(String[] args) {
        Controller gameController = new Controller();
        
        // Mengambil semua games
        ArrayList<Controller> games = gameController.getAllGames();
        
        // Lakukan sesuatu dengan games, seperti menampilkannya
        for (Controller game : games) {
            System.out.println(game.getName() + " - " + game.getGenre() + " - $" + game.getPrice());
        }
    }

    private String getName() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    private String getGenre() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    private String getPrice() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}


