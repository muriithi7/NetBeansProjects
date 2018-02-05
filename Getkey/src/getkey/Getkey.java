/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getkey;

import com.safaricom.psd.ws.TransactionResult;
import java.net.URL;
import java.util.Scanner;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;

/**
 *
 * @author Nerds
 */
public class Getkey {

    /**
     * @param args the command line arguments
     */
   
   

 private static Scanner scanner = new Scanner( System.in );
    public static void main(String[] args) {
        // TODO code application logic here
        //String saf = "red";
       // saf= (TransactionResult getKey( saf));
       //System.out.println(getKey(saf));
       
          
   System.out.println("enter the name");  
  String yourName=  scanner.nextLine();
   
     System.out.println("enter National ID");
    String  nationalId=  scanner.nextLine();
      
     System.out.println("enter mobile Number");
    String  mobileNo=  scanner.nextLine();
      
     System.out.println("enter Hiddenkey");
     String hiddenKey =  scanner.nextLine();
      
      register(yourName,nationalId,mobileNo,hiddenKey);
     
      
   System.out.println(register(yourName,nationalId,mobileNo,hiddenKey));
      
    }

    private static TransactionResult getKey(java.lang.String name) {
        com.safaricom.psd.ws.TestWS_Service service = new com.safaricom.psd.ws.TestWS_Service();
        com.safaricom.psd.ws.TestWS port = service.getTestWSPort();
        return port.getKey(name);
    }

    private static TransactionResult register(java.lang.String yourName, java.lang.String nationalId, java.lang.String mobileNo, java.lang.String hiddenKey) {
        com.safaricom.psd.ws.TestWS_Service service = new com.safaricom.psd.ws.TestWS_Service();
        com.safaricom.psd.ws.TestWS port = service.getTestWSPort();
        return port.register(yourName, nationalId, mobileNo, hiddenKey);
    }
  
   
}
