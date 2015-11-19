/*
 * Plat.java
 */
package practica1;

import java.util.Set;

/**
 * Esta Clase define los objetos de tipo Plat
 * @author: Carlos Navas, Jacint Moya 
 * @version: 1.0
 */
public class Plat  implements java.io.Serializable{
    // Atributos
    private int id;
    private String nom;
    private String descripcio;
    private Set<Recepta> receptes;
    
    // Constructor vacio
    public Plat(){  }
    
    /** 
     * Constructor de plat con parametros 
     * @param nom El nombre del plat
     * @param descripcio Es la descripcion del plat
     */
    public Plat( String nom, String descripcio ){
        this.nom = nom;
        this.descripcio = descripcio;
    }
    
    // Getters and Setters 
    /** 
     * Metodo Set para asignar un nombre a la clase Plat
     * @param nom Es un String con el nombre del palto
     */
    public void setNom(String nom) {
       this.nom = nom;
    }
    
    /**
     * Metodo get de la variable nombre
     * @return El nombre del plato
     */
    public String getNom() {
       return this.nom;
    }
    
    /** 
     * Metodo Set para asignar una descripcion
     * @param descripcio Es un String con la descripcion del plato
     */
    public void setDescripcio(String descripcio) {
       this.descripcio = descripcio;
    }
    
    /**
     * Metodo get de la variable descripcio
     * @return La descripcion del plato
     */
    public String getDescripcio() {
       return this.descripcio;
    }
    
    /** 
     * Metodo Set para asignar un identificador al plato
     * @param id Es un entero con el identificador del plato
     */
    public void setId(int id) {
       this.id = id;
    }
    
    /**
     * Metodo get del identificador
     * @return identificador del plato
     */
    public int getId() {
       return this.id;
    }
    
    /** 
     * Metodo Set para asignar una lista de recetas con este plato
     * @param recepta Lista de recetas que contienen este plato
     */    
    public void setReceptes(Set<Recepta> recepta) {
       this.receptes = recepta;
    }
    
    /**
     * Metodo get de la variable receptes
     * @return Recetas que tienen este plato
     */
    public Set<Recepta> getReceptes() {
       return this.receptes;
    }
    
    /* Class functions */ 
    /**
     * Sobre escribimos el metodo toString para que nos devuelva el String que nosotros queramos
     * @return La familia de ingredientes printada
     */
    @Override
    public String toString () {
            return "Id Plat : "+this.id+", Nom: "+this.nom+",Descripcio: "+this.descripcio;
    }
}
