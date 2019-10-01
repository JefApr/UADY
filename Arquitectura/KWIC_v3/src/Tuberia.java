import java.util.ArrayList;

public class Tuberia implements  Observable{
    private Filtro sigFiltro;
    private ArrayList<Observer> observers;

    public Tuberia(Filtro sigFiltro) {
        observers= new ArrayList();
        this.sigFiltro = sigFiltro;
    }

    public Filtro getSigFiltro() {
        return sigFiltro;
    }

    public void sendStreamToFilter(Object stream){
        if(sigFiltro!=null)
            sigFiltro.recibirStream(stream);
        else
            notifyObservers(stream);
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
