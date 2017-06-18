
/**
 * Beschreiben Sie hier die Klasse Geschoss.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Geschoss extends Physik
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    static public int getHeight(){return 30;}
    static public int getWidth() {return 75;}
    
    private int stärke = 0; //HP die abgezogen werden
    private Spieler Schütze;
    
    public Geschoss(int x, int y, float winkel, Spieler schütze, int st){
        super(x,y,"Schuss");
        addWGesch(0);
        addGesch(schütze.getGeschInt() + 20);
        setGesch(1);
        stärke = st;
        Schütze = schütze;
        super.setRotation(winkel);
        Spiel.geschossHinzufügen(this);
    }
    
    
    public void abgewehrt(Spieler von){
        Schütze = von;
        super.setRotation(super.getRotation() + 180f);
    }
    
    
    public int getStärke() {return stärke;}
    public Spieler getSchütze(){return Schütze;}
}
