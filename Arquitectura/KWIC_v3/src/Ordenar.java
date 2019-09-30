import java.util.Arrays;

public class Ordenar extends Filtro{

    public Ordenar(Tuberia tuberiaSalida){
        super(tuberiaSalida);
    }

    @Override
    public Object doIt(Object arg) {
        String[] frases= (String[]) arg;
        Arrays.sort(frases, String.CASE_INSENSITIVE_ORDER);
        return frases;
    }
}
