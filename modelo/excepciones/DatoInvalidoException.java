/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.excepciones;

/**
 *
 * @author pc
 */
public class DatoInvalidoException extends BancoRuntimeException {
    
    private final String campo;
    private final Object valorRecibido;
    
    public  DatoInvalidoException (String campo , Object valorRecibido){
        super ( "dato invalido en el campo " + campo + ". Valor recibido " + valorRecibido ,"Error - dato- 001");
                this. campo = campo;
                this.valorRecibido = valorRecibido; 
    }
    public String  getcampo ()  {return campo;}
    public Object  getvalorRecivido () { return valorRecibido;}
    
    @Override
     public String toString() {
         return super.toString() + "campo" + campo + "valor " + valorRecibido;
     }
    
    
}
