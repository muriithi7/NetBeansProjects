/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temp;

import net.webservicex.TemperatureUnit;
import java.sql.*;

/**
 *
 * @author Nerds
 */
public class Temp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","");
            
            Statement myStmt = myCon.createStatement();
            
           // ResultSet myRs = myStmt.executeQuery("select * from temp");
            
           // while (myRs.next()){
            ///    System.out.println(myRs.getString("Name") + " "+ myRs.getString("Celcius"));
           // }
           
           String sql = "insert into temp" + "(Name,Celcius,Fahrenheit)" + "values('sample2','','44')";
           
           myStmt.executeUpdate(sql);
        }
        
       catch(SQLException e){
            System.err.println("SQL exception: " + e.getMessage());
            System.exit(1);
           
       }
        
        //System.out.println(convertTemp(4.3, TemperatureUnit.DEGREE_CELSIUS , TemperatureUnit.DEGREE_FAHRENHEIT));
        
    }

    private static double convertTemp(double temperature, net.webservicex.TemperatureUnit fromUnit, net.webservicex.TemperatureUnit toUnit) {
        net.webservicex.ConvertTemperature service = new net.webservicex.ConvertTemperature();
        net.webservicex.ConvertTemperatureSoap port = service.getConvertTemperatureSoap12();
        
        
        return port.convertTemp(temperature, fromUnit, toUnit);
    }
    
}

