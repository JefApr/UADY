public class Partir extends Capa {


    public Partir(Capa siguienteCapa){
        super(siguienteCapa);
    }

    @Override
    public Object doIt(Object arg) {
        String frase= (String) arg;
        return frase.split(" ");
    }
}
