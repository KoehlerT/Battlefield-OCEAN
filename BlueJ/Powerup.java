import java.util.Random;
/**
 * Beschreiben Sie hier die Klasse Powerup.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Powerup
{
    
   private Random rnd = new Random();
   private int typ;
   
   private int cooldown = 0;
   private Spieler spieler;
   
   private int schussverstärkung = 0;
   private int verschnellerung = 0;
   private int mehrSchüsse = 0;
   private boolean schild = false;
    
   public Powerup(int typ, Spieler sp){
       spieler = sp;
       switch (typ){
           case 0:
                schussverstärkung = 5;
                System.out.println("Schüsse 5 stärker");
                cooldown = 300;
                new Ton("powerup_power.wav");
                break;
           case 1:
                verschnellerung = 5;
                System.out.println("Bewegung 10 schneller");
                cooldown = 100;
                new Ton("powerup_speed.wav");
                break;
           case 2:
                mehrSchüsse = rnd.nextInt(6)+2;//zufallszahl
                new Ton("powerup_schuss.wav");
                break;
           case 3:
                schild = true;
                cooldown = 50;
                new Ton("powerup_schield.wav");
                break;
        }
       
        PowerupManager.addPowerup(this);
   }
   
   public void decCooldown(){
       cooldown--;
       if (cooldown <= 0){
           System.out.println("powerup expired");
           spieler.entfernePowerup(this);
           PowerupManager.remPowerup(this);
        }
    }
   
   public int getSchussV(){return schussverstärkung;}
   public int getGeschV(){return verschnellerung;}
   public int getLiveB() {return mehrSchüsse; }
   public boolean getSchieldA() {return schild;}
}
