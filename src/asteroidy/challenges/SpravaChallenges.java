/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidy.challenges;

import asteroidy.objekty.meteoridy.Meteorid;
import asteroidy.objekty.meteoridy.MeteoridVelky;
import asteroidy.spravaHry.TimeWatch;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Basic
 */
public class SpravaChallenges {
    private TreeMap<String, IChallenges> challenges;
    private TimeWatch watch;
    
    public SpravaChallenges() {
        this.watch = this.watch.start();
        this.challenges = new TreeMap<String, IChallenges>();
        this.challenges.put("ZnicMeteority", new ChallengeZnicMeteority());
        this.challenges.put("ZnicVelkeMeteority", new ChallengeZnicVelkeMeteority());
        this.challenges.put("VydrzMinutu", new ChallengeVydrzMinutu());
    }
    
    
    /**
     * 
     * Pokial je Challenge splneny ukonci Challenge, da hracovi odmenu a zapne
     * dalsi Challenge.
     */
    public boolean splnenyChallengeZnicMeteority() {
        
        if (((ChallengesZniceneMeteority)this.challenges.get("ZnicMeteority")).getZnicenychMeteoritov() == 
            ((ChallengesZniceneMeteority)this.challenges.get("ZnicMeteority")).getMeteorityNaZnicenie() && 
            this.challenges.get("ZnicMeteority").getStavChallenge() == StavChallenge.AKTIVNY
        ) { 
            this.challenges.get("ZnicMeteority").ukonciChallenge();
            this.challenges.get("ZnicVelkeMeteority").zadanie();
            return true;
        }
        return false;
    }
    
    /**
     * 
     * Pokial je Challenge splneny ukonci Challenge, da hracovi odmenu a zapne
     * dalsi Challenge.
     */
    
    public boolean splnenyChallengeZnicVelkeMeteority() {
        if (((ChallengesZniceneMeteority)this.challenges.get("ZnicVelkeMeteority")).getZnicenychMeteoritov() == 
                ((ChallengesZniceneMeteority)this.challenges.get("ZnicVelkeMeteority")).getMeteorityNaZnicenie() && 
            this.challenges.get("ZnicVelkeMeteority").getStavChallenge() == StavChallenge.AKTIVNY &&
            this.challenges.get("ZnicMeteority").getStavChallenge() == StavChallenge.SPLNENY
        ) {
            this.challenges.get("ZnicVelkeMeteority").ukonciChallenge();
            this.challenges.get("VydrzMinutu").zadanie();
            this.watch.reset();
            return true;
        }
        return false;
    }
    
    /**
     * 
     * Pokial je Challenge splneny ukonci Challenge, da hracovi odmenu a ukonci
     * Challenge.
     */
    
    public boolean splnenyChallengeVydrzMinutu() {
        
        if (this.challenges.get("ZnicVelkeMeteority").getStavChallenge() == StavChallenge.SPLNENY &&
            this.challenges.get("ZnicMeteority").getStavChallenge() == StavChallenge.SPLNENY &&
            this.challenges.get("VydrzMinutu").getStavChallenge() == StavChallenge.AKTIVNY &&
            this.watch.time(TimeUnit.SECONDS) == 60
        ) {
            this.challenges.get("VydrzMinutu").ukonciChallenge();
            
            return true;
        }
        return false;
    }
    
    /**
     * 
     * Tato metoda vznikla kvoli tomu ze je potrebne zistovat ci je meteorid 
     * zasiahnuty. Je to potrebne pre niektore Challenge.
     * @param meteorid Meteorid ktory sa porovnava.
     */
    public void zasahMeteoridu(Meteorid meteorid) {
        if (this.challenges.get("ZnicMeteority").getStavChallenge() == StavChallenge.AKTIVNY) {
            ((ChallengesZniceneMeteority)this.challenges.get("ZnicMeteority")).pripocitajMeteorit();
        } else if (
            this.challenges.get("ZnicVelkeMeteority").getStavChallenge() == StavChallenge.AKTIVNY &&
            this.challenges.get("ZnicMeteority").getStavChallenge() == StavChallenge.SPLNENY &&
                meteorid instanceof MeteoridVelky
        ) {
            ((ChallengesZniceneMeteority)this.challenges.get("ZnicVelkeMeteority")).pripocitajMeteorit();
            System.out.println("Super este ti zostava: " + (5 - ((ChallengesZniceneMeteority)this.challenges.get("ZnicVelkeMeteority")).getZnicenychMeteoritov()) + " meteoritov.");
        }
    }
    
    public void zacniChallenges() {
        this.challenges.get("ZnicMeteority").zadanie();
    }
    
    public void opakujChallenges() {
        this.zacniChallenges();
        this.challenges.get("ZnicVelkeMeteority").setNeaktivny();
        this.challenges.get("VydrzMinutu").setNeaktivny();
    }
    
    public IChallenges vratChallenge(String nazov) {
        return this.challenges.get(nazov);
    }
}
