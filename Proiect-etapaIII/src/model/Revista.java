package model;

import utile.GenCarte;
import utile.GenRevista;
import utile.TipCarte;

import java.util.Set;

public class Revista extends Carte{
    private GenRevista genRevista;
    private int nrLuniValabilitate;

    public Revista(String titlu, Autor autor, Editura editura, GenCarte gen, int anPublicatie, TipCarte tipCarte, boolean rezervata, String telefon, boolean imprumutata, GenRevista genRevista, int nrLuniValabilitate) {
        super(titlu, autor, editura, gen, anPublicatie, tipCarte, rezervata, telefon, imprumutata);
        this.genRevista = genRevista;
        this.nrLuniValabilitate = nrLuniValabilitate;
    }

    public GenRevista getGenRevista() {
        return genRevista;
    }

    public void setGenRevista(GenRevista genRevista) {
        this.genRevista = genRevista;
    }

    public int getNrLuniValabilitate() {
        return nrLuniValabilitate;
    }

    public void setNrLuniValabilitate(int nrLuniValabilitate) {
        this.nrLuniValabilitate = nrLuniValabilitate;
    }
}
