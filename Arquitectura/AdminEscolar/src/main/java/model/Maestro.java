package model;

public class Maestro extends Persona{
    private int cve_Maestro;

    public Maestro(int cve_Maestro, String apellido1, String apellido2, String nombre){
        this.cve_Maestro= cve_Maestro;
        this.apellido1= apellido1;
        this.apellido2= apellido2;
        this.nombre= nombre;
    }
    
    public int getCve_Maestro() {
        return cve_Maestro;
    }

    public void setCve_Maestro(int cve_Maestro) {
        this.cve_Maestro = cve_Maestro;
    }

    @Override
    public String toString() {
        return this.cve_Maestro+"| "+ this.nombre +" "+ this.apellido1+" "+this.apellido2; //To change body of generated methods, choose Tools | Templates.
    }
}
