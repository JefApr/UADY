public abstract class Capa {
    Capa _siguenteCapa;

    public Capa (Capa siguienteCapa){
        _siguenteCapa=siguienteCapa;

    }

    private boolean isSiguienteCapa() {
        return _siguenteCapa!=null;
    }

    public abstract Object doIt(Object arg);

    public Object ejecutarDoit(Object arg){
        Object resultDoitCapa= doIt(arg);
        if(isSiguienteCapa())
            return _siguenteCapa.ejecutarDoit(resultDoitCapa);
        return resultDoitCapa;
    }
}
