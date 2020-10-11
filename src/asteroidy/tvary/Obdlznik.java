package asteroidy.tvary;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

/**
 * Obdĺžnik, s ktorým možno pohybovať a nakreslí sa na plátno.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0  (15 July 2000)
 */

public class Obdlznik {
    private int stranaA;
    private int stranaB;
    private int lavyHornyX;
    private int lavyHornyY;
    private String farba;
    private boolean jeViditelny;
    private Shape rotatedRect;

    /**
     * Vytvor nový obdĺžnik preddefinovanej farby na preddefinovanej pozícii.
     */
    public Obdlznik() {
        this.stranaA = 30;
        this.stranaB = 60;
        this.lavyHornyX = 60;
        this.lavyHornyY = 50;
        this.farba = "red";
        this.jeViditelny = false;
        this.rotatedRect = null;
    }
    
    public Obdlznik(int stranaA, int stranaB, int lavyHornyX, int lavyHornyY, String farba) {
        this.stranaA = stranaA;
        this.stranaB = stranaB;
        this.lavyHornyX = lavyHornyX;
        this.lavyHornyY = lavyHornyY;
        this.farba = farba;
        this.jeViditelny = false;
        this.rotatedRect = null;
    }

    /**
     * (Obdĺžnik) Zobraz sa.
     */
    public void zobraz() {
        this.jeViditelny = true;
        this.nakresli();
    }
    
    public void zobraz(double lavyHXLode, double lavyHYLode, int pocitadlo) {
        this.jeViditelny = true;
        this.nakresli(lavyHXLode, lavyHYLode, pocitadlo);
    }

    /**
     * (Obdĺžnik) Skry sa.
     */
    public void skry() {
        this.zmaz();
        this.jeViditelny = false;
    }

    /**
     * (Obdĺžnik) Posuň sa vodorovne o dĺžku danú parametrom.
     */
    public void posunVodorovne(int vzdialenost) {
        this.zmaz();
        this.lavyHornyX += vzdialenost;
        this.nakresli();
    }

    /**
     * (Obdĺžnik) Posuň sa zvisle o dĺžku danú parametrom.
     */
    public void posunZvisle(int vzdialenost) {
        this.zmaz();
        this.lavyHornyY += vzdialenost;
        this.nakresli();
    }

    /**
     * (Obdĺžnik) Zmeň dĺžky strán na hodnoty dané parametrami.
     * Dĺžka strany musí byť nezáporné celé číslo. 
     */
    public void zmenStrany(int stranaA, int stranaB) {
        this.zmaz();
        this.stranaA = stranaA;
        this.stranaB = stranaB;
        this.nakresli();
    }

    /**
     * (Obdĺžnik) Zmeň farbu na hodnotu danú parametrom.
     * Nazov farby musí byť po anglicky.
     * Možné farby sú tieto:
     * červená - "red"
     * žltá    - "yellow"
     * modrá   - "blue"
     * zelená  - "green"
     * fialová - "magenta"
     * čierna  - "black"
     * biela   - "white"
     * hnedá   - "brown"
     */
    public void zmenFarbu(String farba) {
        this.farba = farba;
        this.nakresli();
    }

    /*
     * Draw the square with current specifications on screen.
     */
    private void nakresli() {
        if (this.jeViditelny) {
            Platno canvas = Platno.dajPlatno();
            canvas.draw(this, this.farba,
                new Rectangle(this.lavyHornyX, this.lavyHornyY, this.stranaA, this.stranaB));
            //canvas.wait(2);
        }
    }
    

    private void nakresli(double lavyHXLode, double lavyHYLode, int pocitadlo) {
        if (this.jeViditelny) {
            double lavyDXLaseru = lavyHXLode - this.stranaA / 2;
            double lavyDYLaseru = lavyHYLode;
            
            final Platno canvas = Platno.dajPlatno();
            Rectangle myRect = new Rectangle((int)lavyDXLaseru, (int)lavyDYLaseru, this.stranaA, this.stranaB);
            AffineTransform at = AffineTransform.getRotateInstance(
                Math.toRadians(-10 * pocitadlo), 
                lavyHXLode, 
                lavyHYLode);
            this.rotatedRect = at.createTransformedShape(myRect);
            
            canvas.draw((Object)this, this.farba, this.rotatedRect);
            //canvas.wait(1);
        }
    }

    /*
     * Erase the square on screen.
     */
    private void zmaz() {
        if (this.jeViditelny) {
            Platno canvas = Platno.dajPlatno();
            canvas.erase(this);
        }
    }

    public double getStranaA() {
        return this.stranaA;
    }

    public double getStranaB() {
        return this.stranaB;
    }
    
    public Shape getShape() {
        return this.rotatedRect;
    }
}
