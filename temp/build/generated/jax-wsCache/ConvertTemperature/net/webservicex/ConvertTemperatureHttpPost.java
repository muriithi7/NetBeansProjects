
package net.webservicex;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ConvertTemperatureHttpPost", targetNamespace = "http://www.webserviceX.NET/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ConvertTemperatureHttpPost {


    /**
     * 
     * @param temperature
     * @param toUnit
     * @param fromUnit
     * @return
     *     returns double
     */
    @WebMethod(operationName = "ConvertTemp")
    @WebResult(name = "double", targetNamespace = "http://www.webserviceX.NET/", partName = "Body")
    public double convertTemp(
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "Temperature")
        String temperature,
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "FromUnit")
        String fromUnit,
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "ToUnit")
        String toUnit);

}
