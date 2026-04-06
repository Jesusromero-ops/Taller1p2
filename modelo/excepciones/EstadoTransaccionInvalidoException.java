/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.excepciones;

/**
 *
 * @author pc
 */
import modelo.enums.EstadoTransaccion;

public class EstadoTransaccionInvalidoException extends BancoRuntimeException  {
    public EstadoTransaccionInvalidoException (EstadoTransaccion origen, EstadoTransaccion destino
            ) {
        super ("Transicion invalida:" + origen + "->" + destino , "Error - Estado - 001");
    }
    
}
