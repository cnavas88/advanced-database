/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

/**
 *
 * @author dante
 */
public class TipusDeMenjar  implements java.io.Serializable{
    /* Attributes */
    private int id;
    private String nom;
    private String descripcio;
    
    /* Constructors */
    public TipusDeMenjar(){  }
    
    /* Constructor with parameters */
    public TipusDeMenjar( String nom, String descripcio ){
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
    
    /* Class functions */     
}
