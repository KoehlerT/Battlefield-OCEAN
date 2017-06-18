import ea.*;
public class Anzeige{
    static final int max = 7;
    
    private int num;
    private AnzeigenElement[] elemente = new AnzeigenElement[max+1];
    private AnzeigenElement sichtbar = null;
    
    private P_Info info;
    
    public Anzeige(int n){
        num = n;
        for (int i = 0; i< elemente.length; i++){
            System.out.println("new image: "+"Anzeige\\p"+n+"_"+i+".png");
            elemente[i] = new AnzeigenElement(n, i);
        }
        setzeSichtbar(max);
        info = new P_Info((n == 1));
        
    }
    
    private void setzeSichtbar(int ind){
        if (ind < 0 || ind > max)
            return;
        if (sichtbar != null){
            sichtbar.sichtbarSetzen(false);
        }
        sichtbar = elemente[ind];
        sichtbar.sichtbarSetzen(true);
        
    }
    
    public void lebenSetzen(int leben){
        float abstand = Spieler.maxHealth/ max;
        float status = leben / abstand;
        System.out.println("lebencalc: abs: "+abstand+", st: "+status + ", ind: "+(int)status);
        setzeSichtbar((int) status);
    }
    
    public void setzeInfo(int[] infos){
        info.updateText(infos);
    }
    
}