package model;
import java.util.*;
public class Rezervare {
    private Utilizator utilizator;
    private List<Carte> carti;


    public Rezervare(Utilizator utilizator, List<Carte> carti) {
        for (int i = 0;i<carti.size();i++) {
            carti.get(i).setRezervata(true);
            carti.get(i).setUtilizator(utilizator);
        }
        this.utilizator = utilizator;
        this.carti = carti;
    }

    public Utilizator getUtilizator() {
        return utilizator;
    }

    public void setUtilizator(Utilizator utilizator) {
        this.utilizator = utilizator;
    }

    public List<Carte> getCarti() {
        return carti;
    }

    public void setCarti(List<Carte> carti) {
        this.carti = carti;
    }

    @Override
    public String toString() {
        return "Rezervare{" +
                "utilizator=" + utilizator +
                ", carti=" + carti +
                '}';
    }
}
