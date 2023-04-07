package model;

import java.util.Date;

public class PermisDeBiblioteca {
    private Utilizator utilizator;
    private boolean dreptImprumut;
    private boolean dreptRezervare;
    private Date dataInceput;
    private Date dataExpirare;
    private boolean valid;
    private boolean eStudent;

    public PermisDeBiblioteca(Utilizator utilizator, boolean dreptImprumut, boolean dreptRezervare, Date dataInceput, Date dataExpirare, boolean valid) {
        this.utilizator = utilizator;
        this.dreptImprumut = dreptImprumut;
        this.dreptRezervare = dreptRezervare;
        this.dataInceput = dataInceput;
        this.dataExpirare = dataExpirare;
        this.valid = valid;
        this.eStudent = eStudent;
    }

    public Utilizator getUtilizator() {
        return utilizator;
    }

    public void setUtilizator(Utilizator utilizator) {
        this.utilizator = utilizator;
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

    @Override
    public String toString() {
        return "PermisDeBiblioteca{" +
                "utilizator=" + utilizator +
                ", dreptImprumut=" + dreptImprumut +
                ", dreptRezervare=" + dreptRezervare +
                ", dataInceput=" + dataInceput +
                ", dataExpirare=" + dataExpirare +
                ", valid=" + valid +
                ", eStudent=" + eStudent +
                '}';
    }
}

