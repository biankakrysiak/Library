import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;

public class LibraryUklad extends JFrame {
    private User autoryzowany;

    private JLabel jcomp2;
    private JLabel jcomp3;
    private JLabel jcomp4;  // zdjecia
    private JButton borrowBook;
    private JButton logout;
    private JButton myBooks;
    private JTextField title;
    private JLabel jcomp9;
    private JLabel jcomp10;
    private JTextField imieAutor;
    private JTextField nazwiskoAutor;
    private JTextField dataWydania;
    private JLabel jcomp14;
    private JTextField jcomp15;
    private JTextField coIle;
    private JLabel jcomp17;
    private JLabel jcomp18;
    private JButton giveBack;
    public static JTextField tekst;

    private JComboBox<Object> bookList;

    static{
        new Book("Wiedźmin", "Sapkowski", "Andrzej", "1990-05-01", 560, "okladki/wiedzmin.jpg");
        new Book("Harry Potter i Kamień Filozoficzny", "Rowling", "J.K.", "1997-06-26", 264, "okladki/harry-potter.jpg");
        new Book("Zbrodnia i kara", "Dostojewski", "Fiodor", "1866-01-01", 54, "okladki/zbrodnia-i-kara.jpg");
        new Book("Mistrz i Małgorzata", "Bułhakow", "Michaił", "1966-05-01", 962, "okladki/mistrz-i-malgorzata.jpg");
        new Book("Hobbit", "Tolkien", "J.R.R.", "1937-09-21", 145, "okladki/hobbit.jpg");
        new Book("1984", "Orwell", "Eric George", "1949-06-08", 67, "okladki/1984.jpg");
        new Book("Pan Tadeusz", "Mickiewicz", "Adam", "1834-01-01", 43, "okladki/pan-tadeusz.jpg");
        new Book("Wesele", "Wyspiański", "Stanisław", "1901-01-01", 866, "okladki/wesele.jpg");
        new Book("Lalka", "Prus", "Bolesław", "1890-01-01", 38, "okladki/lalka.jpg");
        new Book("Chłopi", "Reymont", "Władysław", "1904-1909", 901, "okladki/chlopi.jpg");
        new Book("Tango", "Mrożek", "Sławomir", "1965-01-01", 341, "okladki/tango.jpg");

        new Magazine("Time", "Luce", "Henry", "2024-12-09", "Tygodnik", "okladki/time.jpg");
        new Magazine("National Geographic", "Grosvenor", "Gilbert", "2024-10-01", "Miesięcznik", "okladki/national-geographic.jpg");
        new Magazine("The Economist", "Wilson", "James", "2024-12-14", "Tygodnik", "okladki/economist.jpg");
        new Magazine("Forbes", "Forbes", "B.C.", "2024-09-01", "Miesięcznik", "okladki/forbes.jpg");
        new Magazine("The New Yorker", "Ross", "Harold", "2024-10-28", "Tygodnik", "okladki/new-yorker.jpg");
    }

