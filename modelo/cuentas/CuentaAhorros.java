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

public  class CuentaAhorros extends Cuenta 
        implements Consultable , Transaccionable , Auditable {

    private double tasaInteres;
    private int retirosMesActual;
    private final int maxRetirosMes;
    

     public CuentaAhorros(String numeroCuenta, double saldoInicial,
                         double tasaInteres, int maxRetirosMes) {
        super(numeroCuenta, saldoInicial);
        setTasaInteres(tasaInteres);
        this.maxRetirosMes = maxRetirosMes;
        this.retirosMesActual = 0;
     }
     
     @Override
    public double calcularInteres() {
        return getSaldo() * tasaInteres / 12;
    }

    @Override
    public double getLimiteRetiro() {
        return getSaldo();
    }

    @Override
    public String getTipoCuenta() {
        return "AHORROS";
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
        if (monto > getSaldo())
            throw new SaldoInsuficienteException(getSaldo(), monto);
        disminuirSaldo(monto);
        retirosMesActual++;
    
    }
    
    
    @Override
    public double calcularComision(double monto) {
        return retirosMesActual > maxRetirosMes ? monto * 0.02 : 0;
    }

    @Override
    public double consultarSaldo() {
        return getSaldo();
    }
    
    
    @Override
    public String obtenerResumen() {
        return "Cuenta Ahorros [" + getNumeroCuenta() + "] "
                + "Saldo: $" + getSaldo()
                + " | Tasa: " + (tasaInteres * 100) + "%"
                + " | Retiros mes: " + retirosMesActual + "/" + maxRetirosMes;
    }

    @Override
    public boolean estaActivo() {
        return !isBloqueada();
    }

    @Override
    public String obtenerTipo() {
        return "AHORROS";
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
    
      public void setTasaInteres(double tasa) {
        if (tasa < 0 || tasa > 1)
            throw new DatoInvalidoException("tasaInteres", tasa);
        this.tasaInteres = tasa;
    }

    public int getRetirosMesActual() { return retirosMesActual; }
    public int getMaxRetirosMes()    { return maxRetirosMes; }
    public double getTasaInteres()   { return tasaInteres; }
    
    
}
