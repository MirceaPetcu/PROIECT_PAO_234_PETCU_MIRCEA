package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
public class AutorSingleton {
    private static AutorSingleton instance = null;
    private Set<Autor> autori;

    public static  AutorSingleton getInstance() {
        if (instance == null)
            instance = new AutorSingleton();
        return instance;
    }
    private AutorSingleton() {
        autori = new HashSet<Autor>();
    }

    public Set<Autor> getAutori() {
        return autori;
    }

    public void setAutori(Set<Autor> autori) {
        this.autori = autori;
    }
    private static List<String[]> getColoaneCSV(String file)
    {
        List <String[]> coloane = new ArrayList<String[]>();
        try (var in = new BufferedReader(new FileReader(file)))
        {
            String line;
            while ((line = in.readLine()) != null)
            {
                String[] fields = line.replaceAll(" ", "").split(",");
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
        var coloane = getColoaneCSV("src/data/autori.csv");
        for (var c : coloane){
            var data =  LocalDate.parse(c[1]);
            Autor autor = new Autor(c[0],data,c[2]);
            autori.add(autor);
        }
    }

    public void scrieInCSV()
    {
        try{
            var g = new FileWriter("src/data/autori.csv");
            for (var a : autori)
            {
                g.write(a.getNume() + "," + a.getDataNasterii() + "," + a.getNationalitate() + "\n");
            }
            g.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
