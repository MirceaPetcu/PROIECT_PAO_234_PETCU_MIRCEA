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

    @Override
    public void addCarte(int tip) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        if (cartiDinBiblioteca == null)
            cartiDinBiblioteca = new ArrayList<Carte>();
        switch (tip) {
            case 1:
                System.out.println("Introduceti datele cartii");
                System.out.println("Titlu, an,nume autor, nume editura");
                String titlu = scanner.next();
                int an = scanner.nextInt();
                GenCarte genCarte = GenCarte.RAZBOI;
                TipCarte tipCarte = TipCarte.ROMAN;
                String numeAutor = scanner.next();
                String numeEditura = scanner.next();
                Autor a = gasesteAutorDupaNume(numeAutor);
                Editura e = gasesteEdituraDupaNume(numeEditura);
                if (a == null) {
                    System.out.println("Introduceti datele autorului");
                    int an2 = rand.nextInt(201) + 1800;
                    int luna = rand.nextInt(12) + 1;
                    int zi = rand.nextInt(30) + 1;
                    LocalDate data = LocalDate.of(an2, luna, zi);
                    System.out.println("Introduceti nationalitatea");
                    String nat = scanner.next();
                    a = new Autor(numeAutor, data, nat);
                    addAutor(numeAutor, data, nat);
                }

                if (e == null) {
                    System.out.println("Introduceti datele editurii");
                    System.out.println("Adresa, nr tel");
                    String adresa = scanner.next();
                    String nrTel = scanner.next();
                    e = new Editura(numeEditura, adresa, nrTel);
                    addEditura(numeEditura, adresa, nrTel);
                }
                Carte carte = new Carte(titlu, a, e, genCarte, an, tipCarte, false, null, false);
                cartiDinBiblioteca.add(carte);
                break;
            case 2:
                GenRevista genRevista = GenRevista.MASINI;
                System.out.println("Introduceti datele cartii");
                System.out.println("nr luni valabilitate,Titlu, an,nume autor, nume editura");
                int nr = scanner.nextInt();
                String titlu1 = scanner.next();
                int an1 = scanner.nextInt();
                String numeAutor1 = scanner.next();
                String numeEditura1 = scanner.next();
                Autor a1 = gasesteAutorDupaNume(numeAutor1);
                Editura e1 = gasesteEdituraDupaNume(numeEditura1);
                if (a1 == null) {
                    int an2 = rand.nextInt(201) + 1800;
                    int luna = rand.nextInt(12) + 1;
                    int zi = rand.nextInt(30) + 1;
                    LocalDate data = LocalDate.of(an2, luna, zi);
                    System.out.println("Introduceti nationalitatea");
                    String nat = scanner.next();
                    a1 = new Autor(numeAutor1, data, nat);
                    addAutor(numeAutor1, data, nat);
                }

                if (e1 == null) {
                    System.out.println("Introduceti datele editurii");
                    System.out.println("Adresa, nr tel");
                    String adresa = scanner.next();
                    String nrTel = scanner.next();
                    e1 = new Editura(numeEditura1, adresa, nrTel);
                    addEditura(numeEditura1, adresa, nrTel);
                }
                Revista revista = new Revista(titlu1,a1,e1,GenCarte.DRAMA,an1,TipCarte.ROMAN,false,null,false,genRevista,nr);
                cartiDinBiblioteca.add(revista);
                break;
            case 3:
                Dificultate dificultate = Dificultate.MEDIE;
                System.out.println("Introduceti datele cartii");
                System.out.println("materie,domeniu,Titlu, an,nume autor, nume editura");
                String materie = scanner.next();
                String domeniu = scanner.next();
                String titlu2 = scanner.next();
                int an2 = scanner.nextInt();
                String numeAutor2 = scanner.next();
                String numeEditura2 = scanner.next();
                Autor a2 = gasesteAutorDupaNume(numeAutor2);
                Editura e2 = gasesteEdituraDupaNume(numeEditura2);
                if (a2 == null) {
                    int an3 = rand.nextInt(201) + 1800;
                    int luna = rand.nextInt(12) + 1;
                    int zi = rand.nextInt(30) + 1;
                    LocalDate data = LocalDate.of(an3, luna, zi);
                    System.out.println("Introduceti nationalitatea");
                    String nat = scanner.next();
                    a2 = new Autor(numeAutor2, data, nat);
                    addAutor(numeAutor2, data, nat);
                }

                if (e2 == null) {
                    System.out.println("Introduceti datele editurii");
                    System.out.println("Adresa, nr tel");
                    String adresa = scanner.next();
                    String nrTel = scanner.next();
                    e2 = new Editura(numeEditura2, adresa, nrTel);
                    addEditura(numeEditura2, adresa, nrTel);
                }
                Manual manual = new Manual(titlu2,a2,e2,GenCarte.DRAMA,an2,TipCarte.ROMAN,false,null,false,materie,domeniu,dificultate);
                cartiDinBiblioteca.add(manual);
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
        Autor autor = new Autor(nume, dateNasteri, nationalitate);
        if (autori == null)
            autori = new HashSet<Autor>();
        if (gasesteAutorDupaNume(nume) == null)
            autori.add(autor);
    }

    @Override
    public void addEditura(String nume, String adresa, String nrTelefon) {
        Editura editura = new Editura(nume, adresa, nrTelefon);
        if (edituri == null)
            edituri = new HashSet<Editura>();
        if (gasesteEdituraDupaNume(nume) == null)
            edituri.add(editura);
    }

    @Override
    public Autor gasesteAutorDupaNume(String nume) {
        Autor a = null;
        if (autori == null)
            return null;
        for (var autor : autori) {
            if (autor.getNume().equals(nume))
                a = autor;
        }
        return a;
    }

    @Override
    public Editura gasesteEdituraDupaNume(String nume) {
        Editura e = null;
        if ( edituri == null)
            return e;
        for (var editura : edituri)
            if (editura.getNume().equals(nume))
                e = editura;
        return e;
    }
}
