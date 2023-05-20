package model;

import java.sql.Date;
import java.time.LocalDate;

public class Autor {
    private String nume;
    private LocalDate dataNasterii;
    private String nationalitate;

    public Autor(String nume, LocalDate dataNasterii, String nationalitate) {
        this.nume = nume;
        this.dataNasterii = dataNasterii;
        this.nationalitate = nationalitate;
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



    @Override
    public String toString() {
        return "Autor{" +
                "nume='" + nume + '\'' +
                ", dataNasterii=" + dataNasterii +
                ", nationalitate='" + nationalitate + '\'' +
                '}';
    }
}
