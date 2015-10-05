/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

//import CRUD.ChefCRUD;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import CRUD.*;

/**
 *
 * @author dante
 */
public class Practica1 {

    /**
     * @param args the command line arguments
     */
    private static final String MENU = "********* MENU ********\n1 - Insert\n2 - Update\n3 - Delete\n4 - get \n5 - exit\n***********************\n";
    private static final String MENU_TABLES = "********* MENU TABLES ********\n1 - Recepta\n2 - Tipus de plat\n3 - Tipus de menjar\n4 - chef\n5 - Ingredient\n6-Exit\n***********************\n";
    
    private static final InputStreamReader isr = new InputStreamReader(System.in);
    private static final BufferedReader br = new BufferedReader(isr);
    
    public static void main(String[] args) {
        boolean exit = false;
        do{
            System.out.println(MENU_TABLES);
            try{     
                int opt = Integer.parseInt(br.readLine()); 
                if( opt >= 1 && opt <= 5 ){
                    menu(opt);
                }else if( opt == 6){
                    exit = true;
                }else{
                    System.out.println("Error: Opcion incorrecta.");
                }
            }catch(IOException ioe){
                System.out.println("ERROR: No se ha podido leer el campo.");
            }catch(NumberFormatException nfe){
                System.out.println("ERROR: Se esperaba un numero.");
            }
        }while(exit == false);
    }
    
    private static void menu( int opt_menu ) {
        boolean exit = false;
        do{
            System.out.println(MENU);
            try{
                int opt = Integer.parseInt(br.readLine());  
                switch(opt){
                    case 1: if(opt_menu == 1){insertRecepta();}
                            else if(opt_menu == 2){insertPlat();}
                            else if(opt_menu == 3){insertTipusMenjar();}
                            else if(opt_menu == 4){insertChef();}
                            else if(opt_menu == 5){insertIngredient();}
                            break;
                    case 2: if(opt_menu == 1){updateRecepta();}
                            else if(opt_menu == 2){updatePlat();}
                            else if(opt_menu == 3){updateTipusMenjar();}
                            else if(opt_menu == 4){updateChef();}
                            else if(opt_menu == 5){updateIngredient();}
                            break;
                    case 3: if(opt_menu == 1){deleteRecepta();}
                            else if(opt_menu == 2){deletePlat();}
                            else if(opt_menu == 3){deleteTipusMenjar();}
                            else if(opt_menu == 4){deleteChef();}
                            else if(opt_menu == 5){deleteIngredient();}
                            break;
                    case 4: System.out.println("Quieres listar (0) o solo un item (1):");
                            int list = Integer.parseInt(br.readLine());
                            if( list == 0 || list ==1 ){
                                if(opt_menu == 1){listRecepta(list);}
                                else if(opt_menu == 2){listPlat(list);}
                                else if(opt_menu == 3){listTipusMenjar(list);}
                                else if(opt_menu == 4){listChef(list);}
                                else if(opt_menu == 5){listIngredient(list);}
                            }else{
                                System.out.println("Error: Opcion Incorrecta.");
                            }
                            break;
                    case 5: exit = true; break;
                    default: System.out.println("Error: Opcion Incorrecta.");
                }                     
            }catch(NumberFormatException nfe){
                System.out.println("ERROR: Se esperaba un numero.");
            }catch( NullPointerException npe ){
                System.out.println("ERROR: No existe el identificador.");
            }catch( IOException ioe ){
                System.out.println("ERROR: No se ha podido el campo.");
            }
        }while(exit == false);
    }
    
    private static void insertChef() throws IOException{
        ChefCRUD chefCRUD = new ChefCRUD(); 
        System.out.println("Dime el nombre del chef:");
        String nom = br.readLine();
        System.out.println("Dime la cantidad de estrellas que tiene:");
        int est = Integer.parseInt(br.readLine());
        Chef chef = new Chef(nom,est); 
        int ident = chefCRUD.saveChef(chef); 
        // TODO - aqui falta comprobar si se ha insertado
        System.out.println("Chef insertado: "+ident+",nom: "+chef.getNom()+",estrelles: "+chef.getEstrelles());    
    }
    
