package asteroidy;

import asteroidy.spravaHry.HraciaPlocha;
import asteroidy.spravaHry.Ovladanie;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import javax.sound.sampled.Clip;
/**
 * Trieda Hra je urcena na spravovanie ostatnych objektov, spaja ich dokopy a vytvara funkcnu hru.
 * Nachadzaju sa v nej rozne atributy a metody pre spravnu funkcnost a hratelnost.
 * 
 * @author Patrik Grexa 
 * @version 8. 12. 2019
 */
public class Hra {
    private HraciaPlocha hraciaPlocha;
    private Ovladanie ovladanie;
    
    /**
     * Prazdny konstruktor.
     */
    
    public Hra() {

    }

    /**
     * Stara sa o spustenie hry vytvara dve hlavne classy ktore su rozdelene do 
     * dalsich konkretnejsich class, nachadzaju sa tu rozne objekty.
     */
    public void spusti() {
        this.hraciaPlocha = new HraciaPlocha();
        this.ovladanie = new Ovladanie(this.hraciaPlocha);
        this.ovladanie.zapni();
        //this.playSound();
    }
    /**
     * Stara sa o hudbu v pozadi. 
     * Je obalena funkciou try catch, ktora vykona kod v try{} a pokial nastane v kode chyba spusti vetvu catch.
     * Vetva chatch vypise poznamku "Chyba pocas prehravania hudby." a nasledne zobrazi error.
     */
    
    public void playSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Battleship.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Chyba pocas prehravania hudby.");
            ex.printStackTrace();
        }
    }

    
    
}
 