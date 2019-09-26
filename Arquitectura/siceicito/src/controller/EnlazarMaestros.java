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
import model.Asignatura;
import model.Maestro;
import model.Licenciatura;
import view.VPrincipal;

public class EnlazarMaestros {
    
    private VPrincipal vista;
    private DefaultComboBoxModel cmbMaestrosModel;
    private File CSVMaestros;
    private DefaultComboBoxModel cmbAsignaturasModel;
    private File CSVAsignaturas;
    private DefaultTableModel tblModel;
    
    public EnlazarMaestros(){
        vista= new VPrincipal();
        vista.setVisible(true);
        vista.setLocationRelativeTo(null);
        vista.lblPersona.setText("Seleccione un maestro");
        vista.lblLista.setText("Maestros enlazados");
        vista.btnInscribir.setText("Enlazar Maestro");
        tblModel= (DefaultTableModel) vista.tblPersonas.getModel();
        tblModel.setRowCount(0);
        tblModel.setColumnIdentifiers(new String[]{"Maestro", "Asignatura"});
        try{
            Scanner scan = new Scanner(new File("rutas.txt"));
            scan.nextLine();
            CSVMaestros= new File(scan.nextLine());
            listarMaestros();
            CSVAsignaturas= new File(scan.nextLine());
            listarAsignaturas();
        }catch(FileNotFoundException ex){
            alertar("Archivo rutas.txt no encontrado. "+ ex.getMessage(), vista);
        }
        
       
        vista.btnInscribir.addActionListener((ActionEvent e)->{
           Maestro maestroSelect=(Maestro) cmbMaestrosModel.getSelectedItem();
           Asignatura asignaturaSelect=(Asignatura) cmbAsignaturasModel.getSelectedItem();
           
           tblModel.insertRow(tblModel.getRowCount(), new Object[]{
               maestroSelect, asignaturaSelect
           });
           cmbAsignaturasModel.removeElement(asignaturaSelect);
        });
        
        vista.btnGuardar.addActionListener((ActionEvent e)->{
            FileWriter writer= null;
            try { 
                File CSVAsigMaestro= new File("asigMaestro.csv");
                writer = new FileWriter(CSVAsigMaestro);
                for(int i=0; i<tblModel.getRowCount(); i++){
                    Maestro maestro= (Maestro) tblModel.getValueAt(i, 0);
                    Asignatura asignatura = (Asignatura) tblModel.getValueAt(i, 1);
                    writer.write(
                            maestro.getCve_Maestro()+","
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
    
    private void listarMaestros(){
        try {  
            Scanner scan= new Scanner(CSVMaestros.getAbsoluteFile());
            cmbMaestrosModel=(DefaultComboBoxModel) vista.cmbPersona.getModel();
            cmbMaestrosModel.removeAllElements();
            scan.nextLine();
            while(scan.hasNext()){
                String[] linea= scan.nextLine().split(",");
                Maestro maestro = new Maestro(Integer.parseInt(linea[0]), linea[1], linea[2], linea[3]);
                cmbMaestrosModel.addElement(maestro);
            }
        } catch (Exception ex) {
            alertar("El formato del archivo "
                    + CSVMaestros.getName()
                    + " no es correcta\n(cve_empleado,apellido1,apellido2,nombre). " + ex.getMessage() , vista);
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
                Asignatura asignatura = new Asignatura(Integer.parseInt(linea[0]), linea[1],Licenciatura.valueOf(linea[2]));
                cmbAsignaturasModel.addElement(asignatura);
            }
        } catch (Exception ex) {
            alertar("El formato del archivo "
                    + CSVAsignaturas.getName()
                    + " no es correcta\n(cve_asig,nombre,licenciatura). " + ex.getMessage() , vista);
     }
    }
    
    public void alertar(String mensaje, JFrame vista) {
        JOptionPane.showMessageDialog(vista,
                mensaje,
                "ERROR",
                JOptionPane.ERROR_MESSAGE);
    }

    
}
