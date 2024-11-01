package biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class Biblioteca {

    static class NodoLibro{
        String id;
        String name;
        String auth;
        NodoLibro left, right;

        public NodoLibro(String id, String name, String auth) {
            this.id = id;
            this.name = name;
            this.auth = auth;
            this.left = this.right = null;
        }
    }
        
    static class NodoUsuario{
        int cedula;
        String name;
        String latName;
        NodoUsuario left, right;

        public NodoUsuario(int cedula, String name, String latName) {
            this.cedula = cedula;
            this.name = name;
            this.latName = latName;
            this.left = this.right = null;
        }
        
        
    }
    public static void main(String[] args) {        
        NodoLibro raizLibros = null;
        NodoUsuario raizUsuarios = null;
        ArrayList<NodoLibro> librosPrestados = new ArrayList<>();
        Scanner entrada = new Scanner(System.in);
        int menuInicio;
        
        do{
            System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''");
            System.out.println("'' Developed by: Luis Fernando Martinez Nino ''");
            System.out.println("''           Cedula: 1023025659               ''");
            System.out.println("''    SISTEMA DE GESTION DE BIBLIOTECA        ''");
            System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''");  
            System.out.println("1. Menu de Libros");    
            System.out.println("2. Menu de Usuarios");    
            System.out.println("3. Solicitar Libro");    
            System.out.println("4. Devolver libro");
            System.out.println("5. Mostrar libros prestados");
            System.out.println("6. Mostrar libros disponibles");
            System.out.println("7. Salir");
            System.out.println("Seleccione una opcion: ");
            menuInicio = entrada.nextInt();
            
            switch(menuInicio){
                case 1:
                   int opcionLibros;
                    do{
                        System.out.println("Menu de libros");
                        System.out.println("1. Agregar Libro");    
                        System.out.println("2. Eliminar Libro");    
                        System.out.println("3. Listar Libros");    
                        System.out.println("4. Volver al menú principal");
                        System.out.println("5. Seleccione una opcion: ");
                        opcionLibros = entrada.nextInt();
                        entrada.nextLine();
                        if(opcionLibros == 1){
                                //Buscar Libros
                            System.out.println("Ingrese el ID del libro: ");
                            String id = entrada.nextLine();
                            if(buscarLibro(raizLibros, id)){
                                System.out.println("El libro con ID:" +id+ "ya existe");
                                }else{
                                        System.out.println("Ingrese el nombre del libro");
                                        String name = entrada.nextLine();
                                        System.out.println("Ingrese el nombre del autor");
                                        String auth = entrada.nextLine();
                                        NodoLibro nuevoLibro = new NodoLibro(id, name, auth);
                                        raizLibros = agregarLibro(raizLibros, nuevoLibro);
                                        System.out.println("Libro agregado Exitosamente");
                                        }
                            }else if (opcionLibros == 2){
                                //Eliminar Libros
                                    System.out.println("Ingrese el ID del libro que desea eliminar");
                                    String idEliminar = entrada.nextLine();
                                    if (buscarLibro (raizLibros, idEliminar)){
                                        raizLibros = eliminarLibro(raizLibros, idEliminar);
                                    }else{
                                        System.out.println("El libro con el ID: " +idEliminar+ "no se puede eliminar");
                                    }
                            }else if(opcionLibros == 3){
                                //Listar Libros
                                System.out.println("Libros en la biblioteca");
                                System.out.printf("%-10s %-20s %-20s%n" , "ID", "Nombre", "Autor");
                                listarLibros(raizLibros);
                            }
                            
                        
                    }while (opcionLibros !=4);
                    
                    break;
                
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 6:
                    break;
                case 7:
                    break;
            } 
            
        }while (menuInicio !=7);
            
        }
        public static boolean buscarLibro (NodoLibro nodo, String id){
            if (nodo == null) return false;
            if(id.equals(nodo.id))return true;
            return id.compareTo(nodo.id)<0? buscarLibro (nodo.left, id): buscarLibro(nodo.right, id);
        }
        public static NodoLibro agregarLibro (NodoLibro raiz, NodoLibro nuevo){
            if (raiz == null) return nuevo;
            if(nuevo.id.compareTo(raiz.id)<0) raiz.left = agregarLibro(raiz.left, nuevo);
            else raiz.right = agregarLibro(raiz.right, nuevo);
            return raiz;
        }
        public static NodoLibro eliminarLibro (NodoLibro nodo, String id){
            if (nodo == null) return null;
            if(id.compareTo(nodo.id)<0) nodo.left = eliminarLibro(nodo.left, id);
            else if (id.compareTo(nodo.id)>0) nodo.right = eliminarLibro(nodo.right, id);
            else{
             if (nodo.left == null) return nodo.right;
             if (nodo.right == null) return nodo.left;
            }
            return nodo;
        }
        public static void listarLibros (NodoLibro nodo){
            if(nodo !=null){
                listarLibros(nodo.left);
                System.out.printf("%-10s %-20s %-20s%n", nodo.id, nodo.name, nodo.auth);
                listarLibros(nodo.right); 
            }
        }    
}
