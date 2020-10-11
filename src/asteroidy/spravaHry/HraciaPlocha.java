/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidy.spravaHry;

import asteroidy.challenges.SpravaChallenges;
import asteroidy.objekty.meteoridy.MeteoridMaly;
import asteroidy.objekty.Laser;
import asteroidy.objekty.Lodicka;
import asteroidy.objekty.meteoridy.Meteorid;
import asteroidy.objekty.meteoridy.MeteoridVelky;
import asteroidy.objekty.meteoridy.MeteoridStredny;
import asteroidy.tvary.Obdlznik;
import java.util.ArrayList;

/**
 *
 * @author Basic
 */
public class HraciaPlocha {
 
    private final ArrayList<Meteorid> meteoridy;
    private final ArrayList<Laser> lasery;
    private final ArrayList<Obdlznik> hpBar;
    private final Lodicka lodicka;
    private final Kolizie kolizie;
    
    private boolean mozeStrielat;
    
    
    private long pocitadloTikov;
    private int score;
    
    /**
     * 
     * HraciaPlocha vytvara objekty ako su napr lodicka, meteority, lasery.
     * Ma na starosti challenge.
     */
    
    public HraciaPlocha() {
        this.meteoridy = new ArrayList<Meteorid>();
        this.lodicka = new Lodicka();
        this.lasery = new ArrayList<Laser>();
        
        //this.challengeVydrzMinutu.zadanie();
        this.kolizie = new Kolizie(this.lodicka);
        this.hpBar = this.urobHPBar();
        
        for (int i = 0; i < 5; i++ ) {
            this.meteoridy.add(this.randomMeteorid());
        }
        this.pocitadloTikov = 0;
        this.score = 0;
        this.mozeStrielat = true;
    }
    
    /**
     * Vytvara meteority iba na stenach hracej plochy a smeruje ich roznymi 
     * smermi.
     * Metodu Random pouziva konstruktor.
     * @return vracia novy meteorid.
     */
    
    public Meteorid randomMeteorid() {
        int randNum = (int)(Math.random() * 3 + 0);
        
        switch (randNum) {
            case 0:
                return new MeteoridStredny();
            case 1:
                return new MeteoridMaly();
            case 2:
                return new MeteoridVelky();
            default:
                return null;
        }
    }
    
    /**
     * 
     * Kazdy piaty tik vytvori laser ktory sa pohybuje konstantnou rychlostou 
     * po mape.
     */
    
    public void makeLaser() {
        if (this.mozeStrielat) {
            this.pocitadloTikov++;
            //System.out.println(pocitadloTikov);
            if (this.pocitadloTikov % 5 == 0) {
                this.lasery.add(new Laser(this.lodicka));
            }
        }
    }
    
    /**
     * 
     * V pravom hornom rohu vytvori HP bar lodicky.
     * @return ArrayList Obdlznikov reprezentujuce HP lodicky.
     */
    
    public ArrayList<Obdlznik> urobHPBar() {
        int pocetHP = this.lodicka.getHP();
        ArrayList<Obdlznik> hpBarArrayList = new ArrayList<Obdlznik>();
        
        for (int i = 0; i < pocetHP; i++) {
            hpBarArrayList.add(new Obdlznik(4, 12, 3 + i * 6, 3, "white"));
            hpBarArrayList.get(i).zobraz();
        }
        return hpBarArrayList;
    }
    
    /**
     * 
     * Odpocita HP lodicky a zaroven vymaze 1 HP bar z mapy a ArrayListu. 
     */
    
    public void odoberHP() {
        
        int pocetHP = this.lodicka.getHP() - 1;            
        this.hpBar.get(pocetHP).skry();
        this.hpBar.remove(pocetHP);
        this.lodicka.setHP(this.lodicka.getHP() - 1);
    }
    
    /**
     * 
     * Pripocita HP lodicky a zaroven prida 1 HP bar do mapy a ArrayListu.
     */
    
    public void pridajHP() {
        int pocetHP = this.lodicka.getHP();
        this.hpBar.add(new Obdlznik(4, 12, 3 + pocetHP * 6, 3, "white"));
        this.hpBar.get(pocetHP).zobraz();
        this.lodicka.setHP(this.lodicka.getHP() + 1);
    }
    
    /**
     * 
     * @return Vrati objekt lodicka reprezentujuci lodicku hraca.
     */
    
    public Lodicka getLodicka() {
        return this.lodicka;
    }
    
