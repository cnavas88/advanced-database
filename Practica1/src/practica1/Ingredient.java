/*
 * Ingredient.java
 */
package practica1;

import java.io.Serializable;
import java.util.Set;

/**
 * Esta Clase define los objetos de tipo Ingrediente
 * @author: Carlos Navas, Jacint Moya 
 * @version: 1.0
 */
public class Ingredient implements Serializable {
    // Atributos
    private int id;
    private String nom;
    private boolean refrigeracio;
    private FamiliaIngredient familiaIngredient; // Foreign Key cardinalitat 1
    private Set<Recepta> receptes;
    
    // Constructor vacio
    public Ingredient(){  }
    
    /** 
     * Constructor de Ingredientes con parametros
     * @param nom El nombre del ingrediente
     * @param refrigeracio Necesita refrigeracion o no.
     */
    public Ingredient( String nom, boolean refrigeracio ){
        this.nom = nom;
        this.refrigeracio = refrigeracio;
    }
    
    /* Getters and Setters */
    /** 
     * Metodo Set para asignar un identificador a ingredientes
     * @param id Es un entero con el identificador de ingredientes
     */    
    public void setid(int id) {
       this.id = id;
    }
    
    /**
     * Metodo get del identificador
     * @return identificador de ingredientes
     */
    public int getId() {
       return this.id;
    }
    
    /** 
     * Metodo Set para asignar un nombre a la clase Ingredient
     * @param nom Es un String con el nombre del ingrediente
     */
    public void setNom(String nom) {
       this.nom = nom;
    }
    
    /**
     * Metodo get de la varialbe nombre
     * @return El nombre de los ingredientes
     */
    public String getNom() {
       return this.nom;
    }
    
    /** 
     * Metodo Set para asignar si el ingrediente necesita refrigeracion
     * @param refrigeracio Boleano para saber si necesita refrigeracion
     */    
    public void setRefrigeracio(boolean refrigeracio) {
       this.refrigeracio = refrigeracio;
    }
    
    /**
     * Metodo get de la varialbe refrigeracio
     * @return Si necesita refrigeracion
     */
    public boolean getRefrigeracio() {
       return this.refrigeracio;
    }
    
    /** 
     * Metodo Set para asignar la familia de ingrediente al ingrediente
     * @param familia Es la familia de ingrediente
     */  
    public void setFamiliaIngredient(FamiliaIngredient familia) {
       this.familiaIngredient = familia;
    }
    
    /**
     * Metodo get de la varialbe familiaIngredient
     * @return La familia de ingrediente a la que pertenece el ingrediente
     */
    public FamiliaIngredient getFamiliaIngredient() {
       return this.familiaIngredient;
    }  
    
    /** 
     * Metodo Set para asignar las recetas que contienen este ingrediente
     * @param receptes Recetas que tienen este ingrediente
     */  
    public void setReceptes(Set<Recepta> receptes) {
       this.receptes = receptes;
    }
    
    /**
     * Metodo get de la varialbe receptes
     * @return Las recetas a las que pertenece el ingrediente
     */
    public Set<Recepta> getReceptes() {
       return this.receptes;
    }
    
    /* Class functions */
    /**
     * Sobre escribimos el metodo toString para que nos devuelva el String que nosotros queramos
     * @return El ingrediente printada
     */
    @Override
    public String toString () {
            return "Id Ingredient: " + getId() + ", nom: "+getNom()+", refrigeracio: " + getRefrigeracio()+", Familia d'ingredients: " + getFamiliaIngredient().getNom();
    }
}
