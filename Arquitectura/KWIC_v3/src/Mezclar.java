import java.util.Arrays;
import java.util.LinkedList;

public class Mezclar extends Filtro {

    public Mezclar(Tuberia tuberiaSalida){
        super(tuberiaSalida);
    }

    @Override
    public Object doIt(Object arg) {

        String[] partes= (String[]) arg;
        String[] frases = new String[partes.length];
        frases[0] = String.join(" ", partes);
        LinkedList<String> listaPalabras= new LinkedList();
        listaPalabras.addAll(Arrays.asList(partes));

        for(int i=1; i<partes.length; i++){
            listaPalabras.push(listaPalabras.pollLast());
            frases[i]= String.join(" ", listaPalabras);
        }

        return frases;
    }
}