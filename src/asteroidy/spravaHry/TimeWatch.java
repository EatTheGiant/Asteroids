package asteroidy.spravaHry;

import java.util.concurrent.TimeUnit;

public class TimeWatch {    
    private long starts;

    /**
     * 
     * @return Vytvori sama seba cim sa resetuje cas.
     */
    public static TimeWatch start() {
        return new TimeWatch();
    }

    /**
     * 
     * Konstruktor.
     */
    private TimeWatch() {
        this.reset();
    }

    /**
     * 
     * @return Ziska aktualny cas zo systemu.
     */
    public TimeWatch reset() {
        this.starts = System.currentTimeMillis();
        return this;
    }

    /**
     * 
     * @return Vrati rozdiel od spostenia hodiniek po cas kedy sa vykona tato 
     * metoda.
     */
    public long time() {
        long ends = System.currentTimeMillis();
        return ends - this.starts;
    }

    /**
     * 
     * @param unit zaciatok merania
     * @return vrati cas v milisekundach.
     */
    public long time(TimeUnit unit) {
        return unit.convert(this.time(), TimeUnit.MILLISECONDS);
    }
}