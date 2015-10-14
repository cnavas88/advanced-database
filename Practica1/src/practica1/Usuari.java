/*
 * usuari.java
 */
package practica1;

/**
 * Esta Clase define los objetos de Usuari
 * @author: Carlos Navas, Jacint Moya 
 * @version: 1.0
 */
public class Usuari {
    // Atributos
    private int id;
    private String alias;
    private String pass;
    
    // Constructor vacio
    public Usuari(){  }
    
    /** 
     * Constructor de Usuario
     * @param alias El nombre con el que el usuario se logea
     * @param pass Es el password con el que el usuario se logea
     */
    public Usuari( String alias, String pass ){
        this.alias = alias;
        this.pass = pass;
    }
    
    // Getters and Setters 
    /** 
     * Metodo Set para asignar un identificador al usuario
     * @param id Es un entero con el identificador del usuario
     */
    public void setId(int id) {
       this.id = id;
    }
      
    /**
     * Metodo get del identificador
     * @return identificador del usuario
     */
    public int getId() {
       return this.id;
    }
    
    /** 
     * Metodo Set para asignar un alias al usuario
     * @param alias Es un String con el alias del usuario
     */    
    public void setAlias(String alias) {
       this.alias = alias;
    }
    
    /**
     * Metodo get de la variable Alias
     * @return El alias del usuario
     */   
    public String getAlias() {
       return this.alias;
    }
    
    /** 
     * Metodo Set para asignar un password al usuario
     * @param pass Es un String con el password del usuario
     */    
    public void setPass(String pass) {
       this.pass = pass;
    }
    
    /**
     * Metodo get de la variable pass
     * @return El pass del usuario
     */    
    public String getPass() {
       return this.pass;
    }
}
