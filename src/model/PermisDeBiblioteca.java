package model;

import java.util.Date;

public class PermisDeBiblioteca {
    private String telefon;
    private boolean dreptImprumut;
    private boolean dreptRezervare;
    private Date dataInceput;
    private Date dataExpirare;
    private boolean valid;
    private boolean eStudent;

    public PermisDeBiblioteca(String telefon, boolean dreptImprumut, boolean dreptRezervare, Date dataInceput, Date dataExpirare, boolean valid, boolean eStudent) {
        this.telefon = telefon;
        this.dreptImprumut = dreptImprumut;
        this.dreptRezervare = dreptRezervare;
        this.dataInceput = dataInceput;
        this.dataExpirare = dataExpirare;
        this.valid = valid;
        this.eStudent = eStudent;
    }


    public boolean isDreptImprumut() {
        return dreptImprumut;
    }

    public void setDreptImprumut(boolean dreptImprumut) {
        this.dreptImprumut = dreptImprumut;
    }

    public boolean isDreptRezervare() {
        return dreptRezervare;
    }

    public void setDreptRezervare(boolean dreptRezervare) {
        this.dreptRezervare = dreptRezervare;
    }

    public Date getDataInceput() {
        return dataInceput;
    }

    public void setDataInceput(Date dataInceput) {
        this.dataInceput = dataInceput;
    }

    public Date getDataExpirare() {
        return dataExpirare;
    }

    public void setDataExpirare(Date dataExpirare) {
        this.dataExpirare = dataExpirare;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean iseStudent() {
        return eStudent;
    }

    public void seteStudent(boolean eStudent) {
        this.eStudent = eStudent;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @Override
    public String toString() {
        return "PermisDeBiblioteca{" +
                "telefon='" + telefon + '\'' +
                ", dreptImprumut=" + dreptImprumut +
                ", dreptRezervare=" + dreptRezervare +
                ", dataInceput=" + dataInceput +
                ", dataExpirare=" + dataExpirare +
                ", valid=" + valid +
                ", eStudent=" + eStudent +
                '}';
    }
}

