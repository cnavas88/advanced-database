/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author dante
 */
public class FamiliaIngredient implements java.io.Serializable{
    /* Attributes */
    private int id;
    private String nom;
    private String descripcio;
    private Set<Ingredient> ingredients;  // Afegim cardinalitat N
    
    /* Constructors */
    public FamiliaIngredient(){  }
    
    /* Constructor with parameters */
    public FamiliaIngredient( String nom, String descripcio ){
        this.nom = nom;
        this.descripcio = descripcio;
    }
    
    /* Getters and Setters */
    public void setNom(String nom) {
       this.nom = nom;
    }
    public String getNom() {
       return this.nom;
    }
    public void setDescripcio(String descripcio) {
       this.descripcio = descripcio;
    }
    public String getDescripcio() {
       return this.descripcio;
    }
    public void setId(int id) {
       this.id = id;
    }
    public int getId() {
       return this.id;
    }
    public Set<Ingredient> getIngredients() { 
        return ingredients; 
    }  

    public void setIngredients(Set<Ingredient> ingredients) { 
        this.ingredients = ingredients; 
    }  

    public void addIngredient(Ingredient ingredient) { 
        this.ingredients.add(ingredient); 
    } 
    
    /* Class functions */     
}
