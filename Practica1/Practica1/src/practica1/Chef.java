/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

/**
 * @author dante
 */
public class Chef  implements java.io.Serializable{
    /* Attributes */
    private int id;
    private String nom;
    private int estrelles;
    
    /* Constructors */
    public Chef(){  }
    
    /* Constructor with parameters */
    public Chef( String nom, int estrelles ){
        this.nom = nom;
        this.estrelles = estrelles;
    }
    
    /* Getters and Setters */
    public void setNom(String nom) {
       this.nom = nom;
    }
    public String getNom() {
       return this.nom;
    }
    public void setEstrelles(int estrelles) {
       this.estrelles = estrelles;
    }
    public int getEstrelles() {
       return this.estrelles;
    }
    public void setId(int id) {
       this.id = id;
    }
    public int getId() {
       return this.id;
    }
    
    /* Class functions */ 
    
}
