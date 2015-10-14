/*
 * Recepta.java
 */
package practica1;

import java.io.Serializable;
import java.util.Set;

/**
 * Esta Clase define los objetos de tipo Recepta.
 * @author: Carlos Navas, Jacint Moya 
 * @version: 1.0
 */
public class Recepta  implements Serializable{
    // Atributos
    private int id;
    private String nom;
    private String temps;
    private int dificultat;
    private String elaboracio;
    private Chef chef;                   // Foreign Key cardinalitat 1
    private Plat plat;                   // Foreign Key cardinalitat 1
    private TipusDeMenjar tipusDeMenjar; // Foreign Key cardinalitat 1
    private Set<Ingredient> ingredients; // Foreign key cardinalitat N
    
    // Constructor vacio
    public Recepta(){  }
    
    /** 
     * Constructor de la recepta con parametros 
     * @param nom El nombre de la receta
     * @param temps Tiempo que se tarda en prepararla
     * @param dificultat Dificultad de la preparacion
     * @param elaboracio Proceso de elaboracion
     * @param ingredients Es una lista de ingredientes utilizados en esta receta
     */
    public Recepta( String nom, String temps, int dificultat, String elaboracio, Set<Ingredient> ingredients ){
        this.nom = nom;
        this.temps = temps;
        this.dificultat = dificultat;
        this.elaboracio = elaboracio;
        this.ingredients = ingredients;
    }
    
    // Getters and Setters 
    /** 
     * Metodo Set para asignar un identificador a la receta
     * @param id Es un entero con el identificador de la receta
     */
    public void setid(int id) {
       this.id = id;
    }
    
    /**
     * Metodo get del identificador
     * @return identificador de la receta
     */
    public int getId() {
       return this.id;
    }
    
    /** 
     * Metodo Set para asignar un nombre a la receta
     * @param nom Es un String con el nombre de la receta
     */
    public void setNom(String nom) {
       this.nom = nom;
    }
    
    /**
     * Metodo get de la variable nombre
     * @return El nombre de la receta
     */
    public String getNom() {
       return this.nom;
    }
    
    /** 
     * Metodo Set para asignar el tiempo de preparacion
     * @param temps Es el tiempo de elaboracion
     */
    public void setTemps(String temps) {
       this.temps = temps;
    }
    
    /**
     * Metodo get del Temps
     * @return el tiempo de preparacion
     */
    public String getTemps() {
       return this.temps;
    }
    
    /** 
     * Metodo Set para asignar la dificultat
     * @param dificultat Es la dificultad de elaboracion de la receta
     */
    public void setDificultat(int dificultat) {
       this.dificultat = dificultat;
    }
    
    /**
     * Metodo get de la Dificultat
     * @return Entero con la dificultat de preparacion
     */
    public int getDificultat() {
       return this.dificultat;
    }
    
    /** 
     * Metodo Set para asignar la elaboracion
     * @param elaboracio Es la elaboracion de la receta
     */
    public void setElaboracio(String elaboracio) {
       this.elaboracio = elaboracio;
    }
    
    /**
     * Metodo get de la Elaboracion
     * @return La elaboracion de la receta
     */
    public String getElaboracio() {
       return this.elaboracio;
    }   
    
    /** 
     * Metodo Set para asignar el chef
     * @param chef Es el chef que tiene la receta
     */
    public void setChef(Chef chef) {
       this.chef = chef;
    }
    
    /**
     * Metodo get del Chef
     * @return El chef al que pertenece la receta
     */
    public Chef getChef() {
       return this.chef;
    } 
    
    /** 
     * Metodo Set para asignar el plato
     * @param plat Es el plato utilizado en la receta
     */
    public void setPlat(Plat plat) {
       this.plat = plat;
    }
    
    /**
     * Metodo get del Plat
     * @return Plato que se usa en la receta
     */
    public Plat getPlat() {
       return this.plat;
    }   
    
    /** 
     * Metodo Set para asignar el tipo de comida
     * @param tipusDeMenjar Es el tipo de comida
     */
    public void setTipusDeMenjar(TipusDeMenjar tipusDeMenjar) {
       this.tipusDeMenjar = tipusDeMenjar;
    }
    
    /**
     * Metodo get del Tipo de comida
     * @return El tipo de comida que es la receta
     */
    public TipusDeMenjar getTipusDeMenjar() {
       return this.tipusDeMenjar;
    } 

    /** 
     * Metodo Set para asignar una lista de ingredientes
     * @param ingredient Es la lista de ingredientes que pertenecen a la receta
     */
    public void setIngredients(Set<Ingredient> ingredient) {
       this.ingredients = ingredient;
    }
    
    /**
     * Metodo get de los ingredientes
     * @return lista de los ingredientes usados para preparar esta receta
     */
    public Set<Ingredient> getIngredients() {
       return this.ingredients;
    }    
}
