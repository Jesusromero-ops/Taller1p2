/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.excepciones;

/**
 *
 * @author pc
 */
public class ClienteNoEncontradoException extends SistemaBancarioException  {
    private final String Clienteid;
    
    public ClienteNoEncontradoException (String Clienteid){
        super ("no se encontro ningun cliente con el id :" + Clienteid , "Error - Cliente - 001");
        this. Clienteid = Clienteid;
        
    }
    
    public String getClienteid () {return Clienteid;}
    
    @Override
    public String toString (){
        return super.toString() + " ID buscando " + Clienteid;
    }
     
}
