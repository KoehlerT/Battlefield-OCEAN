import ea.*;

public class Ton
{

    public Ton(String name)
    {
        Sound s = new Sound("src\\sounds\\"+name);
        s.play();
    }
}
