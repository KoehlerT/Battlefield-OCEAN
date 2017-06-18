import ea.*;
import java.util.*;
/**
 * Beschreiben Sie hier die Klasse PowerupManager.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class PowerupManager
{
    //Ist dafür 
    private int zähler = 0;
    private float wahrscheinlichkeit = 0.5f;
    private Random rnd = new Random();
    private static List<Powerup> aktiv = new ArrayList<Powerup>();
    
    public PowerupManager(){
        
    }
    
    public void updatePowerups(){
        //wird alle 50ms aufgerufen 1000ms = 1s
        zähler++;
        
        if (zähler >= 50){
            zähler = 0;//Alle 200 Frames eine action
            float random = rnd.nextFloat();
            if (random <= wahrscheinlichkeit){
                new Ton("spawn_chest.wav");
                new Kiste(rnd.nextInt(1400)+50, rnd.nextInt(800)+50);
            }
        }
        
        List<Powerup> copy = new ArrayList<Powerup>(aktiv); //Thread save zeug (Concurrent modification)
        for (Powerup p : copy){
            p.decCooldown();
        }
    }
    
    
    public static void addPowerup(Powerup p){aktiv.add(p);}
    public static void remPowerup(Powerup p){aktiv.remove(p);}
}
