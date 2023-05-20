package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PermisDeBibliotecaSingleton {
    private static PermisDeBibliotecaSingleton instance = null;
    private List<PermisDeBiblioteca> permiseDeBiblioteca;

    private PermisDeBibliotecaSingleton() {
        permiseDeBiblioteca = new ArrayList<PermisDeBiblioteca>();
    }

    public static PermisDeBibliotecaSingleton getInstance() {
        if (instance == null) {
            instance = new PermisDeBibliotecaSingleton();
        }
        return instance;
    }

    public List<PermisDeBiblioteca> getPermiseDeBiblioteca() {
        return permiseDeBiblioteca;
    }

    public void setPermiseDeBiblioteca(List<PermisDeBiblioteca> permiseDeBiblioteca) {
        this.permiseDeBiblioteca = permiseDeBiblioteca;
    }

    private static List<String[]> getColoaneCSV(String file) {
        List<String[]> coloane = new ArrayList<String[]>();
        try (var in = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = in.readLine()) != null) {
                String[] fields = line.replaceAll(" ", "").replaceAll("\\\"", "").split(",");
                coloane.add(fields);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return coloane;
    }

    public void citesteDinCSV() {
        var coloane = getColoaneCSV("src/data/permise.csv");
        for (var c : coloane) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            try {
                var dataInceput = formatter.parse(c[3]);
                var dataTerminare = formatter.parse(c[4]);
                var dreptImprumut = Boolean.parseBoolean(c[1]);
                var dreptRezervare = Boolean.parseBoolean(c[2]);
                var valid = Boolean.parseBoolean(c[5]);
                var eStudent = Boolean.parseBoolean(c[6]);
                PermisDeBiblioteca permisDeBiblioteca = new PermisDeBiblioteca(c[0], dreptImprumut, dreptRezervare, dataInceput, dataTerminare, valid, eStudent);
                permiseDeBiblioteca.add(permisDeBiblioteca);
            } catch (Exception e) {
                System.out.println("Nu s-a putut converti string-ul la tipul Date: " + e.getMessage());
            }
        }
    }
        public void scrieInCSV ()
        {
            try {
                var g = new FileWriter("src/data/permise.csv");
                for (var p : permiseDeBiblioteca) {
                    g.write(p.getTelefon() + "," + p.isDreptImprumut() + "," + p.isDreptRezervare() + "," + p.getDataInceput() + "," + p.getDataExpirare() + "," + p.isValid() + "," + p.iseStudent() + "\n");
                }
                g.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
