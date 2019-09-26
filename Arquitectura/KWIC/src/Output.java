
public class Output extends Capa {

    public Output(Capa capaSiguiente){
        super(capaSiguiente);
    }

    @Override
    public Object doIt(Object arg) {
        String impresion="";
        String[] frases= (String[]) arg;

        for (String frase : frases)
            impresion+=frase+"\n";

        return impresion;
    }
}
