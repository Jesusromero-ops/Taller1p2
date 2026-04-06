/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.enums;

/**
 *
 * @author pc
 */
public enum EstadoTransaccion {
   PENDIENTE,
   PROCESANDO,
   COMPLETADA,
   RECHAZADA,
   REVERTIDA;
      
    public boolean PuedeTransicionarA (EstadoTransaccion nuevo)  {
    return  switch (this) {
        case PENDIENTE -> nuevo == PROCESANDO  || nuevo == RECHAZADA;
        case PROCESANDO -> nuevo  == COMPLETADA || nuevo == RECHAZADA;
        case COMPLETADA -> nuevo == REVERTIDA ;
        case RECHAZADA , REVERTIDA  -> false ; // estos son estados finales osea que no hay salida .
            
    
    };

} 
   
   
    
}
