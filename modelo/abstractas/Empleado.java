/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.abstractas;

/**
 *
 * @author pc
 */
import modelo.excepciones.DatoInvalidoException;
import java.time.LocalDate;
import java.time.Period;
public abstract  class Empleado extends Persona  {
    private String legajo;
    private LocalDate fechaContratacion;
    private double salarioBase;
    private boolean activo;
    
    public Empleado (String id , String nombre , String apellido , LocalDate fechaNacimiento , String email ,String legajo ,LocalDate fechaContratacion , double salarioBase  ){
            super (id , nombre , apellido , fechaNacimiento , email);
            setLegajo (legajo);
            setFechaContratacion (fechaContratacion);
            setSalarioBase (salarioBase);
            this.activo = true;
            
        
    }
    public abstract double calcularSalario();
    public abstract double calcularBono ();
    
    
    public int calcularAntiguedad(){
        return Period.between(fechaContratacion , LocalDate.now()).getYears();
        
    }
    public String getLegajo()               { return legajo; }
    public LocalDate getFechaContratacion() { return fechaContratacion; }
    public double getSalarioBase()          { return salarioBase; }
    public boolean isActivo()               { return activo; }

    
    public void setLegajo (String legajo){
        if (legajo == null || legajo.isBlank())
            throw new DatoInvalidoException ("legajo", legajo );
        this.legajo = legajo;
        
       
    }
    
    public void setFechaContratacion  (LocalDate fecha){
        if (fecha == null || fecha.isAfter(LocalDate.now()))
            throw new  DatoInvalidoException  ("fechacontratacion" , fecha );
        this.fechaContratacion = fecha;
            
            
    }
    
    public void setSalarioBase (double salario){
        if (salario <= 0)
            throw new DatoInvalidoException ("salariobase ", salario);
        this.salarioBase = salario ;
        
    }
    
    public void setActivo (boolean activo){
        this.activo = activo;
        
    }
    // jesus romero el proximo programador .
    
   
}
