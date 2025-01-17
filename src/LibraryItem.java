public class LibraryItem {
    String tytul = "";
    String nazwiskoAutor = "";
    String imieAutor = "";
    String dataWydania = "";
    String zdjecie;

    public LibraryItem(String t, String n, String i, String d, String z){
        this.tytul = t;
        this.nazwiskoAutor = n;
        this.imieAutor = i;
        this.dataWydania = d;
        this.zdjecie = z;
    }

    public LibraryItem(){}

    @Override
    public String toString() {
        return "Tytul: " + tytul + ", Autor: " + imieAutor + " " + nazwiskoAutor + ", Data wydania: " + dataWydania;
    }

}
