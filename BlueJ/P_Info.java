import ea.*;
/**
 * Beschreiben Sie hier die Klasse P_Info.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class P_Info
{
    private Text[] lines = new Text[3];
    private final int textgröße = 24;
    
    public P_Info(boolean links){
       
        for (int i = 0; i< lines.length; i++){
            lines[i] = new Text(getTextForLine(i), (links)? 20 : 1500 - 20, 150 + ((textgröße+3)*i), "DJB Get Digital", textgröße, 0, "grün");
            lines[i].setAnker((links)? Text.Anker.LINKS : Text.Anker.RECHTS);
            Spiel.grafikHinzufügen(lines[i]);
        }
    }
    
    
    
    private String getTextForLine(int l){
        switch(l){
            case 0:
                return "Schüsse: ";
            case 1:
                return "Stärke: ";
            case 2:
                return "Geschwindigkeit: ";
            
        }
        return "";
    }
    
    public void updateText(int[] info){
        for (int i = 0; i < lines.length; i++){
            lines[i].setzeInhalt(getTextForLine(i) + info[i]);
        }
    }
}
