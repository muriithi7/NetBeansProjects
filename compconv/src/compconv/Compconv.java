/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compconv;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.beans.Statement;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.webservicex.Computers;

/**
 *
 * @author Nerds
 */
public class Compconv {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws DocumentException {
        // TODO code application logic here
        convo();
        pdfreport();
    }
    private static void convo(){
        
        double k=10000,giga;
         try{
             Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/compconv","root","");            
             java.sql.Statement myStmt = myCon.createStatement();
             
                giga = (changeComputerUnit(k, Computers.KILOBYTE , Computers.GIGABYTE));

                   // System.out.println(miles);
                    String sql = "insert into conve" + "(ki,giga)" + "values ('"+k+"','"+giga+"')";           
                    myStmt.executeUpdate(sql);
         }
          catch(SQLException e){
            System.err.println("SQL exception: " + e.getMessage());
            System.exit(1);
             //dbLog.log(Level.SEVERE, e.getMessage(), e);
           
       }
             
    }
    private static void pdfreport() throws DocumentException{
        
      try{
             Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/compconv","root","");            
             java.sql.Statement myStmt = myCon.createStatement();
             //dbLog.log(Level.INFO, "connection successful");
                         
             ResultSet myRs= myStmt.executeQuery("select id, ki, giga from conve");
             
                Document my_pdf_report = new Document();               
                PdfWriter.getInstance(my_pdf_report, new FileOutputStream("report.pdf"));
                my_pdf_report.open();            
                //we have four columns in our table
                PdfPTable my_report_table = new PdfPTable(4);
                //create a cell object
                PdfPCell table_cell;                             
               
                while (myRs.next()) {                
                                String id = myRs.getString("id");
                                table_cell=new PdfPCell(new Phrase(id));
                                my_report_table.addCell(table_cell);
                                String ki=myRs.getString("ki");
                                table_cell=new PdfPCell(new Phrase(ki));
                                my_report_table.addCell(table_cell);
                                String giga=myRs.getString("giga");
                                table_cell=new PdfPCell(new Phrase(giga));
                                my_report_table.addCell(table_cell);
                               
                                }
                /* Attach report table to PDF */
                my_pdf_report.add(my_report_table);                       
                my_pdf_report.close();
                
                /* Close all DB related objects */
        
                myStmt.close(); 
                myCon.close(); 
                      
     
            }
         catch(SQLException e){
            System.err.println("SQL exception: " + e.getMessage());
            System.exit(1);
            // dbLog.log(Level.SEVERE, e.getMessage(), e);
           
       } catch (FileNotFoundException ex) {
             //Logger.getLogger(tables.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         
         
           
        
    }

    private static double changeComputerUnit(double computerValue, net.webservicex.Computers fromComputerUnit, net.webservicex.Computers toComputerUnit) {
        net.webservicex.ComputerUnit service = new net.webservicex.ComputerUnit();
        net.webservicex.ComputerUnitSoap port = service.getComputerUnitSoap12();
        return port.changeComputerUnit(computerValue, fromComputerUnit, toComputerUnit);
    }
    
}
