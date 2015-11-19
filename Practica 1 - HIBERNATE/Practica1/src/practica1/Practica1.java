/*
 * Practica1.java
 */
package practica1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import CRUD.*;
import java.util.HashSet;
import org.hibernate.Session;

/**
 * Programa principal Practica1
 * @author: Carlos Navas, Jacint Moya 
 * @version: 1.0
 */
public class Practica1 {
    // Atributos globales
    private static final String MENU = "(******** MENU ********)\n1 - Insert\n2 - Update\n3 - Delete\n4 - listar \n5 - exit\n***********************\nEscoge una opcion: ";
    private static final String MENU_TABLES = "(******** MENU TABLES ********)\n1 - Receta\n2 - Tipo de plato\n3 - Tipo de Comida\n4 - chef\n5 - Ingrediente\n6 - Exit\n***********************\nEscoge una opcion: ";
    
    private static final InputStreamReader isr = new InputStreamReader(System.in);
    private static final BufferedReader br = new BufferedReader(isr); 
    
    // Metodo main principal
    public static void main(String[] args) {
        boolean exit = false;
        boolean logginOK = login();                          // Nos logeamos antes de hacer nada
        if( logginOK == true ){                             
            do{
                System.out.print(MENU_TABLES);
                try{     
                    int opt = Integer.parseInt(br.readLine()); 
                    if( opt >= 1 && opt <= 5 ){             // Control sobre las opciones
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
            System.out.println("\n\n\nPractica 1 - Bases de datos Avanzadas.");
            System.out.println("--------------------------------------");
            System.out.println("Autores --> Carlos Navas & Jacint Moya");
        }else{
            System.out.println("No has podido entrar a la aplicacion, login incorrecto,");
        }
    }
    
    /** 
     * Funcion menu para elegir si queremos insertar, actualizar, borrar o listar un elemento de la bases de datos
     * @param opt_menu La opcion del menu elegida
     * @exception NumberFormatException - Se esperaba un numero.
     * @exception NullPointerException - No existe el identificado.
     * @exception IOException - No se ha podido hacer la operacion.
     * @exception Exception - Imposible apuntar al elemento.
     */    
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
                    case 4: System.out.print("Quieres listar (0) o solo un item (1):");
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
                System.out.println("ERROR: No existe el identificador."+npe);
            }catch( IOException ioe ){
                System.out.println("ERROR: No se ha podido hacer la operacion.");
            }catch(Exception ex){
                System.out.println("ERROR: Imposible apuntar al elemento."+ ex);
            }
        }while(exit == false);
    }
    
    /** 
     * Funcion que hara el login comparando los valores de la tabla usuario de la base de datos
     * con los introducidos
     * @return ok, para saber si los valores coinciden o no.
     */      
    private static boolean login() {
        boolean ok = false;
        try{
            System.out.println("****(-LOGIN-)****");
            System.out.print("User: " );
            String user = br.readLine();
            System.out.print("Pass: " );
            String pass = br.readLine();
            
            Session sesion = ConnectorHB.getSession();                              // Nos conectamos a la sesion
            
            List<Usuari> listUser = sesion.createQuery("from Usuari").list();      // Sacamos una lista con todos los usuarios
            for(Usuari c : listUser){ 
                if( c.getAlias().equals(user) && c.getPass().equals(pass) ){       // comparamos que el login sea correcto, con una sencilla comparacion de strins
                    ok = true;
                    System.out.println("----> BIENVENIDO "+ user +" <----");
                    break;
                }
            }
            sesion.close();              
        }catch(IOException ioe){
            System.out.println("ERROR: No se ha podido leer el campo.");
        }
        return ok;
    }
    
    /** 
     * Funcion que pide los datos necesarios para poder insertar un chef en la bases de datos
     * @throws IOExcepcion
     */       
    private static void insertChef() throws IOException{
        ChefCRUD chefCRUD = new ChefCRUD(); 
        chefCRUD.init();                                    // Iniciamos la conexion
        
        System.out.print("Nombre del chef: ");
        String nom = br.readLine();
        System.out.print("Cantidad de estrellas que tiene: ");
        int est = Integer.parseInt(br.readLine());
        Chef chef = new Chef(nom,est);                      // Creamos el objeto del nuevo chef
        int ident = chefCRUD.saveChef(chef); 
        System.out.println("- Chef insertado: "+ chef.toString());
        
        chefCRUD.close();                                   // Cerramos la conexcion
    }