    public LibraryUklad(User a){
        this.autoryzowany = a;

        JLabel backgroundLabel = new JLabel(new ImageIcon("inne/ukladTlo.jpg"));
        backgroundLabel.setBounds(0, 0, 960, 420);
        setContentPane(backgroundLabel);
        backgroundLabel.setLayout(null);

        bookList = new JComboBox<>();
        DefaultComboBoxModel<Object> box = new DefaultComboBoxModel<>(); // dodajemy do combobox obiekty dwoch roznych klas

        for (Book book : Book.ksiazki) {
            box.addElement(book.tytul);
        }
        for (Magazine magazine : Magazine.gazety) {
            box.addElement(magazine.tytul);
        }

        bookList.setModel(box);

        jcomp2 = new JLabel("Okładka");
        jcomp3 = new JLabel("Dostępne książki i magazyny");
        jcomp4 = new JLabel();
        jcomp4.setPreferredSize(new Dimension(200, 200));
        borrowBook = new JButton("Wypożycz wybraną książkę");
        logout = new JButton("Wyloguj się");
        myBooks = new JButton("Moje książki");
        title = new JTextField(5);
        jcomp9 = new JLabel("Tytuł");
        jcomp10 = new JLabel("Autor");
        imieAutor = new JTextField(5);
        nazwiskoAutor = new JTextField(5);
        dataWydania = new JTextField(5);
        jcomp14 = new JLabel("Data wydania");
        jcomp15 = new JTextField(5);
        coIle = new JTextField(5);
        jcomp17 = new JLabel("Numer wydania (książka)");
        jcomp18 = new JLabel("Cykliczność (gazeta)");
        giveBack = new JButton("Oddaj wybraną książkę");
        tekst = new JTextField(5);
        setTitle("Witaj " + autoryzowany.imie + "! Wypożyczanie i zwracanie książek");
        setPreferredSize(new Dimension(960, 420));
        setLayout(null);

        // kolory
        borrowBook.setBackground(new Color(245, 245, 220));
        logout.setBackground(new Color(245, 245, 220));
        myBooks.setBackground(new Color(245, 245, 220));
        title.setBackground(new Color(245, 245, 220));
        imieAutor.setBackground(new Color(245, 245, 220));
        nazwiskoAutor.setBackground(new Color(245, 245, 220));
        dataWydania.setBackground(new Color(245, 245, 220));
        coIle.setBackground(new Color(245, 245, 220));
        giveBack.setBackground(new Color(245, 245, 220));
        bookList.setBackground(new Color(245, 245, 220));
        jcomp15.setBackground(new Color(245, 245, 220));
        tekst.setBackground(new Color(245, 245, 220));

        add(bookList);
        add(jcomp2);
        add(jcomp3);
        add(jcomp4);  // okladki
        add(borrowBook);
        add(logout);
        add(myBooks);
        add(title);
        add(jcomp9);
        add(jcomp10);
        add(imieAutor);
        add(nazwiskoAutor);
        add(dataWydania);
        add(jcomp14);
        add(jcomp15);
        add(coIle);
        add(jcomp17);
        add(jcomp18);
        add(giveBack);
        add(tekst);

        logout.addActionListener(e -> {
            System.out.println("Wylogowano użytkownika: " + autoryzowany.login);
            User.loggedInUser = null;
            dispose();
            new Login();
        });


        borrowBook.addActionListener(e -> {
            Object wybrany = bookList.getSelectedItem();

            for(Book book: Book.ksiazki){
                if(book.tytul.equals(wybrany)){
                    autoryzowany.wypozyczKsiazke(book);
                    //System.out.println("Książka " + book.tytul + " została wypożyczona.");
                   // tekst.setText("Książka " + book.tytul + " została wypożyczona.");
                }
            }

            for(Magazine magazine : Magazine.gazety){
                if(magazine.tytul.equals(wybrany)){
                    autoryzowany.wypozyczGazete(magazine);
                    //System.out.println("Gazeta " + magazine.tytul + " została wypożyczona.");
                   // tekst.setText("Gazeta " + magazine.tytul + " została wypożyczona.");
                }
            }

        });

        giveBack.addActionListener(e -> {
            Object wybrany = bookList.getSelectedItem();
                for (Book book : autoryzowany.wypozyczoneKsiazki) {
                    if (book.tytul.equals(wybrany)) {
                        autoryzowany.wypozyczoneKsiazki.remove(book);
                        System.out.println("Książka " + book.tytul + " została oddana.");
                        tekst.setText("Książka " + book.tytul + " została oddana.");
                        break;
                    }
                }

                for (Magazine magazine : autoryzowany.wypozyczoneGazety) {
                    if (magazine.tytul.equals(wybrany)) {
                        autoryzowany.wypozyczoneGazety.remove(magazine);
                        System.out.println("Gazeta " + magazine.tytul + " została oddana.");
                        tekst.setText("Gazeta " + magazine.tytul + " została oddana.");
                        break;
                    }
                }
        });

        myBooks.addActionListener(e -> {
            int i = 0;
            System.out.println("Twoja biblioteka: ");
            for(Book book : autoryzowany.wypozyczoneKsiazki){
                System.out.println(i + ". Książka: " + book.tytul + " Autor: " + book.imieAutor + " " + book.nazwiskoAutor);
                i++;
            }

            for(Magazine magazine : autoryzowany.wypozyczoneGazety){
                System.out.println(i + ". Gazeta: " + magazine.tytul + " Autor: " + magazine.imieAutor + " " + magazine.nazwiskoAutor);
                i++;
            }

        });


        // pozycje elementow
        bookList.setBounds(70, 60, 260, 25);
        jcomp2.setBounds(440, 30, 100, 25);
        jcomp3.setBounds(120, 30, 215, 30);
        jcomp4.setBounds(370, 60, 200, 300);
        borrowBook.setBounds(95, 135, 215, 40); //
        logout.setBounds(815, 335, 100, 25);
        myBooks.setBounds(95, 260, 215, 40);
        title.setBounds(665, 65, 205, 25);
        jcomp9.setBounds(615, 65, 90, 25);
        jcomp10.setBounds(585, 100, 100, 25);
        imieAutor.setBounds(635, 100, 125, 25);
        nazwiskoAutor.setBounds(765, 100, 125, 25);
        dataWydania.setBounds(710, 135, 100, 25);
        jcomp14.setBounds(620, 135, 100, 25);
        jcomp15.setBounds(710, 200, 100, 25);
        coIle.setBounds(710, 265, 100, 25);
        jcomp17.setBounds(695, 170, 155, 25);
        jcomp18.setBounds(695, 230, 155, 35);
        giveBack.setBounds(95, 195, 215, 40); //
        tekst.setBounds(95, 320, 215, 40);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        bookList.addActionListener(e -> {
            Object selected = bookList.getSelectedItem();
            if (selected != null) {
                String selectedTitle = selected.toString();
                updatePola(selectedTitle);
            }
        });
    }

    private void updatePola(String selectedTitle) {
        for (Book book : Book.ksiazki) {
            if (book.tytul.equals(selectedTitle)) {
                imieAutor.setText(book.imieAutor);
                nazwiskoAutor.setText(book.nazwiskoAutor);
                dataWydania.setText(book.dataWydania);
                title.setText(book.tytul);
                jcomp15.setText(String.valueOf(book.numerWydania));
                coIle.setText("---------------------------------");
                updateImage(book.zdjecie);
                return;
            }
        }
        for (Magazine magazine : Magazine.gazety) {
            if (magazine.tytul.equals(selectedTitle)) {
                imieAutor.setText(magazine.imieAutor);
                nazwiskoAutor.setText(magazine.nazwiskoAutor);
                dataWydania.setText(magazine.dataWydania);
                title.setText(magazine.tytul);
                jcomp15.setText("---------------------------------");
                coIle.setText(magazine.coIle);
                updateImage(magazine.zdjecie);
                return;
            }
        }
    }
    private void updateImage(String imagePath) {  // skalowanie okladek do 200x300
        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage().getScaledInstance(jcomp4.getWidth(), jcomp4.getHeight(), Image.SCALE_SMOOTH);
        jcomp4.setIcon(new ImageIcon(image));
    }

}