package servicii;

import model.Autor;
import model.Carte;
import model.Editura;
import model.PermisDeBiblioteca;
import utile.GenCarte;

import java.time.LocalDate;
import java.util.List;

public interface BibliotecaService {
    Carte addCarte(int tip);

    List<Carte> getCarti();

    boolean esteInBiblioteca(String numeCarte);

    boolean removeCarte(String numeCarte);

    Carte gasesteCarteDupaNume(String numeCarte);

    void addAutor(String nume, LocalDate dateNasteri, String nationalitate);

    void addEditura(String nume, String adresa, String nrTelefon);

    Autor gasesteAutorDupaNume(String nume);

    Editura gasesteEdituraDupaNume(String nume);
    List<PermisDeBiblioteca> getPermise();
    void setPermise(List<PermisDeBiblioteca> permise);
    void insertEditura(String nume, String adresa, String nrTelefon);
    void insertAutor(String nume, LocalDate dataNasterii, String nationalitate);
    void insertCarte(Carte carte);
    void updateAutor(String nume, LocalDate dataNasterii, String nationalitate);
    void updateEditura(String nume, String adresa, String nrTelefon);
    void updateCarte(String titlu, GenCarte genCarte, int anPublicatie, boolean rezervata, String telefon, boolean imprumutata);
    void deleteAutor(String nume);
    void deleteEditura(String nume);
    void deleteCarte(String nume);
    void deletePermis(String nume);
    Editura getEdituraByNume(String nume);
    Autor getAutorByNume(String nume);
    Carte getCarteByTitlu(String titlu);
    void insertPermis(PermisDeBiblioteca permis);
    PermisDeBiblioteca getPermisByTelefon(String telefon);
    void anuleaza_activeaza_permis(String telefon, boolean valid);


}
