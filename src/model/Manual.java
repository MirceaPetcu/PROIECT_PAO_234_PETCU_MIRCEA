package model;

import utile.Dificultate;
import utile.GenCarte;
import utile.TipCarte;

import java.util.Set;

public class Manual extends Carte{
    private String materie;
    private String domeniu;
    private Dificultate dificultate;

    public Manual(String titlu, Autor autor, Editura editura, GenCarte gen, int anPublicatie, TipCarte tipCarte, boolean rezervata, Utilizator utilizator, boolean imprumutata, String materie, String domeniu, Dificultate dificultate) {
        super(titlu, autor, editura, gen, anPublicatie, tipCarte, rezervata, utilizator, imprumutata);
        this.materie = materie;
        this.domeniu = domeniu;
        this.dificultate = dificultate;
    }

    public String getMaterie() {
        return materie;
    }

    public void setMaterie(String materie) {
        this.materie = materie;
    }

    public String getDomeniu() {
        return domeniu;
    }

    public void setDomeniu(String domeniu) {
        this.domeniu = domeniu;
    }

    public Dificultate getDificultate() {
        return dificultate;
    }

    public void setDificultate(Dificultate dificultate) {
        this.dificultate = dificultate;
    }

}
