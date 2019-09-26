package controller; 

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import view.VMenu;

public class Menu {
    
    private VMenu menu;

    public Menu(){
        menu= new VMenu();
        menu.setVisible(true);  
        menu.setLocationRelativeTo(null);  
        File rutas= new File("rutas.txt");
        try {
            Scanner scan = new Scanner(rutas);
            menu.txtCSVAlumnos.setText(new File(scan.nextLine()).getAbsolutePath());
            menu.txtCSVMaestros.setText(new File(scan.nextLine()).getAbsolutePath());
            menu.txtCSVAsignaturas.setText(new File(scan.nextLine()).getAbsolutePath());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        menu.btnCSVAlumnos.addActionListener((ActionEvent e)->{
            File archivo=buscarCSV();
            menu.txtCSVAlumnos.setText(archivo.getAbsolutePath());
        });
        menu.btnCSVMaestros.addActionListener((ActionEvent e)->{
            File archivo=buscarCSV();
            menu.txtCSVMaestros.setText(archivo.getAbsolutePath());
        });
        menu.btnCSVAsignaturas.addActionListener((ActionEvent e)->{
            File archivo=buscarCSV();
            menu.txtCSVAsignaturas.setText(archivo.getAbsolutePath());
        });
        menu.btnMaestrosAsig.addActionListener((ActionEvent e)->{
            guardarRutas(rutas);
            new EnlazarMaestros();
            
        });
        menu.btnAlumnosAsig.addActionListener((ActionEvent e)->{
            guardarRutas(rutas);
            new InscribirAlumnos();
            
        });
    }
    
    private void guardarRutas(File rutas){
         FileWriter writer= null;
        try{
            writer = new FileWriter(rutas);
            writer.write(menu.txtCSVAlumnos.getText());
            writer.write(System.getProperty( "line.separator" ));
            writer.write(menu.txtCSVMaestros.getText());
            writer.write(System.getProperty( "line.separator" ));
            writer.write(menu.txtCSVAsignaturas.getText()+"\n");
            writer.close();
            
        } catch (IOException ex) {
            alertar("Ocurrió un error con el archivo rutas: "+ ex.getMessage(), menu);
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                alertar("Ocurrió un error con el archivo rutas: "+ ex.getMessage(), menu);
            }
        }
    }
    
    private File buscarCSV(){
        try {
            JFileChooser dir = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV FILES", "csv");
            dir.setFileFilter(filter);
            int ventana = dir.showOpenDialog(menu);
            if (ventana == JFileChooser.APPROVE_OPTION) {
                return dir.getSelectedFile();
            }
        } catch (Exception ex) {
            alertar("Error al abrir el explorador de archivos: " + ex.getMessage(), menu);
        }
        return null;
    }
    
    public void alertar(String mensaje, JFrame vista) {
        JOptionPane.showMessageDialog(vista,
                mensaje,
                "ERROR",
                JOptionPane.ERROR_MESSAGE);
    }
    
    public static void main(String[] args) {
        new Menu();
    }
    
}
