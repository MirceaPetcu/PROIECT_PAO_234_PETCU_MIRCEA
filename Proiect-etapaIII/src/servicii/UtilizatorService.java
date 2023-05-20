package servicii;

import model.*;

import java.util.List;
import java.util.UUID;

public interface UtilizatorService {
    void addUtilizator(Utilizator utilizator);
    List<Utilizator> getUtilizatori();
    Utilizator getUtilizator(String telefon);
    void imprumutaCarti(List<Carte> carti, String telefon);
    void rezervaCarti(List<Carte> carti, String telefon);
    List<Rezervare> getListaRezervari(String telefon);
    List<Imprumut> getListaImprumuturi(String telefon);
    PermisDeBiblioteca crearePermisDeBiblioteca(String telefon);

}
