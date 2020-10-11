package asteroidy.objekty.meteoridy;
/**
 * Trieda MeteoridMaly riesi pohyb a koliziu so stenou pre maly meteorid.
 * Velkost a farba meteoritu sa tu da menit tiez.
 * 
 * @author Patrik Grexa 
 * @version 4.5.2020
 */
public class MeteoridMaly extends Meteorid {
    
    
    
    /**
     * Konstruktor Meteorid obsahuje triedu Kruh, plus atributy pre vytvorenie meteoridu.
     * Pri kazdej inicializacii Meteoridu sa v konstruktore vytvori kruh, ktory ho reprezentuje.
     * Taktiez sa tu meni priemer a farba.
     */
    public MeteoridMaly() {
        super(2, 15, "red");
    }
    
}