    /** 
     * Funcion que lista los chef que ahi en la bases de datos
     * @param list es un numero que nos permitira saber si queremos listar solo un elemento o listarlos todos
     * @throws IOExcepcion
     */       
    private static void listChef( int list ) throws IOException{
        ChefCRUD chefCRUD = new ChefCRUD();
        chefCRUD.init();                                                        // Iniciamos la conexion
        
        if( list == 1 ){                                                        // listamos 1 elemento
            System.out.print("Identificador del chef a listar: ");
            int ident = Integer.parseInt(br.readLine());
            Chef c = chefCRUD.getChef(ident); 
            System.out.println("- Chef: "+ c.toString());                       // Mostramos el chef a listar
        }else{                                                                  // listamos todos los elementos
            List<Chef> listachefs = chefCRUD.getListChef();
            System.out.println("Hay " + listachefs.size() + " chefs en la base de datos"); 
            System.out.println("*********** CHEFS ***********");  
            for(Chef c : listachefs){                                           // Recorremos la lista para listar los chefs
                System.out.println("- Chef: "+ c.toString());
            }   
        }   
        
        chefCRUD.close();                                                       // Cerramos la conexion
    }

    /** 
     * Funcion que actualiza los chef de la bases de datos
     * @throws IOExcepcion
     */     
    private static void updateChef() throws IOException{
        ChefCRUD chefCRUD = new ChefCRUD();
         
        listChef( 0 );                                                        // Listamos todos los chefs
        System.out.println("Identificador del elemento a lista: ");
        int ident = Integer.parseInt(br.readLine());
        chefCRUD.init();
        Chef c = chefCRUD.getChef(ident);                                   // Cogemos el chef de la bases de datos
        System.out.println("- Chef : "+ c.toString());
        chefCRUD.close();
        int sortir = 1;
        do{                                                                 // Entramos en un bucle para controlar el error de que nos ponga un atributo que no existe
            System.out.println("1- Nombre.\n2- estrellas.\n");
            System.out.println("Que campo quieres cambiar?: ");
            int ident2 = Integer.parseInt(br.readLine());
            switch(ident2){
                case 1: System.out.println("Nuevo nombre:");
                        String nouNom = (br.readLine());
                        c.setNom(nouNom);                                   // Modificamos el atributo nom del objeto
                        sortir = 0;
                        break;
                case 2: System.out.println("Numero de estrellas:");
                        int nouEstrelles = Integer.parseInt(br.readLine());
                        c.setEstrelles(nouEstrelles);                       // Modificamos el atributo estrellas del objeto
                        sortir = 0;
                        break;
                default: System.out.println("No existe la opcion escogida."); break;
            }
        }while(sortir == 1);
        chefCRUD.init();                                                    // Iniciamos sesion con hibernate para poder actualizar el chef
        chefCRUD.updateChef(c);                                             // Actualizamos el chef 
        System.out.println("Se ha modificado con exito el campo. ");
        System.out.println("- Chef actualizado: "+ c.toString());
        
        chefCRUD.close();                                                   //  Cerramos conexion con hibernate
    }
    
     /** 
     * Funcion que borra un chef de la bases de datos
     * @throws IOExcepcion
     * @throws Excepcion
     * @exception NumberFormatException
     * @exception NullPointerException
     */           
    private static void deleteChef() throws IOException, Exception{
        ChefCRUD chefCRUD = new ChefCRUD();
        listChef(0);                                                            // Listamos todos los chefs
        System.out.print("Que chef quieres eliminar: ");
        int ident = Integer.parseInt(br.readLine());
        chefCRUD.init();
        chefCRUD.deleteChef(ident);                                      // Iniciamos session con hibernate y eliminamos el chef
        chefCRUD.close();
        System.out.println("Se ha eliminado el Chef: "+ ident);
    }
    
