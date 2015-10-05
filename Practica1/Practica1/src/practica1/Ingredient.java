/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

/**
 *
 * @author dantel
 */
public class Ingredient  implements java.io.Serializable{
    /* Attributes */
    private int id;
    private String nom;
    private boolean refrigeracio;
    private FamiliaIngredient familiaIngredient; // Foreign Key
    
    /* Constructors */
    public Ingredient(){  }
    
    /* Constructor with parameters */
    public Ingredient( String nom, boolean refrigeracio ){
        this.nom = nom;
        this.refrigeracio = refrigeracio;
    }
    
    /* Getters and Setters */
    public void setid(int id) {
       this.id = id;
    }
    public int getId() {
       return this.id;
    }
    public void setNom(String nom) {
       this.nom = nom;
    }
    public String getNom() {
       return this.nom;
    }
    public void setRefrigeracio(boolean refrigeracio) {
       this.refrigeracio = refrigeracio;
    }
    public boolean getRefrigeracio() {
       return this.refrigeracio;
    }
    public void setIdFamiliaIngredient(FamiliaIngredient familia) {
       this.familiaIngredient = familia;
    }
    public FamiliaIngredient getIdFamiliaIngredient() {
       return this.familiaIngredient;
    }
    
    /* Class functions */ 
}
