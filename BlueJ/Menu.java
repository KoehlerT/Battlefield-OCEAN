import ea.*;
import java.util.concurrent.TimeUnit;
 /* Beschreiben Sie hier die Klasse Menu.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Menu extends Game implements MausBewegungReagierbar, KlickReagierbar
{
    private static Menu self;
    static final int höhe = 900;
    static final int breite = 1500;
    
    private Bild btn_0;
    private Bild btn_1;
    private boolean aktiv = false;
    private int MouseX = breite/2, MouseY = höhe/2;
    private Hintergrundmusik hm;
    
    public Menu(){
        super(breite,höhe,"Menü", false, true, 20,50);
        iconSetzen(new Bild("src\\BO_icon.png"));
        self = this;
        hintergrundSetzen(new Bild("src\\Menu\\Logo.jpg"));
        new Ton("Startsound_KKDu.wav");
        try{
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e){}
        hintergrundSetzen(new Bild("src\\Menu\\Hintergrund_2.jpg"));
        btn_0 = new Bild("src\\Menu\\start_0.png");
        btn_0.mittelpunktSetzen(breite/2, höhe/2);
        btn_1 = new Bild("src\\Menu\\start_1.png");
        btn_1.mittelpunktSetzen(breite/2, höhe/2);
        btn_1.sichtbarSetzen(false);
        wurzel.add(btn_0);
        wurzel.add(btn_1);
            playMusic();
        mausAnmelden(new Menu_Maus(this));
    }
    
    private void starteSpiel(){
        fensterMinimieren();
        hm.stop();
        new Spiel();
    }
    private void updateBtn(boolean hover){
        if (!aktiv && hover){
            aktiv = true;
            btn_0.sichtbarSetzen(false);
            btn_1.sichtbarSetzen(true);
            new Ton("Menu_select.wav");
        }else if (aktiv && !hover){
            aktiv = false;
            btn_0.sichtbarSetzen(true);
            btn_1.sichtbarSetzen(false);
        }
    }
    private void playMusic(){
        if (hm == null){
            hm = new Hintergrundmusik("OzeanHauptmenü2.wav");
        }else {
            hm.play();
        }
    }
    
    @Override
    public void tasteReagieren(int c){
        if (c == Taste.S){
            //starteSpiel();
        }
    
    }
    @Override
    public void mausBewegt(Vektor v){
        MouseX += v.dX();
        MouseY += v.dY();
        Punkt p = new Punkt(MouseX, MouseY);
        updateBtn(btn_0.beinhaltet(p));
    }
    
    @Override
    public void klickReagieren(Punkt p){
        if (btn_0.beinhaltet(p)){
            starteSpiel();
        }
    }
    
    
    static void openMenu(){
        if (self == null)
            return;
        self.fensterMaximieren();
             self.playMusic();
        
    }
}