    /** 
     * Funcion que pide los datos necesarios para poder insertar una receta en la bases de datos
     * @throws IOExcepcion
     */        
    private static void insertRecepta() throws IOException{
        ReceptaCRUD receptaCRUD = new ReceptaCRUD(); 
        IngredientCRUD ingredientCRUD = new IngredientCRUD(); 
        ChefCRUD chefCRUD = new ChefCRUD(); 
        PlatCRUD platCRUD = new PlatCRUD();
        TipusMenjarCRUD tipusCRUD = new TipusMenjarCRUD();
        
        System.out.println("Dime el nombre de la receta:");
        String nom = br.readLine();
        System.out.println("Cuanto tiempo se tarda en hacerla: (00:00:00): ");
        String temps = br.readLine();
        System.out.println("Dificultat de la recepta; (nombre del 1 - 10): ");
        int dificultat = Integer.parseInt(br.readLine());
        System.out.println("Procesp de elaboracion : ");
        String elaboracio = br.readLine();
        
        HashSet ingredients = new HashSet();
        
        int sortir = 1;                         // Miramos los ingredientes para añadir a la receta
        do{
            listIngredient( 0 );
            System.out.println("Que ingrediente tiene : ");
            int identIngredient = Integer.parseInt(br.readLine());
            ingredientCRUD.init();
            Ingredient ing = ingredientCRUD.getIngredient(identIngredient); 
            ingredientCRUD.close();
            ingredients.add(ing); 
            // TODO - No funciona el metodo contains de la hashset para ver si existe el elemento.
            /*if(!ingredients.contains(ing)){
                ingredients.add(ing);               
            }else{
                System.out.println("El ingrediente esta repetido.");
            }*/
            System.out.println("Quieres añadir otro ingrediente :(1-si,0-no) ");
            sortir = Integer.parseInt(br.readLine());
        }while( sortir == 1 );        
                
        Recepta recepta = new Recepta(nom, temps, dificultat, elaboracio, ingredients);
        // elegimos el chef que hace la receta
        listChef( 0 );
        System.out.println("De que chef es la receta : ");
        int identChef = Integer.parseInt(br.readLine());
        chefCRUD.init();
        Chef chef = chefCRUD.getChef(identChef); 
        chefCRUD.close();
        recepta.setChef(chef);      
        // elegimos el plato de la receta
        listPlat( 0 );
        System.out.println("Que plato tiene : ");
        int identPlat = Integer.parseInt(br.readLine());
        platCRUD.init();
        Plat plat = platCRUD.getPlat(identPlat); 
        platCRUD.close();
        recepta.setPlat(plat);
        // Elegimos el tipo de comida de la receta
        listTipusMenjar( 0 );
        System.out.println("Que tipo de comida es : ");
        int identTipus = Integer.parseInt(br.readLine());
        tipusCRUD.init();
        TipusDeMenjar tipus = tipusCRUD.getTipusDeMenjar(identTipus); 
        tipusCRUD.close();
        recepta.setTipusDeMenjar(tipus);    

        receptaCRUD.init();
        int ident = receptaCRUD.saveRecepta(recepta);               // Insertamos la receta
        receptaCRUD.close();
        System.out.println("Receta insertada con exito: ");
        printRecepta(recepta);
    }
    
    /** 
     * Funcion que muestra los datos de la receta que le pasamos por parametro
     * @param recepta Es la receta que mostraremos por pantalla
     */     
    private static void printRecepta( Recepta recepta ){
        System.out.println("INSERT > ID: " + recepta.getId() + 
                            "\n    - nombre: "+recepta.getNom()+ 
                            "\n    - tiempo: "+recepta.getTemps()+ 
                            "\n    - dificultad: "+recepta.getDificultat()+ 
                            "\n    - elaboracion: "+recepta.getElaboracio()+ 
                            "\n    - chef: "+recepta.getChef().getNom()+ 
                            "\n    - plato: "+recepta.getPlat().getNom()+ 
                            "\n    - tipo de comida: "+recepta.getTipusDeMenjar().getNom()+
                            "\n    - ingredientes:"); 
        for (Ingredient i : recepta.getIngredients()) System.out.println("      *  "+i.getNom());     
    }
    
    /** 
     * Funcion que borra una receta de la bases de datos
     * @throws IOExcepcion
     * @throws Excepcion
     * @throws NumberFormatException
     * @throws NullPointerException
     */           
    private static void deleteRecepta() throws IOException, Exception, NumberFormatException, NullPointerException{
        ReceptaCRUD receptaCRUD = new ReceptaCRUD();
        // Listamos las recetas para escoger una y poder eliminarla
        listRecepta(0);
        System.out.print("Escoge una receta: ");
        int ident = Integer.parseInt(br.readLine());

        receptaCRUD.init();
        receptaCRUD.deleteRecepta(ident);
        receptaCRUD.close();
        System.out.println("Se ha eliminado la receta "+ident);
    }
    
