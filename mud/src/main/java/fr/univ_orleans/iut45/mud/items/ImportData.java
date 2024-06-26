package fr.univ_orleans.iut45.mud.items;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.univ_orleans.iut45.mud.competition.CompetCoop;
import fr.univ_orleans.iut45.mud.competition.CompetInd;
import fr.univ_orleans.iut45.mud.epreuve.EpreuveCoop;
import fr.univ_orleans.iut45.mud.epreuve.EpreuveCoopMasc;
import fr.univ_orleans.iut45.mud.epreuve.EpreuveIndFem;
import fr.univ_orleans.iut45.mud.epreuve.EpreuveIndMasc;


/**
 * Classe permettant d'importer des données à partir d'un fichier CSV et de créer des instances de divers objets tels que 
 * des athlètes, des équipes, des sports, des pays et des compétitions.
 */
public class ImportData{
    private String chemin;
    private Set<Sport> ensSport;
    private Set<Pays> ensPays;
    private List<Athlete> liAthletes;
    private Set<CompetCoop> ensCompetitionsCoop;
    private Set<CompetInd> ensCompetitionsInd;
    private List<Equipe> liEquipes;


    /**
     * Convertit une ligne de texte CSV en une liste de mots.
     *
     * @param line la ligne de texte à convertir.
     * @return une liste de mots.
     */
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
    

     /**
     * Lit le fichier CSV et le convertit en une liste de listes de chaînes de caractères.
     *
     * @param chemin le chemin du fichier CSV.
     * @return une liste de listes de chaînes de caractères représentant le contenu du CSV.
     * @throws IOException si une erreur d'entrée/sortie se produit.
     */
    private List<List<String>> CSVtoJava(String chemin) throws IOException{
        FileReader fileReader = new FileReader(chemin);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = reader.readLine();
        line = reader.readLine();
        List<List<String>> liCSV = new ArrayList<>();
		while (line != null) {
			liCSV.add(ImportData.lineAMot(line));
			line = reader.readLine();
		}
		reader.close();
        
        return liCSV;
    }


    /**
     * Crée un ensemble de pays à partir des données CSV.
     *
     * @param liCSV les données CSV.
     * @return un ensemble de pays.
     */
    private Set<Pays> creationPays(List<List<String>> liCSV){
        Set<Pays> setPays = new HashSet<>();
        for (List<String> line : liCSV){
            Pays pays = new Pays(line.get(3));
            setPays.add(pays);
        }
        return setPays;
    }


    /**
     * Crée un ensemble de sports à partir des données CSV.
     *
     * @param liCSV les données CSV.
     * @return un ensemble de sports.
     */
    private Set<Sport> creationSport(List<List<String>> liCSV){
        Set<Sport> ensSport = new HashSet<>();
        for (List<String> line : liCSV){
            String epreuve = line.get(4);
            String nomSport = "";
            for (int i=0; i<epreuve.length(); ++i){
                if (epreuve.charAt(i) == ' '){i = epreuve.length();}
                else{nomSport += epreuve.charAt(i);}
            }
            Sport sport = new Sport(nomSport);
            ensSport.add(sport);
        }
        return ensSport;
    }


    /**
     * Crée un ensemble de compétitions à partir des sports.
     *
     * @param ensSport l'ensemble des sports.
     * @return un ensemble de compétitions.
     */
    private Set<CompetCoop> creationCompetitionCoop(Set<Sport> ensSport){
        Set<CompetCoop> ensCompetitions = new HashSet<>();
        for (Sport sport: ensSport){
            if(sport.getNom().equals("Natation")){
                CompetCoop compet1 = new CompetCoop("Natation relais libre Homme", "M",sport,4);
                CompetCoop compet2 = new CompetCoop("Natation relais libre Femme", "F",sport,4);
                for(int i = 1; i<5; ++i){
                    EpreuveCoopMasc ep = new EpreuveCoopMasc("épreuve"+i+" "+compet1.getNom(), compet1);
                }
                for(int i = 1; i<5; ++i){
                    EpreuveCoopMasc ep = new EpreuveCoopMasc("épreuve"+i+" "+compet2.getNom(), compet2);
                }
                ensCompetitions.add(compet1);
                ensCompetitions.add(compet2);
            }
            else if(sport.getNom().equals("Volley-Ball")){
                CompetCoop compet1 = new CompetCoop("Volley-Ball Homme", "M", sport,6);
                CompetCoop compet2 = new CompetCoop("Volley-Ball Femme", "F", sport,6);
                for(int i = 1; i<5; ++i){
                    EpreuveCoopMasc ep = new EpreuveCoopMasc("épreuve"+i+" "+compet1.getNom(), compet1);
                }
                for(int i = 1; i<5; ++i){
                    EpreuveCoopMasc ep = new EpreuveCoopMasc("épreuve"+i+" "+compet2.getNom(), compet2);
                }
                ensCompetitions.add(compet1);
                ensCompetitions.add(compet2);
            }
            else if(sport.getNom().equals("Athlétisme")){
                CompetCoop compet1 = new CompetCoop("Athlétisme relais 400m Homme", "M", sport,4);
                CompetCoop compet2 = new CompetCoop("Athlétisme relais 400m Femme", "F", sport,4);
                for(int i = 1; i<5; ++i){
                    EpreuveCoopMasc ep = new EpreuveCoopMasc("épreuve"+i+" "+compet1.getNom(), compet1);
                }
                for(int i = 1; i<5; ++i){
                    EpreuveCoopMasc ep = new EpreuveCoopMasc("épreuve"+i+" "+compet2.getNom(), compet2);
                }
                ensCompetitions.add(compet1);
                ensCompetitions.add(compet2);
            }
            else if(sport.getNom().equals("Handball")){
                CompetCoop compet1 = new CompetCoop("Handball Homme", "M", sport,7);
                CompetCoop compet2 = new CompetCoop("Handball Femme", "F", sport,7);
                for(int i = 1; i<5; ++i){
                    EpreuveCoopMasc ep = new EpreuveCoopMasc("épreuve"+i+" "+compet1.getNom(), compet1);
                }
                for(int i = 1; i<5; ++i){
                    EpreuveCoopMasc ep = new EpreuveCoopMasc("épreuve"+i+" "+compet2.getNom(), compet2);
                }
                ensCompetitions.add(compet1);
                ensCompetitions.add(compet2);
            }
        }

        return ensCompetitions;
    }


