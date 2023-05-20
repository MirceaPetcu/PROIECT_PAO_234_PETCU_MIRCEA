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



public class EdituraSingleton {
    private static EdituraSingleton instance = null;
    private Set<Editura> edituri;

    public static  EdituraSingleton getInstance() {
        if (instance == null)
            instance = new EdituraSingleton();
        return instance;
    }
    private EdituraSingleton() {
        edituri = new HashSet<Editura>();
    }



    public Set<Editura> getEdituri() {
        return edituri;
    }

    public void setEdituri(Set<Editura> edituri) {
        this.edituri = edituri;
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
        var coloane = getColoaneCSV("src/data/edituri.csv");
        for (var c : coloane){
            Editura editura = new Editura(c[0],c[1],c[2]);
            edituri.add(editura);
        }
    }

    public void scrieInCSV()
    {
        try{
            var g = new FileWriter("src/data/edituri.csv");
            for (var e: edituri)
            {
                g.write(e.getNume() + "," + e.getAdresa() + "," + e.getNrTelefon() + "\n");
            }
            g.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
