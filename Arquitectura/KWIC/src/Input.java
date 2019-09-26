import java.util.Scanner;

public class Input extends Capa {

    public Input(Capa siguienteCapa){
        super(siguienteCapa);
    }

    @Override
    public Object doIt(Object arg) {
        return new Scanner(System.in).nextLine();
    }
    public static void main(String[] args){

        Input input= new Input(new Partir( new Mezclar( new Ordenar( new Output(null)))));
        System.out.println(input.ejecutarDoit(null));
    }
}
