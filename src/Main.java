import utile.Meniu;

public class Main {
    public static void main(String[] args) {
        Meniu meniu = Meniu.getInstance();
        meniu.show();
    }
}