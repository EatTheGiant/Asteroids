/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidy.objekty.meteoridy;

import asteroidy.tvary.Kruh;
import asteroidy.tvary.Platno;
import java.awt.Shape;

/**
 * Interface Meteorid urcuje vsetkym meteoritom ich predpisane metody.
 * @author Basic
 */
public abstract class Meteorid {
    private int pozX;
    private int pozY;
    private int smerX;
    private int smerY;
    private Kruh meteorid;
    private int zivoty;
    private int skoreZaZnicenie;

    public Meteorid(int zivoty, int priemer, String farba) {
        
        this.makeMeteorids();
        this.meteorid = new Kruh();
        this.meteorid.zmenPriemer(priemer);
        this.meteorid.posunVodorovne(this.pozX - 25);
        this.meteorid.posunZvisle(this.pozY - 65);
        
        this.meteorid.zmenFarbu(farba);
        this.meteorid.zobraz();
        this.zivoty = zivoty;
        this.skoreZaZnicenie = this.zivoty;
    }
 
    /**
     * 
     * Vytvori meteorid ktory ma nahodnu rychlost, nahodny smer a nahodnu poziciu.
     */
    
    public void makeMeteorids() {

        switch ((int)(Math.random() * 4 + 1)) {
            case 1: 
            //horna stena
                this.pozX =  (int)(Math.random() * Platno.dajPlatno().getSirka() + 1); // Ziska pozX medzi 1 - sirkaPola
                this.pozY =  0; // Ziska pozY 0
                this.smerX = (int)(Math.random() * 7 - 3); //Ziska smerX medzi -3 - 3 premenna udava smer aj rychlost meteoritu
                this.smerY = (int)(Math.random() * 4 + 1); //Ziska smerY medzi 1 - 4   premenna udava smer aj rychlost meteoritu
                break;
            case 2:
            //dolna stena
                this.pozX =  (int)(Math.random() * Platno.dajPlatno().getSirka() + 1); // Ziska pozX medzi 1 - sirkaPola
                this.pozY =  600; // Ziska pozY 600
                this.smerX = (int)(Math.random() * 7 - 3); //Ziska smerX medzi -3 - 3 premenna udava smer aj rychlost meteoritu
                this.smerY = (int)(Math.random() * 4 - 4); //Ziska smerY medzi -4 - -1   premenna udava smer aj rychlost meteoritu
                break;
            case 3:
            //lava stena
                this.pozX =  0; // Ziska pozX 0
                this.pozY =  (int)(Math.random() * Platno.dajPlatno().getVyska() + 1); // Ziska pozY medzi 1 - vyskaPola 
                this.smerX = (int)(Math.random() * 4 + 1); //Ziska smerY medzi 1 - 4   premenna udava smer aj rychlost meteoritu
                this.smerY = (int)(Math.random() * 7 - 3); //Ziska smerY medzi -3 - 3 premenna udava smer aj rychlost meteoritu
                break;
            case 4:
            //prava stena
                this.pozX =  600; // Ziska pozX 600 
                this.pozY =  (int)(Math.random() * Platno.dajPlatno().getVyska() + 1); // Ziska pozY medzi 1 - vyskaPola
                this.smerX = (int)(Math.random() * 4 - 4); //Ziska smerX medzi -4 - -1   premenna udava smer aj rychlost meteoritu
                this.smerY = (int)(Math.random() * 7 - 3); //Ziska smerY medzi -3 - 3 premenna udava smer aj rychlost meteoritu
                break;
            default: //Defaultna pozicia ak by sa switch vykonal chybne meteority sa nebudu hybat
                this.pozX = 0;
                this.pozY = 0;
                this.smerX = 0;
                this.smerY = 0;
        }
    }
    
    /**
     * Kontroluje ci sa stred meteoridu dostal za hraciu plochu.
     * 
     * @return Pokial sa meteorid dostane za hraciu plochu metoda odosle true, inak odosiela false.
     */    
    public boolean zaCiarov() {
        return this.pozX <= -15 || 
            this.pozX >= Platno.dajPlatno().getSirka() + 15 ||
            this.pozY <= -15 ||
            this.pozY >= Platno.dajPlatno().getVyska() + 15;
    }
    
    /**
     * Metoda posuva meteorid smerom ktory je vygenerovany.
     * Pripocitava ku X a Y stredu a o urcitu vzdialenost posuva meteorid v smere ktorym je natoceny.
     */
    public void posun() {
        if (this.smerX == 0 && this.smerY == 0) {
            this.smerX = (int)(Math.random() * 20 - 12);
            this.smerY = (int)(Math.random() * 20 - 12);
        }
        this.pozX += this.smerX;
        this.pozY += this.smerY;
        
        this.meteorid.posunVodorovne(this.smerX);
        this.meteorid.posunZvisle(this.smerY);
    }

    /**
     * Vola metodu skry() v kruhu, nasledne skryje kruh.
     */
    public void znic() {
        this.meteorid.skry();
    }
    
    /**
    * Metoda obsahuje informacie o kruhu x suradnica, y suradnica, priemer. 
    * 
    * @return Vracia tvar meteoridu
    */
    
    public Shape getShape() {
        Shape shape = this.meteorid.getShape();
        return shape;
    }
    
    /**
     * 
     * @return  Vrati HP meteoridu.
     */
    
    public int getHP() {
        return this.zivoty;
    }

    /**
     * 
     * @param zivoty nastavi HP meteoridu. 
     */
    
    public void setHP(int zivoty) {
        this.zivoty = zivoty;
    }

    /**
     * 
     * Kadzy meteorid ma ine skore ktore sa pripocitava do score hraca; 
     * @return vrati pocet bodov za zniceny meteorid.
     */
    
    public int getSkoreZaZnicenie() {
        return this.skoreZaZnicenie;
    }
}