    private Set<CompetInd> creationCompetitionInd(Set<Sport> ensSport){
        Set<CompetInd> ensCompetitions = new HashSet<>();
        for (Sport sport: ensSport){
            if(sport.getNom().equals("Natation")){
                CompetInd compet1 = new CompetInd("Natation 100 brasse Homme", "M",sport);
                CompetInd compet2 = new CompetInd("Natation 100 brasse Femme", "F",sport);
                for(int i = 1; i<5; ++i){
                    EpreuveIndMasc ep = new EpreuveIndMasc("épreuve"+i+" "+compet1.getNom(), compet1);
                }
                for(int i = 1; i<5; ++i){
                    EpreuveIndFem ep = new EpreuveIndFem("épreuve"+i+" "+compet2.getNom(), compet2);
                }
                ensCompetitions.add(compet1);
                ensCompetitions.add(compet2);
            }
            else if(sport.getNom().equals("Escrime")){
                CompetInd compet1 = new CompetInd("Escrime fleuret Homme", "M",sport);
                CompetInd compet2 = new CompetInd("Escrime fleuret Femme","F",sport);
                CompetInd compet3 = new CompetInd("Escrime épée Homme", "M",sport);
                CompetInd compet4 = new CompetInd("Escrime épée Femme","F",sport);
                for(int i = 1; i<5; ++i){
                    EpreuveIndMasc ep = new EpreuveIndMasc("épreuve"+i+" "+compet1.getNom(), compet1);
                }
                for(int i = 1; i<5; ++i){
                    EpreuveIndFem ep = new EpreuveIndFem("épreuve"+i+" "+compet2.getNom(), compet2);
                }
                for(int i = 1; i<5; ++i){
                    EpreuveIndMasc ep = new EpreuveIndMasc("épreuve"+i+" "+compet3.getNom(), compet3);
                }
                for(int i = 1; i<5; ++i){
                    EpreuveIndFem ep = new EpreuveIndFem("épreuve"+i+" "+compet4.getNom(), compet4);
                }
                ensCompetitions.add(compet1);
                ensCompetitions.add(compet2);
                ensCompetitions.add(compet3);
                ensCompetitions.add(compet4);
            }
            else if(sport.getNom().equals("Athlétisme")){
                CompetInd compet1 = new CompetInd("Athlétisme 110 haies Homme", "M", sport);
                CompetInd compet2 = new CompetInd("Athlétisme 110 haies Femme", "F", sport);
                for(int i = 1; i<5; ++i){
                    EpreuveIndMasc ep = new EpreuveIndMasc("épreuve"+i+" "+compet1.getNom(), compet1);
                }
                for(int i = 1; i<5; ++i){
                    EpreuveIndFem ep = new EpreuveIndFem("épreuve"+i+" "+compet2.getNom(), compet2);
                }
                ensCompetitions.add(compet1);
                ensCompetitions.add(compet2);
            }
        }

        return ensCompetitions;
    }


    /**
     * Crée une liste d'équipes à partir des pays et des compétitions.
     *
     * @param ensPays l'ensemble des pays.
     * @param ensCompetitions l'ensemble des compétitions.
     * @return une liste d'équipes.
     */
    private List<Equipe> creationEquipes(Set<Pays> ensPays, Set<CompetCoop> ensCompetitionsCoop){
        List<Equipe> liEquipes = new ArrayList<>();
        for (Pays pays: ensPays){
            for(CompetCoop competition: ensCompetitionsCoop){
                String sexe = competition.getSexe();
                if (sexe.equals("M")){sexe = "masculine";}
                else{sexe="feminine";}
                String nom = "Equipe de " + pays.getNom() + " de " +competition.getSport().getNom() +" "+ sexe;
                Equipe equipe = new Equipe(nom, competition.getSexe(), pays, competition.getSport());
                competition.participer(equipe);
                liEquipes.add(equipe);
            }
        }
        return liEquipes;

    }