    private static void listChef( int list ) throws IOException, NullPointerException{
        ChefCRUD chefCRUD = new ChefCRUD();
        if( list == 1 ){
            // listamos 1 elemento
            System.out.println("Identificador del elemento a lista: ");
            int ident = Integer.parseInt(br.readLine());
            Chef c = chefCRUD.getChef(ident); 
            System.out.println("Chef --> ID: " + c.getId() + ", nom: "+c.getNom()+", estrelles: " + c.getEstrelles()); 
        }else{
            // listamos todos los elementos
            List<Chef> listachefs = chefCRUD.getListChef();
            System.out.println("Hay " + listachefs.size() + " chefs en la base de datos");  
            for(Chef c : listachefs){ 
                System.out.println("--> ID: " + c.getId() + ", nom: "+c.getNom()+", estrelles: " + c.getEstrelles()); 
            }   
        }          
    }
    
    private static void updateChef(){
        System.out.println("update chef");   
    }
    
    private static void deleteChef(){
        System.out.println("delete chef");     
    }
    
    private static void insertRecepta(){
    /*    platCRUD platCRUD = new platCRUD(); 
        System.out.println("Dime el nombre del chef:");
        String nom = br.readLine();
        System.out.println("Dime la cantidad de estrellas que tiene:");
        int est = Integer.parseInt(br.readLine());
        Plat plat = new Plat(nom,est); 
        int ident = platCRUD.savePlat(plat); 
        // TODO - aqui falta comprobar si se ha insertado
        System.out.println("Chef insertado: "+ident+",nom: "+chef.getNom()+",estrelles: "+chef.getEstrelles());     
    */}
    
    private static void deleteRecepta(){
        System.out.println("delete recepta");
    }
    
    private static void updateRecepta(){
        System.out.println("update recepta");   
    }
    
    private static void listRecepta( int list ){
        System.out.println("list recepta");     
    }
    
    private static void insertPlat(){
        System.out.println("insert plat");   
    }
    
    private static void deletePlat(){
        System.out.println("delete plat");
    }
    
    private static void updatePlat(){
        System.out.println("update plat");   
    }
    
    private static void listPlat( int list ){
        System.out.println("list plat");     
    }
    
    private static void insertTipusMenjar(){
        System.out.println("insert TipusMenjar");   
    }
    
    private static void deleteTipusMenjar(){
        System.out.println("delete TipusMenjar");
    }
    
    private static void updateTipusMenjar(){
        System.out.println("update TipusMenjar");   
    }
    
    private static void listTipusMenjar( int list ){
        System.out.println("list TipusMenjars");     
    }
    
    private static void insertIngredient(){
        System.out.println("insert Ingredient");   
    }
    
    private static void deleteIngredient(){
        System.out.println("delete Ingredient");
    }
    
    private static void updateIngredient(){
        System.out.println("update Ingredient");   
    }
    
    private static void listIngredient( int list ){
        System.out.println("list Ingredient");     
    }
}
	/*ChefCRUD chefCRUD = new ChefCRUD(); 
        Chef chefsave = null;  
        int idAEliminar = 0;  

        //Creamos 3 chefs
        Chef chef1 = new Chef("jacint", 3); 
        Chef chef2 = new Chef("carlos", 3);
        Chef chef3 = new Chef("copi", 3);

        //Guardamos las tres instancias, guardamos el id del chef1 para usarlo posteriormente 
        idAEliminar = chefCRUD.saveChef(chef1); 
        chefCRUD.saveChef(chef2); 
        chefCRUD.saveChef(chef3);  

        //Modificamos el chef 2 y lo actualizamos 
        chef2.setNom("Jacint act"); 
        chefCRUD.updateChef(chef2);  

        //Recuperamos el chef1 de la base de datos 
        chefsave = chefCRUD.getChef(idAEliminar); 
        System.out.println("Recuperamos a " + chefsave.getNom());  

        //Eliminamos al chefsave (que es el contacto3) 
        chefCRUD.deleteChef(chefsave);  

        //Obtenemos la lista de contactos que quedan en la base de datos y la mostramos 
        List<Chef> listachefs = chefCRUD.getListChef();
        System.out.println("Hay " + listachefs.size() + "chefs en la base de datos");  

        for(Chef c : listachefs) 
        { 
            System.out.println("-> " + c.getNom()); 
        } */