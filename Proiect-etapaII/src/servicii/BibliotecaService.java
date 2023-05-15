package servicii;

import model.Autor;
import model.Carte;
import model.Editura;
import model.PermisDeBiblioteca;

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
}
