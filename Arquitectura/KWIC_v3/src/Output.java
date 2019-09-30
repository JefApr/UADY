

import java.util.ArrayList;

public class Output extends Filtro implements Observable {

    private ArrayList<Observer> observers;

    public Output(Tuberia tuberiaSalida){
        super(tuberiaSalida);
        observers= new ArrayList();
    }

    @Override
    public Object doIt(Object arg) {
        String impresion="";
        String[] frases= (String[]) arg;

        for (String frase : frases)
            impresion+=frase+"\n";

        notifyObservers(impresion);
        return impresion;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(Object arg) {
        for(Observer observer : observers) {
            observer.update(arg);
        }
    }
}
