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

public class BancoRuntimeException extends RuntimeException  {
    private final String codigoError;
    private final LocalDateTime timetamp;
    
    
public BancoRuntimeException (String mensaje , String codigoError){
    super(mensaje);
    this.codigoError = codigoError;
    this.timetamp = LocalDateTime.now();
   

} 
public String getCodigoError() {return codigoError;}
public LocalDateTime getTimetamp ()  {return timetamp;}


@Override
public String toString() {
    return"{"  + codigoError + "|" + timetamp + "]" +getMessage();
}

 
    
}
