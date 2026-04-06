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

public  abstract class Persona {
    
    private String id ;
    private String nombre ;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String email;
    
    public Persona ( String id , String nombre , String apellido , LocalDate fechaNacimiento , String email){
        
        setId (id);
        setNombre (nombre);
        setApellido (apellido);
        setFechaNacimiento (fechaNacimiento);
        setEmail (email);
        
    }
    
    public abstract int calcularEdad();
    public abstract String obtenerTipo();
    public abstract String obtenerDocumentoidentidad();
    
    public String getNombrecompleto () {
        return nombre + " " + apellido;
       
    }
     protected  int calcularEdadbase (){
         return Period.between(fechaNacimiento , LocalDate.now() ).getYears();
     }
     
     public String getId() {return id;}
     public String getNombre ()  { return nombre ;}
     public String getApellido() { return  apellido;}
     public LocalDate getFechaNacimiento ()   { return  fechaNacimiento;}
     public String getEmail ()  {return   email;}
     
     
     public void setId (String id){
         if (id == null  || id.isBlank())
             throw new DatoInvalidoException("id", id);
         this.id = id ;
         
     }
     
     public void setNombre (String nombre ){
         if (nombre == null || nombre.isBlank())
             throw new DatoInvalidoException ("nombre", nombre);
         this.nombre = nombre ;
     }
     
     public void setApellido ( String apellido){ 
         if (apellido == null || apellido.isBlank())
             throw new DatoInvalidoException ("apellido" , apellido);
         this.apellido = apellido;
         
     }
     
     public void setFechaNacimiento (LocalDate fecha){
         if (fecha == null   || fecha.isAfter(LocalDate.now()))
             throw new DatoInvalidoException ("fechanacimiento", fecha);
         this.fechaNacimiento = fecha;
             
     }
     
     public void  setEmail (String email){
         if (email == null || !email.contains("@"))
             throw new DatoInvalidoException ("email", email);
         this.email = email;
     }
     
     @Override 
     public String toString (){
         return obtenerTipo() + " | " + getNombrecompleto() + "|" +  obtenerDocumentoidentidad ();
     }
             
     
   
            
    
}
