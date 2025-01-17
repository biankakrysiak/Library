import javax.swing.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    String imie;
    String nazwisko;
    String login;
    String password;
    int indeks;
    String dataUrodzenia;
    public static User loggedInUser = null;
    public ArrayList<Book> wypozyczoneKsiazki = new ArrayList<>();
    public ArrayList<Magazine> wypozyczoneGazety = new ArrayList<>();
    static ArrayList<User> users = new ArrayList<>();

    User(){
        this.imie = "Jan";
        this.nazwisko = "Kowalski";
        this.login = "username";
        this.password = "password";
        //this.indeks = Integer.parseInt("12345");
        this.dataUrodzenia = "1998-01-10";
        users.add(this);
    }

    User(String i, String n, int in, String d, String l, String p){ // konstruktor pełny
        this.imie = i;
        this.nazwisko = n;
        this.indeks = in;
        this.dataUrodzenia = d;
        this.login = l;
        this.password = p;
        users.add(this);
    }

    public static User authenticate(String login, String password) {
        for (User user : users) {
            if (user.login.equals(login) && user.password.equals(password)) {
                loggedInUser = user;
                System.out.println("Zalogowano jako ");
                System.out.println(loggedInUser);
                return user;
            }
        }
        System.out.println("Niepoprawne dane logowania.");
        return null;
    }


    public static String wiek(String x){
        LocalDate data = LocalDate.now();
        LocalDate poprzednia = LocalDate.parse(x);
        Period roznica = Period.between(poprzednia, data);
        return roznica.getYears() + " lat i " + roznica.getMonths() + " miesięcy";
    }

    static {
        new User("admin", "admin", 1, "2025-01-01", "admin", "123");
        new User("Anna", "Nowak", 12345, "2000-05-15", "anna123", "pass123");
        new User("Piotr", "Wiśniewski", 23456, "1999-08-25", "piotr99", "secure99");
        new User("Kasia", "Zielińska", 34567, "2001-12-10", "kasia01", "mypassword");
        new User("Tomasz", "Lewandowski", 45678, "1997-03-20", "tomlewan", "1234");
        new User("Maria", "Kowal", 56789, "1995-11-30", "maria95", "qwerty");
        new User("Janusz", "Wójcik", 67890, "1990-06-10", "janwoj", "password123");
    }

    public void wypozyczKsiazke(Book book) {
        if (!wypozyczoneKsiazki.contains(book)) {
            wypozyczoneKsiazki.add(book);
            System.out.println("Książka " + book.tytul + " została wypożyczona.");
            LibraryUklad.tekst.setText("Książka " + book.tytul + " została wypożyczona.");

        } else {
            System.out.println("Książka " + book.tytul + " jest już wypożyczona.");
            LibraryUklad.tekst.setText("Książka " + book.tytul + " jest już wypożyczona.");
        }
    }

    public void wypozyczGazete(Magazine magazine) {
        if (!wypozyczoneGazety.contains(magazine)) {
            wypozyczoneGazety.add(magazine);
            System.out.println("Gazeta " + magazine.tytul + " została wypożyczona.");
            LibraryUklad.tekst.setText("Gazeta " + magazine.tytul + " została wypożyczona.");
        } else {
            System.out.println("Gazeta " + magazine.tytul + " jest już wypożyczona.");
            LibraryUklad.tekst.setText("Gazeta " + magazine.tytul + " jest już wypożyczona.");
        }
    }

    @Override
    public String toString(){
        return "Login: " + this.login + " Imię: " + this.imie + " Nazwisko: " + this.nazwisko + " Wiek: " + this.wiek(dataUrodzenia) +" Indeks: " + this.indeks ;
    }

}