/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.excepciones;

/**
 *
 * @author pc
 */

public class SaldoInsuficienteException  extends SistemaBancarioException {
    
    private final double saldoActual;
    private final double montoSolicitado;
    
    public SaldoInsuficienteException (double saldoActual, double montoSolicitado){
        super ("saldo insuficiente.  Disponible" + saldoActual + "dinero solicitaado" + montoSolicitado,"Error - saldo -001" );
        this.saldoActual = saldoActual;
        this.montoSolicitado = montoSolicitado;
          
    }
    public double getSaldoActual () { return saldoActual;}
    public double getMontoSolicitado() { return montoSolicitado;}
    
    
     @Override
     public String toString() {
         return super.toString()
                 + " Saldo actual:  " + saldoActual
                 + "Monto solicitado: " + montoSolicitado;
     }
    
}
