/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.cuentas;

/**
 *
 * @author pc
 */
import modelo.abstractas.Cuenta;
import modelo.excepciones.CuentaBloqueadaException;
import modelo.excepciones.DatoInvalidoException;
import modelo.excepciones.SaldoInsuficienteException;
import modelo.interfaces.Auditable;
import modelo.interfaces.Consultable;
import modelo.interfaces.Transaccionable;
import java.time.LocalDateTime;

public class CuentaCorriente extends Cuenta
         implements Consultable, Transaccionable, Auditable {
    
    
     private double montoSobregiro;
    private double comisionMantenimiento;

    public CuentaCorriente(String numeroCuenta, double saldoInicial,
                           double montoSobregiro, double comisionMantenimiento) {
        super(numeroCuenta, saldoInicial);
        setMontoSobregiro(montoSobregiro);
        setComisionMantenimiento(comisionMantenimiento);
    }
    
    
    @Override
    public double calcularInteres() {
        return 0;
    }

    @Override
    public double getLimiteRetiro() {
        return getSaldo() + montoSobregiro;
    }

    @Override
    public String getTipoCuenta() {
        return "CORRIENTE";
    }
    
      @Override
    public void depositar(double monto) throws CuentaBloqueadaException {
        verificarBloqueada();
        if (monto <= 0) throw new DatoInvalidoException("monto", monto);
        aumentarSaldo(monto);
    }

    @Override
    public void retirar(double monto) throws SaldoInsuficienteException,
                                             CuentaBloqueadaException {
        verificarBloqueada();
        if (monto <= 0) throw new DatoInvalidoException("monto", monto);
        if (monto > getLimiteRetiro())
            throw new SaldoInsuficienteException(getSaldo(), monto);
        disminuirSaldo(monto);
    }
    
    @Override
    public double calcularComision(double monto) {
        return comisionMantenimiento;
    }

    @Override
    public double consultarSaldo() {
        return getSaldo();
    }
    
     @Override
    public String obtenerResumen() {
        return "Cuenta Corriente [" + getNumeroCuenta() + "] "
                + "Saldo: $" + getSaldo()
                + " | Sobregiro: $" + montoSobregiro
                + " | Comision: $" + comisionMantenimiento;
    }

    @Override
    public boolean estaActivo() {
        return !isBloqueada();
    }

    @Override
    public String obtenerTipo() {
        return "CORRIENTE";
        
    }
    
    
    @Override
    public LocalDateTime obtenerFechaCreacion() {
        return getFechaCreacion();
    }

    @Override
    public LocalDateTime obtenerUltimaModificacion() {
        return getUltimaModificacion();
    }

    @Override
    public String ObtenerUsuarioModificacion() {
        return getUsuarioModificacion();
    }
    
     @Override
    public void registrarModificacion(String usuario) {
        super.registrarModificacion(usuario);
    }

    public void setMontoSobregiro(double monto) {
        if (monto < 0) throw new DatoInvalidoException("montoSobregiro", monto);
        this.montoSobregiro = monto;
    }

    public void setComisionMantenimiento(double comision) {
        if (comision < 0) throw new DatoInvalidoException("comisionMantenimiento", comision);
        this.comisionMantenimiento = comision;
        
    }  
    
     public double getMontoSobregiro()        { return montoSobregiro; }
    public double getComisionMantenimiento() { return comisionMantenimiento; }
    
    
     
}
