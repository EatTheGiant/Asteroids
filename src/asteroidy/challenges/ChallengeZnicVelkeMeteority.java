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
public class ChallengeZnicVelkeMeteority extends ChallengesZniceneMeteority implements IChallenges {
    
    private StavChallenge stavChallenge;
    
    /**
     * Konstruktor metody implementuje interface Challenges.
     * Nastavuje stav ulohy na NEAKTIVNY.
     */
    
    public ChallengeZnicVelkeMeteority() {
        super(5);
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
        System.out.println("Prejdime na druhu vyzvu.");
        System.out.println("Vidis vie velke zlte meteority?");
        System.out.println("Rozsekaj ich na marne kusky!");
        System.out.println("Vzdy mi robili nervy!");
        System.out.println("Znic " + "5" + " zltych meteoritov.");
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
        System.out.println("Tak to bola ale show!");
        System.out.println("Som s tebou velmi spokojny.");
        System.out.println("Prihodim ti dalsi zivot.");
        System.out.println("Nech nezerem...");
        System.out.println("Dalsia uloha bude uz narocnejsia.");
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
