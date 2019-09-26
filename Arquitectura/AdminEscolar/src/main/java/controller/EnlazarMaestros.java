package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import static javax.imageio.ImageIO.read;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Asignatura;
import model.Maestro;
import model.Licenciatura;
import model.XLSXReader;
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
        
        vista.btnInscribir.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Maestro maestroSelect=(Maestro) cmbMaestrosModel.getSelectedItem();
                Asignatura asignaturaSelect=(Asignatura) cmbAsignaturasModel.getSelectedItem();

                tblModel.insertRow(tblModel.getRowCount(), new Object[]{
                   maestroSelect, asignaturaSelect
                });
                cmbAsignaturasModel.removeElement(asignaturaSelect);           
            }
        });
       
        
        vista.btnGuardar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        });
        
        vista.btnCancelar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.setVisible(false);}
        });
    }
    
    private void listarMaestros(){
        try {  
            cmbMaestrosModel=(DefaultComboBoxModel) vista.cmbPersona.getModel();
            cmbMaestrosModel.removeAllElements();
            XLSXReader reader= new XLSXReader();
            ArrayList<ArrayList<String>>lstMaestros= reader.read(CSVMaestros);
            for(ArrayList<String> maestro : lstMaestros ){
                Maestro maestroObj= new Maestro(Integer.parseInt(maestro.get(0)),
                maestro.get(1), maestro.get(2), maestro.get(3));
                cmbMaestrosModel.addElement(maestroObj);
            }
        
           } catch (Exception ex) {
            alertar("El formato del archivo "
                    + CSVMaestros.getName()
                    + " no es correcto. " + ex.getMessage() , vista);
        }     
    }
        
    private void listarAsignaturas() {
        try {
     
            cmbAsignaturasModel=(DefaultComboBoxModel) vista.cmbAsignatura.getModel();
            cmbAsignaturasModel.removeAllElements();
            XLSXReader reader= new XLSXReader();
            ArrayList<ArrayList<String>>lstAsig= reader.read(CSVAsignaturas);
            for(ArrayList<String> asig : lstAsig ){
                Asignatura asigObj= new Asignatura(Integer.parseInt(asig.get(0)),
                asig.get(1),  Licenciatura.valueOf(asig.get(2)));
                cmbAsignaturasModel.addElement(asigObj);
            }
        } catch (Exception ex) {
            alertar("El formato del archivo "
                    + CSVAsignaturas.getName()
                    + " no es correcta. " + ex.getMessage() , vista);
        }
    }
    
    public void alertar(String mensaje, JFrame vista) {
        JOptionPane.showMessageDialog(vista,
                mensaje,
                "ERROR",
                JOptionPane.ERROR_MESSAGE);
    }

    
}
