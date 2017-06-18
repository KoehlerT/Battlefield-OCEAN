
/**
 * Write a description of class Physik here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Physik extends WECHSELBILD
{
    //Konstanten mehr oder weniger
    protected float gesch = 0; //in x/s
    private float winkelGesch = 0; //in Grad / Clockcycle
    
    
    private int bew = 0;
    private int wBew = 0;
    private float rotation = 0; //IN Grad
    private float deltaX, deltaY;
    
    
    public Physik(int x, int y, String animationsVerueichnis )
    {
        super(0,0, animationsVerueichnis);
        //super(grafikname);
        super.bewegen(x,y);
        berechneDeltasNeu();
        Spiel.physikObjektHinzufügen(this);
    }
    
        
    
    void move(){
       super.bewegen(deltaX, deltaY);
    }
    
    void rotate(){
        if (wBew == 0){return; }
        setRotation(rotation += (wBew * winkelGesch));
    }
    
    void berechneDeltasNeu(){
        deltaX =(float) ((bew * gesch) * Math.cos((double)toRad(rotation)));
        deltaY =(float) ((bew * gesch) * Math.sin((double)toRad(rotation)));
        //geschwindigkeitSetzen(new Vector(deltaX, deltaY));
    }
    
    public static float toRad(float winkel){
        return (float) (winkel * Math.PI / 180f);
    }
    
    //PUBLICS
    public void Update(){
        rotate();
        move();
        if (deltaX != 0 && deltaY != 0){
            super.wechseln();
        }
    }
    
    public void zurück(){
        bewegen(-deltaX * 2, -deltaY * 2);
    }
    
    public void addGesch(int g){//Basis wert verändern
        gesch += g;
    }
    
    public void addWGesch(int wG){ winkelGesch += wG;}
    
    public void setGesch(int s){ //In bewegung versetzen
        bew = bew + s;
        berechneDeltasNeu();
    }
    
    public void setWinkelGesch(int w){
        wBew = wBew + w;
    }
    
    
    public void setRotation(float w){
        rotation = w;
        super.setzeDrehwinkel(rotation);
        berechneDeltasNeu();
    }
    
    public int getGeschInt() {return (int)gesch;}
    public float getRotation() {return rotation;}

}






