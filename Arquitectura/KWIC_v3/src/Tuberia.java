public class Tuberia {
    private Filtro sigFiltro;

    public Tuberia(Filtro sigFiltro) {
        this.sigFiltro = sigFiltro;
    }

    public Filtro getSigFiltro() {
        return sigFiltro;
    }

    public void setStream(Object stream){
        sendStreamToFilter(stream);

    }

    public void sendStreamToFilter(Object stream){
        sigFiltro.recibirStream(stream);
    }

}
