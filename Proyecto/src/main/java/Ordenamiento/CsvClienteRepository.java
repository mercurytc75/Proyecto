/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ordenamiento;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ricoy
 */
public class CsvClienteRepository {
    private final Path rutaArchivo;
    
    public CsvClienteRepository(String rutaArchivo){
        this.rutaArchivo = Paths.get(rutaArchivo);
    }
    /*
    * carga todos los Clientes   del archivo csv
    * lineas invalidas se reportany se omiten, no detienen el programa. 
    */
    public List<Cliente> cargarClientes() throws RepositorioException, IOException{
        List<Cliente> clientes = new ArrayList<>();
        
        if(!Files.exists(rutaArchivo)){
            throw new RepositorioException("El arhivo csv no existe "+ rutaArchivo.toAbsolutePath());
        }
        
        try(BufferedReader br = Files.newBufferedReader(rutaArchivo, StandardCharsets.UTF_8)){
            String linea;
            int numeroLinea=0;
            
            while((linea = br.readLine())!= null){
                numeroLinea++;
                linea = linea.trim();
                if(linea.isEmpty()){
                    //se salta las lineas vacias
                    continue;
                }
                String[] partes = linea.split(",");
                
                if(partes.length < 5){
                    System.err.println("linea " + numeroLinea+ " ignorada: se esperaban 5 campos, se encontraron " + partes.length);
                    continue;
                }
                
                try{
                    int Clave = Integer.parseInt(partes[0].trim());
                    String nombre = partes[1].trim();
                    double saldo = Double.parseDouble(partes[2].trim());
                    String fehaCreacion = partes[3].trim();
                    String fechaUltimoMovimiento = partes[4].trim();
                    
                    Cliente c = new Cliente(Clave, nombre, saldo, fehaCreacion, fechaUltimoMovimiento);
                    clientes.add(c);
                }catch(NumberFormatException e){
                    System.err.println("linea "+ numeroLinea +" ignorada: error de formato numerico -> " + e.getMessage());
                }
            }
            
        }catch(IOException e){
            throw  new RepositorioException("Error al leer el archivo csv: " + rutaArchivo.toAbsolutePath(), e);
        }
        return clientes;
    }
    
    public void guardarClientes(List<Cliente> clientes) throws RepositorioException{
        try(BufferedWriter bw = Files.newBufferedWriter(rutaArchivo,
                StandardCharsets.UTF_8, 
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING)){
            for(Cliente c: clientes){
                bw.write(c.toCsvline());
                bw.newLine();
            }
        }catch(IOException e){
            throw new RepositorioException("Error al escrivir el archivo csv: " + rutaArchivo.toAbsolutePath(), e);
        }
    }
}
