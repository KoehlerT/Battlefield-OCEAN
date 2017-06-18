import ea.*;
/**
 * Write a description of class Gameloop here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gameloop implements Ticker
{
    private PowerupManager pm;
    public Gameloop(PowerupManager m){
        pm = m;
    }
    
    
    public void tick(){
        //System.out.println("Tick"); 
        Spiel.updatePhysics();
        Spiel.checkKolliders();
        pm.updatePowerups();
    }
}
