import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Register extends JFrame {
    private JTextField imieField;
    private JTextField nazwiskoField;
    private JTextField loginField;
    private JPasswordField passwordField;
    private JTextField indeksField;
    private JTextField dataUrodzeniaField;
    private JButton registerButton;
    private JButton back;

    public Register() {
        setTitle("Rejestracja");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new Dimension(700, 400));
        setLayout(null);

        JLabel backgroundLabel = new JLabel(new ImageIcon("inne/registerTlo2.jpg"));
        backgroundLabel.setBounds(0, 0, 700, 400);
        setContentPane(backgroundLabel);
        backgroundLabel.setLayout(null);

        imieField = new JTextField(5);
        nazwiskoField = new JTextField(5);
        loginField = new JTextField(5);
        passwordField = new JPasswordField(5);
        indeksField = new JTextField(5);
        dataUrodzeniaField = new JTextField(5);
        registerButton = new JButton("Zarejestruj się");
        back = new JButton("Wróć");

        add(new JLabel("Imię:")).setBounds(180, 55, 100, 25);
        add(imieField).setBounds(280, 55, 195, 35);
        add(new JLabel("Nazwisko:")).setBounds(180, 95, 100, 25);
        add(nazwiskoField).setBounds(280, 95, 195, 35);
        add(new JLabel("Login:")).setBounds(180, 135, 100, 25);
        add(loginField).setBounds(280, 135, 195, 35);
        add(new JLabel("Hasło:")).setBounds(180, 175, 100, 25);
        add(passwordField).setBounds(280, 175, 195, 35);
        add(new JLabel("Indeks:")).setBounds(180, 215, 100, 25);
        add(indeksField).setBounds(280, 215, 195, 35);
        add(new JLabel("Data urodzenia (yyyy-MM-dd):")).setBounds(110, 255, 180, 25);
        add(dataUrodzeniaField).setBounds(280, 255, 195, 35);
        add(registerButton).setBounds(280, 300, 195, 35);
        add(back).setBounds(550, 306, 90, 25);

        imieField.setBackground(new Color(245, 245, 220));
        nazwiskoField.setBackground(new Color(245, 245, 220));
        loginField.setBackground(new Color(245, 245, 220));
        passwordField.setBackground(new Color(245, 245, 220));
        indeksField.setBackground(new Color(245, 245, 220));
        dataUrodzeniaField.setBackground(new Color(245, 245, 220));
        registerButton.setBackground(new Color(245, 245, 220));
        back.setBackground(new Color(245, 245, 220));

        registerButton.addActionListener(e -> handleRegistration());

        back.addActionListener(e -> {
            dispose();
            new Login();
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void handleRegistration() {
        String imie = imieField.getText();
        String nazwisko = nazwiskoField.getText();
        String login = loginField.getText();
        String password = new String(passwordField.getPassword());
        String indeks = indeksField.getText();
        String dataUrodzenia = dataUrodzeniaField.getText();

        if (imie.isEmpty() || nazwisko.isEmpty() || login.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Wszystkie pola muszą być wypełnione.");
            return;
        }

        int numer;
        try {
            numer = Integer.parseInt(indeks);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Indeks musi być liczbą całkowitą.");
            return;
        }

        LocalDate data;
        try {
            data = LocalDate.parse(dataUrodzenia, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            if (data.getYear() < 1900 || data.getYear() > 2025) {
                JOptionPane.showMessageDialog(null, "Niepoprawny rok w dacie urodzenia.");
                return;
            }
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(null, "Niepoprawny format daty urodzenia.");
            return;
        }

        boolean zajety = false;
        for (User  user : User.users) {
            if (String.valueOf(user.indeks).equals(indeks)) {
                zajety = true;
                break;
            }
        }

        if (!zajety) {
            new User(imie, nazwisko, numer, dataUrodzenia, login, password);
            JOptionPane.showMessageDialog(this, "Rejestracja zakończona.");
            this.setVisible(false);
            new Login();
        } else {
            JOptionPane.showMessageDialog(this, "Indeks jest zajęty.");
        }

    }
}
