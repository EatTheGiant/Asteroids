package asteroidy.spravaHry;
    
import asteroidy.challenges.ChallengesZniceneMeteority;
import asteroidy.challenges.SpravaChallenges;
import asteroidy.objekty.Lodicka;
import asteroidy.spravaHry.HraciaPlocha;
import java.util.Scanner;

public class Ovladanie {

    private final Manazer manazer;
    private HraciaPlocha hraciaPlocha;
    private final SpravaChallenges spravaChallenges;
    private final Kolizie kolizie;
    private final Lodicka lodicka;
    private final double polomerVrcholu;
    private int pocitadlo;
    private boolean prerusHru;
    private double vzVrchStaryNovy;
    private double transformedHornyX;
    private double transformedHornyY;
    private double lavyHornyX;
    private double lavyHornyY;
    

    /**
     * 
     * Ma na starosti pracu s objektami a ovladanie Lodicky.
     * @param hraciaPlocha Je potrebna k
     */
    
    public Ovladanie(HraciaPlocha hraciaPlocha) {
        this.manazer = new Manazer();
        this.hraciaPlocha = hraciaPlocha;
        this.spravaChallenges = new SpravaChallenges();
        this.lodicka = this.hraciaPlocha.getLodicka();
        this.pocitadlo = this.lodicka.getPocitadlo();
        this.prerusHru = false;
        
        this.kolizie = new Kolizie(this.lodicka);
        this.polomerVrcholu = this.lodicka.getVyska() / 2;
        this.vzVrchStaryNovy = this.zistiStrCosV(this.polomerVrcholu, this.polomerVrcholu, 10 * this.pocitadlo);
        this.transformedHornyX = this.lodicka.getTransformedHornyX();
        this.transformedHornyY = this.lodicka.getTransformedHornyY();
        this.lavyHornyX = this.lodicka.getLavyHornyX();
        this.lavyHornyY = this.lodicka.getLavyHornyY();
        
        
    }
    
    /**
     * 
     * Tuto triedu spravuje manazer takze sa tu nachcadza tik, ktory kazdych 0.25 sekundy vykona tuto metodu.
     */
    public void tik() {
        //this.hraciaPlocha.tik();
        if (true) {
            if (!this.prerusHru) {
                this.hraciaPlocha.spravaLaserov(this.spravaChallenges);
                this.hraciaPlocha.spravaMeteoridov();
                this.kolizie.zaCiarov();

                if (this.spravaChallenges.splnenyChallengeZnicMeteority()) {
                    this.hraciaPlocha.pridajHP();
                }
                
                if (this.spravaChallenges.splnenyChallengeZnicVelkeMeteority()) {
                    this.hraciaPlocha.pridajHP();
                    this.hraciaPlocha.setPocitadloTikov(0);
                    
                    this.hraciaPlocha.setMozeStrielat(false); 
                }
                
                if (this.spravaChallenges.splnenyChallengeVydrzMinutu()) {
                    
                    this.hraciaPlocha.setMozeStrielat(true);
                    this.hraciaPlocha.setScore(this.hraciaPlocha.getScore() + 100);
                }

                if (!this.hraSpustena()) {
                    this.koniecHry();
                }
            }
        }
    }
    
    /**
     * 
     * Stara sa o opakovanie, pripadne ukoncenie hry.
     */

    public void koniecHry() {
        
        this.manazer.isHraSkoncena(true);
        Scanner sc = new Scanner(System.in);
        System.out.println("Chces hrat znova?");
        String potvrdenie = sc.next();
        if (potvrdenie.equals("ano")) {
            this.hraciaPlocha.setScore(0);
            this.hraciaPlocha.pridajHP();
            this.hraciaPlocha.pridajHP();
            this.hraciaPlocha.pridajHP();
            this.manazer.isHraSkoncena(false);
            this.hraciaPlocha.setMozeStrielat(true);
            ((ChallengesZniceneMeteority)this.spravaChallenges.vratChallenge("ZnicVelkeMeteority")).setZnicenychMeteoritov(0);
            ((ChallengesZniceneMeteority)this.spravaChallenges.vratChallenge("ZnicMeteority")).setZnicenychMeteoritov(0);
            this.spravaChallenges.opakujChallenges();
        } else if (potvrdenie.equals("ukonci")) {
            System.out.println("Tak sa teda maj.");
            System.exit(0);
        } else {
            System.out.println("Tomuto prikazu nerozumiem. :(");
        }
                    
    }
    
