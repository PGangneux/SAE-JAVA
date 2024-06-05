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
    private List<Competition> liCompetitions;
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
        ensSport.add(new Sport("Volley-Ball",true));
        ensSport.add(new Sport("Escrime",false));
        ensSport.add(new Sport("Athlétisme",true));
        ensSport.add(new Sport("Handball",true));
        
        
        return ensSport;
    }

    private List<Competition> creationCompetition(){
        List<Competition> liCompetitions = new ArrayList<>();
        for (Sport sport: ensSport){
            if(sport.getNom().equals("Natation")){
                liCompetitions.add(new CompetCoop("Natation relais libre Homme", "M",sport));
                liCompetitions.add(new CompetCoop("Natation relais libre Femme", "F",sport));
                liCompetitions.add(new CompetInd("Natation 100 brasse Homme", "M",sport));
                liCompetitions.add(new CompetInd("Natation 100 brasse Femme", "F",sport));
            }
            else if(sport.getNom().equals("Volley-Ball")){
                liCompetitions.add(new CompetCoop("Volley-Ball Homme", "M", sport));
                liCompetitions.add(new CompetCoop("Volley-Ball Femme", "F", sport));
            }
            else if(sport.getNom().equals("Escrime")){
                liCompetitions.add(new CompetInd("Escrime fleuret Homme", "M",sport));
                liCompetitions.add(new CompetInd("Escrime fleuret Femme","F",sport));
                liCompetitions.add(new CompetInd("Escrime épée Homme", "M",sport));
                liCompetitions.add(new CompetInd("Escrime épée Femme","F",sport));
            }
            else if(sport.getNom().equals("Athlétisme")){
                liCompetitions.add(new CompetCoop("Athlétisme relais 400m Homme", "M", sport));
                liCompetitions.add(new CompetCoop("Athlétisme relais 400m Femme", "F", sport));
                liCompetitions.add(new CompetInd("Athlétisme 110 haies Homme", "M", sport));
                liCompetitions.add(new CompetInd("Athlétisme relais 400m Femme", "F", sport));
            }
            else if(sport.getNom().equals("Handball")){
                liCompetitions.add(new CompetCoop("Handball Homme", "M", sport));
            liCompetitions.add(new CompetCoop("Handball Femme", "F", sport));
            }
        }

        return liCompetitions;
    }

    private List<Equipe> creationEquipes(Set<Pays> ensPays, List<Competition> liCompetitions){
        List<Equipe> liEquipes = new ArrayList<>();
        for (Pays pays: ensPays){
            for(Competition competition: liCompetitions){
                if (competition instanceof CompetCoop){
                    String sexe = competition.getSexe();
                    if (sexe.equals("M")){sexe = "masculine";}
                    else{sexe="feminine";}
                    String nom = "Equipe de " + pays.getNom() + " de " +competition.getSport().getNom() +" "+ sexe;
                    Equipe equipe = new Equipe(nom, competition.getSexe(), pays, competition.getSport());
                    liEquipes.add(equipe);
                }
            }
        }
        return liEquipes;

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
                String epreuve = line.get(4);
                String nomSport = "";
                for (int i=0; i<epreuve.length(); ++i){
                    if (epreuve.charAt(i) == ' '){i = epreuve.length();}
                    else{nomSport += epreuve.charAt(i);}
                }
                if (nomSport.equals(sport.getNom())){sportAthlete = sport;}
            }
            Athlete athlete = new Athlete(line.get(0), line.get(1), line.get(2), paysAthlete, sportAthlete, Integer.valueOf(line.get(5)), Integer.valueOf(line.get(6)), Integer.valueOf(line.get(7)));
            liAthletes.add(athlete);
            
        }
        return liAthletes;
    }

    

    

    public importData(String chemin){
        this.chemin = chemin;
        try{
            List<List<String>> liDonnees = this.CSVtoJava(this.chemin);
            this.ensPays = this.creationPays(liDonnees);
            this.ensSport = this.creationSport();
            this.liAthletes = this.creationAthletes(liDonnees, this.ensPays, this.ensSport);
            this.liCompetitions = this.creationCompetition();
            this.liEquipes = this.creationEquipes(this.ensPays, this.liCompetitions);
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

    public List<Competition> getLiCompetitions() {
        return liCompetitions;
    }

    public List<Equipe> getListEquipes(){
        return this.liEquipes;
    }
}