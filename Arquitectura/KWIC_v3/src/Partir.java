public class Partir extends Filtro {


    public Partir(Tuberia tuberiaSalida){
        super(tuberiaSalida);
    }

    @Override
    public Object doIt(Object arg) {
        String frase= (String) arg;
        return frase.split(" ");
    }
}
