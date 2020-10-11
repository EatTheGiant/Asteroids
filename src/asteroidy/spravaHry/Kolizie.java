package asteroidy.spravaHry;

import asteroidy.objekty.Laser;
import asteroidy.tvary.Platno;
import asteroidy.objekty.Lodicka;
import asteroidy.objekty.meteoridy.Meteorid;
import java.awt.Shape;
import java.awt.geom.Area;

/**
 *
 * @author Basic
 */
public class Kolizie {
    private KdeTrci kdeTrci;
    private Lodicka lodicka;  
    private int pocitadlo;
    private final double vyska;
    private double lavyHornyX;
    private double lavyHornyY;
    private final int sirkaPlatna;
    private final int vyskaPlatna;
    private double transformedHornyX;
    private double transformedHornyY;
    
    public Kolizie(Lodicka lodicka) {
        this.kdeTrci = KdeTrci.NIKDE;
        this.lodicka = lodicka;
        this.pocitadlo = this.lodicka.getPocitadlo();
        this.vyska = this.lodicka.getVyska();
        this.transformedHornyX = this.lodicka.getTransformedHornyX();
        this.transformedHornyY = this.lodicka.getTransformedHornyY();
        this.sirkaPlatna = Platno.dajPlatno().getSirka();
        this.vyskaPlatna = Platno.dajPlatno().getVyska();
        this.lavyHornyX = this.lodicka.getLavyHornyX();
        this.lavyHornyY = this.lodicka.getLavyHornyY();
    }
    
    /**
     * Zistuje kolizie medzi roznymi predmetmy (tvarmy).Metode odoslete 2 krat Shape a ona porovna ci sa nejakym sposobom dotykaju alebo ci nie su nahodou v sebe.
     *
     * @param shapePrvy Prvy z tvarov u ktorych sa zistuje ci sa dva objekti stretli.
     * @param shapeDruhy Druhy z tvarov u ktorych sa zistuje ci sa dva objekti stretli.
     * @return vracia true pokial nastala kolizua medzi tvarmi.
     */
    
    public boolean kolizia(Shape shapePrvy, Shape shapeDruhy) {
        Area areaA = new Area(shapePrvy);
        Area areaB = new Area(shapeDruhy);
        areaA.intersect(areaB);
        return !areaA.isEmpty();
    }
    
    /**
     * 
     * Pre tuto metodu plati, "ak to funguje nedotykaj sa toho", metoda 
     * sposobuje bolest mne, aj kazdemu kto sa na nu pozera;
     */
    
    public void zaCiarov() {

        if (this.transformedHornyX <= 0) {
            this.kdeTrci = KdeTrci.VLAVOPREDKOM;
        } else if (  
            this.lavyHornyX - this.vyska / 2 <= 0 && this.pocitadlo >= 18 && this.pocitadlo <= 35) {
            this.kdeTrci = KdeTrci.VLAVOZADKOM; 
        } else if (this.lavyHornyX + this.vyska / 2 >= this.sirkaPlatna && this.pocitadlo >= 17 && this.pocitadlo <= 35) {
            this.kdeTrci = KdeTrci.VPRAVOPREDKOM;
        } else if (this.transformedHornyX + this.vyska >= this.sirkaPlatna && this.pocitadlo >= 1 && this.pocitadlo <= 17) {
            this.kdeTrci = KdeTrci.VPRAVOZADKOM;
        } else if (this.lavyHornyY <= 0 && ( (this.pocitadlo >= -1 && this.pocitadlo <= 8) || (this.pocitadlo >= 28 && this.pocitadlo <= 35))) {
            this.kdeTrci = KdeTrci.HOREPREDKOM;
        } else if (this.transformedHornyY - this.vyska <= 0 && (this.pocitadlo >= 17 && this.pocitadlo <= 19)) {
            this.kdeTrci = KdeTrci.HOREZADKOM;
        } else if (this.transformedHornyY >= this.vyskaPlatna && (this.pocitadlo >= 10 && this.pocitadlo <= 26)) {
            this.kdeTrci = KdeTrci.DOLEPREDKOM;
        } else if (this.lavyHornyY + this.vyska - 5 >= this.vyskaPlatna && ( (this.pocitadlo >= 0 && this.pocitadlo <= 8) || (this.pocitadlo >= 28 && this.pocitadlo <= 35))) {
            this.kdeTrci = KdeTrci.DOLEZADKOM;
        } else {
            this.kdeTrci = KdeTrci.NIKDE;
        }
    }
    
    /**
     * Zistuje ci sa lodicka moze hybat vzad.
     * @return Ak sa moze hybat vrati true
     */
    
    public boolean mozeVzad() {
        this.zaCiarov();
        return  this.kdeTrci == KdeTrci.NIKDE ||
                this.kdeTrci == KdeTrci.DOLEPREDKOM ||
                this.kdeTrci == KdeTrci.VPRAVOPREDKOM ||
                this.kdeTrci == KdeTrci.HOREPREDKOM ||
                this.kdeTrci == KdeTrci.VLAVOPREDKOM;
         
    }
    
    /**
     * Zistuje ci sa lodicka moze hybat vpred.
     * @return Ak sa moze hybat return true
     */
    
    public boolean mozeVpred() {
        this.zaCiarov();
        return  this.kdeTrci == KdeTrci.NIKDE ||
                this.kdeTrci == KdeTrci.DOLEZADKOM ||
                this.kdeTrci == KdeTrci.VPRAVOZADKOM ||
                this.kdeTrci == KdeTrci.HOREZADKOM ||
                this.kdeTrci == KdeTrci.VLAVOZADKOM;
    }
    
    /**
     * Zistuje ci sa lodicka moze hybat vpravo.
     * @return Ak sa moze hybat return true.
     */
    
    public boolean mozeVpravo() {
        this.zaCiarov();
        return this.kdeTrci == KdeTrci.NIKDE;
    }
    
    /**
     * Zistuje ci sa lodicka moze hybat vlavo.
     * @return Ak sa moze hybat return true.
     */
    
    public boolean mozeVlavo() {
        this.zaCiarov();
        return this.kdeTrci == KdeTrci.NIKDE;
    }
    
    
    /**
     * Metoda zistuje ci sa nastala kolizia medzi laserom a meteoridom.
     * 
     * @param laser Prvy z tvarov u ktorych sa zistuje ci sa dva objekti stretli.
     * @param meteorid Druhy z tvarov u ktorych sa zistuje ci sa dva objekti stretli.
     * @return Pokial stret nastal vrati true.
     */
    public boolean zrazkaMeteoridLaser(Laser laser, Meteorid meteorid) {
        
        Shape shapeLaser = laser.getShape();
        Shape shapeMeteorid = meteorid.getShape();
    
        return this.kolizia(shapeLaser, shapeMeteorid);
    }
    
    /**
     * Metoda zistuje ci sa nastala kolizia medzi lodickou a meteoridom.
     * 
     * @param lodicka Prvy z tvarov u ktorych sa zistuje ci sa dva objekti stretli.
     * @param meteorid Druhy z tvarov u ktorych sa zistuje ci sa dva objekti stretli.
     * @return Pokial stret nastal vrati true.
     */
    
    public boolean zrazkaMeteoridLodicka(Lodicka lodicka, Meteorid meteorid) {
        Shape shapeLodicka = lodicka.getShape();
        Shape shapeMeteorid = meteorid.getShape();   
        return this.kolizia(shapeLodicka, shapeMeteorid);

    }
}
