package fr.univ_orleans.iut45.mud;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class importData{
    private String chemin;
    private Set<Sport> ensSport;
    private Set<Pays> ensPays;
    private List<Athlete> liAthletes;


    private List<List<String>> CSVtoJava(String chemin) throws IOException{
        FileReader fileReader = new FileReader(chemin);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = reader.readLine();
        line = reader.readLine();
		
        List<List<String>> liCSV = new ArrayList<>();
		while (line != null) {
            List<String> liLigne = new ArrayList<>();
            liLigne.add(line);
			liCSV.add(liLigne);
			line = reader.readLine();
		}
		reader.close();
        return liCSV;
    }

    private Set<Pays> creationPays(List<List<String>> liCSV){
        Set<Pays> setPays = new HashSet<>();
        for (List<String> line : liCSV){
            Pays pays = new Pays(line.get(3));
            setPays.add(pays);
        }
        return setPays;
    }

    private Set<Sport> creationSport(List<List<String>> liCSV){
        Set<Sport> setSport = new HashSet<>();
        for (List<String> line : liCSV){
            Sport sport = new Sport(line.get(4));
            setSport.add(sport);
        }
        return setSport;
    }

    private List<Athlete> creationAthletes(List<List<String>> liCSV, Set<Pays> setPays, Set<Sport> setSport){
        List<Athlete> liAthletes = new ArrayList<>();
        for (List<String> line: liCSV){
            Pays paysAthlete = new Pays("erreur");
            Sport sportAthlete = new Sport("erreur");
            for (Pays pays : setPays){
                if (line.get(3).equals(pays.getNom())){paysAthlete = pays;}
            }
            for (Sport sport : setSport){
                if (line.get(3).equals(sport.getNom())){sportAthlete = sport;}
            }
            Athlete athlete = new Athlete(line.get(0), line.get(1), line.get(2), paysAthlete, sportAthlete, Integer.valueOf(line.get(6)), Integer.valueOf(line.get(7)), Integer.valueOf(line.get(8)));
            liAthletes.add(athlete);
        }

        return liAthletes;
    }

    public importData(String chemin){
        this.chemin = chemin;
        try{
            List<List<String>> liDonnees = this.CSVtoJava(this.chemin);
            this.ensSport = this.creationSport(liDonnees);
            this.ensPays = this.creationPays(liDonnees);
            this.liAthletes = this.creationAthletes(liDonnees, this.ensPays, this.ensSport);
        }
        catch(IOException e){}

    }

    public Set<Sport> getEnsSports(){
        return this.ensSport;
    }

    public Set<Pays> getEnsPays(){
        return this.ensPays;
    }

    public List<Athlete> getEnsAthletes(){
        return this.liAthletes;
    }
}