/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.excepciones;

/**
 *
 * @author pc
 */
import java.time.LocalDateTime;
public class SistemaBancarioException extends Exception {
    
    private final String codigoError;
    private final LocalDateTime timestamp;
    
    
public SistemaBancarioException (String mensaje , String codigoError){
    super (mensaje);
    this.codigoError = codigoError;
    this.timestamp = LocalDateTime.now();
    
}
public String getCodigoError()  { return codigoError; }
public LocalDateTime getTimestamp () { return timestamp;}

@Override
public String toString (){
    return "[" + codigoError + " | " + timestamp  + "]" + getMessage();
}
}
