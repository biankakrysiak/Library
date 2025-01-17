import java.awt.*;
import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AdminPanel extends JFrame {
    private JButton usersDisplay;
    private JButton userDelete;
    private JButton booksDisplay;
    private JButton bookAdd;
    private JButton bookDelete;
    private JButton userChange;
    private JLabel userManagment;
    private JLabel bookManagment;
    private JButton logout;

    public AdminPanel() {
        usersDisplay = new JButton("Wyświetl użytkowników");
        userDelete = new JButton("Usuń użytkownika");
        booksDisplay = new JButton("Wyświetl książki");
        bookAdd = new JButton("Dodaj książkę");
        bookDelete = new JButton("Usuń książkę");
        userChange = new JButton("Zmień dane użytkownika");
        userManagment = new JLabel("Zarządzanie użytkownikami");
        bookManagment = new JLabel("Zarządzanie książkami");
        logout = new JButton("Wyloguj się");

        setPreferredSize(new Dimension(375, 600));
        setLayout(null);

        JLabel backgroundLabel = new JLabel(new ImageIcon("inne/adminTlo.jpg"));
        backgroundLabel.setBounds(0, 0, 375, 600);
        setContentPane(backgroundLabel);
        backgroundLabel.setLayout(null);

        add(usersDisplay);
        add(userDelete);
        add(booksDisplay);
        add(bookAdd);
        add(bookDelete);
        add(userChange);
        add(userManagment);
        add(bookManagment);
        add(logout);

        usersDisplay.setBounds(65, 75, 245, 50);
        userDelete.setBounds(65, 205, 245, 50);
        booksDisplay.setBounds(65, 315, 245, 50);
        bookAdd.setBounds(65, 380, 245, 50);
        bookDelete.setBounds(65, 445, 245, 50);
        userChange.setBounds(65, 140, 245, 50);
        userManagment.setBounds(100, 40, 160, 25);
        bookManagment.setBounds(120, 285, 135, 25);
        logout.setBounds(140, 520, 100, 25);

        usersDisplay.setBackground(new Color(245, 245, 220));
        userDelete.setBackground(new Color(245, 245, 220));
        booksDisplay.setBackground(new Color(245, 245, 220));
        bookAdd.setBackground(new Color(245, 245, 220));
        bookDelete.setBackground(new Color(245, 245, 220));
        userChange.setBackground(new Color(245, 245, 220));
        userManagment.setBackground(new Color(245, 245, 220));
        bookManagment.setBackground(new Color(245, 245, 220));
        logout.setBackground(new Color(245, 245, 220));

        logout.addActionListener(e -> {
            dispose();
            new Login();
        });

        usersDisplay.addActionListener(e -> {
            int i = 1;
            for(User user : User.users){
                System.out.println(i + ". " + user);
                i++;
            }
            System.out.println("==========================");
        });

        userChange.addActionListener(e ->{
            String input = JOptionPane.showInputDialog("Podaj indeks użytkownika którego dane chcesz zmienić:");
            int indeks = Integer.parseInt(input);
            User change = null;

            for(User user : User.users){
                if(user.indeks == indeks){
                    change = user;
                    break;
                }
            }

            if(change != null && indeks != 1) {
                String newImie = JOptionPane.showInputDialog("Podaj nowe imię:", change.imie);
                change.imie = newImie;

                String newNazwisko = JOptionPane.showInputDialog("Podaj nowe nazwisko:", change.nazwisko);
                change.nazwisko = newNazwisko;

                String newLogin = JOptionPane.showInputDialog("Podaj nowy login:", change.login);
                change.login = newLogin;

                String newDate = JOptionPane.showInputDialog("Podaj nową datę urodzenia: ", change.dataUrodzenia);
                change.dataUrodzenia = newDate;

                String newPassword = JOptionPane.showInputDialog("Podaj nowe hasło:", change.password);
                change.password = newPassword;
            } else if (indeks == 1) {
                JOptionPane.showMessageDialog(null, "Nie można zmienić danych administratora.");
            } else{
                JOptionPane.showMessageDialog(null, "Nie znaleziono użytkownika o podanym indeksie.");
            }
        });

        userDelete.addActionListener(e -> {
            String input = JOptionPane.showInputDialog("Podaj indeks użytkownika do usunięcia:");
            if (input == null) {  // przerwanie metody
                return;
            }

            int indeks = Integer.parseInt(input);
            boolean znaleziono = false;

            for (int i = 0; i < User.users.size(); i++) {
                User user = User.users.get(i);
                if (user.indeks == 1 && indeks == 1) {
                    JOptionPane.showMessageDialog(null, "Nie można usunąć konta admina.");
                    return;
                }
                if (user.indeks == indeks) {
                    User.users.remove(i);
                    znaleziono = true;
                    JOptionPane.showMessageDialog(null, "Użytkownik został usunięty.");
                    break;
                }
            }

            if (!znaleziono) {
                JOptionPane.showMessageDialog(null, "Nie znaleziono użytkownika o podanym indeksie.");
            }
        });

        booksDisplay.addActionListener(e -> {
            int i = 1;
            for(Book book : Book.ksiazki){
                System.out.println(i + ". " + book);
                i++;
            }

            for(Magazine magazine : Magazine.gazety){
                System.out.println(i + ". " + magazine);
                i++;
            }

            System.out.println("==========================");
        });

        bookAdd.addActionListener(e -> {
            String input = JOptionPane.showInputDialog("Chcesz dodać książkę czy gazetę? [K/G]:");
            if (input == null) {  // przerwanie metody / cancel
                return;
            }

            String zdjecie = "okladki/default.jpg";

            if(input.equals("K") || input.equals("k")){
                String tytul = JOptionPane.showInputDialog("Podaj tytuł książki:");
                String autorNazwisko = JOptionPane.showInputDialog("Podaj nazwisko autora książki:");
                String autorImie = JOptionPane.showInputDialog("Podaj imię autora książki:");
                String dataWydania = JOptionPane.showInputDialog("Podaj datę wydania (yyyy-MM-dd):");
                String numerWydania = JOptionPane.showInputDialog("Podaj numer wydania: ");

                if (tytul == null || tytul.isEmpty() || autorNazwisko == null || autorNazwisko.isEmpty() || autorImie == null || autorImie.isEmpty() ||
                        dataWydania == null || dataWydania.isEmpty() || numerWydania == null || numerWydania.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nie podano wszystkich danych. Spróbuj ponownie.");
                    return;
                }

                int numer = 0;
                try {
                    numer = Integer.parseInt(numerWydania);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Numer wydania musi być liczbą całkowitą.");
                    return;
                }

                LocalDate date = sprawdzData(dataWydania);
                if (date == null) {
                    JOptionPane.showMessageDialog(null, "Niepoprawny format daty. Użyj formatu yyyy-MM-dd.");
                    return;
                }

                Book newBook = new Book(tytul, autorNazwisko, autorImie, dataWydania, numer, zdjecie);

                JOptionPane.showMessageDialog(null, "Książka została dodana. Nie zapomnij zmienić zdjęcia.");
            }
            else if (input.equals("G") || input.equals("g")) {
                String tytul = JOptionPane.showInputDialog("Podaj tytuł gazety:");
                String autorNazwisko = JOptionPane.showInputDialog("Podaj nazwisko autora gazety:");
                String autorImie = JOptionPane.showInputDialog("Podaj imię autora gazety:");
                String dataWydania = JOptionPane.showInputDialog("Podaj datę wydania (yyyy-MM-dd):");
                String coIle = JOptionPane.showInputDialog("Podaj cykliczność gazety: ");

                if (tytul == null || tytul.isEmpty() ||
                        autorNazwisko == null || autorNazwisko.isEmpty() ||
                        autorImie == null || autorImie.isEmpty() ||
                        dataWydania == null || dataWydania.isEmpty() ||
                        coIle == null || coIle.isEmpty()) {

                    JOptionPane.showMessageDialog(null, "Nie podano wszystkich danych. Spróbuj ponownie.");
                    return;
                }

                LocalDate date = sprawdzData(dataWydania);
                if (date == null) {
                    JOptionPane.showMessageDialog(null, "Niepoprawny format daty. Format: yyyy-MM-dd.");
                    return;
                }

                Magazine newMagazine = new Magazine(tytul, autorNazwisko, autorImie, dataWydania, coIle, zdjecie);

                JOptionPane.showMessageDialog(null, "Gazeta została dodana. Nie zapomnij zmienić zdjęcia.");
            }
            else{
                JOptionPane.showMessageDialog(null, "Niepoprawny wybór.");
            }
        });

        bookDelete.addActionListener(e -> {
            String input = JOptionPane.showInputDialog("Chcesz usunąć książkę czy gazetę? [K/G]:");
            if (input == null) {  // przerwanie metody
                return;
            }

            if(input.equals("k") || input.equals("K")){
                boolean znaleziona = false;
                String tytul = JOptionPane.showInputDialog("Podaj tytuł książki którą chcesz usunąć:");
                for(int i = 0; i < Book.ksiazki.size(); i++){
                    if (Book.ksiazki.get(i).tytul.equalsIgnoreCase(tytul)) {
                        Book.ksiazki.remove(i);
                        znaleziona = true;
                        JOptionPane.showMessageDialog(null, "Książka została usunięta.");
                        break;
                    }
                }
                if (!znaleziona) {
                    JOptionPane.showMessageDialog(null, "Nie znaleziono książki o podanym tytule.");
                }
            }
            else if (input.equals("G") || input.equals("g")) {
                boolean znaleziona = false;
                String tytul = JOptionPane.showInputDialog("Podaj tytuł gazety którą chcesz usunąć:");
                for(int i = 0; i < Magazine.gazety.size(); i++){
                    if (Magazine.gazety.get(i).tytul.equalsIgnoreCase(tytul)) {
                        Magazine.gazety.remove(i);
                        znaleziona = true;
                        JOptionPane.showMessageDialog(null, "Gazeta została usunięta.");
                        break;
                    }
                }
                if (!znaleziona) {
                    JOptionPane.showMessageDialog(null, "Nie znaleziono gazety o podanym tytule.");
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Niepoprawny wybór.");
            }

        });

        setTitle("Panel administratora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private LocalDate sprawdzData(String dateStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

}
