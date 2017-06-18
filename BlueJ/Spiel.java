import ea.edu.*;
import ea.*;
import ea.internal.gui.Frage;
import java.util.*;
/**
 * Write a description of class Spiel here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Spiel extends Game
{
    // instance variables - replace the example below with your own
    static public final int fensterBreite = 1500;
    static public final int fensterHöhe = 900;
    static Spiel s;
    private Hintergrundmusik hm;
    
    private List<Physik> objekte = new ArrayList<Physik>();
    private Barriere[] ränder = new Barriere[4];
    private List<Geschoss> geschosse = new ArrayList<Geschoss>();
    private List<Kiste> kisten = new ArrayList<Kiste>();
    private Gameloop gl;
    
    private Spieler spieler1;
    private Spieler spieler2;
    
    //Konstruktor
    public Spiel()
    {
        super(1500,900,"Battlefield:OCEAN", false, false, 20,50);
        s = this;
        iconSetzen(new Bild("src\\BO_icon.png"));
        hintergrundSetzen(new Bild("src\\Background.png"));
        
        spieler1 = new Spieler(200,450, 1);
        spieler2 = new Spieler(1300,450, 2);
        spieler2.setRotation(180f);
        
        Barrieren();
        
        Tastatur t = new Tastatur(spieler1, spieler2);
        super.tastenReagierbarAnmelden(t);
        super.tastenLosgelassenReagierbarAnmelden(t);
        
        gl = new Gameloop(new PowerupManager());
        manager.anmelden(gl,50); //Spielgeschwindigkeit
        
        hm = new Hintergrundmusik("hgwav.wav");
        //hm = new Hintergrundmusik("blue.wav");
    }
    @Override
    public void tasteReagieren(int code){
        if (code == Taste.ESCAPE){
            Menu.openMenu();
            hm.stop();
            manager.abmelden(gl);
            beenden();
        }
    } //WHY
    
    private void Barrieren(){
        ränder[0] = new Barriere(0,0,-100,fensterHöhe);//Links
        ränder[1] = new Barriere(0,fensterHöhe, fensterBreite, fensterHöhe + 100); //Unten
        ränder[2] = new Barriere(fensterBreite, 0, fensterBreite + 100, fensterHöhe); //Rechts
        ränder[3] = new Barriere(0,0,fensterBreite, -100); //Oben
    }
    
    private void kollisionskontrolle(){
        //Kugel trifft Spieler
        //Kugel trifft Grenze
        //Spieler trifft Spieler
        //Spieler trifft powerup
        
        //Kugel -> Grenze
        
        for (int i = geschosse.size() - 1; i >= 0; i--){
            if (schneidetRand(geschosse.get(i))){
                removeGeschoss(geschosse.get(i));
                System.out.println("Geschosse: "+geschosse.size() + ", Objekte: "+objekte.size());
            }
        }
        
        for (int i = geschosse.size() - 1; i >= 0; i--){
            Spieler get = getroffenVon(geschosse.get(i));
            if (get != null){
                System.out.println("TREFFER!");
                boolean despawn = get.getroffen(geschosse.get(i));
                
                if (despawn)
                    removeGeschoss(geschosse.get(i));
            }
        }
        
        for (int i = kisten.size() - 1; i >= 0; i--){
            Spieler ges = schneidetRaum(kisten.get(i));
            if (ges != null){
                System.out.println("Powerup wurde gesammelt");
                Kiste k = kisten.get(i);
                k.gesammelt(ges);
                k.sichtbarSetzen(false);
                k.loeschen();
                kisten.remove(i);
            }
        }
        
        if (schneidetRand(spieler1)){
            spieler1.abprallen();
        }
        if (schneidetRand(spieler2)){
            spieler2.abprallen();
        }
        
    }
    
    private Spieler getroffenVon(Geschoss g){
        if (g.schneidet(spieler1) && g.getSchütze() != spieler1){
            return spieler1;
        }
        if (g.schneidet(spieler2) && g.getSchütze() != spieler2){
            return spieler2;
        }
        return null;
    }
    
    private Spieler schneidetRaum(Raum r){
        if (r.schneidet(spieler1)){
            return spieler1;
        }
        if (r.schneidet(spieler2)){
            return spieler2;
        }
        return null;
    }
    
    private boolean schneidetRand(Raum r){
        boolean res = false;
        for (int i = 0; i < ränder.length; i++){
            if (r.schneidet(ränder[i])){
                res = true;
            }
        }
        return res;
    }
    
    private void removeGeschoss(Geschoss rem){
        geschosse.remove(rem);
        removePhysik(rem);
    }
    private void removePhysik(Physik rem){
        rem.bewegen(2000,2000);
        rem.loeschen();
        objekte.remove(rem);
    }
    
    
    private void over(Spieler verlierer){
        hm.stop();
        s.nachrichtSchicken("Spieler "+verlierer.getNum() +" hat verloren");
        
        //new Frage(this, "Spieler "+verlierer.getNum() +" hat verloren", false, "DJB Get Digital");
        s.beenden();
        manager.abmelden(gl);
        Menu.openMenu();
    }
    //Statische Methoden
    //Hinzufügen
    public static void grafikHinzufügen(Raum r){
        if (s == null){return;}
        if (s.wurzel != null){
            s.wurzel.add(r);
        }
    }
    
    public static void physikObjektHinzufügen(Physik ph){
        if (s==null){return;}
        s.objekte.add(ph);
    }
    
    public static void geschossHinzufügen(Geschoss g){
        if (s == null)
            return;
        s.geschosse.add(g);
    }
   
    public static void kisteHinzufügen(Kiste k){
        if (s == null)
            return;
        s.kisten.add(k);
    }
    
    public static void Over(Spieler verlierer){
        if (s==null)
            return;
        s.over(verlierer);
    }

    
    
    
    //Gameloop calls
    public static void updatePhysics(){
        //WARNUNG NICHT THREAD SAVE
        if (s == null)
            return;
        for (int i=0; i<s.objekte.size(); i++){
            s.objekte.get(i).Update();
        }
    }
    
    
    public static void checkKolliders(){
        
        if (s == null)
            return;
        s.kollisionskontrolle();
        
    }
    
    
}
