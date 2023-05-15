package model;
import java.time.LocalDate;
import java.util.*;
public class Imprumut {
    private String telefon;
    private List<Carte> carti;
    private Date dataImprumut;
    private Date dataScadenta;
    private Date dataRestituire;

    public Imprumut(String telefon, List<Carte> carti, Date dataImprumut, Date dataScadenta, Date dataRestituire) {
        for(int i = 0;i<carti.size();i++) {
            carti.get(i).setImprumutata(true);
            carti.get(i).setTelefon(telefon);
        }
        this.telefon = telefon;
        this.carti = carti;
        this.dataImprumut = dataImprumut;
        this.dataScadenta = dataScadenta;
        this.dataRestituire = dataRestituire;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public List<Carte> getCarti() {
        return carti;
    }

    public void setCarti(List<Carte> carti) {
        this.carti = carti;
    }

    public Date getDataImprumut() {
        return dataImprumut;
    }

    public void setDataImprumut(Date dataImprumut) {
        this.dataImprumut = dataImprumut;
    }

    public Date getDataScadenta() {
        return dataScadenta;
    }

    public void setDataScadenta(Date dataScadenta) {
        this.dataScadenta = dataScadenta;
    }

    public Date getDataRestituire() {
        return dataRestituire;
    }

    public void setDataRestituire(Date dataRestituire) {
        this.dataRestituire = dataRestituire;
    }

    @Override
    public String toString() {
        return "Imprumut{" +
                "telefon='" + telefon + '\'' +
                ", carti=" + carti +
                ", dataImprumut=" + dataImprumut +
                ", dataScadenta=" + dataScadenta +
                ", dataRestituire=" + dataRestituire +
                '}';
    }
}
