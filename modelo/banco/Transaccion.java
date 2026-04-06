/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.banco;

/**
 *
 * @author pc
 */
import modelo.abstractas.Cuenta;
import modelo.enums.EstadoTransaccion;
import modelo.excepciones.EstadoTransaccionInvalidoException;
import java.time.LocalDateTime;

public class Transaccion {
    
    private final String id;
    private final Cuenta cuentaOrigen;
    private final Cuenta cuentaDestino;
    private final double monto;
    private EstadoTransaccion estado;
    private final LocalDateTime fecha;
    private final String descripcion;
    
    public Transaccion(String id, Cuenta cuentaOrigen, Cuenta cuentaDestino,
                       double monto, String descripcion) {
        this.id = id;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.monto = monto;
        this.estado = EstadoTransaccion.PENDIENTE;
        this.fecha = LocalDateTime.now();
        this.descripcion = descripcion;
    } 
    
     public void cambiarEstado(EstadoTransaccion nuevo) {
        if (!estado.PuedeTransicionarA(nuevo))
            throw new EstadoTransaccionInvalidoException(estado, nuevo);
        this.estado = nuevo;
    }

    public String generarComprobante() {
        String destino = cuentaDestino != null
                ? cuentaDestino.getNumeroCuenta() : "N/A";
        return "================================\n"
             + "  COMPROBANTE DE TRANSACCION\n"
             + "  ID        : " + id + "\n"
             + "  Fecha     : " + fecha.toString() + "\n"
             + "  Origen    : " + cuentaOrigen.getNumeroCuenta() + "\n"
             + "  Destino   : " + destino + "\n"
             + "  Monto     : $" + monto + "\n"
             + "  Estado    : " + estado + "\n"
             + "  Detalle   : " + descripcion + "\n"
             + "=================================";
    }
    
    public String getId()                { return id; }
    public Cuenta getCuentaOrigen()      { return cuentaOrigen; }
    public Cuenta getCuentaDestino()     { return cuentaDestino; }
    public double getMonto()             { return monto; }
    public EstadoTransaccion getEstado() { return estado; }
    public LocalDateTime getFecha()      { return fecha; }
    public String getDescripcion()       { return descripcion; }
    
    @Override
    public String toString() {
        return "[" + id + "] " + descripcion 
                + " | $" + monto 
                + " | " + estado;
        
    }    
        
}
