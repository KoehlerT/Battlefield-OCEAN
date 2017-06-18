import ea.*;
import java.util.*;
/**
 * Beschreiben Sie hier die Klasse Spieler.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Spieler extends Physik
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    static int höhe = 91;
    static int breite = 64;
    static int maxHealth = 100; //Readonly evtl
    //static int cooldownMax = 100;
    
    private int leben = maxHealth; //Hp
    private int schussstärke = 10;
    private int schüsse = 20;
    private int schilde = 0;
    //private int cooldown = cooldownMax; //Schusscooldown
    private int num;
    Anzeige anz;
    //List<Powerup> powerups = new ArrayList<Powerup>();
    
    public Spieler(int x, int y, int n){
        super(x, y, (n == 1)? "Fisch_blau": "Fisch_orange");
        //super("Test.png",x,y);
        addGesch(15);//Geschwindigkeit setzen
        addWGesch(10);//Winkelgeschwindigkeit setzen
        num = n;
        anz = new Anzeige(num);
        updateAnz();
    }
    
    private void updateAnz(){
        anz.setzeInfo(new int[] {schüsse, schussstärke, super.getGeschInt()});
        setSchield((schilde > 0)? true: false);
    }
    
    public void schießen(){
        //Mittelpunkt
        if (schüsse <= 0){
            return; //Keine Schüsse
        }
        Punkt m  = mittelPunkt();
        int x = breite/2 + Geschoss.getWidth()/2;
        int y = höhe/2 + Geschoss.getHeight() / 2;
        x = (int)(x* Math.cos(Physik.toRad((float)getRotation())));
        y = (int)(y* Math.sin(Physik.toRad((float)getRotation())));
        schüsse --;
        updateAnz();
        new Ton("shot.wav");
        new Geschoss(m.x()+x, m.y()+y, getRotation(), this, schussstärke);
    }
    
    public boolean getroffen(Geschoss g){
        if (schilde > 0){
            //schild aktiv
            g.abgewehrt(this);
            return false;
        }
        
        leben -= g.getStärke();
        anz.lebenSetzen(leben);
        if (leben <= 0){
            new Ton("Todesstoß.wav");
            Spiel.Over(this);
            sichtbarSetzen(false);
        }else {
            new Ton("hit.wav");
        }
        return true;
    }
    
    public void abprallen(){
        super.zurück();
        super.setRotation(super.getRotation()  + 60f);
    }
    
    
    //Powerup code
    /*
       Beim Hinzufügen eines Powerups werden die Werte verbessert.
       Beim Entfernen eines Powerups werden die Verbesserungen wirder rückgängig gemacht
       
       Der Quelltext ist etwas seltsam, jedoch finde ich keine Elegantere möglichkeit
       */
    public void addPowerup(Powerup p){
        schussstärke += p.getSchussV();
        super.addGesch(p.getGeschV());
        schüsse += p.getLiveB();
        schilde += (p.getSchieldA() == true)? 1 : 0;
        updateAnz();
    }
    
    
    public void entfernePowerup(Powerup p){
        schussstärke -= p.getSchussV();
        super.addGesch(-p.getGeschV());
        schilde -= (p.getSchieldA() == true)? 1 : 0;
        updateAnz();
    };
    
    public int getNum(){return num;}

}






