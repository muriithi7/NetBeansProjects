/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//this class handles the creation of the table for the first time...
package length;

/**
 *
 * @author Nerds
 */
import java.io.FileOutputStream;
import java.sql.*;
import net.webservicex.Lengths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import java.io.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;


public class tables {
     private static final Logger dbLog = Logger.getLogger(tables.class.getName());
     public static void main(String[] args) throws DocumentException{
         
         tal();
         val();
         pdfreport();    
         
        
    
    }
     //creates the tables in the database;
     public static void tal(){
         try{
             //connecting to the datatabase
             Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","");            
             Statement myStmt = myCon.createStatement();
             
             dbLog.log(Level.INFO, "connection successful");
             //drops the table if it exists to prevent the initial values being loaded more than once
             String sqli ="DROP TABLE IF EXISTS length";
             
             myStmt.executeUpdate(sqli);

             
             String sql ="Create table if not exists length(LengthID INT NOT NULL AUTO_INCREMENT, Miles INT , Kilometers DOUBLE, DateModified TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, PRIMARY KEY(LengthID) )";
             
             myStmt.executeUpdate(sql);
            dbLog.log(Level.INFO, "table created successfully");
         }
         catch(SQLException e){
           // System.err.println("SQL exception: " + e.getMessage());
            //dbLog.log(Level.INFO, "table created successfully");
            //System.exit(1);
             dbLog.log(Level.SEVERE, e.getMessage(), e);
           
       }
         
         
      }
     
     
     //produces the values for the miles;
     
     public static void val(){
         try{
             Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","");            
             Statement myStmt = myCon.createStatement();
             dbLog.log(Level.INFO, "connection successful");
         
            double miles;
             for(double i = 10; i<=100; i+=10){
            
                   
                   miles = (changeLengthUnit(i, Lengths.MILES , Lengths.KILOMETERS));

                   // System.out.println(miles);
                    String sql = "insert into length" + "(Miles,Kilometers)" + "values ('"+i+"','"+miles+"')";           
                    myStmt.executeUpdate(sql);
                    }
     
            }
         catch(SQLException e){
           // System.err.println("SQL exception: " + e.getMessage());
           // System.exit(1);
             dbLog.log(Level.SEVERE, e.getMessage(), e);
           
       }
         
         }
     public static void  pdfreport() throws DocumentException{
         
          try{
             Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","");            
             Statement myStmt = myCon.createStatement();
             dbLog.log(Level.INFO, "connection successful");
                         
             ResultSet myRs= myStmt.executeQuery("select LengthID, Miles, Kilometers, DateModified from length");
             
                Document my_pdf_report = new Document();               
                PdfWriter.getInstance(my_pdf_report, new FileOutputStream("report.pdf"));
                my_pdf_report.open();            
                //we have four columns in our table
                PdfPTable my_report_table = new PdfPTable(4);
                //create a cell object
                PdfPCell table_cell;                             
               
                while (myRs.next()) {                
                                String dept_id = myRs.getString("LengthID");
                                table_cell=new PdfPCell(new Phrase(dept_id));
                                my_report_table.addCell(table_cell);
                                String dept_name=myRs.getString("Miles");
                                table_cell=new PdfPCell(new Phrase(dept_name));
                                my_report_table.addCell(table_cell);
                                String manager_id=myRs.getString("Kilometers");
                                table_cell=new PdfPCell(new Phrase(manager_id));
                                my_report_table.addCell(table_cell);
                                String location_id=myRs.getString("DateModified");
                                table_cell=new PdfPCell(new Phrase(location_id));
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
           // System.err.println("SQL exception: " + e.getMessage());
           // System.exit(1);
             dbLog.log(Level.SEVERE, e.getMessage(), e);
           
       } catch (FileNotFoundException ex) {
             Logger.getLogger(tables.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         
         
         
         
         
         
         
     }
     
     
     
     

    private static double changeLengthUnit(double lengthValue, net.webservicex.Lengths fromLengthUnit, net.webservicex.Lengths toLengthUnit) {
        net.webservicex.LengthUnit service = new net.webservicex.LengthUnit();
        net.webservicex.LengthUnitSoap port = service.getLengthUnitSoap12();
        return port.changeLengthUnit(lengthValue, fromLengthUnit, toLengthUnit);
    }
     
    
}