    /** 
     * Funcion que actualiza las recetas de la bases de datos
     * @throws IOExcepcion
     */       
    private static void updateRecepta() throws IOException{
        ReceptaCRUD receptaCRUD = new ReceptaCRUD(); 
        ChefCRUD chefCRUD = new ChefCRUD(); 
        PlatCRUD platCRUD = new PlatCRUD();
        TipusMenjarCRUD tipusCRUD = new TipusMenjarCRUD();
        IngredientCRUD ingCRUD = new IngredientCRUD();
        
        receptaCRUD.init();
        System.out.println("update receta"); 
        listRecepta( 0 );                                         // Listamos las recetas a actualziar
        System.out.print("Identificador del elemento a listar: ");
        int ident = Integer.parseInt(br.readLine());
        Recepta r = receptaCRUD.getRecepta(ident);              //  Escojemos la receta que actualizaremos
        printRecepta( r );
        receptaCRUD.close();
        // Aqui mostraremos un menu para que escoja que campo quiere cambiar de la receta que ha escojido previamente
        int sortir = 1;
        do{
            System.out.println("1- Nombre.\n2- tiempo.\n3- Dificultad\n4- Elaboracion\n5- chef\n6- plato\n7- tipo de comida\n8- nuevo ingrediente\n9- salir");
            System.out.print("Que campo quieres cambiar?: ");
            int ident2 = Integer.parseInt(br.readLine());
            switch(ident2){
                case 1: System.out.print("Nuevo nombre:");
                        String nouNom = (br.readLine());
                        r.setNom(nouNom);                                       // Cambiamos el atributo nombre del objeto
                        sortir = 0;
                        break;
                case 2: System.out.print("Nuevo tiempo:");
                        String nouTemps = br.readLine();
                        r.setTemps(nouTemps);                                   // Cambiamos el atributo tiempo del objeto
                        sortir = 0;
                        break;
                case 3: System.out.print("Nueva dificultad:");
                        int novadif = Integer.parseInt(br.readLine());
                        r.setDificultat(novadif);                               // Cambiamos el atributo dificultad del objeto
                        sortir = 0;
                        break;
                case 4: System.out.print("Nueva elaboracion:");
                        String novaela = br.readLine();
                        r.setElaboracio(novaela);                               // Cambiamos el atributo elaboracion del objeto
                        sortir = 0;
                        break;
                case 5: System.out.println("Nuevo chef:");
                        listChef( 0 );                                            // Mostramos los chefs
                        System.out.print("Que chef es el nuevo : ");
                        int identChef = Integer.parseInt(br.readLine());
                        chefCRUD.init();                                        // Iniciamos sesion para sacar los chef
                        Chef chef = chefCRUD.getChef(identChef);                // Cogemos el nuevo chef que tendra la receta
                        chefCRUD.close();                   
                        r.setChef(chef);                                        // Cambiamos el atributo chef del objeto
                        sortir = 0;
                        break;
                case 6: System.out.println("Nuevo plato:");
                        listPlat( 0 );
                        System.out.println("Que Plato es el nuevo : ");
                        int identPlat = Integer.parseInt(br.readLine());
                        platCRUD.init();                                        // Iniciamos sesion para sacar los chef
                        Plat plat = platCRUD.getPlat(identPlat);                // Cogemos el nuevo palto que tendra la receta 
                        platCRUD.close();
                        r.setPlat(plat);                                        // Cambiamos el atributo plat del objeto 
                        sortir = 0;
                        break;
                case 7: System.out.println("Nuevo tipo de comida:");
                        listTipusMenjar( 0 );
                        System.out.println("Cual es el tipo de comida nuevo : ");
                        int idtipus = Integer.parseInt(br.readLine());
                        tipusCRUD.init();                                       
                        TipusDeMenjar tipus = tipusCRUD.getTipusDeMenjar(idtipus); // Cogemos el nuevo tipo de comida que tendra la receta
                        tipusCRUD.close();
                        r.setTipusDeMenjar(tipus);                              // Cambiamos el atributo tipo de comida del objeto 
                        sortir = 0;
                        break;
                case 8: System.out.println("Nuevo ingrediente:");
                        listIngredient( 0 );
                        System.out.println("Cual es el ingrediente nuevo : ");
                        int iding = Integer.parseInt(br.readLine());
                        ingCRUD.init();                                       
                        Ingredient ingredient = ingCRUD.getIngredient(iding); // Cogemos el nuevo tipo ingrediente
                        ingCRUD.close();
                        r.getIngredients().add(ingredient);
                        sortir = 0;
                        break;
                case 9: sortir = 0; break;
                default: System.out.println("No existe la opcion escogida."); break;
            }
        }while(sortir == 1);
        receptaCRUD.init();
        receptaCRUD.updateRecepta(r);                                   // Actualizamos la receta y mostramos un feedback
        System.out.println("Se ha modificado con exito el campo. ");
        printRecepta(r);
        receptaCRUD.close();
    }
    
