import config.DataSetup;
import model.*;
import servicii.BibliotecaServiceImpl;
import servicii.UtilizatorServiceImpl;
import utile.Audit;
import utile.GenCarte;
import utile.Meniu;
import utile.TipCarte;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;

public class Main {

    private static DataSetup dataSetup = new DataSetup();
    private static BibliotecaServiceImpl biblioteca = new BibliotecaServiceImpl();
    private static UtilizatorServiceImpl utilizatorService = new UtilizatorServiceImpl(null, biblioteca);
    private static Audit audit = new Audit();
    private static AutorSingleton autorSingleton = AutorSingleton.getInstance();
    private static EdituraSingleton edituraSingleton = EdituraSingleton.getInstance();
    private static CarteSingleton carteSingleton = CarteSingleton.getInstance();
    private static PermisDeBibliotecaSingleton permisDeBibliotecaSingleton = PermisDeBibliotecaSingleton.getInstance();
    private static Map<String, String> comenzi = new HashMap<String, String>() {{
        put("a", "adaugaCarte");
        put("b", "afiseazaCarti");
        put("c", "verificaCarte");
        put("d", "adaugaUtilizator");
        put("e", "creeazaPermis");
        put("f", "rezervaCarte");
        put("g", "imprumutaCarte");
        put("h", "afiseazaRezervari");
        put("i", "afiseazaIstoric");
        put("j", "afiseazaUtilizatori");
        put("k", "cautaCartiAutor");
        put("l", "cautaCartiEditura");
        put("n", "Afiseaza autorii");
        put("o", "Afiseaza editurile");
        put("m", "Afiseaza permisele de biblioteca");
    }};

