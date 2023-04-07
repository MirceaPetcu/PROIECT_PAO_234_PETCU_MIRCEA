package model;

import java.util.Map;

public class Editura {
    private String nume;
    private String adresa;
    private String nrTelefon;
    private Map<Carte,Autor> cartiTiparite;

    public Editura(String nume, String adresa, String nrTelefon, Map<Carte, Autor> cartiTiparite) {
        this.nume = nume;
        this.adresa = adresa;
        this.nrTelefon = nrTelefon;
        this.cartiTiparite = cartiTiparite;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getNrTelefon() {
        return nrTelefon;
    }

    public void setNrTelefon(String nrTelefon) {
        this.nrTelefon = nrTelefon;
    }

    public Map<Carte, Autor> getCartiTiparite() {
        return cartiTiparite;
    }

    public void setCartiTiparite(Map<Carte, Autor> cartiTiparite) {
        this.cartiTiparite = cartiTiparite;
    }

    @Override
    public String toString() {
        return "Editura{" +
                "nume='" + nume + '\'' +
                ", adresa='" + adresa + '\'' +
                ", nrTelefon='" + nrTelefon + '\'' +
                ", cartiTiparite=" + cartiTiparite +
                '}';
    }
}
