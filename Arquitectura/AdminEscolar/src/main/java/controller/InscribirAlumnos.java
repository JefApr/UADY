package controller;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Alumno;
import model.Asignatura;
import model.Licenciatura;
import model.Maestro;
import model.XLSXReader;
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
        
           vista.btnInscribir.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        });
        
        vista.btnGuardar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
       
                FileWriter writer= null;
                try { 
                    File CSVAsigMaestro= new File("alumnosAsig.csv");
                    writer = new FileWriter(CSVAsigMaestro);
                    for(int i=0; i< cmbAsignaturasModel.getSize(); i++ ){
                        ArrayList<Alumno> alumnosInscritos= new ArrayList();
                        Asignatura asignaturaActual=(Asignatura) cmbAsignaturasModel.getElementAt(i);
                        for(int j=0; j<tblModel.getRowCount(); j++){
                            Asignatura asignaturaAsignada = (Asignatura) tblModel.getValueAt(j, 1);
                            if(asignaturaActual.getNombre().equals(asignaturaAsignada.getNombre())){
                                  Alumno alumno = (Alumno) tblModel.getValueAt(j, 0);
                                  alumnosInscritos.add(alumno);
                            }
                        }
                        if(alumnosInscritos.size()>0){
                            crearPDF(asignaturaActual, alumnosInscritos);
                        }
                        
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
                vista.setVisible(false);
            }
        });
    }
    

    private void listarAlumnos(){
        try {  
          
            cmbAlumnosModel=(DefaultComboBoxModel) vista.cmbPersona.getModel();
            cmbAlumnosModel.removeAllElements();
            XLSXReader reader= new XLSXReader();
            ArrayList<ArrayList<String>>lstAlumnos= reader.read(CSVAlumnos);
            for(ArrayList<String> alumno : lstAlumnos ){
                Alumno alumnoObj= new Alumno(Integer.parseInt(alumno.get(0)),
                alumno.get(1), alumno.get(2), alumno.get(3), Licenciatura.valueOf(alumno.get(4)));
                cmbAlumnosModel.addElement(alumnoObj);
            }
        } catch (Exception ex) {
            alertar("El formato del archivo "
                    + CSVAlumnos.getName()
                    + " no es correcto. " + ex.getMessage() , vista);
      }
            
    }
        
    private void listarAsignaturas() {
        try {
            Scanner scan= new Scanner(CSVAsignaturas.getAbsoluteFile());
            
            cmbAsignaturasModel=(DefaultComboBoxModel) vista.cmbAsignatura.getModel();
            cmbAsignaturasModel.removeAllElements();
          
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
     
     
    private void crearPDF(Asignatura asignaturaActual, ArrayList<Alumno> alumnosInscritos){
        Document document = new Document(PageSize.A4, 20, 20, 20, 20);
        try{
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(asignaturaActual.getNombre()+"-inscritos.pdf"));
            BaseFont bf_courier = BaseFont.createFont(BaseFont.COURIER, "Cp1252", false);
            HeaderFooter header = new HeaderFooter(
                            new Phrase(asignaturaActual.toString(), new Font(bf_courier)), false);
            header.setAlignment(Element.ALIGN_CENTER);
            document.setHeader(header);

            document.open();

            Paragraph par = new Paragraph("Alumnos Inscritos:");
            par.getFont().setStyle(Font.BOLD);
            document.add(par);

            Table table = new Table(5);
            table.setBorderWidth(2);
            table.setBorderColor(new Color(163, 0, 91));
            table.addCell("Matrícula");
            table.addCell("Nombre");
            table.addCell("Apellido P");
            table.addCell("Apellido M");
            table.addCell("Licenciatura");

            for(Alumno alumno : alumnosInscritos){
                table.addCell(String.valueOf(alumno.getMatricula()));
                table.addCell(alumno.getNombre());
                table.addCell(alumno.getApellido1());
                table.addCell(alumno.getApellido2());
                table.addCell(alumno.getLicenciatura().toString());
            }
           
            document.add(table);
            document.close();
            
            File archivoGuardado= new File(asignaturaActual.getNombre()+"-inscritos.pdf");
            if(archivoGuardado.exists())
                JOptionPane.showMessageDialog(vista,  
                        "El PDF se guardó de la asignatura "
                                +asignaturaActual.getNombre()+ " en " 
                                + archivoGuardado.getAbsolutePath(), 
                        "PDF CREADO CORRECTAMENTE", 
                        JOptionPane.INFORMATION_MESSAGE, null);
            else
                throw new Exception("Ocurrió un error al crear el PDF");
        }catch(Exception ex){
            alertar(ex.getMessage(), vista);
        }
    }
    
}
