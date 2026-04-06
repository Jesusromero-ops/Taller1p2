/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.empleados;

/**
 *
 * @author pc
 */
import modelo.abstractas.Empleado;
import modelo.enums.Turno;
import modelo.excepciones.DatoInvalidoException;
import modelo.interfaces.Auditable;
import modelo.interfaces.Consultable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Cajero  extends Empleado  
        implements Consultable, Auditable  {
    
    private static final double BONO_POR_TRANSACCION = 500.0;

    private Turno turno;
    private String sucursalAsignada;
    private int transaccionesDia;

    private final LocalDateTime fechaCreacion;
    private LocalDateTime ultimaModificacion;
    private String usuarioModificacion;
    
    public Cajero(String id, String nombre, String apellido,
                  LocalDate fechaNacimiento, String email,
                  String legajo, LocalDate fechaContratacion, double salarioBase,
                  Turno turno, String sucursalAsignada) {
        super(id, nombre, apellido, fechaNacimiento, email,
              legajo, fechaContratacion, salarioBase);
        setTurno(turno);
        setSucursalAsignada(sucursalAsignada);
        this.transaccionesDia = 0;
        this.fechaCreacion = LocalDateTime.now();
        this.ultimaModificacion = LocalDateTime.now();
        this.usuarioModificacion = "SISTEMA";
    }
    
    @Override
    public int calcularEdad() {
        return calcularEdadbase();
    }

    @Override
    public String obtenerTipo() {
        return "Cajero";
    }

    @Override
    public String obtenerDocumentoidentidad() {
        return "Legajo: " + getLegajo();
    }

    @Override
    public double calcularBono() {
        return transaccionesDia * BONO_POR_TRANSACCION;
    } 
    
     @Override
    public double calcularSalario() {
        return getSalarioBase() + calcularBono();
    }

    @Override
    public String obtenerResumen() {
        return "Cajero | " + getNombrecompleto()
                + " | Sucursal: " + sucursalAsignada
                + " | Turno: " + turno
                + " | Transacciones hoy: " + transaccionesDia
                + " | Salario: $" + calcularSalario();
    }

    @Override
    public boolean estaActivo() {
        return isActivo();
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
    
    public void setTurno(Turno turno) {
        if (turno == null)
            throw new DatoInvalidoException("turno", null);
        this.turno = turno;
    }

    public void setSucursalAsignada(String sucursal) {
        if (sucursal == null || sucursal.isBlank())
            throw new DatoInvalidoException("sucursalAsignada", sucursal);
        this.sucursalAsignada = sucursal;
    }
    
    public void registrarTransaccion() {
        this.transaccionesDia++;
    }

    public Turno getTurno()             { return turno; }
    public String getSucursalAsignada() { return sucursalAsignada; }
    public int getTransaccionesDia()    { return transaccionesDia; }
    
    
}