    /**
     * 
     * Generuje nahodne meteoridy v nahodnom case.
     */

    private void dajMeteoridVNahodnomCase() {
        double nahoda = (int)(Math.random() * 250);
            
        if (nahoda == 1) {
            this.meteoridy.add(this.randomMeteorid());
        }
    }
    
    /**
     * 
     * Zabezpecuje pohyb, vytvaranie laseru a interakciu s dalsimi objektmi.
     */
    
    public void spravaLaserov(SpravaChallenges spravaChallenges) {
        //Kazdy piaty tik vytvori novu strelu
        this.makeLaser();

        for (int idLaser = 0; idLaser < this.lasery.size(); idLaser++) {
            
            this.lasery.get(idLaser).vPred();

            //Laser za ciarov
            if (this.lasery.get(idLaser).zaCiarov()) {
                this.lasery.get(idLaser).skry();
                this.lasery.remove(idLaser);
            }

            for (int idMeteorid = 0; idMeteorid < this.meteoridy.size(); idMeteorid++) {
                if (this.kolizie.zrazkaMeteoridLaser(this.lasery.get(idLaser), this.meteoridy.get(idMeteorid))) {
                
                    this.ukonciMeteoridPoStrele(idMeteorid, spravaChallenges);
                    
                    this.lasery.get(idLaser).skry();
                    this.lasery.remove(idLaser);
                }
            }
        }
    }

    /**
     * 
     * Zabezpecuje pohyb, vytvaranie a interakciu s dalsimi objektmi.
     */
    
    public void spravaMeteoridov() {
        this.dajMeteoridVNahodnomCase();
        for ( int idMeteorid = 0; idMeteorid < this.meteoridy.size(); idMeteorid++) {
            this.meteoridy.get(idMeteorid).posun();

            //Meteorid za ciarov
            if (this.meteoridy.get(idMeteorid).zaCiarov()) {
                this.meteoridy.get(idMeteorid).znic();
                this.meteoridy.remove(idMeteorid);
                this.meteoridy.add(this.randomMeteorid());
            }

            if (this.kolizie.zrazkaMeteoridLodicka(this.lodicka, this.meteoridy.get(idMeteorid))) {
                this.odoberHP();
                this.meteoridy.get(idMeteorid).znic();
                this.meteoridy.remove(idMeteorid);
                this.meteoridy.add(this.randomMeteorid());
                if (this.lodicka.getHP() == 0) {
                    //manazer.ukonciObjekt(this);
                    System.out.println("");
                    System.out.println("----------------------------------------------------");
                    System.out.println("Prehral si. To je ale skoda.");
                    System.out.println("Tvoje skore je: " + this.score);
                    System.out.println("----------------------------------------------------");
                    System.out.println("");
                }
            }
        }
    }

    /**
     * 
     * @return Vracia ArrayList Meteoridov ktore su na mape.
     */
    
    public ArrayList<Meteorid> getMeteoridy() {
        return this.meteoridy;
    }
    
    /**
     * 
     * @return Vracia ArrayList Laserov ktore su na mape.
     */

    public ArrayList<Laser> getLasery() {
        return this.lasery;
    }
    
    /**
     * Zabezpecuje odstranenie meteoridu po tom co ho zasiahne laser a jeho 
     * HP je 0.
     * @param idMeteorid id Meteoridu podla ktoreh0o odstranujeme metoerid 
     * v ArrayListe
     */
    
    /*Takticka poznamka: int tam je kvoli tomu ze pouzivam remove, no trha mi 
                       to oci. :(     */
    public void ukonciMeteoridPoStrele(int idMeteorid, SpravaChallenges spravaChallenges) {
        this.meteoridy.get(idMeteorid).setHP(this.meteoridy.get(idMeteorid).getHP() - 1);
        if (this.meteoridy.get(idMeteorid).getHP() == 0) {
            this.score = this.score + this.meteoridy.get(idMeteorid).getSkoreZaZnicenie();
            
            spravaChallenges.zasahMeteoridu(this.meteoridy.get(idMeteorid));
            
            this.meteoridy.get(idMeteorid).znic();
            this.meteoridy.remove(idMeteorid);
            this.meteoridy.add(this.randomMeteorid());
        }
        
    }

    

    public void setMozeStrielat(boolean mozeStrielat) {
        this.mozeStrielat = mozeStrielat;
    }

    public void setPocitadloTikov(long pocitadloTikov) {
        this.pocitadloTikov = pocitadloTikov;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }
    
    
    
}
