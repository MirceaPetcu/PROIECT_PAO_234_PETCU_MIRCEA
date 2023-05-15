package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ImprumutSingleton {
    private static ImprumutSingleton instance = null;
    private Set<Imprumut> imprumuturi;

    public ImprumutSingleton() {
        this.imprumuturi = new HashSet<Imprumut>();
    }

    public Set<Imprumut> getImprumuturi() {
        return imprumuturi;
    }

    public void setImprumuturi(Set<Imprumut> imprumuturi) {
        this.imprumuturi = imprumuturi;
    }

    public static ImprumutSingleton getInstance() {
        if (instance == null) {
            instance = new ImprumutSingleton();
        }
        return instance;
    }


    private static List<String[]> getColoaneCSV(String file) {
        List<String[]> coloane = new ArrayList<String[]>();
        try (var in = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = in.readLine()) != null) {
                String[] fields = line.replaceAll(" ", "").split(",");
                coloane.add(fields);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return coloane;
    }

    public void citesteDinCSV() throws ParseException {
        var coloane = getColoaneCSV("src/data/imprumuturi.csv");
        for (var c : coloane) {
            var carti = new ArrayList<Carte>();
            // ia titlurile cartilor
            var titluri = c[1].split(";");
            for (var t : titluri) {
                var carte = CarteSingleton.getInstance().getCartiDinBiblioteca().stream().filter(x -> x.getTitlu().equals(t)).findFirst().get();
                carti.add(carte);
            }
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            try {
                var dataImprumut = formatter.parse(c[2]);
                var dataScadenta = formatter.parse(c[3]);
                var dataRestituire = formatter.parse(c[4]);
                Imprumut imprumut = new Imprumut(c[0], carti, dataImprumut, dataScadenta, dataRestituire);
                imprumuturi.add(imprumut);
            }
            catch (Exception e) {
                System.out.println("Nu s-a putut converti string-ul la tipul Date: " + e.getMessage());
            }
        }
    }

    public void scrieInCSV() {
        try {
            var g = new FileWriter("src/data/imprumuturi.csv");
            for (var i: imprumuturi) {
                var titluri = new ArrayList<String>();
                for (var c : i.getCarti()) {
                    titluri.add(c.getTitlu());
                }
                var linie = String.join(";", titluri);
                g.write(i.getTelefon() + "," +  linie +"," + i.getDataImprumut() + "," + i.getDataScadenta() + "," + i.getDataRestituire() + "\n");
            }
            g.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