    /** 
     * Funcion que lista las recetas que ahi en la bases de datos
     * @param list es un numero que nos permitira saber si queremos listar solo un elemento o listarlos todos
     * @throws IOExcepcion
     */          
    private static void listRecepta( int list ) throws IOException{
        ReceptaCRUD receptaCRUD = new ReceptaCRUD();
        receptaCRUD.init();
        if( list == 1 ){
            // listamos 1 elemento
            System.out.print("Identificador de la receta a listar: ");
            int ident = Integer.parseInt(br.readLine());
            Recepta c = receptaCRUD.getRecepta(ident); 
            printRecepta(c);
        }else{
            // listamos todos los elementos
            List<Recepta> listareceptes = receptaCRUD.getListRecepta();
            System.out.println("Hay " + listareceptes.size() + " recetas en la base de datos");  
            for(Recepta c : listareceptes){ 
                printRecepta(c);
            }     
        } 
        receptaCRUD.close();
    }
    
    /** 
     * Funcion que pide los datos necesarios para poder insertar un plato en la bases de datos
     * @throws IOExcepcion
     */         
    private static void insertPlat() throws IOException{
        PlatCRUD platCRUD = new PlatCRUD(); 
        System.out.print("Nombre del plato: ");
        String nom = br.readLine();
        System.out.print("Descripcion del plato: ");
        String desc = br.readLine();
        Plat plat = new Plat(nom,desc);                     // Creamos un nuevo plato para insertar en la base de datos
        platCRUD.init();                                    // Iniciamos la conexion
        int ident = platCRUD.savePlat(plat);                // Insertamos el plato en la base de datos
        platCRUD.close();                                   // Cerramos la conexion
        System.out.println("- Plato insertado: "+ plat.toString()); 
    }
    
     /** 
     * Funcion que borra un plato de la bases de datos
     * @throws IOExcepcion
     * @throws Excepcion
     * @throws NumberFormatException
     * @throws NullPointerException
     */   
    private static void deletePlat() throws IOException, Exception, NumberFormatException, NullPointerException{
        PlatCRUD platCRUD = new PlatCRUD();

        // Listamos los platos para que escoja cual quiere eliminar
        listPlat(0);
        System.out.print("Que receta quieres eliminar: ");
        int ident = Integer.parseInt(br.readLine());
        platCRUD.init();
        platCRUD.deletePlat(ident);
        platCRUD.close();
        System.out.println("- Plato Elimiando: "+ ident);
    }
    
    /** 
     * Funcion que actualiza los platos de la bases de datos
     * @throws IOExcepcion
     */      
    private static void updatePlat() throws IOException{   
        PlatCRUD platCRUD = new PlatCRUD(); 
        System.out.println("update plat"); 
        listPlat(0);                                                               // Mostramos los platos
        System.out.println("Identificador del elemento a lista: ");
        int ident = Integer.parseInt(br.readLine());
        platCRUD.init();                                                        // Iniciamos sesion con hibernate
        Plat p = platCRUD.getPlat(ident);                                       // Cogemos el plato adecuado para actualizar
        platCRUD.close();                                                       // Cerramos la sesion
        System.out.println("Plato a actualitzar: "+ p.toString());
        
        int sortir = 1;                                                         
        do{                                                                     // Miramos todos los campos a actualizar
            System.out.println("1- Nombre.\n2- descripcion.\n");
            System.out.println("Que campo quieres cambiar?: ");
            int ident2 = Integer.parseInt(br.readLine());
            switch(ident2){
                case 1: System.out.println("Nuevo nombre:");
                        String nouNom = (br.readLine());
                        p.setNom(nouNom);                                   
                        sortir = 0;
                        break;
                case 2: System.out.println("Nueva descripcion:");
                        String novaDescripcio = br.readLine();
                        p.setDescripcio(novaDescripcio);
                        sortir = 0;
                        break;
                default: System.out.println("No existe la opcion escogida."); break;
            }
        }while(sortir == 1);
        platCRUD.init();
        platCRUD.updatePlat(p);                                                 // Actualizamos el plato
        platCRUD.close();
        System.out.println("Se ha modificado con exito el campo. ");
        System.out.println("- Update Plat: "+ p.toString());
    }
    
