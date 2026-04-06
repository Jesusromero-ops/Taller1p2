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

public class CuentaCredito  extends Cuenta
        implements Consultable, Transaccionable, Auditable {
    
      private double limiteCredito;
    private double tasaInteres;
    private double deudaActual;

    public CuentaCredito(String numeroCuenta, double limiteCredito, 
                         double tasaInteres) {
        super(numeroCuenta, 0);
        setLimiteCredito(limiteCredito);
        setTasaInteres(tasaInteres);
        this.deudaActual = 0;
    }
    
     @Override
    public double calcularInteres() {
        return deudaActual * tasaInteres / 12;
    }

    @Override
    public double getLimiteRetiro() {
        return limiteCredito - deudaActual;
    }

    @Override
    public String getTipoCuenta() {
        return "CREDITO";
    }
    
     @Override
    public void depositar(double monto) throws CuentaBloqueadaException {
        verificarBloqueada();
        if (monto <= 0) throw new DatoInvalidoException("monto", monto);
        deudaActual = Math.max(0, deudaActual - monto);
        aumentarSaldo(monto);
    }

    @Override
    public void retirar(double monto) throws SaldoInsuficienteException,
                                             CuentaBloqueadaException {
        verificarBloqueada();
        if (monto <= 0) throw new DatoInvalidoException("monto", monto);
        deudaActual += monto;
        disminuirSaldo(monto);
    }
    
     @Override
    public double calcularComision(double monto) {
        return monto * 0.03;
    }

    @Override
    public double consultarSaldo() {
        return getLimiteRetiro();
    }

    @Override
    public String obtenerResumen() {
        return "Cuenta Credito [" + getNumeroCuenta() + "] "
                + "Limite: $" + limiteCredito
                + " | Deuda: $" + deudaActual
                + " | Disponible: $" + getLimiteRetiro();
    }
    
    
    
    @Override
    public boolean estaActivo() {
        return !isBloqueada();
    }

    @Override
    public String obtenerTipo() {
        return "CREDITO";
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
    
    
    public void setLimiteCredito(double limite) {
        if (limite <= 0) throw new DatoInvalidoException("limiteCredito", limite);
        this.limiteCredito = limite;
    }

    public void setTasaInteres(double tasa) {
        if (tasa < 0 || tasa > 1) throw new DatoInvalidoException("tasaInteres", tasa);
        this.tasaInteres = tasa;
    }

    public double getLimiteCredito() { return limiteCredito; }
    public double getTasaInteres()   { return tasaInteres; }
    public double getDeudaActual()   { return deudaActual; }

        
               
    
}
