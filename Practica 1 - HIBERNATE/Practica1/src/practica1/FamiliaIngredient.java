/*
 * FamiliaIngredient.java
 */
package practica1;

import java.io.Serializable;
import java.util.Set;

/**
 * Esta Clase define los objetos de tipo Familia de Ingredientes.
 * @author: Carlos Navas, Jacint Moya 
 * @version: 1.0
 */
public class FamiliaIngredient implements Serializable{
    // Atributos
    private int id;
    private String nom;
    private String descripcio;
    private Set<Ingredient> ingredients;  // Afegim cardinalitat N
    
    // Constructor vacio
    public FamiliaIngredient(){  }
    
    /** 
     * Constructor de familia de ingredientes con parametros 
     * @param nom El nombre de la familia de ingredientes
     * @param descripcio Es la descripcion de la familia de ingredientes
     */
    public FamiliaIngredient( String nom, String descripcio ){
        this.nom = nom;
        this.descripcio = descripcio;
    }
    
    // Getters and Setters 
    /** 
     * Metodo Set para asignar un nombre a la clase Familia Ingredient
     * @param nom Es un String con el nombre del chef
     */
    public void setNom(String nom) {
       this.nom = nom;
    }
    
    /**
     * Metodo get de la variable nombre
     * @return El nombre de la familia de ingredientes
     */
    public String getNom() {
       return this.nom;
    }
    
    /** 
     * Metodo Set para asignar una descripcion
     * @param descripcio Es un String con la descripcion de la familia
     */
    public void setDescripcio(String descripcio) {
       this.descripcio = descripcio;
    }
    
    /**
     * Metodo get de la variable descripcio
     * @return La descripcion del chef
     */
    public String getDescripcio() {
       return this.descripcio;
    }
    
    /** 
     * Metodo Set para asignar un identificador a la Familia de ingredientes
     * @param id Es un entero con el identificador de la familia de ingredientes
     */
    public void setId(int id) {
       this.id = id;
    }
    
    /**
     * Metodo get del identificador
     * @return identificador de la familia de ingredientes
     */
    public int getId() {
       return this.id;
    }
    
    /**
     * Metodo get de la variable ingredientes
     * @return ingredientes que pertenecen a la familia
     */
    public Set<Ingredient> getIngredients() { 
        return ingredients; 
    } 
    
    /** 
     * Metodo Set para asignar una lista de ingredientes
     * @param ingredients Es una lista con los ingredientes que pertenecen a la familia
     */
    public void setIngredients(Set<Ingredient> ingredients) { 
        this.ingredients = ingredients; 
    }  
    
    /* Class functions */   
    /**
     * Sobre escribimos el metodo toString para que nos devuelva el String que nosotros queramos
     * @return La familia de ingredientes printada
     */
    @Override
    public String toString () {
            return "Id Familia Ingredient : "+this.id+", Nom: "+this.nom+",Descripcio: "+this.descripcio;
    }
}
