# System Biblioteczny

Desktopowa aplikacja biblioteczna napisana w Javie z graficznym interfejsem użytkownika (Swing). Umożliwia zarządzanie książkami i magazynami oraz obsługę użytkowników z podziałem na role.

---

## Struktura projektu

```
├── Login.java          # Ekran logowania i punkt startowy aplikacji
├── Register.java       # Rejestracja nowych użytkowników
├── LibraryUklad.java   # Panel użytkownika - przeglądanie i wypożyczanie
├── AdminPanel.java     # Panel administratora - zarządzanie zasobami
├── User.java           # Model użytkownika + logika autoryzacji
├── LibraryItem.java    # Klasa bazowa dla pozycji bibliotecznych
├── Book.java           # Model książki (dziedziczy po LibraryItem)
├── Magazine.java       # Model magazynu/gazety (dziedziczy po LibraryItem)
├── inne/               # Zasoby graficzne (tła okien)
│   ├── loginTlo.jpg
│   ├── registerTlo2.jpg
│   ├── adminTlo.jpg
│   └── ukladTlo.jpg
└── okladki/            # Okładki książek i magazynów
    ├── default.jpg
    ├── wiedzmin.jpg
    └── ...
```

---

## Wymagania

- **Java** w wersji 8 lub nowszej (JDK)
- Brak zewnętrznych zależności - projekt korzysta wyłącznie ze standardowej biblioteki Java (Swing, AWT)

---

## Uruchomienie

1. Skompiluj wszystkie pliki `.java`:
   ```bash
   javac *.java
   ```

2. Uruchom aplikację:
   ```bash
   java Login
   ```

> Upewnij się, że katalogi `inne/` i `okladki/` znajdują się w tym samym folderze co skompilowane klasy.

---

## Domyślne konta

| Rola          | Login     | Hasło  | Indeks |
|---------------|-----------|--------|--------|
| Administrator | `admin`   | `123`  | 1      |
| Użytkownik    | `anna123` | `pass123` | 12345 |
| Użytkownik    | `piotr99` | `secure99` | 23456 |

---

## Funkcjonalności

### Ekran logowania (`Login`)
- Logowanie istniejącego użytkownika
- Przejście do rejestracji nowego konta

### Rejestracja (`Register`)
- Tworzenie konta z danymi: imię, nazwisko, login, hasło, indeks, data urodzenia
- Walidacja unikalności numeru indeksu

### Panel użytkownika (`LibraryUklad`)
- Przeglądanie dostępnych książek i magazynów z listy rozwijanej
- Podgląd szczegółów wybranej pozycji (autor, data wydania, okładka)
- Wypożyczanie i zwracanie książek/magazynów
- Wyświetlanie listy własnych wypożyczeń w konsoli

### Panel administratora (`AdminPanel`)
- **Użytkownicy:** wyświetlanie listy, edycja danych, usuwanie kont
- **Księgozbiór:** wyświetlanie listy, dodawanie i usuwanie książek oraz magazynów
- Ochrona konta administratora przed modyfikacją lub usunięciem

---

## Model danych

```
LibraryItem
├── Book        (tytul, autor, dataWydania, numerWydania, zdjecie)
└── Magazine    (tytul, autor, dataWydania, coIle, zdjecie)

User            (imie, nazwisko, indeks, login, password, dataUrodzenia)
                └── wypozyczoneKsiazki: List<Book>
                └── wypozyczoneGazety:  List<Magazine>
```

Dane przechowywane są w pamięci operacyjnej (`static ArrayList`) - po zamknięciu aplikacji nie są zapisywane.

---

## 📄 Licencja

Projekt edukacyjny. Brak licencji komercyjnej.
