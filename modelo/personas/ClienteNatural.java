/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.personas;

/**
 *
 * @author pc
 */
import modelo.abstractas.Cuenta;
import modelo.abstractas.Persona;
import modelo.enums.TipoDocumento;
import modelo.excepciones.CapacidadExcedidaException;
import modelo.excepciones.DatoInvalidoException;
import modelo.interfaces.Auditable;
import modelo.interfaces.Consultable;
import modelo.interfaces.Notificable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ClienteNatural extends Persona
        implements Consultable, Notificable, Auditable {
    
    private static final int MAX_CUENTAS = 5;

    private TipoDocumento tipoDocumento;
    private String numeroDocumento;
    private boolean notificacionesActivas;

    private final Cuenta[] cuentas;
    private int totalCuentas;

    private final LocalDateTime fechaCreacion;
    private LocalDateTime ultimaModificacion;
    private String usuarioModificacion;
    
    public ClienteNatural(String id, String nombre, String apellido,
                          LocalDate fechaNacimiento, String email,
                          TipoDocumento tipoDocumento, String numeroDocumento) {
        super(id, nombre, apellido, fechaNacimiento, email);
        setTipoDocumento(tipoDocumento);
        setNumeroDocumento(numeroDocumento);
        this.notificacionesActivas = true;
        this.cuentas = new Cuenta[MAX_CUENTAS];
        this.totalCuentas = 0;
        this.fechaCreacion = LocalDateTime.now();
        this.ultimaModificacion = LocalDateTime.now();
        this.usuarioModificacion = "SISTEMA";
    }
     
    @Override
    public int calcularEdad() {
        return LocalDate.now().getYear() - getFechaNacimiento().getYear();
    }

    @Override
    public String obtenerTipo() {
        return "Cliente Natural";
    }

    @Override
    public String obtenerDocumentoidentidad() {
        return tipoDocumento + ": " + numeroDocumento;
    }
    
    public void agregarCuenta(Cuenta cuenta) throws CapacidadExcedidaException {
        if (totalCuentas >= MAX_CUENTAS)
            throw new CapacidadExcedidaException("cuentas por cliente", MAX_CUENTAS);
        cuentas[totalCuentas] = cuenta;
        totalCuentas++;
    }

    public Cuenta[] getCuentas() {
        Cuenta[] copia = new Cuenta[totalCuentas];
        System.arraycopy (cuentas, 0, copia, 0, totalCuentas);
        return copia;
    
    }  
    
    @Override
    public String obtenerResumen() {
        return "Cliente Natural | " + getNombrecompleto()
                + " | " + obtenerDocumentoidentidad()
                + " | Edad: " + calcularEdad()
                + " | Cuentas: " + totalCuentas;
    }

    @Override
    public boolean estaActivo() {
        return true;
    }
    
    
    @Override
    public void notificar(String mensaje) {
        if (notificacionesActivas)
            System.out.println("[NOTIFICACION -> " + getNombrecompleto()
                    + "] " + mensaje);
        else
            System.out.println("[NOTIFICACION BLOQUEADA] "
                    + getNombrecompleto() + " no acepta notificaciones.");
    }

    @Override
    public String obtenerContacto() {
        return getEmail();
    }

    @Override
    public boolean aceptaNotificaciones() {
        return notificacionesActivas;
    }
    
    
    @Override
    public LocalDateTime obtenerFechaCreacion()      { return fechaCreacion; }
    @Override
    public LocalDateTime obtenerUltimaModificacion() { return ultimaModificacion; }
    @Override
    public String ObtenerUsuarioModificacion()       { return usuarioModificacion; }

    @Override
    public void registrarModificacion(String usuario) {
        if (usuario == null || usuario.isBlank())
            throw new DatoInvalidoException("usuario", usuario);
        this.ultimaModificacion = LocalDateTime.now();
        this.usuarioModificacion = usuario;
    }
    
    public void setTipoDocumento(TipoDocumento tipo) {
        if (tipo == null) throw new DatoInvalidoException("tipoDocumento", null);
        this.tipoDocumento = tipo;
    }

    public void setNumeroDocumento(String numero) {
        if (numero == null || numero.isBlank())
            throw new DatoInvalidoException("numeroDocumento", numero);
        this.numeroDocumento = numero;
    }

    public void setNotificacionesActivas(boolean activas) {
        this.notificacionesActivas = activas;
    }
    
    public TipoDocumento getTipoDocumento()  { return tipoDocumento; }
    public String getNumeroDocumento()       { return numeroDocumento; }
    public int getTotalCuentas()             { return totalCuentas; }

     

        
    
}