    public static void main(String[] args) throws ParseException {
        autorSingleton.citesteDinCSV();
        edituraSingleton.citesteDinCSV();
        carteSingleton.citesteDinCSV();
        permisDeBibliotecaSingleton.citesteDinCSV();
        biblioteca.setAutori(autorSingleton.getAutori());
        biblioteca.setEdituri(edituraSingleton.getEdituri());
        biblioteca.setCartiDinBiblioteca(carteSingleton.getCartiDinBiblioteca());
        biblioteca.setPermise(permisDeBibliotecaSingleton.getPermiseDeBiblioteca());
        Scanner scanner = new Scanner(System.in);
        String choice = "m";
        try {
            while (!choice.equals("x")) {
                System.out.println("1. Adauga carte in biblioteca.(Tasta a)");
                System.out.println("2. Afiseaza cartile din biblioteca.(Tasta b)");
                System.out.println("3. Verifica dacă o carte este în bibliotecă.(Tasta c)");
                System.out.println("4. Adauga cont utilizator.(Tasta d)");
                System.out.println("5. Creeaza permis de biblioteca pentru un utilizator.(Tasta e)");
                System.out.println("6. Rezerva o carte.(Tasta f)");
                System.out.println("7. Imprumuta o carte.(Tasta g)");
                System.out.println("8. Afiseaza lista de rezervari.csv a unui utilizator.(Tasta h)");
                System.out.println("9. Afiseaza istoricul imprumuturilor unui utilizator.(Tasta i)");
                System.out.println("10. Afiseaza toti utilizatorii. (Tasta j)");
                System.out.println("11. Gaseste toate cartile de la un autor.(Tasta k)");
                System.out.println("12. Gaseste toate cartile de la o anumita editura.(Tasta l)");
                System.out.println("13. Afiseaza toate permisele de biblioteca.(Tasta m)");
                System.out.println("14. Afiseaza toti autorii.(Tasta n)");
                System.out.println("15. Afiseaza toate editurile.(Tasta o)");
                System.out.println("16. Iesire.(Tasta x)");


                choice = scanner.next();

                switch (choice) {
                    case "a":
                        int tip = scanner.nextInt();
                        Carte newcarte = biblioteca.addCarte(tip);
                        autorSingleton.setAutori(biblioteca.getAutori());
                        edituraSingleton.setEdituri(biblioteca.getEdituri());
                        carteSingleton.setCartiDinBiblioteca(biblioteca.getCartiDinBiblioteca());
                        autorSingleton.scrieInCSV();
                        edituraSingleton.scrieInCSV();
                        carteSingleton.scrieInCSV();
                        break;
                    case "b":
                        if (biblioteca.getCarti() != null) {
                            biblioteca.sortCarti();
                            System.out.println(biblioteca.getCarti());
                        } else {
                            System.out.println("Nu exista carti in biblioteca");
                        }
                        break;
                    case "c":
                        System.out.println("Introduceti numele cartii: ");
                        Scanner scanner2 = new Scanner(System.in);
                        String numeCarte = scanner2.next();
                        if (biblioteca.esteInBiblioteca(numeCarte))
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

                            PermisDeBiblioteca permisDeBiblioteca = utilizatorService.crearePermisDeBiblioteca(telefon);
                            biblioteca.getPermise().add(permisDeBiblioteca);
                            permisDeBibliotecaSingleton.setPermiseDeBiblioteca(biblioteca.getPermise());
                            permisDeBibliotecaSingleton.scrieInCSV();
//                                biblioteca.setPermise(permisDeBibliotecaSingleton.getPermiseDeBiblioteca());
                        } catch (Exception exception) {
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
                        for (int i = 0; i < nrCarti; i++) {
                            numeCarte = scanner.next();
                            carti.add(biblioteca.gasesteCarteDupaNume(numeCarte));
                        }
                        utilizatorService.imprumutaCarti(carti, telefon);
                        break;
                    case "f":
                        System.out.println("introduceti numarul de telefon al utilizatorului care doreste sa rezerve carti.");
                        telefon = scanner.next();
                        carti = new ArrayList<Carte>();
                        System.out.println("Cate carti doriti sa rezervati?");
                        nrCarti = scanner.nextInt();
                        System.out.println("Introduceti numele cartilor care se vor rezerva, pe rand.");
                        for (int i = 0; i < nrCarti; i++) {
                            numeCarte = scanner.next();
                            carti.add(biblioteca.gasesteCarteDupaNume(numeCarte));
                        }
                        utilizatorService.rezervaCarti(carti, telefon);
                        break;
                    case "h":
                        System.out.println("introduceti numarul de telefon al utilizatorului care doreste sa vizualizeze cartile rezervate.");
                        telefon = scanner.next();
                        System.out.println("cartile rezervate: ");
                        for (Rezervare rezervare : utilizatorService.getListaRezervari(telefon))
                            for (Carte carte : rezervare.getCarti())
                                System.out.println(carte);
                        break;
                    case "i":
                        System.out.println("introduceti numarul de telefon al utilizatorului care doreste sa vizualizeze istoricul cartilor imprumutates.");
                        telefon = scanner.next();
                        System.out.println("cartile rezervate: ");
                        for (Imprumut imprumut : utilizatorService.getListaImprumuturi(telefon))
                            for (Carte carte : imprumut.getCarti())
                                System.out.println(carte);
                        break;
                    case "l":
                        System.out.println("introduceti numele editurii pentru care doriti sa gasiti cartile.");
                        String numeEditura = scanner.next();
                        System.out.println("cartile sunt: ");
                        for (Carte carte : biblioteca.getCarti()) {
                            if (carte.getEditura().getNume().equals(numeEditura))
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
                        for (Carte carte : biblioteca.getCarti()) {
                            if (carte.getAutor().getNume().equals(numeAutor))
                                System.out.println(carte);
                        }
                        break;
                    case "x":
                        break;
                    case "m":
                        System.out.println("permisele de biblioteca sunt: ");
                        System.out.println(biblioteca.getPermise());
                        break;
                    case "n":
                        System.out.println("Autorii sunt: ");
                        System.out.println(biblioteca.getAutori());
                        break;
                    case "o":
                        System.out.println("Editurile sunt: ");
                        System.out.println(biblioteca.getEdituri());
                        break;
                    case "p":
                        dataSetup.setUp();
                        dataSetup.addEditura();
                        dataSetup.displayEdituri();
                        break;
                    case "q":
                        biblioteca.insertEditura("CartiPeTava", "Iasi", "0788822299");
                        break;
                    case "r":
                        System.out.println("Introduceti numele editurii pe care doriti sa o gasiti: ");
                        String numeEdituraBD = scanner.next();
                        var editura = biblioteca.getEdituraByNume(numeEdituraBD);
                        if (editura == null)
                            System.out.println("Editura nu exista!");
                        else
                            System.out.println(editura);
                        break;
                    case "s":
                        System.out.println("Introduceti numele editurii pe care doriti sa o modificati: ");
                        String numeEdituraBD1 = scanner.next();
                        System.out.println("Introduceti adresa si numarul de telefon pe care doriti sa le schimbati");
                        String adresaEditura = scanner.next();
                        String telefonEditura = scanner.next();
                        biblioteca.updateEditura(numeEdituraBD1, adresaEditura, telefonEditura);
                        break;
                    case "t":
                        System.out.println("Introdceti numele editurii pe care doriti sa o stergeti: ");
                        String numeEdituraBD2 = scanner.next();
                        biblioteca.deleteEditura(numeEdituraBD2);
                        break;
                    case "u":
                        System.out.println("Introduceti numele autorului pe care doriti sa il adaugati in baza de date: ");
                        String numeAutorBD = scanner.next();
                        System.out.println("Introduceti data nasterii autorului pe care doriti sa il adaugati in baza de date: ");
                        LocalDate dataNasteriiAutor = LocalDate.parse(scanner.next());
                        System.out.println("Introduceti nationalitatea autorului pe care doriti sa il adaugati in baza de date: ");
                        String nationalitateAutor = scanner.next();
                        biblioteca.insertAutor(numeAutorBD, dataNasteriiAutor, nationalitateAutor);
                        break;
                    case "v":
                        System.out.println("Introduceti numele autorului pe care doriti sa il gasiti: ");
                        String numeAutorBD1 = scanner.next();
                        var autor = biblioteca.getAutorByNume(numeAutorBD1);
                        if (autor == null)
                            System.out.println("Autorul nu exista!");
                        else
                            System.out.println(autor);
                        break;
                    case "w":
                        System.out.println("Introduceti numele autorului pe care doriti sa il modificati: ");
                        String numeAutorBD2 = scanner.next();
                        System.out.println("Introduceti data nasterii si nationalitatea pe care doriti sa le schimbati: ");
                        LocalDate dataNasteriiAutor1 = LocalDate.parse(scanner.next());
                        String nationalitateAutor1 = scanner.next();
                        biblioteca.updateAutor(numeAutorBD2, dataNasteriiAutor1, nationalitateAutor1);
                        break;
                    case "y":
                        System.out.println("Introduceti numele autorului pe care doriti sa il stergeti: ");
                        String numeAutorBD3 = scanner.next();
                        biblioteca.deleteAutor(numeAutorBD3);
                        break;
                    case "z":
                        System.out.println("Introduceti titlul,numele autorului, numele editurii, genul carti,anul publicatiei,tipul cartii cartii pe care doriti sa o adaugati in baza de date: ");
                        System.out.println("genul cartii poate fi doar:'POLITIST', 'SCIPY', 'DRAMA', 'RAZBOI' ");
                        System.out.println("tipul cartii poate fi doar: 'ROMAN', 'NUVELA', 'POEZIE', 'BALADA'");
                        String titluCarte = scanner.next();
                        String numeAutorCarte = scanner.next();
                        String numeEdituraCarte = scanner.next();
                        String genCarte = scanner.next();
                        String anPublicatieCarte = scanner.next();
                        String tipCarte = scanner.next();
                        biblioteca.insertCarte(new Carte(titluCarte, biblioteca.getAutorByNume(numeAutorCarte), biblioteca.getEdituraByNume(numeEdituraCarte), GenCarte.valueOf(genCarte), Integer.parseInt(anPublicatieCarte), TipCarte.valueOf(tipCarte), false, null, false));
                        break;
                    case "aa":
                        System.out.println("Introduceti titlul cartii pe care doriti sa o gasiti: ");
                        String titluCarte1 = scanner.next();
                        var carte = biblioteca.getCarteByTitlu(titluCarte1);
                        if (carte == null)
                            System.out.println("Cartea nu exista!");
                        else
                            System.out.println(carte);
                        break;
                    case "bb":
                        System.out.println("Introduceti titlul cartii pe care doriti sa o modificati: ");
                        String titluCarte2 = scanner.next();
                        System.out.println("Introduceti  genul carti,anul publicatiei,daca este rezervata sau nu, telefonul cititorului care a rezervat-o/imprumutat-o si daca este imprumutata sau nu cartii pe care doriti sa o modificati: ");
                        System.out.println("genul cartii poate fi doar:'POLITIST', 'SCIPY', 'DRAMA', 'RAZBOI' ");
                        System.out.println("daca este rezervata sau nu poate fi doar: 'true' sau 'false'");
                        System.out.println("daca este imprumutata sau nu poate fi doar: 'true' sau 'false'");
                        String genCarte1 = scanner.next();
                        String anPublicatieCarte1 = scanner.next();
                        String rezervata = scanner.next();
                        String telefonCititor = scanner.next();
                        String imprumutata = scanner.next();
                        biblioteca.updateCarte(titluCarte2, GenCarte.valueOf(genCarte1), Integer.parseInt(anPublicatieCarte1), Boolean.parseBoolean(rezervata), telefonCititor, Boolean.parseBoolean(imprumutata));
                        break;
                    case "cc":
                        System.out.println("Introduceti titlul cartii pe care doriti sa o stergeti: ");
                        String titluCarte3 = scanner.next();
                        biblioteca.deleteCarte(titluCarte3);
                        break;
                    case "dd":
                        System.out.println("Introduceti telefonul cititorului si daca e sau nu student pentru permisul de biblioteca pe care doriti sa il adaugati in baza de date: ");
                        System.out.println("daca e sau nu student poate fi doar: 'true' sau 'false'");
                        String telefonCititor1 = scanner.next();
//                        var utilizator = utilizatorService.getUtilizator(telefonCititor1);
//                        if (utilizator == null) {
//                            System.out.println("Utilizatorul nu exista!");
//                            break;
//                        } else
//                            System.out.println(utilizator);
                        String student = scanner.next();
                        Calendar calendar = Calendar.getInstance();
                        Date dataCurenta = calendar.getTime();
                        calendar.add(Calendar.MONTH, 1);
                        // Obține data cu o lună înainte
                        Date dataCuOLunaDupa = calendar.getTime();
                        biblioteca.insertPermis(new PermisDeBiblioteca(telefonCititor1, true, true, dataCurenta, dataCuOLunaDupa, true, Boolean.parseBoolean(student)));
                        break;
                    case "ee":
                        System.out.println("Introduceti telefonul cititorului de pe permisul de biblioteca pe care doriti sa il gasiti: ");
                        String telefonCititor2 = scanner.next();
                        var permis = biblioteca.getPermisByTelefon(telefonCititor2);
                        if (permis == null)
                            System.out.println("Permisul nu exista!");
                        else
                            System.out.println(permis);
                        break;
                    case "ff":
                        System.out.println("Introduceti telefonul cititorului de pe permisul de biblioteca pe care doriti sa il modificati: ");
                        System.out.println("Introduceti daca vreti sa ii activati sau anulati permisul de biblioteca: ");
                        System.out.println("pentru anulare: false");
                        System.out.println("pentru activare: true");
                        String telefonCititor3 = scanner.next();
                        String activare = scanner.next();
                        biblioteca.anuleaza_activeaza_permis(telefonCititor3, Boolean.parseBoolean(activare));
                        break;
                    case "gg":
                        System.out.println("Introduceti telefonul cititorului de pe permisul de biblioteca pe care doriti sa il stergeti: ");
                        String telefonCititor4 = scanner.next();
                        biblioteca.deletePermis(telefonCititor4);
                        break;

                    default:
                        System.out.println("Optiune invalida!");
                        break;
                }
                try {
                    audit.logAction(comenzi.get(choice));
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }
            }

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }


    }
}