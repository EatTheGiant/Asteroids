package asteroidy.tvary;

import java.awt.Shape;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
public class Trojuholnik {
    private int vyska;
    private int zakladna;
    private String farba;
    private boolean jeViditelny;

    private Shape rotatedPol;
    private double hornyX;
    private double hornyY;

    
    public Trojuholnik() {
        this.vyska = 70;
        this.zakladna = 40;
        this.farba = "green";
        this.jeViditelny = false;
        this.rotatedPol = null;
        this.hornyX = 300;
        this.hornyY = 300;
    }

    public void zobraz() {
        this.jeViditelny = true;
        this.nakresli();
    }

    public void skry() {
        this.zmaz();
        this.jeViditelny = false;
    }

    public void posunVodorovne(final int vzdialenost) {
        this.zmaz();
        this.hornyX += vzdialenost;
        this.nakresli();
    }

    public void posunZvisle(final int vzdialenost) {
        this.zmaz();
        this.hornyY += vzdialenost;
        this.nakresli();
    }

    public void zmenRozmery(final int vyska, final int zakladna) {
        this.zmaz();
        this.vyska = vyska;
        this.zakladna = zakladna;
        this.nakresli();
    }

    public void zmenFarbu(final String farba) {
        this.farba = farba;
        this.nakresli();
    }

    public void nakresli() {
        if (this.jeViditelny) {
            final Platno canvas = Platno.dajPlatno();
            final int[] xpoints = { (int)this.hornyX, (int)this.hornyX + this.zakladna / 2, (int)this.hornyX - this.zakladna / 2};
            final int[] ypoints = { (int)this.hornyY, (int)this.hornyY + this.vyska, (int)this.hornyY + this.vyska};

            canvas.draw((Object)this, this.farba, (Shape)new Polygon(xpoints, ypoints, 3));
            //canvas.wait(2);
        }
    }

    public void nakresli(double lavyHornyX, double lavyHornyY, int pocitadlo) {
        if (this.jeViditelny) {
            final Platno canvas = Platno.dajPlatno();            
            final int[] xpoints = { (int)lavyHornyX, (int)lavyHornyX - this.zakladna / 2, (int)lavyHornyX + this.zakladna / 2};
            final int[] ypoints = { (int)lavyHornyY, (int)lavyHornyY + this.vyska, (int)lavyHornyY + this.vyska};
            Polygon myPol = new Polygon(xpoints, ypoints, 3);
            AffineTransform at = AffineTransform.getRotateInstance(
                    Math.toRadians(-10 * pocitadlo), 
                    lavyHornyX, 
                    lavyHornyY + this.vyska / 2);
            this.rotatedPol = at.createTransformedShape(myPol); 

            canvas.draw((Object)this, this.farba, this.rotatedPol);
            //canvas.wait(2);
        }
    }

    private void zmaz() {
        if (this.jeViditelny) {
            final Platno canvas = Platno.dajPlatno();
            canvas.erase((Object)this);
        }
    }

    public double getHornyX() {
        return this.hornyX;
    }

    public double getHornyY() {
        return this.hornyY;
    }

    public double getVyska() {
        return this.vyska;
    }

    public double getZakladna() {
        return this.zakladna;
    }

    public int getBoundX() {
        return this.rotatedPol.getBounds().x;
    }

    public int getBoundY() {
        return this.rotatedPol.getBounds().y;
    }

    public int getBoundW() {
        return this.rotatedPol.getBounds().width;
    }

    public int getBoundH() {
        return this.rotatedPol.getBounds().height;
    }

    public Shape getPol() {
        return this.rotatedPol;
    }
}