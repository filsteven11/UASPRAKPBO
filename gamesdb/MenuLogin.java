import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MenuLogin extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;

    public MenuLogin() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
    panel.setLayout(null);
    
    JLabel nameLabel = new JLabel("Name");
    nameLabel.setBounds(10, 10, 80, 25);
    panel.add(nameLabel);

    JTextField nameField = new JTextField(20);
    nameField.setBounds(100, 80, 165, 25);
    panel.add(nameField);

    JLabel emailLabel = new JLabel("Email");
    emailLabel.setBounds(10, 20, 80, 25);
    panel.add(emailLabel);

    emailField = new JTextField(20);
    emailField.setBounds(100, 20, 165, 25);
    panel.add(emailField);

    JLabel passwordLabel = new JLabel("Password");
    passwordLabel.setBounds(10, 50, 80, 25);
    panel.add(passwordLabel);

    passwordField = new JPasswordField(20);
    passwordField.setBounds(100, 50, 165, 25);
    panel.add(passwordField);

    JButton loginButton = new JButton("Login");
    loginButton.setBounds(100, 110, 80, 25);
    panel.add(loginButton);

    loginButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText(); // Ambil nilai dari input "Name"
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

                        try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gamesdb", "root", " ");
                Statement stmt = conn.createStatement();

                String query = "SELECT * FROM Users WHERE email='" + email + "' AND password='" + password + "'";
                ResultSet rs = stmt.executeQuery(query);

                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Login failed. Invalid email or password.");
                }

                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    });
}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MenuLogin();
            }
        });
    }
}
