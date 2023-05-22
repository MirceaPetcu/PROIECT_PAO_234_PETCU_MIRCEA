package servicii;

import exceptii.NuExista;
import exceptii.NuExistaUtilizatori;
import exceptii.NuSePoateImprumuta;
import exceptii.NuSePoateRezerva;
import model.*;

import java.util.*;

public class UtilizatorServiceImpl implements UtilizatorService {
    List<Utilizator> utilizatori;
    BibliotecaService bibliotecaService;

    public UtilizatorServiceImpl(List<Utilizator> utilizatori, BibliotecaService bibliotecaService) {
        this.utilizatori = utilizatori;
        this.bibliotecaService = bibliotecaService;
    }

    @Override
    public void addUtilizator(Utilizator utilizator) {
        if (utilizatori == null)
            utilizatori = new ArrayList<Utilizator>();
        utilizatori.add(utilizator);
    }

    @Override
    public List<Utilizator> getUtilizatori() {
        try {
            if (utilizatori == null)
                throw new NuExistaUtilizatori("nu exista utilizatori");
        } catch (NuExistaUtilizatori ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return utilizatori;
    }

    @Override
    public Utilizator getUtilizator(String telefon) {
        try {
            if (utilizatori == null)
                throw new NuExistaUtilizatori("nu exista utilizator");
        } catch (NuExistaUtilizatori ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        for (Utilizator utilizator : utilizatori) {
            if (utilizator.getNrTelefon().equals(telefon))
                return utilizator;
        }
        return null;
    }

    @Override
    public void imprumutaCarti(List<Carte> carti, String telefon) {
        Utilizator utilizator = getUtilizator(telefon);
        try {
            if (utilizator == null)
                throw new NuExistaUtilizatori("nu exista utilizator");
        } catch (NuExistaUtilizatori ex) {
            System.out.println(ex.getMessage());
            return;
        }
        List<Carte> newList = new ArrayList<Carte>();
        for (int i = 0; i < carti.size(); i++) {
            if (carti.get(i).isImprumutata() == false && carti.get(i).isRezervata() == false && bibliotecaService.esteInBiblioteca(carti.get(i).getTitlu()))
                newList.add(carti.get(i));
            if (carti.get(i).isRezervata() == true && carti.get(i).getTelefon().equals(telefon))
                newList.add(carti.get(i));
        }
        try {
            if (utilizator.getPermisDeBiblioteca() == null)
                throw new NuSePoateImprumuta("permisul nu exista");
            if (utilizator.getPermisDeBiblioteca().isValid() == false)
                throw new NuSePoateImprumuta("permisul nu este valid");
            if (utilizator.getPermisDeBiblioteca().isDreptImprumut() == false)
                throw new NuSePoateImprumuta("permisul nu are drept de rezervare");
            for (int i = 0; i < carti.size(); i++) {
                if (carti.get(i).isRezervata())
                    if (carti.get(i).getTelefon().equals(telefon) == false)
                        throw new NuSePoateImprumuta("carte rezervata");
                if (carti.get(i).isImprumutata())
                    throw new NuSePoateImprumuta("carte imprumutata");
            }
        } catch (NuSePoateImprumuta ex) {
            System.out.println(ex.getMessage());
        }
        Date actualDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(actualDate);
        calendar.add(Calendar.MONTH, 1);
        Date oneMonthLater = calendar.getTime();
        Imprumut imprumut = new Imprumut(telefon, newList, actualDate, oneMonthLater, null);
        if (utilizator.getImprumuturi() == null)
            utilizator.setImprumuturi(new ArrayList<Imprumut>());
        utilizator.getImprumuturi().add(imprumut);

    }

    @Override
    public void rezervaCarti(List<Carte> carti, String telefon) {
        Utilizator utilizator = getUtilizator(telefon);
        try {
            if (utilizatori == null)
                throw new NuExistaUtilizatori("nu exista utilizator");
        } catch (NuExistaUtilizatori ex) {
            System.out.println(ex.getMessage());
            return;
        }
        try {
            if (getListaImprumuturi(telefon).size() > 2)
                throw new NuSePoateImprumuta("nu se poate imprumuta deoarece aveti deja 3 carti imprumutate");
        } catch (NuSePoateImprumuta ex) {
            System.out.println(ex.getMessage());
            return;
        }
        List<Carte> newList = new ArrayList<Carte>();
        try {
            if (utilizator.getPermisDeBiblioteca() == null)
                throw new NuSePoateImprumuta("permisul nu exista");
            if (utilizator.getPermisDeBiblioteca().isValid() == false)
                throw new NuSePoateRezerva("permisul nu este valid");
            if (utilizator.getPermisDeBiblioteca().isDreptRezervare() == false)
                throw new NuSePoateRezerva("permisul nu are drept de rezervare");
            for (int i = 0; i < carti.size(); i++)
                if (carti.get(i).isRezervata())
                    throw new NuSePoateRezerva("carte rezervata");
        } catch (NuSePoateRezerva ex) {
            System.out.println(ex.getMessage());
        }
        List<Carte> newList1 = new ArrayList<Carte>();
        for (int i = 0; i < carti.size(); i++) {
            if (carti.get(i).isImprumutata() == false && carti.get(i).isRezervata() == false && bibliotecaService.esteInBiblioteca(carti.get(i).getTitlu()))
                newList1.add(carti.get(i));
        }
        Rezervare rezervare = new Rezervare( newList1, telefon);
        if (utilizator.getRezervari() == null)
            utilizator.setRezervari(new ArrayList<Rezervare>());
        utilizator.getRezervari().add(rezervare);
    }

    public List<Rezervare> getListaRezervari(String telefon) {
        Utilizator utilizator = getUtilizator(telefon);
        try {
            if (utilizatori == null)
                throw new NuExistaUtilizatori("nu exista utilizator");
        } catch (NuExistaUtilizatori ex) {
            System.out.println(ex.getMessage());
            return null;
        }

        List<Rezervare> rezervari = utilizator.getRezervari();
        try {


            if (rezervari == null)
                throw new NuExista("nu ati rezervat nicio carte");
        } catch (NuExista ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return rezervari;
    }

    @Override
    public List<Imprumut> getListaImprumuturi(String telefon) {
        Utilizator utilizator = getUtilizator(telefon);
        try {
            if (utilizator == null)
                throw new NuExistaUtilizatori("nu exista utilizator");
        } catch (NuExistaUtilizatori ex) {
            System.out.println(ex.getMessage());
            return null;
        }

        List<Imprumut> imprumuturi = utilizator.getImprumuturi();
        try {
            if (imprumuturi == null)
                throw new NuExista("nu ati imprumutat nicio carte");
        } catch (NuExista ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return imprumuturi;
    }

    @Override
    public PermisDeBiblioteca crearePermisDeBiblioteca(String telefon) {
        Utilizator utilizator = getUtilizator(telefon);
        try {
            if (utilizatori == null)
                throw new NuExistaUtilizatori("nu exista utilizator");
        } catch (NuExistaUtilizatori ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        Date actualDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(actualDate);
        calendar.add(Calendar.YEAR, 1);
        Date oneYearLater = calendar.getTime();
        PermisDeBiblioteca permisDeBiblioteca = new PermisDeBiblioteca(telefon,true,true,actualDate,oneYearLater,true,true);
        utilizator.setPermisDeBiblioteca(permisDeBiblioteca);
        return permisDeBiblioteca;
    }
}