    /**
     * 
     * Metoda zastavuje alebo spusta hru. Je potrebne stisknut medzernik.
     */
    private void aktivuj() {
        this.prerusHru = !this.prerusHru;
    }
    
    /**
     * 
     * Metoda spusta manazera, cize vdaka nej funguje pohyb vsetkych objektov
     * po mape.
     */
    public void zapni() {
        this.manazer.spravujObjekt(this);
        this.spravaChallenges.zacniChallenges();
    }
    
    /**
     * 
     * @return Vracia true pokial ma lodicka nejake zivoty.
     */
    private boolean hraSpustena() {
        return !(this.hraciaPlocha.getLodicka().getHP() == 0);
    }
    
    /**
     * Otaca lodicku dolava.
     */

    public void otocDolava() {
        if (this.kolizie.mozeVlavo()) {
            ++this.pocitadlo;
            if (this.pocitadlo > 36) {
                this.pocitadlo = 1;
            }
            
            this.vzVrchStaryNovy = this.zistiStrCosV(this.polomerVrcholu, this.polomerVrcholu, 10 * this.pocitadlo);
            
            this.lodicka.setTransformedHornyX(this.transformedX('-')); //zaporne znamienko
            this.lodicka.setTransformedHornyY(this.transformedY('+')); //kladne znamienko
            
            this.lodicka.setPocitadlo(this.pocitadlo);
            this.lodicka.getTrojuholnik().nakresli(this.lavyHornyX, this.lavyHornyY, this.pocitadlo);
        }
    }

    /**
     * Otaca lodicku doprava.
     */
    
    public void otocDoprava() {
        if (this.kolizie.mozeVpravo()) {
            --this.pocitadlo;
            if (this.pocitadlo < 1) {
                this.pocitadlo = 36;
            }

            this.vzVrchStaryNovy = this.zistiStrCosV(this.polomerVrcholu, this.polomerVrcholu, 10 * this.pocitadlo);
            
            this.lodicka.setTransformedHornyX(this.transformedX('-')); //zaporne znamienko
            this.lodicka.setTransformedHornyY(this.transformedY('+')); //kladne znamienk
             
            this.lodicka.setPocitadlo(this.pocitadlo);
            this.lodicka.getTrojuholnik().nakresli(this.lavyHornyX, this.lavyHornyY, this.pocitadlo);
        }
    }
    
    /**
     * Posuva lodicku vpred.
     */
    
    public void chodVpred() {
        if ( this.kolizie.mozeVpred()) {
            
            this.lodicka.setLavyHornyX(this.lavyHornyX('-')); //zaporne znamienko
            this.lodicka.setLavyHornyY(this.lavyHornyY('-')); //zaporne znamienko
            
            this.lodicka.setTransformedHornyX(this.transformedX('P')); //Vpred X
            this.lodicka.setTransformedHornyY(this.transformedY('P')); //Vpred Y

            this.lodicka.setPocitadlo(this.pocitadlo);
            this.lodicka.getTrojuholnik().nakresli(this.lavyHornyX, this.lavyHornyY, this.pocitadlo);
        }
    }
    
    /**
     * Posuva lodicku vzad.
     */
    public void chodVzad () {
        if (this.kolizie.mozeVzad()) {
            
            this.lodicka.setLavyHornyX(this.lavyHornyX('+')); //kladne znamienk
            this.lodicka.setLavyHornyY(this.lavyHornyY('+')); //kladne znamienk
             
            this.lodicka.setTransformedHornyX(this.transformedX('Z')); //Vzad X
            this.lodicka.setTransformedHornyY(this.transformedY('Z')); //Vzad Y
            
            this.lodicka.setPocitadlo(this.pocitadlo);
            this.lodicka.getTrojuholnik().nakresli(this.lavyHornyX, this.lavyHornyY, this.pocitadlo);
        }
    }
    
    /**
     * Metoda pocita preponu pravouhleho trojuholnika.
     * 
     * @return Vracia stranu c v pravouhlom trojuholniku.
     */
    
    public double zistiPreponu(final double a, final double b) {
        return Math.sqrt(Math.pow(a, 2.0) + Math.pow(b, 2.0));
    }
    
