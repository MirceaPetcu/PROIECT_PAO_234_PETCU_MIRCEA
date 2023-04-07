package model;

import utile.GenCarte;
import utile.TipCarte;

import java.util.Set;

public class Carte {
    protected String titlu;
    protected Autor autor;
    protected Editura editura;
    private GenCarte gen;
    private int anPublicatie;
    private TipCarte tipCarte;
    protected boolean rezervata;
    protected Utilizator utilizator;
    protected boolean imprumutata;

    public Carte(String titlu, Autor autor, Editura editura, GenCarte gen, int anPublicatie, TipCarte tipCarte, boolean rezervata, Utilizator utilizator, boolean imprumutata) {
        this.titlu = titlu;
        this.autor = autor;
        this.editura = editura;
        this.gen = gen;
        this.anPublicatie = anPublicatie;
        this.tipCarte = tipCarte;
        this.rezervata = rezervata;
        this.utilizator = utilizator;
        this.imprumutata = imprumutata;
    }

    public boolean isImprumutata() {
        return imprumutata;
    }

    public void setImprumutata(boolean imprumutata) {
        this.imprumutata = imprumutata;
    }

    public Utilizator getUtilizator() {
        return utilizator;
    }

    public void setUtilizator(Utilizator utilizator) {
        this.utilizator = utilizator;
    }

    public boolean isRezervata() {
        return rezervata;
    }

    public void setRezervata(boolean rezervata) {
        this.rezervata = rezervata;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editura getEditura() {
        return editura;
    }

    public void setEditura(Editura editura) {
        this.editura = editura;
    }

    public GenCarte getGen() {
        return gen;
    }

    public void setGen(GenCarte gen) {
        this.gen = gen;
    }

    public int getAnPublicatie() {
        return anPublicatie;
    }

    public void setAnPublicatie(int anPublicatie) {
        this.anPublicatie = anPublicatie;
    }


    public TipCarte getTipCarte() {
        return tipCarte;
    }

    public void setTipCarte(TipCarte tipCarte) {
        this.tipCarte = tipCarte;
    }


    @Override
    public String toString() {
        return "Carte{" +
                "titlu='" + titlu + '\'' +
                ", autor=" + autor +
                ", editura=" + editura +
                ", gen=" + gen +
                ", anPublicatie=" + anPublicatie +
                ", tipCarte=" + tipCarte +
                ", rezervata=" + rezervata +
                ", utilizator=" + utilizator +
                ", imprumutata=" + imprumutata +
                '}';
    }
}
