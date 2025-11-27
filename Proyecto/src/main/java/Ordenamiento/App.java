/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ordenamiento;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ricoy
 */
public class App {
    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws IOException{
        String rutaCsv = "C:\\Users\\ricoy\\OneDrive\\Documentos\\NetBeansProjects\\Proyecto\\Clientes.csv";
        
        CsvClienteRepository repositorio = new CsvClienteRepository(rutaCsv);
        ClienteService servicio = new ClienteService(repositorio);

        try {
            servicio.cargarDesdeCsv();
            System.out.println("\n=================================");
            System.out.println("Clientes cargados automaticamente \n");
        } catch (RepositorioException e) {
            System.err.println("Erro del repositorio al cargar automaticamente" + e.getMessage());
        }
        boolean salir = false;

        
        while(!salir){
            mostrarMenu();
            int opcion = leerEntero("elige una opcion: ");
            
            try{
                switch (opcion) {
                    
                    case 1 -> mostrarClientes(servicio);
                    case 2 -> ordenarClientes(servicio);
                    case 3 -> agregarClientes(servicio);
                    case 4 -> guardarClientes(servicio);
                    case 0 -> { 
                        if(servicio.haycambios()){
                            System.out.println("hay cambios sin guardar. desas guardar antes de salir(S/N): ");
                            String respuesta = sc.nextLine().trim().toLowerCase();
                            if(respuesta.equals("S")|| respuesta.equals("s")){
                                try {
                                    servicio.guardarEnCsv();
                                    System.out.println("cambios guardados correctamente.");
                                } catch (RepositorioException e) {
                                    System.err.println("Error al guardar "+ e.getMessage());
                                }
                            }
                        }
                        salir = true;
                        System.out.println("saliendo del programa ");
                    }
                    default -> System.out.println("opcion no valida. intente de nuevo");
                }
            }catch(RepositorioException e){
                System.err.println("Error de repositorio: " + e.getMessage());
            }catch(IllegalArgumentException e){
                System.err.println("Error de validacion: " + e.getMessage());
            }catch(Exception e){
                System.err.println("Ocurrio un error inesperado: " + e.getMessage());
            }
            System.out.println();
        }
    }

    

    private static void mostrarClientes(ClienteService servicio) {
        if(servicio.estaVacio()){
            System.out.println("no hay clientes cargador actualmente.");
            return;
        }
        List<Cliente> clientes = servicio.obtenerClientes();
        System.out.println("=== LISTA DE CLIENTES ===");
        for(Cliente c: clientes){
            System.out.println(c);
        }
    }

    private static void ordenarClientes(ClienteService servicio) {
        if(servicio.estaVacio()){
            System.out.println("No hay clientes para ordenar. cargar primero el CSV o agrega clientes.");
            return;
        }
        
        servicio.ordenarPorClave();
        System.out.println("Clientes ordenador por clave usando bubble sort");
    }

    private static void agregarClientes(ClienteService servicio) {
        int clave = leerEntero("Clave del cliente (entero): ");
        String nombre = leerTexto("Nombre del cliente: ");
        double saldo = leerDouble("Saldo del cliente: ");
        String fechaCreacion = leerTexto("Fecha de creación (ej. 2020-01-01): ");
        String fechaUltMov = leerTexto("Fecha del último movimiento (ej. 2024-01-01): ");

        Cliente nuevo = new Cliente(clave, nombre, saldo, fechaCreacion, fechaUltMov);
        servicio.agregarCliente(nuevo);
        System.out.println("Cliente agregado correctamente.");
        // metodo para guardar
    }

    private static void guardarClientes(ClienteService servicio) throws RepositorioException {
        if(servicio.estaVacio()){
            System.out.println("no hay clientes para guardar.");
            return;
        }
        
        servicio.guardarEnCsv();
        System.out.println("Clientes guardados correctamente en el archivo csv");
    }

    private static void mostrarMenu() {
        System.out.println("========= MENÚ CLIENTES =========");
        System.out.println("1. Mostrar clientes actuales");
        System.out.println("2. Ordenar clientes por clave (Burbuja)");
        System.out.println("3. Agregar nuevo cliente");
        System.out.println("4. Guardar clientes en CSV");
        System.out.println("0. Salir");
        System.out.println("=================================");
    }

    private static int leerEntero(String elige_una_opcion_) {
        while(true){
            System.out.println(elige_una_opcion_);
            try{
                int valor = Integer.parseInt(sc.nextLine().trim());
                return valor;
            }catch(NumberFormatException e){
                System.out.println("Entrada invalida. debes escribir un numero entero. ");
            }
        }
    
    }
    private static double leerDouble(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                double valor = Double.parseDouble(sc.nextLine().trim());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Debes escribir un número (puede llevar decimales).");
            }
        }
    }
    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }
}
