package servicii;

import exceptii.NuExista;
import model.*;
import repository.AutorRepository;
import repository.CarteRepository;
import repository.EdituraRepository;
import repository.PermisDeBibliotecaRepository;
import utile.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class BibliotecaServiceImpl implements BibliotecaService {
    List<Carte> cartiDinBiblioteca;
    Set<Autor> autori;
    Set<Editura> edituri;
    List<PermisDeBiblioteca> permise;
    EdituraRepository edituraRepository = new EdituraRepository();
    AutorRepository autorRepository = new AutorRepository();
    CarteRepository carteRepository = new CarteRepository();
    PermisDeBibliotecaRepository permisDeBibliotecaRepository = new PermisDeBibliotecaRepository();
    @Override
    public Carte addCarte(int tip) {
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
                    autori.add(a);
//                    addAutor(numeAutor, data, nat);
                }

                if (e == null) {
                    System.out.println("Introduceti datele editurii");
                    System.out.println("Adresa, nr tel");
                    String adresa = scanner.next();
                    String nrTel = scanner.next();
                    e = new Editura(numeEditura, adresa, nrTel);
                    edituri.add(e);
//                    addEditura(numeEditura, adresa, nrTel);
                }
                Carte carte = new Carte(titlu, a, e, genCarte, an, tipCarte, false, null, false);
                cartiDinBiblioteca.add(carte);
                return carte;
//                break;
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
                return revista;
//                break;
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
                return manual;
//                break;
            default:
                break;
        }
        return null;
    }

    @Override
    public List<Carte> getCarti() {

        return cartiDinBiblioteca;
    }

    @Override
    public List<PermisDeBiblioteca> getPermise() {
        return permise;
    }

    @Override
    public void setPermise(List<PermisDeBiblioteca> permise) {
        this.permise = permise;
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

    @Override
    public String toString() {
        return cartiDinBiblioteca.stream()
                .map(carte -> carte.toString())
                .collect(Collectors.toList()).toString();
    }

    public List<Carte> getCartiDinBiblioteca() {
        return cartiDinBiblioteca;
    }

    public void setCartiDinBiblioteca(List<Carte> cartiDinBiblioteca) {
        this.cartiDinBiblioteca = cartiDinBiblioteca;
    }
    public Set<Autor> getAutori() {
        return autori;
    }

    public void setAutori(Set<Autor> autori) {
        this.autori = autori;
    }

    public Set<Editura> getEdituri() {
        return edituri;
    }

    public void setEdituri(Set<Editura> edituri) {
        this.edituri = edituri;
    }

    Comparator<Carte> comparatorTitlu = new Comparator<Carte>() {
        public int compare(Carte carte1, Carte carte2) {
            return carte1.getTitlu().compareTo(carte2.getTitlu());
        }
    };

    public void sortCarti(){
        Collections.sort(this.cartiDinBiblioteca, comparatorTitlu);
    }

    @Override
    public void insertEditura(String nume, String adresa, String nrTelefon){
        var editura  = edituraRepository.getEdituraByNume(nume);
        if (editura != null)
        {
            System.out.println("Editura exista deja");
            return;
        }
        editura = new Editura(nume, adresa, nrTelefon);
        edituraRepository.insertEditura(editura);
    }

    @Override
    public Editura getEdituraByNume(String nume){
        return edituraRepository.getEdituraByNume(nume);
    }

    @Override
    public void updateEditura(String nume, String adresa, String nrTelefon){
        Editura editura = edituraRepository.getEdituraByNume(nume);
        if (editura == null)
        {
            System.out.println("Editura nu exista");
            return;
        }
        edituraRepository.updateEditura(editura.getNume(), adresa, nrTelefon);
        System.out.println("Editura a fost modificata");
    }

    @Override
    public void deleteEditura(String nume)
    {
        var editura = edituraRepository.getEdituraByNume(nume);
        if (editura == null)
        {
            System.out.println("Editura nu exista");
            return;
        }
        edituraRepository.deleteEditura(nume);
        System.out.println("Editura a fost stearsa");
    }

    @Override
    public void insertAutor(String nume, LocalDate dataNasterii, String nationalitate){
        var autor = autorRepository.getAutorByNume(nume);
        if (autor != null)
        {
            System.out.println("Autorul exista deja");
            return;
        }
        autor = new Autor(nume, dataNasterii, nationalitate);
        autorRepository.insertAutor(autor);
    }

    @Override
    public Autor getAutorByNume(String nume){
        return autorRepository.getAutorByNume(nume);
    }

    @Override
    public void updateAutor(String nume, LocalDate dataNasterii, String nationalitate){
        Autor autor = autorRepository.getAutorByNume(nume);
        if (autor == null)
        {
            System.out.println("Autorul nu exista");
            return;
        }
        autorRepository.updateAutor(autor.getNume(), dataNasterii, nationalitate);
        System.out.println("Datele autorului a fost modificat");
    }

    @Override
    public void deleteAutor(String nume){
        var autor = autorRepository.getAutorByNume(nume);
        if (autor == null)
        {
            System.out.println("Autorul nu exista");
            return;}

        autorRepository.deleteAutor(nume);
        System.out.println("Autorul a fost sters");
    }

    @Override
    public void insertCarte(Carte carte)
    {
        var carte1 = carteRepository.getCarteByTitlu(carte.getTitlu());
        if (carte1 != null)
        {
            System.out.println("Cartea exista deja");
            return;
        }
        carteRepository.insertCarte(carte);
    }

    @Override
    public Carte getCarteByTitlu(String titlu){
        return carteRepository.getCarteByTitlu(titlu);
    }

    @Override
    public void updateCarte(String titlu,GenCarte genCarte,int anPublicatie,boolean rezervata,String telefon,boolean imprumutata)
    {
        var carte = carteRepository.getCarteByTitlu(titlu);
        if (carte == null)
        {
            System.out.println("Cartea nu exista");
            return;
        }
        carteRepository.updateCarte(carte.getTitlu(), genCarte, anPublicatie, rezervata, telefon, imprumutata);
        System.out.println("Cartea a fost modificata");
    }

    @Override
    public void deleteCarte(String titlu)
    {
        var carte = carteRepository.getCarteByTitlu(titlu);
        if (carte == null)
        {
            System.out.println("Cartea nu exista");
            return;
        }
        carteRepository.deleteCarte(titlu);
        System.out.println("Cartea a fost stearsa");
    }

    @Override
    public void insertPermis(PermisDeBiblioteca permis)
    {
        var permis1 = permisDeBibliotecaRepository.getPermisByTelefon(permis.getTelefon());
        if (permis1 != null)
        {
            System.out.println("Permisul exista deja");
            return;
        }
        permisDeBibliotecaRepository.insertPermis(permis);
    }

    @Override
    public PermisDeBiblioteca getPermisByTelefon(String telefon){
        return permisDeBibliotecaRepository.getPermisByTelefon(telefon);
    }

    @Override
    public  void anuleaza_activeaza_permis(String telefon, boolean valid)
    {
        var permis = permisDeBibliotecaRepository.getPermisByTelefon(telefon);
        if (permis == null)
        {
            System.out.println("Permisul nu exista");
            return;
        }
        permisDeBibliotecaRepository.anuleaza_activeaza_permis(permis.getTelefon(), valid);
        System.out.println("Permisul a fost modificat");
    }

    @Override
    public void deletePermis(String telefon)
    {
        var permis = permisDeBibliotecaRepository.getPermisByTelefon(telefon);
        if (permis == null)
        {
            System.out.println("Permisul nu exista");
            return;
        }
        permisDeBibliotecaRepository.deletePermis(telefon);
        System.out.println("Permisul a fost sters");
    }

}
