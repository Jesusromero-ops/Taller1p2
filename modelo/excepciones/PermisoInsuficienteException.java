/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.excepciones;

/**
 *
 * @author pc
 */
public class PermisoInsuficienteException extends BancoRuntimeException {
    
    public PermisoInsuficienteException (String accion, String rol) {
        super ("el rol " + rol + "no se puede ejecutar la accion " + accion , "Error - permiso - 001");
    }
    
}
