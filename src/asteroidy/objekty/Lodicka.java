package asteroidy.objekty;

import asteroidy.tvary.Trojuholnik;
import java.awt.Shape;
/**
 * Trieda Lodicka zabezpecuje pohyb, otacanie a kolizie lodicky s ostatnymi objektami.
 * Na spravne fungovanie potrebuje triedy Hra a Trojuholnik.
 * 
 * @author Patrik Grexa
 * @version 8. 12. 2019
 */
public class Lodicka {
    private final Trojuholnik trojuholnik;
    private int pocitadlo;

    private final double vyska;
    private final double zakladna;
    private double lavyHornyX;
    private double lavyHornyY;
    private double transformedHornyX;
    private double transformedHornyY;
    private int zivoty;

    
    /**
     * Konstruktor obsahuje triedu Hra a Trojuholnik, plus atributy pre vytvorenie lodicky.
     * Trieda lodicka pracuje hlavne s trojuholnikom a preto ho vytvara a hned aj zobrazi.
     */

    public Lodicka() {
        this.trojuholnik = new Trojuholnik();
        this.pocitadlo = 0;
        
        this.vyska = this.trojuholnik.getVyska();
        this.zakladna = this.trojuholnik.getZakladna();
        this.lavyHornyX = this.trojuholnik.getHornyX();
        this.lavyHornyY = this.trojuholnik.getHornyY();
        this.zivoty = 3;

        this.transformedHornyX = this.lavyHornyX;
        this.transformedHornyY = this.lavyHornyY ;

        this.trojuholnik.zobraz();
    }
    
    /**
     * Vola metodu skry() v trojuholniku, nasledne skryje trojuholnik.
     */
    public void skry() {
        this.trojuholnik.skry();
    }
    
    /**
     * 
     * @return Vracia suradnicu X v lavom hornom rohu trojuholnika.
     */
    
    public double getLavyHornyX() {
        return this.lavyHornyX;
    }
   
    /**
     * 
     * @return Vracia suradnicu Y v lavom hornom rohu trojuholnika.
     */
    
    public double getLavyHornyY() {
        return this.lavyHornyY;
    }

    /**
     * 
     * @return Vracia vysku trojuholnika.
     */
    
    public double getVyska() {
        return this.vyska;
    }
    
    /**
     * 
     * @return Vracia zakladnu trojuholnika.
     */

    public double getZakladna() {
        return this.zakladna;
    }
    
    /**
     * 
     * @return Vracia pocet stupnov  v .
     */

    public int getPocitadlo() {
        return this.pocitadlo;
    }
    
    /**
     * 
     * @return Vracia suradnicu X v lavom hornom po tom co bola zmenena podla 
     * toho v akom uhle je nahnuta lodicka.
     */
    
    public double getTransformedHornyX() {
        return this.transformedHornyX;
    }
  
    /**
     * 
     * @return Vracia suradnicu Y v lavom hornom po tom co bola zmenena podla 
     * toho v akom uhle je nahnuta lodicka.
     */
    
    public double getTransformedHornyY() {
        return this.transformedHornyY;
    }

    /**
     * 
     * @return Vracia tvar trojuholniku.
     */
    
    public Shape getShape() {
        Shape shape = this.trojuholnik.getPol();
        return shape;
    }

    /**
     * 
     * @param transformedHornyX nastavy transformovane X na danu hodnotu.
     */
    
    public void setTransformedHornyX(double transformedHornyX) {
        this.transformedHornyX = transformedHornyX;
    }

    /**
     * 
     * @param transformedHornyY nastavy transformovane Y na danu hodnotu.
     */
    
    public void setTransformedHornyY(double transformedHornyY) {
        this.transformedHornyY = transformedHornyY;
    }

    /**
     * 
     * @return Vrati trojuholnik z ktoreho sa sklada lodicka.
     */
    
    public Trojuholnik getTrojuholnik() {
        return this.trojuholnik;
    }

    /**
     * 
     * @param lavyHornyX Nastavy suradnicu X na danu hodnotu.
     */
    
    public void setLavyHornyX(double lavyHornyX) {
        this.lavyHornyX = lavyHornyX;
    }

    /**
     * 
     * @param lavyHornyY Nastavy suradnicu Y na danu hodnotu.
     */
    
    public void setLavyHornyY(double lavyHornyY) {
        this.lavyHornyY = lavyHornyY;
    }

    /**
     * 
     * @param pocitadlo nastavy pocet stupnov naklonenia trojuholnika / 10
     */
    
    public void setPocitadlo(int pocitadlo) {
        this.pocitadlo = pocitadlo;
    }

    /**
     * 
     * @return vrati HP lodicky
     */
    
    public int getHP() {
        return this.zivoty;
    }

    /**
     * 
     * zivoty HP  nastavi HP lodicky
     */
    public void setHP(int zivoty) {
        this.zivoty = zivoty;
    }
    
}
