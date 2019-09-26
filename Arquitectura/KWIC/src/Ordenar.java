import java.util.Arrays;

public class Ordenar extends Capa{

    public Ordenar(Capa siguienteCapa){
        super(siguienteCapa);
    }

    @Override
    public Object doIt(Object arg) {
        String[] frases= (String[]) arg;
        Arrays.sort(frases, String.CASE_INSENSITIVE_ORDER);
        return frases;
    }
}
