package utile;

import model.Carte;
import model.Utilizator;
import servicii.BibliotecaServiceImpl;
import servicii.UtilizatorServiceImpl;

import java.util.Scanner;

public final class Meniu {
    private static Meniu instance = null;
    private static BibliotecaServiceImpl biblioteca = new BibliotecaServiceImpl();
    private UtilizatorServiceImpl utilizatorService = new UtilizatorServiceImpl(null, biblioteca);

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
        int choice = 0;
        do {
            System.out.println("1. Adauga carte in biblioteca");
            System.out.println("2. Afiseaza cartile din biblioteca");
            System.out.println("3. Verifica dacă o carte este în bibliotecă");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    int tip = scanner.nextInt();
                    biblioteca.addCarte(tip);
                    break;
                case 2:
                    if (biblioteca.getCarti() != null)
                        System.out.println(biblioteca.getCarti());
                    else {
                        System.out.println("Nu exista carti in biblioteca");
                    }
                    break;
                case 3:
                    //de corectat
                    System.out.println("Introduceti numele cartii: ");
                    Scanner scanner2 = new Scanner(System.in);  // create a scanner object
                    String numeCarte = scanner2.nextLine().toString();
                    System.out.println(biblioteca.esteInBiblioteca(numeCarte));
                    scanner2.close();
                    break;
                case 4:
                    Scanner scanner3 = new Scanner(System.in);  // create a scanner object
                    String nume = scanner3.nextLine().toString();
                    String adresa = scanner3.nextLine().toString();
                    String nrTelefon = scanner3.nextLine().toString();
                    utilizatorService.addUtilizator(new Utilizator(nume, adresa, nrTelefon, null, null, null));
                    break;
                default:
                    System.out.println("Optiune invalida!");
                    break;
            }
        } while (choice < 10);
    }


}
