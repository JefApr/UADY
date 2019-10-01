import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Cliente implements Observer {
    private Tuberia inicio;
    private Tuberia fin;

    public Tuberia getInicio() {
        return inicio;
    }

    public Tuberia getFin() {
        return fin;
    }

    public Cliente(Tuberia inicio, Tuberia fin) {
        this.inicio = inicio;
        this.fin = fin;
    }

    @Override
    public void update(Object arg) {
        System.out.println(arg.toString()+"\n");
    }

    public static void main(String[] args){

        Input input = new Input();
        Partir partir = new Partir();
        Mezclar mezclar= new Mezclar();
        Ordenar ordenar= new Ordenar();
        Output output= new Output();
        Tuberia tuberiaInicio= new Tuberia(input);
        Tuberia tuberia2= new Tuberia(partir);
        Tuberia tuberia3 = new Tuberia(mezclar);
        Tuberia tuberia4 = new Tuberia(ordenar);
        Tuberia tuberia5 = new Tuberia(output);
        Tuberia tuberiaFin = new Tuberia(null);
        input.setTuberiaSalida(tuberia2);
        partir.setTuberiaSalida(tuberia3);
        mezclar.setTuberiaSalida(tuberia4);
        ordenar.setTuberiaSalida(tuberia5);
        output.setTuberiaSalida(tuberiaFin);

        Cliente cliente= new Cliente(tuberiaInicio,tuberiaFin);
        cliente.getFin().addObserver(cliente);

        FileReader fileReader = null;
        try {
            fileReader = new FileReader("sentencias.txt");
            Scanner scanner= new Scanner(fileReader);
            while(scanner.hasNext()){
                cliente.inicio.sendStreamToFilter(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
