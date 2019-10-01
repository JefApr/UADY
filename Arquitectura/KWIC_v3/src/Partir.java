public class Partir extends Filtro {

    @Override
    public Object doIt(Object arg) {
        String frase= (String) arg;
        return frase.split(" ");
    }
}
