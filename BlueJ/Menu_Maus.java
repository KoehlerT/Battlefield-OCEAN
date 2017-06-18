import ea.*;
/**
 * Beschreiben Sie hier die Klasse Menu_Maus.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Menu_Maus extends Maus
{
    private Menu menu;
    public Menu_Maus(Menu m){
        super(0);
        menu = m;
        mausBewegungReagierbarAnmelden(m);
        klickReagierbarAnmelden(m);
    }
}
