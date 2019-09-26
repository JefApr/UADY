package model;

public class Alumno extends Persona{
    private int matricula;
    private Licenciatura licenciatura;

    public Alumno(int matricula, String apellido1, String apellido2, String nombre, Licenciatura licenciatura) {
        this.matricula = matricula;
        this.apellido1=apellido1;
        this.apellido2= apellido2;
        this.nombre= nombre;
        this.licenciatura = licenciatura;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public Licenciatura getLicenciatura() {
        return licenciatura;
    }

    public void setLicenciatura(Licenciatura licenciatura) {
        this.licenciatura = licenciatura;
    } 

    @Override
    public String toString() {
        return this.matricula+"| "+ this.nombre +" "+ this.apellido1 +" "+ this.apellido2;
    }
    
    
}
