import ea.*;
/**
 * Beschreiben Sie hier die Klasse Barriere.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Barriere extends Rechteck
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    public Barriere(int x1, int y1, int x2, int y2){
        super(x1, y1, x2-x1, y2-y1);
        sichtbarSetzen(false);
        //Spiel.grafikHinzufügen(this);
    }
}
