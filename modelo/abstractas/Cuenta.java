/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.abstractas;

/**
 *
 * @author pc
 */

import modelo.banco.Transaccion;
import modelo.excepciones.CapacidadExcedidaException;
import modelo.excepciones.CuentaBloqueadaException;
import modelo.excepciones.DatoInvalidoException;
import java.time.LocalDateTime;

public  abstract class Cuenta {
    private static final int max_historial = 20 ;
    
    
    private String numeroCuenta;
    private double saldo;
    private boolean bloqueada ;
    private final LocalDateTime fechaCreacion;
    private LocalDateTime ultimaModificacion;
    private String usuarioModificacion;
    
    
    private final Transaccion[] historial;
    private int totalTransacciones;
    
    public Cuenta (String numeroCuenta , double saldoInicial ){
        setNumeroCuenta (numeroCuenta);
        setSaldo (saldoInicial);
        this.bloqueada = false ;
        this.fechaCreacion = LocalDateTime.now();
        this.ultimaModificacion = LocalDateTime.now();
        this.usuarioModificacion = " sistema ";
        this.historial = new Transaccion [max_historial ];
        this.totalTransacciones = 0;
        
    }
    
    public abstract double calcularInteres();
    public abstract double getLimiteRetiro();
    public abstract String getTipoCuenta ();
    
    public void verificarBloqueada() throws CuentaBloqueadaException {
        if (bloqueada)
            throw new CuentaBloqueadaException(numeroCuenta);
    }
    
    public void agregarAlhistorial (Transaccion t) throws CapacidadExcedidaException {
        if (totalTransacciones >= max_historial)
            throw new CapacidadExcedidaException ("historial de cuenta " , max_historial );
        historial [totalTransacciones] = t;
        totalTransacciones ++;
             
    }
    
    public Transaccion [] getHistorial() {
        Transaccion[] copia = new Transaccion[totalTransacciones ];
        System.arraycopy( historial, 0, copia ,0, totalTransacciones );
        return copia;
        
    }
    
    public LocalDateTime getFechaCreacion()        { return fechaCreacion; }
    public LocalDateTime getUltimaModificacion()   { return ultimaModificacion; }
    public String getUsuarioModificacion()         { return usuarioModificacion; }

    public void registrarModificacion(String usuario){
        if (usuario == null || usuario.isBlank())
            throw new DatoInvalidoException ( "usuario" , usuario);
        this.ultimaModificacion = LocalDateTime.now();
        this.usuarioModificacion = usuario;
            
    }
    
     public String getNumeroCuenta()      { return numeroCuenta; }
    public double getSaldo()             { return saldo; }
    public boolean isBloqueada()         { return bloqueada; }
    public int getTotalTransacciones()   { return totalTransacciones; }
    
    public void setNumeroCuenta(String numeroCuenta){
        if (numeroCuenta == null || numeroCuenta.isBlank())
            throw new DatoInvalidoException ("numerocuenta " , numeroCuenta);
        this.numeroCuenta = numeroCuenta;
    }
    
    protected void setSaldo (double saldo){
        if (saldo < 0)
            throw new DatoInvalidoException ("saldo", saldo);
        this.saldo = saldo ;
    }
    
    public void setBloqueada (boolean bloqueada){
        this.bloqueada = bloqueada;
    }
    
    protected void aumentarSaldo (double monto){
        this.saldo += monto ;
        this.ultimaModificacion = LocalDateTime.now();
    }
    
    protected void disminuirSaldo (double monto){
        this.saldo -= monto;
        this.ultimaModificacion = LocalDateTime.now();
    }
    
    @Override 
    public String toString () {
        return getTipoCuenta () + "[" + numeroCuenta + "] saldo : $" + String.format("%.2f" , saldo) + (bloqueada ? "[bloqueada]" : "[activa]");
                
    }
    
    
}
    

