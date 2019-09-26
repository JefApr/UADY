package controller;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Alumno;
import model.Asignatura;
import model.Licenciatura;
import model.Maestro;
import view.VPrincipal;

public class InscribirAlumnos {
    
    private VPrincipal vista;
    private DefaultComboBoxModel cmbAlumnosModel;
    private File CSVAlumnos;
    private DefaultComboBoxModel cmbAsignaturasModel;
    private File CSVAsignaturas;
    private DefaultTableModel tblModel;
    
    public InscribirAlumnos(){
        vista= new VPrincipal();
        vista.setVisible(true);
        vista.setLocationRelativeTo(null);
        tblModel= (DefaultTableModel) vista.tblPersonas.getModel();
        tblModel.setRowCount(0);
        try{
            Scanner scan = new Scanner(new File("rutas.txt"));
            CSVAlumnos= new File(scan.nextLine());
            scan.nextLine();
            listarAlumnos();
            CSVAsignaturas= new File("asigMaestro.csv");
            listarAsignaturas();
        }catch(FileNotFoundException ex){
            alertar("Archivo rutas.txt no encontrado. "+ ex.getMessage(), vista);
        }
        
        vista.btnInscribir.addActionListener((ActionEvent e)->{
            Alumno alumnoSelect=(Alumno) cmbAlumnosModel.getSelectedItem();
            Asignatura asignaturaSelect=(Asignatura) cmbAsignaturasModel.getSelectedItem();
            boolean aux=true;
            for(int i=0; i<tblModel.getRowCount(); i++){
                Alumno alumno =(Alumno) tblModel.getValueAt(i, 0);
                Asignatura asignatura = (Asignatura) tblModel.getValueAt(i, 1);
             
                if(alumnoSelect.equals(alumno)&&asignaturaSelect.equals(asignatura)){
                    alertar(alumno.getNombre()+ " ya está inscrito a "+ asignatura.getNombre(), vista);
                    aux=false;
                }
            }
            if(aux){
                tblModel.insertRow(tblModel.getRowCount(), new Object[]{
                alumnoSelect, asignaturaSelect, asignaturaSelect.getMaestro()
                });
            }  
        });
        
        vista.btnGuardar.addActionListener((ActionEvent e)->{
            FileWriter writer= null;
            try { 
                File CSVAsigMaestro= new File("alumnosAsig.csv");
                writer = new FileWriter(CSVAsigMaestro);
                for(int i=0; i<tblModel.getRowCount(); i++){
                  
                    Alumno alumno = (Alumno) tblModel.getValueAt(i, 0);
                    Asignatura asignatura = (Asignatura) tblModel.getValueAt(i, 1);
                    Maestro maestro= (Maestro) tblModel.getValueAt(i, 2);
                    writer.write(
                            alumno.getMatricula()+","
                            +alumno.getNombre()+","
                            +alumno.getApellido1()+","
                            +alumno.getApellido2()+","
                            +alumno.getLicenciatura()+","
                            +maestro.getCve_Maestro()+","
                            +maestro.getApellido1()+","
                            +maestro.getApellido2()+","
                            +maestro.getNombre()+","
                            +asignatura.getCve_Asig()+","
                            +asignatura.getNombre()+","
                            +asignatura.getLicenciatura()+","
                            +System.getProperty( "line.separator" )
                    );
                    alertar("CSV guardado con éxito en: " + CSVAsigMaestro.getAbsolutePath(), vista);
                    vista.setVisible(false);
                }
            } catch (IOException ex) {
                alertar("Error al generar el archivo CSV. " + ex.getMessage(), vista);
            } finally{
                try {
                    writer.close();
                } catch (IOException ex) {
                    alertar("Error al generar el archivo CSV. " + ex.getMessage(), vista);
                }
            }
        });
        vista.btnCancelar.addActionListener((ActionEvent e)->{vista.setVisible(false);});
    }

    private void listarAlumnos(){
        try {  
            Scanner scan= new Scanner(CSVAlumnos.getAbsoluteFile());
            cmbAlumnosModel=(DefaultComboBoxModel) vista.cmbPersona.getModel();
            cmbAlumnosModel.removeAllElements();
            scan.nextLine();
            while(scan.hasNext()){
                String[] linea= scan.nextLine().split(",");
                Alumno alumno = new Alumno(Integer.parseInt(linea[0]), linea[1], linea[2], linea[3], Licenciatura.valueOf(linea[4]));
                cmbAlumnosModel.addElement(alumno);
            }
        } catch (Exception ex) {
            alertar("El formato del archivo "
                    + CSVAlumnos.getName()
                    + " no es correcta\n(matricula,apellido1,apellido2,nombre,licenciatura). " + ex.getMessage() , vista);
      }
            
    }
        
    private void listarAsignaturas() {
        try {
            Scanner scan= new Scanner(CSVAsignaturas.getAbsoluteFile());
            
            cmbAsignaturasModel=(DefaultComboBoxModel) vista.cmbAsignatura.getModel();
            cmbAsignaturasModel.removeAllElements();
            scan.nextLine();
            while(scan.hasNext()){
                String[] linea= scan.nextLine().split(",");
                 Maestro maestro=new Maestro(Integer.parseInt(linea[0]), linea[1], linea[2], linea[3]);
                 Asignatura asignatura = new Asignatura(Integer.parseInt(linea[4]), linea[5],Licenciatura.valueOf(linea[6]));
                 asignatura.setMaestro(maestro);
                cmbAsignaturasModel.addElement(asignatura);
            }
        } catch (Exception ex) {
           alertar("El formato del archivo "
                    + CSVAsignaturas.getName()
                    + " no es correcta\n(cve_Maestro,apellido1,apellido2,nombre,cve_asig,nombre,licenciatura). " + ex.getMessage() , vista);
        }
    }
     public void alertar(String mensaje, JFrame vista) {
        JOptionPane.showMessageDialog(vista,
                mensaje,
                "ERROR",
                JOptionPane.ERROR_MESSAGE);
    }
    
}
