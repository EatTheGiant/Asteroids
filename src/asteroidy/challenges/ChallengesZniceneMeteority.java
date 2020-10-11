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
public abstract class ChallengesZniceneMeteority {
    
    private int znicenychMeteoritov;
    private int meteorityNaZnicenie;
    
    public ChallengesZniceneMeteority( int meteorityNaZnicenie) {
        this.meteorityNaZnicenie = meteorityNaZnicenie;
        this.znicenychMeteoritov = 0;
    }
    
    public int getMeteorityNaZnicenie() {
        return this.meteorityNaZnicenie;
    }
    
    /**
     * 
     * @return vrati pocet znicenych velkych meteoritov.
     */
    
    public int getZnicenychMeteoritov() {
        return this.znicenychMeteoritov;
    }
    
    /**
     * 
     * Pripocita zniceny meteorit ak je zlty (velky) do zoznamu. 
     * Cielom je znici 5 velkych meteoritov.
     */
    
    public void pripocitajMeteorit() {
        this.znicenychMeteoritov++;
    }
    
    public void setZnicenychMeteoritov(int pocetZnicenychMeteoritov) {
        this.znicenychMeteoritov = pocetZnicenychMeteoritov;
    }
}
