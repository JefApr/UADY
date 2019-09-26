package model;

public class Asignatura {
    private int cve_Asig;
    private String nombre;
    private Licenciatura licenciatura;
    private Maestro maestro;

    public Asignatura(int cve_Asig, String nombre, Licenciatura licenciatura) {
        this.cve_Asig = cve_Asig;
        this.nombre = nombre;
        this.licenciatura = licenciatura;
    }
    
    public int getCve_Asig() {
        return cve_Asig;
    }

    public void setCve_Asig(int cve_Asig) {
        this.cve_Asig = cve_Asig;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Licenciatura getLicenciatura() {
        return licenciatura;
    }

    public void setLicenciatura(Licenciatura licenciatura) {
        this.licenciatura = licenciatura;
    }
    
    public Maestro getMaestro() {
        return maestro;
    }

    public void setMaestro(Maestro maestro) {
        this.maestro = maestro;
    }   

    @Override
    public String toString() {
        String name=this.cve_Asig+"| "+ this.nombre+" "+this.licenciatura;
        if(maestro!=null){
            name+=" - "+this.maestro.nombre+" "+this.maestro.apellido1+" "+this.maestro.apellido2;
        }
        return name; 
    }
    
    
    
}
