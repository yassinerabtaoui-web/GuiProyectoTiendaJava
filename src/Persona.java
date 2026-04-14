/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * @version 1.0
 * @mail yassine@gmail.com
 * @compay IESABASTOS
 * @author Yassine
 */
public class Persona {
    String nombre;
    String apellido;
    int edad;
    
    
    public Persona(String nombre, String apellido, int edad){
        this.nombre=nombre;
        this.apellido=apellido;
        this.edad=edad;
    }
}

class Vendedor extends Persona{
    int codigoVendedor;
    public Vendedor(String nombre, String apellido, int edad,int codigo) {
        super(nombre, apellido, edad);
        this.codigoVendedor=codigo;
        
    }
    
}
class Comprador extends Persona{
    int idComprador;
    public Comprador (String nombre, String apellido, int edad,int id) {
        super(nombre, apellido, edad);
        this.idComprador=id; 
        
    }
    
}

