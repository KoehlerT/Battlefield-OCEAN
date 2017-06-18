 import java.util.Random;
/**
 * Beschreiben Sie hier die Klasse Kiste.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Kiste extends Grafik
{
    
    private Random rnd = new Random();
    //                                                 stärke, verschn, schüsse, schild 
    private float[] wahrscheinlichkeiten = new float[] {0.25f,  0.5f,    0.8f,   1.0f};
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    public Kiste(int x, int y){
        super("Kiste_v2.png");
        mittelpunktSetzen(x, y);
        zIndex(10);
        Spiel.kisteHinzufügen(this);
    }
    
    public void gesammelt(Spieler von){
        Powerup p = new Powerup(getTyp()/*0*/, von);
        von.addPowerup(p);
    }
    
    private int getTyp(){
       float zufall = rnd.nextFloat();
      for (int i = 0; i< wahrscheinlichkeiten.length; i++) {
          if (zufall < wahrscheinlichkeiten[i]){
              return i;
            }
        }
        return wahrscheinlichkeiten.length -1;
    }
}
