 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.interfaces;

/**
 *
 * @author pc
 */
import java.time.LocalDateTime;

public interface Auditable {
    LocalDateTime obtenerFechaCreacion();
    LocalDateTime obtenerUltimaModificacion();
    String ObtenerUsuarioModificacion();
    void registrarModificacion(String usuario);
    
    
}
