package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLSXReader {
    
    public ArrayList read(File excelFile) throws FileNotFoundException, IOException{
        ArrayList<ArrayList<String>> data= new ArrayList();
        FileInputStream fis = new FileInputStream(excelFile);
        XSSFWorkbook workbook= new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIt= sheet.iterator();
        while(rowIt.hasNext()){
            Row row= rowIt.next();
            Iterator<Cell> cellIterator= row.cellIterator();
            ArrayList<String> row_data= new ArrayList();
            while(cellIterator.hasNext()){
                Cell cell=cellIterator.next();
                row_data.add(cell.toString());
            }
            data.add(row_data);
        }
        return data;
    }
}