    /** 
     * Funcion que lista los platos que ahi en la bases de datos
     * @param list es un numero que nos permitira saber si queremos listar solo un elemento o listarlos todos
     * @throws IOExcepcion
     */          
    private static void listPlat( int list ) throws IOException{
        PlatCRUD platCRUD = new PlatCRUD(); 
        platCRUD.init();
        if( list == 1 ){
            // listamos 1 elemento
            System.out.print("Identificador del plato a listar: ");
            int ident = Integer.parseInt(br.readLine());
            Plat p = platCRUD.getPlat(ident); 
            System.out.println("Plato --> "+ p.toString());
        }else{
            // listamos todos los elementos
            List<Plat> llistaplats = platCRUD.getListPlat();
            System.out.println("Hay " + llistaplats.size() + " platos en la base de datos"); 
            System.out.println("*********** PLATS ***********");  
            for(Plat p : llistaplats){ 
                System.out.println("Plato --> "+ p.toString());
            }   
        }    
        platCRUD.close();
    }
    
    /** 
     * Funcion que pide los datos necesarios para poder insertar un tipo de comida en la bases de datos
     * @throws IOExcepcion
     */         
    private static void insertTipusMenjar() throws IOException{
        TipusMenjarCRUD tipusMenjarCRUD = new TipusMenjarCRUD(); 
        tipusMenjarCRUD.init();                                                 // Iniciamos una nueva conexion
        System.out.print("Nombre del tipo de comida: ");
        String nom = br.readLine();
        System.out.print("Descripcion del tipo de comida: ");
        String desc = br.readLine();
        TipusDeMenjar tipus = new TipusDeMenjar(nom,desc);                      // Creamos un tipo de comida nuevo para insertar en la base de datos
        int ident = tipusMenjarCRUD.saveTipusDeMenjar(tipus);                   // Insertamos el objeto en la base de datos
        System.out.println("- Tipo de comida insertado: "+ tipus.toString());    
        tipusMenjarCRUD.close();                                                // Cerramos la conexion
    }
    
     /** 
     * Funcion que borra un tipo de comida de la bases de datos
     * @throws IOExcepcion
     * @throws Excepcion
     * @exception NumberFormatException
     * @exception NullPointerException
     */               
    private static void deleteTipusMenjar() throws IOException, Exception {
        TipusMenjarCRUD tipusMenjarCRUD = new TipusMenjarCRUD();
        tipusMenjarCRUD.init();

        // Listamos el tipo de comida para que escoja cual eliminar
        listTipusMenjar(0);
        System.out.print("Que receta quieres eliminar: ");
        int ident = Integer.parseInt(br.readLine());
        tipusMenjarCRUD.deleteTipusDeMenjar(ident);
        System.out.println("- Tipo de comida eliminiado: "+ ident);
        tipusMenjarCRUD.close();
    }

    /** 
     * Funcion que actualiza los tipos de comida de la bases de datos
     * @throws IOExcepcion
     */         
    private static void updateTipusMenjar() throws IOException{
        TipusMenjarCRUD tipusMenjarCRUD = new TipusMenjarCRUD(); 
        System.out.println("update tipo de comida"); 
        listTipusMenjar( 0 );
        System.out.println("Identificador del elemento a listar: ");
        int ident = Integer.parseInt(br.readLine());
        tipusMenjarCRUD.init();
        TipusDeMenjar t = tipusMenjarCRUD.getTipusDeMenjar(ident);
        tipusMenjarCRUD.close();
        System.out.println("- Tipo de Menjar --> "+ t.toString());
        int sortir = 1;
        // Entramos en un bucle para elegir el atributo del objeto a cambiar y asi controlar los errores
        do{
            System.out.println("1- Nombre.\n2- descripcion.\n");
            System.out.println("Que campo quieres cambiar?: ");
            int ident2 = Integer.parseInt(br.readLine());
            switch(ident2){
                case 1: System.out.println("Nuevo nombre:");
                        String nouNom = (br.readLine());
                        t.setNom(nouNom);                                       // Metemos el nuevo nombre en el objeto
                        sortir = 0;
                        break;
                case 2: System.out.println("Nueva descripcion:");
                        String novaDescripcio = br.readLine();
                        t.setDescripcio(novaDescripcio);                        // Metemos la nueva descripcion en el objeto
                        sortir = 0;
                        break;
                default: System.out.println("No existe la opcion escogida."); break;
            }
        }while(sortir == 1);
        tipusMenjarCRUD.init();
        tipusMenjarCRUD.updateTipusDeMenjar(t);                                 // Actualizamos el tipo de comida y mostramos un feedback
        tipusMenjarCRUD.close();
        System.out.println("Se ha modificado con exito el campo. ");
        System.out.println("- Update tipo de comida: "+ t.toString());
    }
    
