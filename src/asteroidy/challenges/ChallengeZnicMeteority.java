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
public class ChallengeZnicMeteority extends ChallengesZniceneMeteority implements IChallenges {
    
    private StavChallenge stavChallenge;
    
    
    /**
     * Konstruktor metody implementuje interface Challenges.
     * Nastavuje stav ulohy na NEAKTIVNY.
     */ 
    
    public ChallengeZnicMeteority() {
        super(7);
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
        System.out.println("Zdravim ta udatny bojovnik");
        System.out.println("Tvoja lod sa dostala do zoskupenia meteoritov.");
        System.out.println("Pokus sa branit co najdlhsie,");
        System.out.println("Stavim sa ze ich nedokazes znicit ani " + "5" + ". Hahahaha");
        System.out.println("Znic " + "5" + " meteoritov.");
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
        System.out.println("Coze tak ty si este zivy? Haha.");
        System.out.println("Velmi sa mi paci tvoj zapal.");
        System.out.println("Ako odmenu odo mna dostavas bonusovy zivot.");
        System.out.println("Vyuzi ho s rozumom.");
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
