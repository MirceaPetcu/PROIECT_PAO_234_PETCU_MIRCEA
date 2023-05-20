package model;

import utile.GenCarte;
import utile.TipCarte;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
public class CarteSingleton {
    private static CarteSingleton instance = null;
    private List<Carte> cartiDinBiblioteca;

    public static  CarteSingleton getInstance() {
        if (instance == null)
            instance = new CarteSingleton();
        return instance;
    }
    private CarteSingleton() {
        cartiDinBiblioteca = new ArrayList<Carte>();
    }


    public List<Carte> getCartiDinBiblioteca() {
        return cartiDinBiblioteca;
    }

    public void setCartiDinBiblioteca(List<Carte> cartiDinBiblioteca) {
        this.cartiDinBiblioteca = cartiDinBiblioteca;
    }

    private static List<String[]> getColoaneCSV(String file)
    {
        List <String[]> coloane = new ArrayList<String[]>();
        try (var in = new BufferedReader(new FileReader(file)))
        {
            String line;
            while ((line = in.readLine()) != null)
            {
                String[] fields = line.replaceAll(" ", "").replaceAll("\\\"", "").split(",");
                coloane.add(fields);
            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return coloane;
    }

    public void citesteDinCSV()
    {
        var coloane = getColoaneCSV("src/data/carti.csv");
        for (var c : coloane){
            //tre sa fac cast la toate campurile
            var autor = AutorSingleton.getInstance().getAutori().stream().filter(a -> a.getNume().equals(c[1])).findFirst().orElse(null);
            var editura = EdituraSingleton.getInstance().getEdituri().stream().filter(e -> e.getNume().equals(c[2])).findFirst().orElse(null);
            var tipCarte = TipCarte.valueOf(c[5]);
            var genCarte = GenCarte.valueOf(c[3]);
            var an = Integer.parseInt(c[4]);
            var rezervata = Boolean.parseBoolean(c[6]);
            var imprumutata = Boolean.parseBoolean(c[8]);
            Carte carte = new Carte(c[0],autor,editura,genCarte,an,tipCarte,rezervata,c[7],imprumutata);
            cartiDinBiblioteca.add(carte);
        }
    }

    public void scrieInCSV()
    {
        try{
            var g = new FileWriter("src/data/carti.csv");
            for (var c : cartiDinBiblioteca)
            {
                g.write(c.getTitlu() + "," + c.getAutor().getNume() + "," + (c.getEditura() != null ? c.getEditura().getNume() : "") + "," + c.getGen() + "," + c.getAnPublicatie() + "," + c.getTipCarte() + "," + c.isRezervata() + "," + c.getTelefon() + "," + c.isImprumutata() + "\n");
            }
            g.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
