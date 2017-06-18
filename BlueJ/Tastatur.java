import ea.*;
/**
 * Write a description of class Tastatur here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tastatur implements TastenReagierbar, TastenLosgelassenReagierbar
{
    private Spieler spieler1, spieler2;
    
    public Tastatur(Spieler s1, Spieler s2){
        spieler1 = s1;
        spieler2 = s2;
    }
    
    public void reagieren(int code){
        //System.out.println(code);
        switch(code){
            case Taste.OBEN:
                //System.out.println("Spieler 2 nach vorne");
                spieler2.setGesch(1);
                break;
            case Taste.LINKS:
                //System.out.println("Spieler 2 nach links drehen");
                spieler2.setWinkelGesch(-1);
                break;
             case Taste.RECHTS:
                //System.out.println("Spieler 2 nach rechts drehen");
                spieler2.setWinkelGesch(1);
                break;
             case Taste.ENTER:
                //System.out.println("Spieler2 Schießen");
                spieler2.schießen();
                break;
             case Taste.W:
                //System.out.println("Spieler 1 nach vorne");
                spieler1.setGesch(1);
                break;
             case Taste.A:
                //System.out.println("Spieler 1 nach links drehen");
                spieler1.setWinkelGesch(-1);
                break;
             case Taste.D:
                //System.out.println("Spieler 1 nach rechts drehen");
                spieler1.setWinkelGesch(1);
                break;
             case Taste.LEERTASTE:
                //System.out.println("Spieler 1 schießen");
                spieler1.schießen();
                break;
            
        }
       
    }
    
    public void tasteLosgelassen(int code){
        //System.out.println(code);
        switch(code){
            case Taste.OBEN:
                spieler2.setGesch(-1);
                break;
            case Taste.LINKS:
                spieler2.setWinkelGesch(+1);
                break;
             case Taste.RECHTS:
                spieler2.setWinkelGesch(-1);
                break;
             case Taste.ENTER:
                //System.out.println("Spieler2 Schießen losgelassen");
                break;
             case Taste.W:
                spieler1.setGesch(-1);
                break;
             case Taste.A:
                spieler1.setWinkelGesch(+1);
                break;
             case Taste.D:
                spieler1.setWinkelGesch(-1);
                break;
             case Taste.LEERTASTE:
                //System.out.println("Spieler 1 schießen losgelassen");
                break;
            
        }
    }
}
