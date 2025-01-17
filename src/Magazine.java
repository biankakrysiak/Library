import java.util.ArrayList;

public class Magazine extends LibraryItem{
    String coIle; // kwartalnik, miesiecznik, tygodnik, dziennik
    public static ArrayList<Magazine> gazety = new ArrayList<>();

    Magazine(String t, String n, String i, String d, String ile, String z){
        super(t, n, i, d, z);
        this.coIle = ile;
        gazety.add(this);
    }


    @Override
    public String toString() {
        return super.toString() + ", Częstotliwość wydawania: " + coIle;
    }

}
