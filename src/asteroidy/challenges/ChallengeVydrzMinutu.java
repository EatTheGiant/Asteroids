/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidy.challenges;

/**
 *
 * @author Basic
 */
public class ChallengeVydrzMinutu implements IChallenges {
    
    private StavChallenge stavChallenge;

    /**
     * Konstruktor metody implementuje interface Challenges.
     * Nastavuje stav ulohy na NEAKTIVNY.
     */    
    public ChallengeVydrzMinutu() {
        this.stavChallenge = StavChallenge.NEAKTIVNY;
    }
    
    /**
     * 
     * Zadanie pre hraca aby vedel co ma robit.
     * Nastavy stav challengeu na AKTIVNY.
     */
    public void zadanie() {
        System.out.println("");
        System.out.println("----------------------------------------------------");
        System.out.println("Tretia vyzva bude trochu ina.");
        System.out.println("Musim si overit ze si dobry letec.");
        System.out.println("Prezi aspon jednu minutu!");
        System.out.println("Vypnem ti aj dela... lebo preco nie.");
        System.out.println("Prezi 1 minutu.");
        System.out.println("----------------------------------------------------");
        System.out.println("");

        this.stavChallenge = StavChallenge.AKTIVNY;
    }
    
    /**
     * 
     * Po splneni ulohy sa objavy oznamenie o ukonceni ulohy.
     * Nastavi stav na SPLNENY.
     */
    
    public void ukonciChallenge() {
        System.out.println("");
        System.out.println("----------------------------------------------------");
        System.out.println("Preukazal si ze si statny bojovnik.");
        System.out.println("Zapnem ti opat dela");
        System.out.println("Teraz idem otravovat niekoho ineho.");
        System.out.println("Zatial sa maj.");
        System.out.println("----------------------------------------------------");
        System.out.println("");

        this.stavChallenge = StavChallenge.SPLNENY;
    }

    /**
     * 
     * 
     * @return vrati v akom stave challenge je.
     */
    public StavChallenge getStavChallenge() {
        return this.stavChallenge;
    }

    public void setStavChallenge(StavChallenge stavChallenge) {
        this.stavChallenge = stavChallenge;
    }
    
    public void setNeaktivny() {
        this.stavChallenge = StavChallenge.NEAKTIVNY;
    }
}
