package asteroidy.objekty;

import asteroidy.tvary.Platno;
import asteroidy.tvary.Obdlznik;
import java.awt.Shape;
/**
 * Trieda Laser zabezpecuje pohyb a koliziu so stenou. 
 * Otocenie laseru zabezpecuje priamo Trieda Obdlznik.
 * 
 * @author Patrik Grexa 
 * @version 12. 8. 2019
 */
public class Laser {

    private final Obdlznik obdlznik;
    private int pocitadloLode;
    private double lavyHornyX;
    private double lavyHornyY;
    /**
     * Konstruktor Laser obsahuje triedu Hra a Trojuholnik, plus atributy pre vytvorenie laseru.
     * Pri kazdej inicializacii Lasera sa v konstruktore vytvori obdlznik, ktory ho reprezentuje.
     */
    public Laser(Lodicka lodicka) {
        
        this.lavyHornyX =   lodicka.getTransformedHornyX();
        this.lavyHornyY =   lodicka.getTransformedHornyY();
        this.pocitadloLode = (int)lodicka.getPocitadlo();
        this.obdlznik = new Obdlznik();
        this.obdlznik.zmenStrany(3, 10);
        this.obdlznik.zobraz( 
            this.lavyHornyX,
            this.lavyHornyY,
            this.pocitadloLode
        );
    }
    /**
     * Kontroluje ci sa lavy horny bod laseru dostal za hraciu plochu.
     * @return Pokial sa Laser dostane za hraciu plochu metoda odosle true, inak odosiela false.
     */
    public boolean zaCiarov() {
        return this.lavyHornyX <= -10 || 
            this.lavyHornyX >= Platno.dajPlatno().getSirka() + 10 ||
            this.lavyHornyY <= -10 ||
            this.lavyHornyY >= Platno.dajPlatno().getVyska() + 10;
    }
    /**
     * Metoda posuva laser dopredu.
     * Pripocitava ku lavemu hornemu X a Y urcitu vzdialenost posuva laser v smere ktorym je natoceny.
     */
    public void vPred() {
        this.obdlznik.skry();
        this.lavyHornyX = this.lavyHornyX - Math.sqrt(200) * Math.sin(Math.toRadians(10 * this.pocitadloLode));
        this.lavyHornyY = this.lavyHornyY - Math.sqrt(200) * Math.cos(Math.toRadians(10 * this.pocitadloLode));
        this.obdlznik.zobraz(this.lavyHornyX, this.lavyHornyY, this.pocitadloLode);
    }
    /**
     * Metoda obsahuje informacie o obdlzniku x suradnica, y suradnica, vyska, sirka.
     * 
     * @return vracia tvar laseru.
     */
    public Shape getShape() {
        Shape shape = this.obdlznik.getShape();
        return shape;
    }
    /**
     * Vola metodu skry() v obdlzniku, nasledne skryje obdlzniku.
     */
    public void skry() {
        this.obdlznik.skry();
    }

}