    /** 
     * Funcion que lista los tipos de comida que ahi en la bases de datos
     * @param list es un numero que nos permitira saber si queremos listar solo un elemento o listarlos todos
     * @throws IOExcepcion
     */          
    private static void listTipusMenjar( int list ) throws IOException{
        TipusMenjarCRUD tipusCRUD = new TipusMenjarCRUD(); 
        tipusCRUD.init();
        if( list == 1 ){
            // listamos 1 elemento
            System.out.print("Identificador del tipo de comida a listar: ");
            int ident = Integer.parseInt(br.readLine());
            TipusDeMenjar i = tipusCRUD.getTipusDeMenjar(ident); 
            System.out.println("--> : "+ i.toString());
        }else{
            // listamos todos los elementos
            List<TipusDeMenjar> llistaTipusDeMenjar = tipusCRUD.getListTipusDeMenjar();
            System.out.println("Hay " + llistaTipusDeMenjar.size() + " tipos de comida en la base de datos"); 
            System.out.println("*********** TIPUS DE MENJAR ***********");  
            for(TipusDeMenjar i : llistaTipusDeMenjar){ 
                System.out.println("--> : "+ i.toString());
            }   
        }   
        tipusCRUD.close();
    }
    
    /** 
     * Funcion que pide los datos necesarios para poder insertar un ingrediente en la bases de datos
     * @throws IOExcepcion
     */     
    private static void insertIngredient() throws IOException{
        Boolean refrigeracio = false;
        IngredientCRUD ingredientCRUD = new IngredientCRUD(); 
        ingredientCRUD.init();
        System.out.print("Nombre del ingrediente: ");
        String nom = br.readLine();
        System.out.print("Necesita refrigeracion:(1-si)(0-no) : ");
        int refrg = Integer.parseInt(br.readLine());
        if( refrg == 1 ){
            refrigeracio = true;
        }
        Ingredient ing = new Ingredient(nom,refrigeracio);                      // Creamos un objeto ingrediente para insertar
        // Escogemos la familia de ingredientes a la que pertenece
        List<FamiliaIngredient> familiaIngredients = ingredientCRUD.getListFamiliaIngredient();
        listFamiliaIngredients(familiaIngredients);
        int fam = Integer.parseInt(br.readLine());                                // Escogemos la familia de ingrediente
        FamiliaIngredient familia = ingredientCRUD.getFamiliaIngredient(fam);  

        ing.setFamiliaIngredient(familia);                                      // Insertamos la familia de ingrediente en el ingrediente
        int ident = ingredientCRUD.saveIngredient(ing);                         // Insertamos el ingrediente
        System.out.println("- Ingrediente insertado: "+ ing.toString());  
        ingredientCRUD.close();
    }
    
    /** 
     * Funcion que borra un ingrediente de la bases de datos
     * @throws IOExcepcion
     * @throws Excepcion
     * @exception NumberFormatException
     * @exception NullPointerException
     */             
    private static void deleteIngredient() throws IOException, Exception/*, NumberFormatException, NullPointerException*/ {
        IngredientCRUD ingredientCRUD = new IngredientCRUD();
        ingredientCRUD.init();
        // Listamos los ingredientes para que escoja cual eliminar
        listIngredient(0);
        System.out.print("Que ingrediente quieres eliminar: ");
        int ident = Integer.parseInt(br.readLine());
        ingredientCRUD.deleteIngredient(ident);
        System.out.println("- Ingrediente eliminado: "+ ident);
        ingredientCRUD.close();
    }
    
