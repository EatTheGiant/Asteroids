package asteroidy.spravaHry;

import asteroidy.tvary.Platno;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

/**
 * Automaticky posiela spravy danym objektom:<br />
 * posunDole() - pri stlaceni klavesy DOWN<br />
 * posunHore() - pri stlaceni klavesy UP<br />
 * posunVlavo() - pri stlacen klavesy LEFT<br />
 * posunVpravo() - pri stlaceni klavesy RIGHT<br />
 * aktivuj() - pri stlaceni klavesy ENTER alebo SPACE<br />
 * zrus() - pri stlaceni klavesy ESC<br />
 * tik() - kazdych 0,25 sekundy<br />
 * vyberSuradnice(x, y) - pri kliknuti mysou
 */
public class Manazer {
    private ArrayList<Object> spravovaneObjekty;
    private long oldTick;
    private boolean hraSkoncena;
    private static final long TICK_LENGTH = 25000000;
    private static boolean hraZastavena = false;
    
    private class ManazerKlaves extends KeyAdapter {
        
        public void keyPressed(KeyEvent event) {
            if (!Manazer.this.hraSkoncena) {
                if (event.getKeyCode() == KeyEvent.VK_SPACE) {
                    Manazer.this.posliSpravu("aktivuj");
                    hraZastavena = !hraZastavena;
                }

                if (!hraZastavena) {
                    if (event.getKeyCode() == KeyEvent.VK_UP) {
                        Manazer.this.posliSpravu("chodVpred");
                    } else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
                        Manazer.this.posliSpravu("chodVzad");
                    } else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
                        Manazer.this.posliSpravu("otocDolava");
                    } else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
                        Manazer.this.posliSpravu("otocDoprava");
                    } else if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        Manazer.this.posliSpravu("zrus");
                    }
                }
            
            }
        }
    }
    
    private class ManazerCasovaca implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (!hraZastavena) {
                long newTick = System.nanoTime();
                if (newTick - Manazer.this.oldTick >= Manazer.TICK_LENGTH || newTick < Manazer.TICK_LENGTH) {
                    Manazer.this.oldTick = (newTick / Manazer.TICK_LENGTH) * Manazer.TICK_LENGTH;
                    Manazer.this.posliSpravu("tik");
                }
            }
        }
    }
    
    private class ManazerMysi extends MouseAdapter {
        public void mouseClicked(MouseEvent event) {
            if (event.getButton() == MouseEvent.BUTTON1) {
                Manazer.this.posliSpravu("vyberSuradnice", event.getX(), event.getY());
            }
        }
    }
    
    private void posliSpravu(String selektor) {
        try {
            for (Object adresat : this.spravovaneObjekty) {
                try {
                    Method sprava = adresat.getClass().getMethod(selektor);
                    sprava.invoke(adresat);
                } catch (SecurityException e) {
                    this.doNothing();
                } catch (NoSuchMethodException e) {
                    this.doNothing();
                } catch (IllegalArgumentException e) {
                    this.doNothing();
                } catch (IllegalAccessException e) {
                    this.doNothing();
                } catch (InvocationTargetException e) {
                    this.doNothing();
                } 
            }
        } catch (java.util.ConcurrentModificationException e) {
            this.doNothing();
        }
    }
    
    private void posliSpravu(String selektor, int prvyParameter, int druhyParameter) {
        for (Object adresat : this.spravovaneObjekty) {
            try {
                Method sprava = adresat.getClass().getMethod(selektor, Integer.TYPE, Integer.TYPE);
                sprava.invoke(adresat, prvyParameter, druhyParameter);
            } catch (SecurityException e) {
                this.doNothing();
            } catch (NoSuchMethodException e) {
                this.doNothing();
            } catch (IllegalArgumentException e) {
                this.doNothing();
            } catch (IllegalAccessException e) {
                this.doNothing();
            } catch (InvocationTargetException e) {
                this.doNothing();
            }
        }
    }
    
    private void doNothing() {
        
    }
    
    /**
     * Vytvori novy manazer, ktory nespravuje zatial ziadne objekty.
     */
    public Manazer() {
        this.spravovaneObjekty = new ArrayList<Object>();
        this.hraSkoncena = false;
        Platno.dajPlatno().addKeyListener(new ManazerKlaves());
        Platno.dajPlatno().addTimerListener(new ManazerCasovaca());
        Platno.dajPlatno().addMouseListener(new ManazerMysi());
    }
    
    /**
     * Manazer bude spravovat dany objekt.
     */
    public void spravujObjekt(Object objekt) {
        this.spravovaneObjekty.add(objekt);
    }
    
    public void ukonciObjekt(Object objekt) {
        this.spravovaneObjekty.remove(objekt);
    }
    
    public boolean isHraSkoncena(boolean hraSkoncena) {
        this.hraSkoncena = hraSkoncena;
        return hraSkoncena;
    }
}
