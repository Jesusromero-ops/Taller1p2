/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.interfaces;

/**
 *
 * @author pc
 */
public interface Notificable {
    void notificar (String mensaje);
    String obtenerContacto();
    boolean aceptaNotificaciones();
    
}
