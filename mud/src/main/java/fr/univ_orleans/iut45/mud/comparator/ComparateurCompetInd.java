package fr.univ_orleans.iut45.mud.comparator;

import java.util.Comparator;
import java.util.Map;

import fr.univ_orleans.iut45.mud.items.Athlete;


/**
 * La classe ComparateurCompetInd implémente l'interface Comparator pour comparer deux athlètes
 * en fonction de leur score dans une compétition individuelle donnée.
 */
public class ComparateurCompetInd implements Comparator<Athlete>{

    /**
     * Les données de scores des athlètes.
     * La clé est un objet Athlete et la valeur est le score associé à cet athlète.
     */
    private Map<Athlete,Integer> donnees;


    /**
     * Constructeur pour créer un comparateur d'athlètes avec les scores spécifiés.
     *
     * @param donnees Une map contenant les athlètes et leurs scores respectifs.
     */
    public ComparateurCompetInd(Map<Athlete,Integer> donnees){
        this.donnees = donnees;
    }


    /**
     * Compare deux athlètes en fonction de leur score.
     *
     * @param a1 Le premier athlète à comparer.
     * @param a2 Le deuxième athlète à comparer.
     * @return une valeur négative si a1 a un score inférieur à a2,
     *         zéro s'ils ont le même score, une valeur positive sinon.
     */
    @Override
    public int compare(Athlete a1, Athlete a2){
        return this.donnees.get(a1)-this.donnees.get(a2);
    }

}
