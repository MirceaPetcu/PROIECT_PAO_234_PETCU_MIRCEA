package servicii;

import model.Carte;
import model.Imprumut;
import model.Rezervare;
import model.Utilizator;

import java.util.List;
import java.util.UUID;

public interface UtilizatorService {
    void addUtilizator(Utilizator utilizator);
    List<Utilizator> getUtilizator();
    Utilizator getUtilizator(String telefon);
    void imprumutaCarti(List<Carte> carti, String telefon);
    void rezervaCarti(List<Carte> carti, String telefon);
    List<Rezervare> getListaRezervari(String telefon);
    List<Imprumut> getListaImprumuturi(String telefon);
    void crearePermisDeBiblioteca(String telefon);

}
