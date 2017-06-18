import ea.*;
/**
 * Write a description of class Grafik here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Grafik extends Bild
{
 
    
    public Grafik(String name)
    {
        super("src\\"+name);
        Spiel.grafikHinzufügen(this);
    }
    
   

}
