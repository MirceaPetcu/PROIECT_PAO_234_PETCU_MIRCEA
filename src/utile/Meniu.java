package utile;

import model.Carte;
import model.Utilizator;
import servicii.BibliotecaServiceImpl;
import servicii.UtilizatorServiceImpl;

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
                    if (biblioteca.getCarti() != null)
                        System.out.println(biblioteca.getCarti());
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
                    Scanner scanner3 = new Scanner(System.in);
                    String nume = scanner3.next();
                    String adresa = scanner3.next();
                    String nrTelefon = scanner3.next();
                    utilizatorService.addUtilizator(new Utilizator(nume, adresa, nrTelefon, null, null, null));
                    break;
                case "e":
                    break;
                case "f":
                    break;
                case "g":
                    break;
                case "h":
                    break;
                case "i":
                    break;
                case "k":
                    break;
                case "l":
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