    /** 
     * Funcion que actualiza los ingredientes de la bases de datos
     * @throws IOExcepcion
     */        
    private static void updateIngredient() throws IOException{
        IngredientCRUD ingredientCRUD = new IngredientCRUD(); 
        
        System.out.println("UPDATE ingrediente"); 
        listIngredient( 0 );                                                                // listamos los ingredientes
        System.out.println("Identificador del elemento a actualizar: ");
        int ident = Integer.parseInt(br.readLine());
        ingredientCRUD.init();
        Ingredient i = ingredientCRUD.getIngredient(ident);                             // Sacamos el elemento que queremos actualizar
        System.out.println("- Ingrediente: "+ i.toString());
        ingredientCRUD.close();
        int sortir = 1;
        // Entramos en un bucle para determinar que campo quieres cambiar del ingrediente
        do{
            System.out.println("1- Nombre.\n2- refrigeracion.\n3 - Familia de Ingrediente\n");
            System.out.println("Que campo quieres cambiar?: ");
            int ident2 = Integer.parseInt(br.readLine());
            switch(ident2){
                case 1: System.out.println("Nuevo nombre:");
                        String nouNom = (br.readLine());
                        i.setNom(nouNom);
                        sortir = 0;
                        break;
                case 2: Boolean refr = false;
                        System.out.println("Necessita refrigeracion (1-si,0-no):");
                        int nouRefrigeracio = Integer.parseInt(br.readLine());
                        if( nouRefrigeracio == 1){
                            refr = true;
                        }
                        i.setRefrigeracio(refr);
                        sortir = 0;
                        break;
                case 3: 
                        // Escogemos la familia de ingrediente a la que pertenece
                        ingredientCRUD.init();
                        List<FamiliaIngredient> familiaIngredients = ingredientCRUD.getListFamiliaIngredient();
                        ingredientCRUD.close();
                        listFamiliaIngredients(familiaIngredients);                                      // Listamos familia de ingredientes
                        int fam = Integer.parseInt(br.readLine());
                        ingredientCRUD.init();
                        FamiliaIngredient familia = ingredientCRUD.getFamiliaIngredient(fam);       // Cogemos la familia de ingrediente
                        ingredientCRUD.close();
                        i.setFamiliaIngredient(familia);                                            // Metemos la familia de ingrediente en el ingrediente
                        sortir = 0;
                        break;
                default: System.out.println("No existe la opcion escogida."); break;
            }
        }while(sortir == 1);
        ingredientCRUD.init();                                                      // Iniciamos la conexion
        ingredientCRUD.updateIngredient(i);                                         // Actualizamos el ingrediente y cerramos conexion
        System.out.println("Se ha modificado con exito el campo. ");
        System.out.println("- Ingrediente a actualitzar: "+ i.toString());
        ingredientCRUD.close();
    }
    
    /** 
     * Funcion que lista los ingredientes que ahi en la bases de datos
     * @param list es un numero que nos permitira saber si queremos listar solo un elemento o listarlos todos
     * @throws IOExcepcion
     */          
    private static void listIngredient( int list ) throws IOException{
        IngredientCRUD ingredientCRUD = new IngredientCRUD(); 
        ingredientCRUD.init();
        if( list == 1 ){
            // listamos 1 elemento
            System.out.print("Identificador del ingredient a listar: ");
            int ident = Integer.parseInt(br.readLine());
            Ingredient i = ingredientCRUD.getIngredient(ident); 
            System.out.println("--> : "+ i.toString());
        }else{
            // listamos todos los elementos
            List<Ingredient> llistaIngredients = ingredientCRUD.getListIngredient();
            System.out.println("Hay " + llistaIngredients.size() + " ingredients en la base de datos"); 
            System.out.println("*********** INGREDIENTS ***********");  
            for(Ingredient i : llistaIngredients){ 
                System.out.println("--> : "+ i.toString());
            }   
        }    
        ingredientCRUD.close();
    }
    
    /** 
     * Funcion que lista la familia de ingredientes que ahi en la bases de datos
     * @param list es un numero que nos permitira saber si queremos listar solo un elemento o listarlos todos
     * @throws IOExcepcion
     * @throws NullPointerException
     */          
    private static void listFamiliaIngredients(List<FamiliaIngredient> familiaIngredients){
        System.out.println("***** Familia de ingredientes *****");
        for(FamiliaIngredient f : familiaIngredients){ 
            System.out.println("--> : "+ f.toString());
        }  
        System.out.print("De que familia es el ingrediente: ");     
    }
}