package model;

import exceptii.NuExista;
import exceptii.NuSePoateImprumuta;
import exceptii.NuSePoateRezerva;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Utilizator {
    private String nume;
    private String adresa;
    private String nrTelefon;
    private PermisDeBiblioteca permisDeBiblioteca;
    private List<Imprumut> imprumuturi;
    private List<Rezervare> rezervari;


    public Utilizator(String nume, String adresa, String nrTelefon, PermisDeBiblioteca permisDeBiblioteca, List<Imprumut> imprumuturi, List<Rezervare> rezervari) {
        this.nume = nume;
        this.adresa = adresa;
        this.nrTelefon = nrTelefon;
        this.permisDeBiblioteca = permisDeBiblioteca;
        this.imprumuturi = imprumuturi;
        this.rezervari = rezervari;
    }

    public List<Rezervare> getRezervari() {
        return rezervari;
    }

    public void setRezervari(List<Rezervare> rezervari) {
        this.rezervari = rezervari;
    }

    public PermisDeBiblioteca getPermisDeBiblioteca() {
        return permisDeBiblioteca;
    }

    public void setPermisDeBiblioteca(PermisDeBiblioteca permisDeBiblioteca) {
        this.permisDeBiblioteca = permisDeBiblioteca;
    }

    public List<Imprumut> getImprumuturi() {
        return imprumuturi;
    }

    public void setImprumuturi(List<Imprumut> imprumuturi) {
        this.imprumuturi = imprumuturi;
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

    @Override
    public String toString() {
        return "Utilizator{" +
                "nume='" + nume + '\'' +
                ", adresa='" + adresa + '\'' +
                ", nrTelefon='" + nrTelefon + '\'' +
                ", permisDeBiblioteca=" + permisDeBiblioteca +
                ", imprumuturi=" + imprumuturi +
                ", rezervari.csv=" + rezervari +
                '}';
    }
}
