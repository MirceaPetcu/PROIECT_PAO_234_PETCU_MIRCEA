package model;

import java.time.LocalDate;
import java.util.*;

public class Autor {
    private String nume;
    private LocalDate dataNasterii;
    private String nationalitate;
    private List<Carte> cartiPublicate;

    public Autor(String nume, LocalDate dataNasterii, String nationalitate, List<Carte> cartiPublicate) {
        this.nume = nume;
        this.dataNasterii = dataNasterii;
        this.nationalitate = nationalitate;
        this.cartiPublicate = cartiPublicate;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public LocalDate getDataNasterii() {
        return dataNasterii;
    }

    public void setDataNasterii(LocalDate dataNasterii) {
        this.dataNasterii = dataNasterii;
    }

    public String getNationalitate() {
        return nationalitate;
    }

    public void setNationalitate(String nationalitate) {
        this.nationalitate = nationalitate;
    }

    public List<Carte> getCartiPublicate() {
        return cartiPublicate;
    }

    public void setCartiPublicate(List<Carte> cartiPublicate) {
        this.cartiPublicate = cartiPublicate;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "nume='" + nume + '\'' +
                ", dataNasterii=" + dataNasterii +
                ", nationalitate='" + nationalitate + '\'' +
                ", cartiPublicate=" + cartiPublicate +
                '}';
    }
}
