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
import modelo.excepciones.CapacidadExcedidaException;
import modelo.excepciones.DatoInvalidoException;
import modelo.interfaces.Auditable;
import modelo.interfaces.Consultable;
import modelo.interfaces.Notificable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ClienteEmpresarial extends Persona 
        implements Consultable, Notificable, Auditable {
    
    
    private static final int MAX_CUENTAS = 5;

    private String nit;
    private String razonSocial;
    private String representanteLegal;
    private boolean notificacionesActivas;

    private final Cuenta[] cuentas;
    private int totalCuentas;

    private final LocalDateTime fechaCreacion;
    private LocalDateTime ultimaModificacion;
    private String usuarioModificacion;
    
    
    public ClienteEmpresarial(String id, String nombre, String apellido,
                               LocalDate fechaNacimiento, String email,
                               String nit, String razonSocial, 
                               String representanteLegal) {
        super(id, nombre, apellido, fechaNacimiento, email);
        setNit(nit);
        setRazonSocial(razonSocial);
        setRepresentanteLegal(representanteLegal);
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
        return "Cliente Empresarial";
    }

    @Override
    public String obtenerDocumentoidentidad() {
        return "NIT: " + nit;
    }
    
    public void agregarCuenta(Cuenta cuenta) throws CapacidadExcedidaException {
        if (totalCuentas >= MAX_CUENTAS)
            throw new CapacidadExcedidaException("cuentas por cliente empresarial", MAX_CUENTAS);
        cuentas[totalCuentas] = cuenta;
        totalCuentas++;
    }

    public Cuenta[] getCuentas() {
        Cuenta[] copia = new Cuenta[totalCuentas];
        System.arraycopy(cuentas, 0, copia, 0, totalCuentas);
        return copia;
    
    }
    
    @Override
    public String obtenerResumen() {
        return "Cliente Empresarial | " + razonSocial
                + " | NIT: " + nit
                + " | Rep. Legal: " + representanteLegal
                + " | Cuentas: " + totalCuentas;
    }

    @Override
    public boolean estaActivo() {
        return true;
    }
    
    @Override
    public void notificar(String mensaje) {
        if (notificacionesActivas)
            System.out.println("[NOTIFICACION -> " + razonSocial
                    + "] " + mensaje);
        else
            System.out.println("[NOTIFICACION BLOQUEADA] "
                    + razonSocial + " no acepta notificaciones.");
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
    
    public void setNit(String nit) {
        if (nit == null || nit.isBlank())
            throw new DatoInvalidoException("nit", nit);
        this.nit = nit;
    }

    public void setRazonSocial(String razonSocial) {
        if (razonSocial == null || razonSocial.isBlank())
            throw new DatoInvalidoException("razonSocial", razonSocial);
        this.razonSocial = razonSocial;
    }
    
    public void setRepresentanteLegal(String rep) {
        if (rep == null || rep.isBlank())
            throw new DatoInvalidoException("representanteLegal", rep);
        this.representanteLegal = rep;
    }

    public void setNotificacionesActivas(boolean activas) {
        this.notificacionesActivas = activas;
    }

    public String getNit()                { return nit; }
    public String getRazonSocial()        { return razonSocial; }
    public String getRepresentanteLegal() { return representanteLegal; }
    public int getTotalCuentas()          { return totalCuentas; }

    
    

    
}
