/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.excepciones;

/**
 *
 * @author pc
 */
public class CapacidadExcedidaException extends  SistemaBancarioException {
     
    private final int capacidadMaxima;
    
    public CapacidadExcedidaException  (String entidad , int capacidadMaxima  ) {
        super ("se alcanzo la capacidad maxima de " + entidad + ". limite " + capacidadMaxima , "Error - capacidad - 001");
        this. capacidadMaxima = capacidadMaxima;
    }
    public int getcapacidadMaxima (){
        return capacidadMaxima;
    }
    @Override
    public String toString (){
            return super.toString() + "Capacidad Maxima:" + capacidadMaxima;}
    
    
}
