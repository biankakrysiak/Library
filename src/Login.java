import java.awt.*;
import javax.swing.*;

public class Login extends JFrame {
    private JButton register;
    private JButton login;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JButton exit;


    public Login() {
        setTitle("Library");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(750, 400));
        setLayout(null);
        setUndecorated(true);

        JLabel backgroundLabel = new JLabel(new ImageIcon("inne/loginTlo.jpg"));
        backgroundLabel.setBounds(0, 0, 750, 400);
        setContentPane(backgroundLabel);
        backgroundLabel.setLayout(null);

        register = new JButton("Zarejestruj się");
        login = new JButton("Zaloguj się");
        usernameField = new JTextField(5);
        passwordField = new JPasswordField(5);
        usernameLabel = new JLabel("Login");
        passwordLabel = new JLabel("Hasło");
        exit = new JButton("Wyjdź");

        register.setBackground(new Color(245, 245, 220));
        login.setBackground(new Color(245, 245, 220));
        usernameField.setBackground(new Color(245, 245, 220));
        passwordField.setBackground(new Color(245, 245, 220));
        exit.setBackground(new Color(245, 245, 220));

        add(register);
        add(login);
        add(usernameField);
        add(passwordField);
        add(usernameLabel);
        add(passwordLabel);
        add(exit);

        register.setBounds(365, 255, 225, 50);
        login.setBounds(120, 255, 210, 50);
        usernameField.setBounds(280, 110, 195, 35);
        passwordField.setBounds(280, 175, 195, 35);
        usernameLabel.setBounds(180, 115, 100, 25);
        passwordLabel.setBounds(180, 180, 100, 25);
        exit.setBounds(615,325,100, 25);

        login.addActionListener(e -> {
            String login = usernameField.getText();
            String password = new String(passwordField.getPassword());
            User autoryzowany = User.authenticate(login, password);

            if (autoryzowany != null) {
                if(login.equals("admin") && password.equals("123")){
                    dispose();
                    LibraryUklad x = new LibraryUklad(autoryzowany); // bez tego zalogowanie sie na admina nie wyswietla ksiazek, trzeba uruchomic klase LibraryUklad
                    x.setVisible(false);
                    dispose();
                    new AdminPanel();
                }
                else{
                    dispose();
                    new LibraryUklad(autoryzowany);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Nieprawidłowy login lub hasło", "Błąd", JOptionPane.ERROR_MESSAGE);
            }
        });

        register.addActionListener(e -> {
            dispose();
            new Register();
        });

        exit.addActionListener(e ->{
            dispose();
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }
}
