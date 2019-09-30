public abstract class Filtro {
    private Tuberia tuberiaSalida;

    public Filtro(Tuberia tuberiaSalida){
        this.tuberiaSalida= tuberiaSalida;
    }

    public void recibirStream(Object stream){
        Object newStream= doIt(stream);
        sendResultToNextPipe(newStream);
    }

    protected abstract Object doIt(Object stream);

    protected void sendResultToNextPipe(Object stream){
        if(tuberiaSalida!=null){
            tuberiaSalida.setStream(stream);
        }
    }

}
