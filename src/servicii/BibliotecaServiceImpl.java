package servicii;

import exceptii.NuExista;
import model.*;
import utile.Dificultate;
import utile.GenCarte;
import utile.GenRevista;
import utile.TipCarte;

import java.time.LocalDate;
import java.util.*;

public class BibliotecaServiceImpl implements BibliotecaService {
    List<Carte> cartiDinBiblioteca;
    Set<Autor> autori;
    Set<Editura> edituri;

    //schimb sa pun si autor si editura
    @Override
    public void addCarte(int tip) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        if (cartiDinBiblioteca == null)
            cartiDinBiblioteca = new ArrayList<Carte>();
        switch (tip) {
            case 1:
                System.out.println("Introduceti date pentru carte");
                System.out.println("Introduceti datele cartii");
                String titlu = scanner.nextLine();
                int an = scanner.nextInt();
                GenCarte genCarte = GenCarte.RAZBOI;
                TipCarte tipCarte = TipCarte.ROMAN;
                String numeAutor = scanner.nextLine();
                String numeEditura = scanner.nextLine();
                Autor a = gasesteAutorDupaNume(numeAutor);
                Editura e = gasesteEdituraDupaNume(numeEditura);
                int oka = 1;
                int oke = 1;
                if (a == null) {
                    oka = 0;
                    int an2 = rand.nextInt(201) + 1800;
                    int luna = rand.nextInt(12) + 1;
                    int zi = rand.nextInt(30) + 1;
                    LocalDate data = LocalDate.of(an2, luna, zi);
                    String nat = scanner.nextLine();
                    a = new Autor(numeAutor, data, nat, null);
                    addAutor(numeAutor, data, nat);
                }

                if (e == null) {
                    oke = 0;
                    String adresa = scanner.nextLine();
                    String nrTel = scanner.nextLine();
                    e = new Editura(numeEditura, adresa, nrTel, null);
                    addEditura(numeEditura, adresa, nrTel);
                }
                Carte carte = new Carte(titlu, a, e, genCarte, an, tipCarte, false, null, false);
                cartiDinBiblioteca.add(carte);
                a.getCartiPublicate().add(carte);
                e.getCartiTiparite().put(carte, a);
                scanner.close();
                break;
            case 2:
                GenRevista genRevista = GenRevista.MASINI;
                System.out.println("Introduceti date pentru carte");
                System.out.println("Introduceti datele cartii");
                int nr = scanner.nextInt();
                String titlu1 = scanner.nextLine();
                int an1 = scanner.nextInt();
                String numeAutor1 = scanner.nextLine();
                String numeEditura1 = scanner.nextLine();
                Autor a1 = gasesteAutorDupaNume(numeAutor1);
                Editura e1 = gasesteEdituraDupaNume(numeEditura1);
                int oka1 = 1;
                int oke1 = 1;
                if (a1 == null) {
                    oka1 = 0;
                    int an2 = rand.nextInt(201) + 1800;
                    int luna = rand.nextInt(12) + 1;
                    int zi = rand.nextInt(30) + 1;
                    LocalDate data = LocalDate.of(an2, luna, zi);
                    String nat = scanner.nextLine();
                    a1 = new Autor(numeAutor1, data, nat, null);
                    addAutor(numeAutor1, data, nat);
                }

                if (e1 == null) {
                    oke1 = 0;
                    String adresa = scanner.nextLine();
                    String nrTel = scanner.nextLine();
                    e1 = new Editura(numeEditura1, adresa, nrTel, null);
                    addEditura(numeEditura1, adresa, nrTel);
                }
                Revista revista = new Revista(titlu1,a1,e1,GenCarte.DRAMA,an1,TipCarte.ROMAN,false,null,false,genRevista,nr);
                cartiDinBiblioteca.add(revista);
                a1.getCartiPublicate().add(revista);
                e1.getCartiTiparite().put(revista, a1);
                scanner.close();
                break;
            case 3:
                Dificultate dificultate = Dificultate.MEDIE;
                System.out.println("Introduceti date pentru carte");
                System.out.println("Introduceti datele cartii");
                String materie = scanner.nextLine();
                String domeniu = scanner.nextLine();
                String titlu2 = scanner.nextLine();
                int an2 = scanner.nextInt();
                String numeAutor2 = scanner.nextLine();
                String numeEditura2 = scanner.nextLine();
                Autor a2 = gasesteAutorDupaNume(numeAutor2);
                Editura e2 = gasesteEdituraDupaNume(numeEditura2);
                if (a2 == null) {
                    int an3 = rand.nextInt(201) + 1800;
                    int luna = rand.nextInt(12) + 1;
                    int zi = rand.nextInt(30) + 1;
                    LocalDate data = LocalDate.of(an3, luna, zi);
                    String nat = scanner.nextLine();
                    a2 = new Autor(numeAutor2, data, nat, null);
                    addAutor(numeAutor2, data, nat);
                }

                if (e2 == null) {
                    String adresa = scanner.nextLine();
                    String nrTel = scanner.nextLine();
                    e2 = new Editura(numeEditura2, adresa, nrTel, null);
                    addEditura(numeEditura2, adresa, nrTel);
                }
                Manual manual = new Manual(titlu2,a2,e2,GenCarte.DRAMA,an2,TipCarte.ROMAN,false,null,false,materie,domeniu,dificultate);
                cartiDinBiblioteca.add(manual);
                a2.getCartiPublicate().add(manual);
                e2.getCartiTiparite().put(manual,a2);
                scanner.close();
                break;
            default:
                break;
        }
    }

    @Override
    public List<Carte> getCarti() {
        return cartiDinBiblioteca;
    }

    @Override
    public boolean esteInBiblioteca(String numeCarte) {
        try {
            if (cartiDinBiblioteca == null)
                throw new NuExista("nu exista carti in biblioteca");
        } catch (NuExista ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        for (int i = 0; i < cartiDinBiblioteca.size(); i++) {
            if (cartiDinBiblioteca.get(i).getTitlu().equals(numeCarte))
                return true;
        }
        return false;
    }

    @Override
    public boolean removeCarte(String numeCarte) {
        if (esteInBiblioteca(numeCarte) == false)
            return false;
        cartiDinBiblioteca.remove(gasesteCarteDupaNume(numeCarte));
        return true;
    }

    @Override
    public Carte gasesteCarteDupaNume(String numeCarte) {
        for (int i = 0; i < cartiDinBiblioteca.size(); i++) {
            if (cartiDinBiblioteca.get(i).getTitlu().equals(numeCarte))
                return cartiDinBiblioteca.get(i);
        }
        return null;
    }

    @Override
    public void addAutor(String nume, LocalDate dateNasteri, String nationalitate) {
        Autor autor = new Autor(nume, dateNasteri, nationalitate, null);
        if (gasesteAutorDupaNume(nume) == null)
            autori.add(autor);
    }

    @Override
    public void addEditura(String nume, String adresa, String nrTelefon) {
        Editura editura = new Editura(nume, adresa, nrTelefon, null);
        if (gasesteEdituraDupaNume(nume) == null)
            edituri.add(editura);
    }

    @Override
    public Autor gasesteAutorDupaNume(String nume) {
        Autor a = null;
        for (var autor : autori) {
            if (autor.getNume().equals(nume))
                a = autor;
        }
        return a;
    }

    @Override
    public Editura gasesteEdituraDupaNume(String nume) {
        Editura e = null;
        for (var editura : edituri)
            if (editura.getNume().equals(nume))
                e = editura;
        return e;
    }
}
