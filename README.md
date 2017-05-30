# Finance-Manager

Aplikacja została stworzona w celu pomocy kontrolowania finasów małych firm.
Finance Manager nadaje sie idealnie dla kazdego menadżera firmy czy też osoby zajmującej się finansami w firmie.


Głowna funkcjonalność aplikacji:
- Lista kluczowych klientów firmy
- Wgląd w miesieczny bilans finansowy firmy.
- Tabele przechowywujące wydatki oraz przychody.
    - Możliwość akutalizacji tabel o kolejne pozycje.
- Wszystkie dane zapisywane na serwerze.

## Obsługiwanie gotowej Aplikacji

Podczas uruchomienia aplikacji, ukazuję nam się okno do logowania z opcja stworzenia konto gdy jeszcze nie mamy.
    -Dodatkową opcją jest możliwość zapamietania hasła.
    
![Okno logowania](https://raw.githubusercontent.com/margwokrdzis/Finance-Manager/master/images/1.png)

Po wybraniu opcji Stworz Konto zostaniemy przeniesieni do kolejnej aktywności
gdzie bedziemy mogli stworzyć nowe konto z wybranym loginem oraz hasłem.

![Tworzenie Konta](https://raw.githubusercontent.com/margwokrdzis/Finance-Manager/master/images/2.png)


Po pierwszym logowaniu, użytkownik zostanie poproszony o podanie danych swojej firmy.

![Dane firmy](https://raw.githubusercontent.com/margwokrdzis/Finance-Manager/master/images/3.png)
![Dane firmy](https://raw.githubusercontent.com/margwokrdzis/Finance-Manager/master/images/3.1.png)

Gdy zatwierdzimy wprowadzone dane naszej firmy, zostaniemy przeniesieni do głownego menu naszej aplikacji.
Menu składa się z 7 przycisków

![Menu aplikacji](https://raw.githubusercontent.com/margwokrdzis/Finance-Manager/master/images/4.png)

Przycisk 'Moje Dane' - przechowuje dane firmy użytkownika oraz pozwala na ich edytcje.

![Dane firmy edytowanie](https://raw.githubusercontent.com/margwokrdzis/Finance-Manager/master/images/5.png)

Przycisk 'Klienci' - przechowuje liste kluczowych klientów firmy.
    -Opcja dodania nowego klienta
    -Użytkownik po nacisnieći na liscie wybranego klienta, zobaczy jego pełne dane.
    -Opcja usunięcia wybranego klienta

![Lista Klientów](https://raw.githubusercontent.com/margwokrdzis/Finance-Manager/master/images/6.png)
![Dodawanie klienta](https://raw.githubusercontent.com/margwokrdzis/Finance-Manager/master/images/7.1.png)
![Lista klientów](https://raw.githubusercontent.com/margwokrdzis/Finance-Manager/master/images/7.2.png)
![Pełny podgląd klienta](https://raw.githubusercontent.com/margwokrdzis/Finance-Manager/master/images/7.3.png)
    
Przycisk 'wypłaty' - przechowuje liste wydatków(Kosztów) firmy. 
    -Opcja dodania kolejnego wpisu [Za co/Kwota/Data]
    
![Lista wypłaty](https://raw.githubusercontent.com/margwokrdzis/Finance-Manager/master/images/8.png)
![Dodawanie wydatku](https://raw.githubusercontent.com/margwokrdzis/Finance-Manager/master/images/8.1.png)
![Lista wypłat z wpisami](https://raw.githubusercontent.com/margwokrdzis/Finance-Manager/master/images/8.3.png)

-Przycisk 'Wpłaty' - przechowuje liste wpłat(przychodów) firmy.
    -Dziala analogicznie jak 'wypłaty'
    
  
![Lista wyłat z wpisami](https://raw.githubusercontent.com/margwokrdzis/Finance-Manager/master/images/9.png)
-Przycisk 'Bilans' - przechowuje bilans finansowy dla danego miesiąca.

![Bilans](https://raw.githubusercontent.com/margwokrdzis/Finance-Manager/master/images/10.png)

-Przycisk 'Kalkulator' - uruchamia systemowy kalkulator
-Przycisk 'Wyloguj'- wylogowywuje użytkownika i przełącza aplikacje do aktywności logowania.


## Opis Aktywności oraz Klas

AddIncomeActivity
    Aktywność odpowiadająca za dodawanie kolejnych wpisów 'wpłat' firmy użytkownika.
    
AddNewClientActivity
    Aktywność odpowiadająca za dodawanie nowych klientów firmy użytkownika.
    
AddOutgoingsActivity
    Aktywność odpowiadająca za doawnie kolejnych wpisów "wypłat" firmy uzytkownika.

BackgroundWorker
    Klasa odpowiadająca za komunikacje z baza danych.

CalculatorActivity
    Aktywność odpowiadająca za uruchamianie kalkulatora systemowego.
    
CheckConnection
    Klasa odpowiadająca za sprawdzenie połączenia internetowego.

CheckData
    Klasa odpowiadająca za sprawdzanie poprawności danych wprowadzanych przez użytkownika np: (mail.nip,telefon).
    
ClientInfoActivity
    Aktywność odpowiadająca za wczytanie i ukazanie pełnych danych klienta z listy klientów użytkownika.
    
ClientListActivity
    Aktywność odpowiadająca za przechowywanie listy klientów użytkownika.
    
CreateAccountActivity
    Aktywność odpowiadająca za tworzenie nowego użytkownika aplikacji.
    
DataBaseOperations
    Interfejs z tagami do komunikacji z baza i adresy do "serwisow".
    
IncomeActivity
    Aktywność przechowująca liste wpłat (przychodów) firmy użytkownika.

JSONParser
    Parser do parsowania danych przesylanych przez serwisy.
    
LoginActivity
    Aktywność odpowiadająca za logowanie do aplikacji oraz tworzenia nowych kont uzytkowników.
    
MainWindowActivity
    Aktywność stanowiąca głowne menu aplikacji.
    
MonthlyStatisticsActivity
    Aktywność przechowująca miesieczny bilans finansowy użytkownika.

MyProfileActivity
    Aktywność przechowująca dane firmy użytkownika(Dane można edytować).

OutgoingsActivity
    Aktywność przechowująca liste wypłat(kosztów) firmy użytkownika.

Toast
    Klasa stworzon do tworzenia dymków.
    
UpdateCompanyActivity
    Aktywność zajmująca się akutualizowaniem danych firmy użtkownika.
  
  
  
## Struktura Bazy Danych
Baza danych znajduje się na serwerze v-ie.uek.krakow.pl

![Baza Danych](https://raw.githubusercontent.com/margwokrdzis/Finance-Manager/master/images/struct.png)

## Autorzy

### Krzysztof Pytel
### Marcin Gwóźdź
