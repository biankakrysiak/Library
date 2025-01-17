import java.util.ArrayList;

public class Book extends LibraryItem{
    int numerWydania = 1;
    public static ArrayList<Book> ksiazki = new ArrayList<>();

    Book(String t, String n, String i, String d, int nr, String z ){
        super(t, n, i, d, z);
        this.numerWydania = nr;
        ksiazki.add(this);
    }

    @Override
    public String toString() {
        return super.toString() + ", Numer wydania: " + numerWydania;
    }

}
