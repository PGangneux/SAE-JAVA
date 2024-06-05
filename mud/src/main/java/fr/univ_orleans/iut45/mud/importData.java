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
    private List<Equipe> liEquipes;

    private static List<String> lineAMot(String line) {
        List<String> liMots = new ArrayList<>();
        int i = 0;
    
        while (i < line.length()) {
            String mot = "";
            while (i < line.length() && line.charAt(i) != ',') {
                mot += line.charAt(i);
                i++;
            }
            if (!mot.isEmpty()) {
                liMots.add(mot);
            }
            i++;
        }
        return liMots;
    }
    


    private List<List<String>> CSVtoJava(String chemin) throws IOException{
        FileReader fileReader = new FileReader(chemin);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = reader.readLine();
        line = reader.readLine();
        List<List<String>> liCSV = new ArrayList<>();
		while (line != null) {
			liCSV.add(importData.lineAMot(line));
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

    private Set<Sport> creationSport(){
        Set<Sport> ensSport = new HashSet<>();
        ensSport.add(new Sport("Natation",true));
        ensSport.add(new Sport("Volley-ball",true));
        ensSport.add(new Sport("Escrime",false));
        ensSport.add(new Sport("Athlétisme",true));
        return ensSport;
    }

    private List<Athlete> creationAthletes(List<List<String>> liCSV, Set<Pays> setPays, Set<Sport> setSport){
        List<Athlete> liAthletes = new ArrayList<>();
        for (List<String> line: liCSV){
            Pays paysAthlete = new Pays("erreur");
            Sport sportAthlete = new Sport("erreur", false);
            for (Pays pays : setPays){
                if (line.get(3).equals(pays.getNom())){paysAthlete = pays;}
            }
            for (Sport sport : setSport){
                if (line.get(3).equals(sport.getNom())){sportAthlete = sport;}
            }
            Athlete athlete = new Athlete(line.get(0), line.get(1), line.get(2), paysAthlete, sportAthlete, Integer.valueOf(line.get(5)), Integer.valueOf(line.get(6)), Integer.valueOf(line.get(7)));
            liAthletes.add(athlete);
        }

        return liAthletes;
    }

    private List<Equipe> creationEquipes(Set<Pays> ensPays, Set<Sport> enSports, List<Athlete> liAthletes){
        List<Equipe> liEquipes = new ArrayList<>();
        for(Athlete athlete : liAthletes){
            Pays pays = athlete.getPays();
            Sport sport = athlete.getSport();
            String sexe = athlete.getSexe();
            Boolean creerEquipe = true;
            for (Equipe equipe : liEquipes){
                if(sport.equals(equipe.getSport()) && pays.equals(equipe.getPays()) && sexe.equals(equipe.getSexe())){
                    equipe.ajouteAthlete(athlete);
                    creerEquipe = false;
                }
            }
            if(creerEquipe){
                String nom = "Equipe "+pays.getNom()+ " " +sexe+" de "+sport.getNom();
                Equipe e = new Equipe(nom,sexe,pays,sport);
                e.ajouteAthlete(athlete);
                liEquipes.add(e);
            }
        }
        return liEquipes;

    }

    public importData(String chemin){
        this.chemin = chemin;
        try{
            List<List<String>> liDonnees = this.CSVtoJava(this.chemin);
            this.ensPays = this.creationPays(liDonnees);
            this.ensSport = this.creationSport();
            this.liAthletes = this.creationAthletes(liDonnees, this.ensPays, this.ensSport);
            this.liEquipes = this.creationEquipes(this.ensPays, this.ensSport, this.liAthletes);
        }
        catch(IOException e){
            System.out.println("erreur");
        }

    }

    public Set<Sport> getEnsSports(){
        return this.ensSport;
    }

    public Set<Pays> getEnsPays(){
        return this.ensPays;
    }

    public List<Athlete> getListAthletes(){
        return this.liAthletes;
    }

    public List<Equipe> getListEquipes(){
        return this.liEquipes;
    }
}