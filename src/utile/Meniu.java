package utile;

import model.Carte;
import model.Imprumut;
import model.Rezervare;
import model.Utilizator;
import servicii.BibliotecaServiceImpl;
import servicii.UtilizatorServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class Meniu {
    private static Meniu instance = null;
    private static BibliotecaServiceImpl biblioteca = new BibliotecaServiceImpl();
    private static UtilizatorServiceImpl utilizatorService = new UtilizatorServiceImpl(null, biblioteca);

    private Meniu() {
    }

    public static synchronized Meniu getInstance() {
        if (instance == null) {
            instance = new Meniu();
        }
        return instance;
    }

    public void show() {
        Scanner scanner = new Scanner(System.in);
        String  choice = "m";
        while (!choice.equals("x"))
        {
            System.out.println("1. Adauga carte in biblioteca.(Tasta a)");
            System.out.println("2. Afiseaza cartile din biblioteca.(Tasta b)");
            System.out.println("3. Verifica dacă o carte este în bibliotecă.(Tasta c)");
            System.out.println("4. Adauga cont utilizator.(Tasta d)");
            System.out.println("5. Creeaza permis de biblioteca pentru un utilizator.(Tasta e)");
            System.out.println("6. Rezerva o carte.(Tasta f)");
            System.out.println("7. Imprumuta o carte.(Tasta g)");
            System.out.println("8. Afiseaza lista de rezervari a unui utilizator.(Tasta h)");
            System.out.println("9. Afiseaza istoricul imprumuturilor unui utilizator.(Tasta i)");
            System.out.println("10. Afiseaza toti utilizatorii. (Tasta j)");
            System.out.println("11. Gaseste toate cartile de la un autor.(Tasta k)");
            System.out.println("12. Gaseste toate cartile de la o anumita editura.(Tasta l)");
            System.out.println("13. Iesire.(Tasta x)");



            choice = scanner.next();
            switch (choice) {
                case "a":
                    int tip = scanner.nextInt();
                    biblioteca.addCarte(tip);
                    break;
                case "b":
                    if (biblioteca.getCarti() != null) {
                        biblioteca.sortCarti();
                        System.out.println(biblioteca.getCarti());
                    }
                    else {
                        System.out.println("Nu exista carti in biblioteca");
                    }
                    break;
                case "c":
                    System.out.println("Introduceti numele cartii: ");
                    Scanner scanner2 = new Scanner(System.in);
                    String numeCarte = scanner2.next();
                    if(biblioteca.esteInBiblioteca(numeCarte))
                        System.out.println("cartea se afla in biblioteca.");
                    else
                        System.out.println("cartea nu se afla in biblioteca.");
                    break;
                case "d":
                    System.out.println("introduceti numele, adresa si numarul de telefon al utilizatorului.");
                    String nume = scanner.next();
                    String adresa = scanner.next();
                    String nrTelefon = scanner.next();
                    utilizatorService.addUtilizator(new Utilizator(nume, adresa, nrTelefon, null, new ArrayList<>(), new ArrayList<>()));
                    break;
                case "e":
                    System.out.println("introduceti numarul de telefon al utilizatorului pentru care doriti sa creati card.");
                    String telefon = scanner.next();
                    try {

                        utilizatorService.crearePermisDeBiblioteca(telefon);
                    }
                    catch (Exception exception)
                    {
                        System.out.println("nu a reusit creearea permisului de biblioteca.");
                    }
                    break;
                case "g":
                    System.out.println("introduceti numarul de telefon al utilizatorului care doreste sa imprumute carti.");
                    telefon = scanner.next();
                    List<Carte> carti = new ArrayList<Carte>();
                    System.out.println("Cate carti doriti sa imprumutati?");
                    int nrCarti = scanner.nextInt();
                    System.out.println("Introduceti numele cartilor care se vor imprumuta, pe rand.");
                    for(int i = 0;i<nrCarti;i++)
                    {
                        numeCarte = scanner.next();
                        carti.add(biblioteca.gasesteCarteDupaNume(numeCarte));
                    }
                    utilizatorService.imprumutaCarti(carti,telefon);
                    break;
                case "f":
                    System.out.println("introduceti numarul de telefon al utilizatorului care doreste sa rezerve carti.");
                    telefon = scanner.next();
                    carti = new ArrayList<Carte>();
                    System.out.println("Cate carti doriti sa rezervati?");
                    nrCarti = scanner.nextInt();
                    System.out.println("Introduceti numele cartilor care se vor rezerva, pe rand.");
                    for(int i = 0;i<nrCarti;i++)
                    {
                        numeCarte = scanner.next();
                        carti.add(biblioteca.gasesteCarteDupaNume(numeCarte));
                    }
                    utilizatorService.rezervaCarti(carti,telefon);
                    break;
                case "h":
                    System.out.println("introduceti numarul de telefon al utilizatorului care doreste sa vizualizeze cartile rezervate.");
                    telefon = scanner.next();
                    System.out.println("cartile rezervate: ");
                    for(Rezervare rezervare: utilizatorService.getListaRezervari(telefon))
                        for (Carte carte : rezervare.getCarti())
                            System.out.println(carte);
                    break;
                case "i":
                    System.out.println("introduceti numarul de telefon al utilizatorului care doreste sa vizualizeze istoricul cartilor imprumutates.");
                    telefon = scanner.next();
                    System.out.println("cartile rezervate: ");
                    for(Imprumut imprumut: utilizatorService.getListaImprumuturi(telefon))
                        for (Carte carte : imprumut.getCarti())
                            System.out.println(carte);
                    break;
                case "l":
                    System.out.println("introduceti numele editurii pentru care doriti sa gasiti cartile.");
                    String numeEditura = scanner.next();
                    System.out.println("cartile sunt: ");
                    for (Carte carte : biblioteca.getCarti())
                    {
                        if(carte.getEditura().getNume().equals(numeEditura))
                            System.out.println(carte);
                    }
                    break;
                case "j":
                    System.out.println("utilizatorii sunt: ");
                    System.out.println(utilizatorService.getUtilizatori());
                    break;
                case "k":
                    System.out.println("introduceti numele autorului pentru care doriti sa gasiti cartile.");
                    String numeAutor = scanner.next();
                    System.out.println("cartile sunt: ");
                    for (Carte carte : biblioteca.getCarti())
                    {
                        if(carte.getAutor().getNume().equals(numeAutor))
                            System.out.println(carte);
                    }
                    break;
                case "x":
                    break;
                case "m":
                    break;
                default:
                    System.out.println("Optiune invalida!");
                    break;
            }
        }
    }


}
