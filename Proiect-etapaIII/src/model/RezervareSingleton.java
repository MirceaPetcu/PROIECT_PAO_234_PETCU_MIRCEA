package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RezervareSingleton {
    private static RezervareSingleton instance = null;
    private List<Rezervare> rezervari;

    public static RezervareSingleton getInstance() {
        if (instance == null)
            instance = new RezervareSingleton();
        return instance;
    }

    private RezervareSingleton() {
        rezervari = new ArrayList<Rezervare>();
    }


    public List<Rezervare> getRezervari() {
        return rezervari;
    }

    public void setRezervari(List<Rezervare> rezervari) {
        this.rezervari = rezervari;
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

    public void citesteDinCSV() {
        var coloane = getColoaneCSV("src/data/autori.csv");
        for (var c : coloane) {
            var carti = new ArrayList<Carte>();
            // ia titlurile cartilor
            var titluri = c[2].split(";");
            for (var t : titluri) {
                var carte = CarteSingleton.getInstance().getCartiDinBiblioteca().stream().filter(x -> x.getTitlu().equals(t)).findFirst().get();
                carti.add(carte);
            }

            Rezervare rezervare = new Rezervare(carti, c[1]);
            rezervari.add(rezervare);

        }
    }

    public void scrieInCSV() {
        try {
            var g = new FileWriter("src/data/rezervari.csv.csv");
            for (var r : rezervari) {
                var titluri = new ArrayList<String>();
                for (var c : r.getCarti()) {
                    titluri.add(c.getTitlu());
                }
                var linie = String.join(";", titluri);
                g.write(r.getTelefon() + "," + linie + "\n");
            }
            g.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
