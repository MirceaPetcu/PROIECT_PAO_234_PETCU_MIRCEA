package exceptii;

public class NuExistaUtilizatori extends RuntimeException{
    public NuExistaUtilizatori(String mes)
    {
        super(mes);
    }
}
