

import java.util.ArrayList;

public class Output extends Filtro {

    @Override
    public Object doIt(Object arg) {
        String impresion="";
        String[] frases= (String[]) arg;

        for (String frase : frases)
            impresion+=frase+"\n";

        return impresion;
    }


}
