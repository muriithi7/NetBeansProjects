/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lengthredo;

import java.util.Scanner;
import net.webservicex.Lengths;

/**
 *
 * @author Nerds
 */
public class Lengthredo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        val();
    }
    
    private static void val(){
        double miles,miless;
        Scanner scan = new Scanner( System.in );
        System.out.println("enter the length in miles.");     
        miles  = scan.nextDouble();
        miless = changeLengthUnit(miles,Lengths.MILES, Lengths.KILOMETERS);
        System.out.println(miless);
        
    }

    private static double changeLengthUnit(double lengthValue, net.webservicex.Lengths fromLengthUnit, net.webservicex.Lengths toLengthUnit) {
        net.webservicex.LengthUnit service = new net.webservicex.LengthUnit();
        net.webservicex.LengthUnitSoap port = service.getLengthUnitSoap12();
        return port.changeLengthUnit(lengthValue, fromLengthUnit, toLengthUnit);
    }
    
}
