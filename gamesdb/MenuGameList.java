/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamesdb;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuGameList extends JFrame {
    private JPanel gamePanel;

    public MenuGameList() {
        setTitle("Game List");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ArrayList<Game> games = createGames();

        JPanel panel = new JPanel(new BorderLayout());
        add(panel);

        gamePanel = new JPanel(new GridLayout(0, 1));
        JScrollPane scrollPane = new JScrollPane(gamePanel);
        panel.add(scrollPane, BorderLayout.CENTER);

        displayGames(games);

        JButton transactionButton = new JButton("Transaction");
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(transactionButton);
        panel.add(topPanel, BorderLayout.NORTH);

        transactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openTransactions();
            }
        });

        setVisible(true); 
    }

    private void openTransactions() {
        MenuGameList transactions = new MenuGameList();
        transactions.setVisible(true);
        this.setVisible(false);
    }

    private void displayGames(ArrayList<Game> games) {
        for (Game game : games) {
            JPanel gameInfoPanel = new JPanel(new GridLayout(0, 1));

            JLabel nameLabel = new JLabel("Name: " + game.getName());
            JLabel genreLabel = new JLabel("Genre: " + game.getGenre());
            JLabel priceLabel = new JLabel("Price: $" + game.getPrice());

            JButton buyButton = new JButton("Buy Game");
            buyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean success = buyGame(game.getId());
                    if (success) {
                        JOptionPane.showMessageDialog(null, "Purchase successful!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Purchase failed!");
                    }
                }
            });

            gameInfoPanel.add(nameLabel);
            gameInfoPanel.add(genreLabel);
            gameInfoPanel.add(priceLabel);
            gameInfoPanel.add(buyButton);

            gamePanel.add(gameInfoPanel);
        }
    }

    private ArrayList<Game> createGames() {
        ArrayList<Game> gameList = new ArrayList<>();
        
        // Create three sample games
        gameList.add(new Game(1, "Spiderman", "Action", 19.99));
        gameList.add(new Game(2, "Jumanji", "Adventure", 9.99));
        gameList.add(new Game(3, "Scrabble", "Puzzle", 17.45));

        return gameList;
    }

    private boolean buyGame(int gameId) {
         return true;
    }

    private static class Game {
        private int id;
        private String name;
        private String genre;
        private double price;

        public Game(int id, String name, String genre, double price) {
            this.id = id;
            this.name = name;
            this.genre = genre;
            this.price = price;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getGenre() {
            return genre;
        }

        public double getPrice() {
            return price;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MenuGameList();
            }
        });
    }
}
