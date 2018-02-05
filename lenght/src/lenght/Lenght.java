/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lenght;

import net.webservicex.Lengths;

/**
 *
 * @author Nerds
 */
public class Lenght {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {  
        
         System.out.println(changeLengthUnit(50, Lengths.MILES, Lengths.KILOMETERS));
       
    }

    private static double changeLengthUnit(double lengthValue, net.webservicex.Lengths fromLengthUnit, net.webservicex.Lengths toLengthUnit) {
        net.webservicex.LengthUnit service = new net.webservicex.LengthUnit();
        net.webservicex.LengthUnitSoap port = service.getLengthUnitSoap12();
        
        
        
        return port.changeLengthUnit(lengthValue, fromLengthUnit, toLengthUnit);
    }
    
}
