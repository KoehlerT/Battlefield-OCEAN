public class AnzeigenElement extends Grafik{
    
    private int player, status;
    
    public AnzeigenElement(int _player, int _status){
        super("Anzeige\\p"+_player+"_"+_status+".png");
        player = _player;
        status = _status;
        zIndex(100);
        sichtbarSetzen(false);
    }
    
    
}
