/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.excepciones;

/**
 *
 * @author pc
 */

public class CuentaBloqueadaException extends SistemaBancarioException {
    public CuentaBloqueadaException (String numeroCuenta) {
        super ("la cuenta " +  numeroCuenta + "esta bloqueada y no permite ninguna operacion .",  "Error - cuenta - 001");
    }
    
}
