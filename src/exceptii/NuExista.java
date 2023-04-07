package exceptii;

public class NuExista extends RuntimeException{
    public NuExista(String mes) {
        super(mes);
    }
}
