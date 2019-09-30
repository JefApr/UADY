import java.util.Scanner;

public class Input extends Filtro {

    public Input(Tuberia tuberiaSalida){
        super(tuberiaSalida);
    }

    @Override
    protected Object doIt(Object arg) {
        return new Scanner(System.in).nextLine();
    }

}