    /**
     * Crée une liste d'athlètes à partir des données CSV, des pays, des sports, des équipes et des compétitions.
     *
     * @param liCSV les données CSV.
     * @param setPays l'ensemble des pays.
     * @param setSport l'ensemble des sports.
     * @param liEquipes la liste des équipes.
     * @param ensCompetitions l'ensemble des compétitions.
     * @return une liste d'athlètes.
     */
    private List<Athlete> creationAthletes(List<List<String>> liCSV, Set<Pays> setPays, Set<Sport> setSport, List<Equipe> liEquipes, Set<CompetInd> ensCompetitionsInd){
        List<Athlete> liAthletes = new ArrayList<>();
        for (List<String> line: liCSV){
            Pays paysAthlete = new Pays("erreur");
            Sport sportAthlete = new Sport("erreur");
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

            //ajout des athlètes dans leurs équipes 
            if (line.get(4).equals("Natation relais libre") || line.get(4).equals("Volley-Ball") || line.get(4).equals("Athlétisme relais 400m") || line.get(4).equals("Handball")){
               
                for (Equipe equipe : liEquipes){
                    if (equipe.getPays().equals(athlete.getPays()) && equipe.getSexe().equals(athlete.getSexe()) && equipe.getSport().equals(athlete.getSport())){
                        equipe.ajouteAthlete(athlete);
                    }
                }
            }
            
            //ajout des athlètes dans leurs compétitions 
            else if (line.get(4).equals("Natation 100 brasse") || line.get(4).equals("Athlétisme 110 haies")){
                for (CompetInd competition: ensCompetitionsInd){
                    if (competition.getSexe().equals(athlete.getSexe()) && competition.getSport().equals(athlete.getSport())){competition.participer(athlete);}
                }
            }

            // pour les compétitions d'escrime
            else if (line.get(4).equals("Escrime fleuret")) {
                for (CompetInd competition: ensCompetitionsInd){
                    if (competition.getNom().equals("Escrime fleuret Homme") && athlete.getSexe().equals("M") ){competition.participer(athlete);}
                    else if(competition.getNom().equals("Escrime fleuret Femme") && athlete.getSexe().equals("F") ){competition.participer(athlete);}
                }
            }

            else{
                for (CompetInd competition: ensCompetitionsInd){
                    if (competition.getNom().equals("Escrime épée Homme") && athlete.getSexe().equals("M") ){competition.participer(athlete);}
                    else if(competition.getNom().equals("Escrime épée Femme") && athlete.getSexe().equals("F") ){competition.participer(athlete);}
                }
            }
            
        }
        return liAthletes;
    }



    /**
     * Permet d'importer des donner d'un fichier csv.
     *
     * @param chemin le chemin du fichier CSV à importer.
     */
    public ImportData(String chemin){
        this.chemin = chemin;
        try{
            List<List<String>> liDonnees = this.CSVtoJava(this.chemin);
            this.ensPays = this.creationPays(liDonnees);
            this.ensSport = this.creationSport(liDonnees);
            this.ensCompetitionsCoop = this.creationCompetitionCoop(this.ensSport);
            this.ensCompetitionsInd = this.creationCompetitionInd(this.ensSport);
            this.liEquipes = this.creationEquipes(this.ensPays, this.ensCompetitionsCoop);
            this.liAthletes = this.creationAthletes(liDonnees,this.ensPays,this.ensSport,this.liEquipes, this.ensCompetitionsInd);
            
            
        }
        catch(IOException e){
            System.out.println("erreur");
        }

    }


    /**
     * Retourne l'ensemble des sports importés.
     *
     * @return un ensemble de sports.
     */
    public Set<Sport> getEnsSports(){
        return this.ensSport;
    }


     /**
     * Retourne l'ensemble des pays importés.
     *
     * @return un ensemble de pays.
     */
    public Set<Pays> getEnsPays(){
        return this.ensPays;
    }


    /**
     * Retourne la liste des athlètes importés.
     *
     * @return une liste d'athlètes.
     */
    public List<Athlete> getListAthletes(){
        return this.liAthletes;
    }


    /**
     * Retourne l'ensemble des compétitions coopérative importées.
     *
     * @return un ensemble de CompetCoop.
     */
    public Set<CompetCoop> getEnsCompetitionsCoop() {
        return this.ensCompetitionsCoop;
    }

    /**
     * Retourne l'ensemble des compétitions individuel importées.
     *
     * @return un ensemble de CompetInd.
     */
    public Set<CompetInd> getEnsCompetitionsInd() {
        return this.ensCompetitionsInd;
    }

    /**
     * Retourne la liste des équipes importées.
     *
     * @return une liste d'équipes.
     */
    public List<Equipe> getListEquipes(){
        return this.liEquipes;
    }
}