package model;
import java.util.*;
public class Rezervare {
    private List<Carte> carti;
    private String telefon;


    public Rezervare(List<Carte> carti, String telefon) {
        for (int i = 0;i<carti.size();i++) {
            carti.get(i).setRezervata(true);
            carti.get(i).setTelefon(telefon);
        }
        this.carti = carti;
        this.telefon = telefon;
    }


    public List<Carte> getCarti() {
        return carti;
    }

    public void setCarti(List<Carte> carti) {
        this.carti = carti;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @Override
    public String toString() {
        return "Rezervare{" +
                "carti=" + carti +
                ", telefon='" + telefon + '\'' +
                '}';
    }
}