    /**
     * Metoda vypocita tretiu stranu lubovolneho trojuholnika pomocou Kosinusovej vety.
     * 
     * @return Vracia stranu c podla kosinusovej vety. Inputy strana A, strana B, uhol medzi nimi
     */
    private double zistiStrCosV(final double stranaA, final double stranaB, final double pocetStupnov) {
        return 
            Math.sqrt(   
                Math.pow(stranaA, 2.0) + Math.pow(stranaB, 2.0) - 2.0 * stranaA * stranaB * Math.cos(Math.toRadians(pocetStupnov))
            );
    }
    
    /**
     * 
     * @return vracia stupen v akom je lodicka otocena / 10.
     */
    
    public double getPocitadlo() {
        return this.pocitadlo;
    }
    
    /**
     * Transformuje suradnicu X lodicky a otaca lodicku.
     * @param znak '-' Urcene pre otacanie do prava a do lava.
     * @param znak 'P' Urcene pre pohyb vpred.
     * @param znak 'Z' Urcene pre pohyb vzad.
     * @return Vracia transformovanu suradnicu X
     */
    
    public double transformedX(char znak) {
        switch (znak) {
            case '-':
                this.transformedHornyX = this.lavyHornyX - this.vzVrchStaryNovy * Math.cos(Math.toRadians(5 * this.pocitadlo));
                break;
            case 'P':
                this.transformedHornyX = this.transformedHornyX - Math.sqrt(50) * Math.sin(Math.toRadians(10 * this.pocitadlo));
                break;
            case 'Z':
                this.transformedHornyX = this.transformedHornyX + Math.sqrt(50) * Math.sin(Math.toRadians(10 * this.pocitadlo));
                break;
            default:
                break;
        }
        return this.transformedHornyX;
    }
    
    /**
     * Transformuje suradnicu Y lodicky a otaca lodicku.
     * @param znak '+' Urcene pre otacanie do prava a do lava.
     * @param znak 'P' Urcene pre pohyb vpred.
     * @param znak 'Z' Urcene pre pohyb vzad.
     * @return Vracia transformovanu suradnicu Y
     */
    
    public double transformedY(char znak) {
        switch (znak) {
            case '+':
                this.transformedHornyY = this.lavyHornyY + this.vzVrchStaryNovy * Math.sin(Math.toRadians(5 * this.pocitadlo));
                break;
            case 'P':
                this.transformedHornyY = this.transformedHornyY - Math.sqrt(50) * Math.cos(Math.toRadians(10 * this.pocitadlo));
                break;
            case 'Z':
                this.transformedHornyY = this.transformedHornyY + Math.sqrt(50) * Math.cos(Math.toRadians(10 * this.pocitadlo));
                break;
            default:
                break;
        }
        return this.transformedHornyY;
    }
    
    /**
     * Nastavi suradnicu X trojuholniku.
     * @param znak '-' pokial chceme mat vo vzorci zaporne znamienko.
     * @param znak '+' pokial chceme mat vo vzorci kladne znamienko.
     * @return vrati suradnicu X.
     */
    
    public double lavyHornyX(char znak) {
        if (znak == '-') {
            this.lavyHornyX = this.lavyHornyX - Math.sqrt(50) * Math.sin(Math.toRadians(10 * this.pocitadlo));
        } else if (znak == '+') {
            this.lavyHornyX = this.lavyHornyX + Math.sqrt(50) * Math.sin(Math.toRadians(10 * this.pocitadlo));
        }
        return this.lavyHornyX;
    }
    
    /**
     * Nastavi suradnicu Y trojuholniku.
     * @param znak '-' pokial chceme mat vo vzorci zaporne znamienko.
     * @param znak '+' pokial chceme mat vo vzorci kladne znamienko.
     * @return vrati suradnicu Y.
     */
    
    public double lavyHornyY(char znak) {
        if (znak == '-') {
            this.lavyHornyY = this.lavyHornyY - Math.sqrt(50) * Math.cos(Math.toRadians(10 * this.pocitadlo));
        } else if (znak == '+') {
            this.lavyHornyY = this.lavyHornyY + Math.sqrt(50) * Math.cos(Math.toRadians(10 * this.pocitadlo));
        }
        return this.lavyHornyY;
    }
}