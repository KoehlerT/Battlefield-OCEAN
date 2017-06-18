
import ea.*;
/**
 * Beschreiben Sie hier die Klasse Hintergrundmusik.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Hintergrundmusik
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private Sound s;
    public Hintergrundmusik(String name)
    {
        s = new Sound("src\\sounds\\"+name);
        
        
        s.play();
        s.loop();
        
    }

    public void stop(){
        System.out.println("Music Stop");
        s.stop();
    }
    public void play(){
        s.play();
    }
}
