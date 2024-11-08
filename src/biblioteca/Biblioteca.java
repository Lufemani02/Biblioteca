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
        String lastName;
        NodoUsuario left, right;

        public NodoUsuario(int cedula, String name, String lastName) {
            this.cedula = cedula;
            this.name = name;
            this.lastName = lastName;
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
                        System.out.println("4. Volver al menu principal");
                        System.out.println("5. Seleccione una opcion: ");
                        opcionLibros = entrada.nextInt();
                        entrada.nextLine();
                        if(opcionLibros == 1){
                                //Buscar Libros
                            System.out.println("Ingrese el ID del libro: ");
                            String id = entrada.nextLine();
                            if(buscarLibro(raizLibros, id)){
                                System.out.println("El libro con ID: " + id + " ya existe");
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
                                        System.out.println("El libro con el ID: " +idEliminar+ " no se puede eliminar porque no existe, seleccione 1 para agregar un libro");
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
                    int opcionUsuarios;
                    do{
                        System.out.println("Menu de usuarios");
                        System.out.println("1. Agregar usuario");    
                        System.out.println("2. Eliminar usuario");    
                        System.out.println("3. Listar usuarios");    
                        System.out.println("4. Volver al menu principal");
                        System.out.println("5. Seleccione una opcion: ");
                        opcionUsuarios = entrada.nextInt();
                        entrada.nextLine();
                        if(opcionUsuarios == 1){
                                //Agregar Usuarios
                            System.out.println("Ingrese la cedula del usuario: ");
                            int cedula = entrada.nextInt();
                            entrada.nextLine();
                            if(buscarUsuario(raizUsuarios, cedula)){
                                System.out.println("El usuario con la cedula:" +cedula+ "ya existe");
                                }else{
                                        System.out.println("Ingrese el primer y segundo nombre del usuario");
                                        String name = entrada.nextLine();
                                        System.out.println("Ingrese los apellidos del usuario");
                                        String lastName = entrada.nextLine();
                                        NodoUsuario nuevoUsuario = new NodoUsuario(cedula, name, lastName);
                                        if (raizUsuarios == null){
                                            raizUsuarios = nuevoUsuario;
                                        }else{
                                            NodoUsuario actual = raizUsuarios;
                                            while(true){
                                                if (cedula<actual.cedula){
                                                    if(actual.left==null){
                                                        actual.left=nuevoUsuario;
                                                        break;
                                                    }
                                                    actual=actual.left;
                                                }else{
                                                    if(actual.right==null){
                                                        actual.right=nuevoUsuario;
                                                        break;
                                                }
                                                    actual=actual.right;
                                            }
                                        }
                                    }
                                        System.out.println("Usuario agregado !!Exitosamente¡¡");
                                        }
                            }else if (opcionUsuarios == 2){
                                //Eliminar Usuarios
                                    System.out.println("Ingrese la cedula del usuario que desea eliminar");
                                    int cedulaEliminar = entrada.nextInt();
                                    if (buscarUsuario (raizUsuarios, cedulaEliminar)){
                                        raizUsuarios = eliminarUsuario(raizUsuarios, cedulaEliminar);
                                        System.out.println("Usuario eliminado");
                                    }else{
                                        System.out.println("El usuario con la cedula: " +cedulaEliminar+ "no se puede eliminar porque no existe, presione 1 para agreagarlo");
                                    }
                            }else if(opcionUsuarios == 3){
                                //Listar Usuarios
                                System.out.println("Usuarios en la biblioteca");
                                System.out.printf("%-10s %-20s %-20s%n" , "Cedula", "Nombres", "Apellidos");
                                listarUsuarios(raizUsuarios);
                            }
                    }while (opcionUsuarios !=4);
                    break;
                    
                case 3:
                    System.out.println("Ingrese el ID del libro que desea solicitar prestado: ");
                    String idPrestar = entrada.next();
                    System.out.println("Ingrese la cedula del usuario");
                    int cedulaPrestar = entrada.nextInt();
                    entrada.nextLine();
                    
                    if(buscarLibro(raizLibros, idPrestar)&& buscarUsuario(raizUsuarios, cedulaPrestar)){
                        NodoLibro libroPrestado = obtenerLibro(raizLibros, idPrestar);
                        if(libroPrestado !=null){
                            librosPrestados.add(libroPrestado);
                            raizLibros = eliminarLibro(raizLibros, idPrestar);
                            System.out.println(String.format("Libro %s prestado exitosamente al usuario con cedula %d", idPrestar, cedulaPrestar));
                        }else{
                            System.out.println("No se puede prestar el libro ya que el usuario o el libro no existen");
                        }
                    }
                    break;
                case 4:
                    System.out.println("Ingrese el ID del libro que desea devolver");
                    String idDevolver = entrada.next();
                    NodoLibro libroDevolver = null;
                    for(NodoLibro libro: librosPrestados){
                        if(libro.id.equals(idDevolver)){
                            libroDevolver=libro;
                            break;
                        }
                    }
                    if(libroDevolver !=null){
                        raizLibros = agregarLibro(raizLibros, libroDevolver);
                        librosPrestados.remove(libroDevolver);
                        System.out.println(String.format("Libro %s (%s) devuelto exitosamente", libroDevolver.id, libroDevolver.name));
                    }else{
                        System.out.println("El libro no se ha prestado");
                    }
                    break;
                case 5:
                    if(librosPrestados.isEmpty()){
                        System.out.println("No hay libros prestados actualmente");
                    }else{
                        System.out.println("Libros prestados: ");
                        System.out.printf("%-10s %-20s %-20s%n","ID","Nombre","Autor");
                        for(NodoLibro libro: librosPrestados){
                            System.out.printf("%-10s %-20s %-20s%n",libro.id, libro.name, libro.auth);
                        }
                    }
                    break;
                case 6:
                    System.out.println("Libros disponibles para prestamo: ");
                    System.out.printf("%-10s %-20s %-20s%n","ID","Nombre","Autor");
                    listarLibros(raizLibros);
                    break;
                case 7:
                    System.out.println("Gracias por usar el sistema de gestion de biblioteca, hasta la proxima");
                    break;
                default:
                    System.out.println("Opcion no valida. Intente de nuevo: ");
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
        public static boolean buscarUsuario (NodoUsuario nodo, int cedula){
            if (nodo == null) return false;
            if(cedula == nodo.cedula)return true;
            return cedula < nodo.cedula ? buscarUsuario (nodo.left, cedula): buscarUsuario(nodo.right, cedula);
        }
         public static NodoUsuario eliminarUsuario (NodoUsuario nodo, int cedula){
            if (nodo == null) return null;
            if(cedula < nodo.cedula) nodo.left = eliminarUsuario (nodo.left, cedula);
            else if (cedula>nodo.cedula) nodo.right = eliminarUsuario(nodo.right, cedula);
            else{
             if (nodo.left == null) return nodo.right;
             if (nodo.right == null) return nodo.left;
            }
            return nodo;
        }
         public static void listarUsuarios (NodoUsuario nodo){
            if(nodo !=null){
                listarUsuarios(nodo.left);
                System.out.printf("%-10s %-20s %-20s%n", nodo.cedula, nodo.name, nodo.lastName);
                listarUsuarios(nodo.right); 
            }
        } 
         public static NodoLibro obtenerLibro(NodoLibro nodo, String id){
             if(nodo == null)return null;
             if(id.equals(nodo.id))return nodo;
             return id.compareTo(nodo.id) < 0 ? obtenerLibro(nodo.left, id): obtenerLibro(nodo.right, id);
         }
         
}      
