/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ordenamiento;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author ricoy
 */
public class ClienteService {
    private final CsvClienteRepository repositorio;
    private List<Cliente> clientes;
    private boolean haycambios= false;
    
    public boolean haycambios(){
        return haycambios;
    }
    public  void marcarCambios(){
        haycambios = true;
    }
    public void limpiarcambios(){
        haycambios = false;
    }
    public ClienteService(CsvClienteRepository repositorio){
        this.repositorio = repositorio;
        this.clientes = new ArrayList<>();
    }
    
    public void cargarDesdeCsv() throws RepositorioException, IOException{
        this.clientes = repositorio.cargarClientes();
    }
    
    public void guardarEnCsv() throws RepositorioException{
        repositorio.guardarClientes(clientes);
        limpiarcambios();
    }
    public List<Cliente> obtenerClientes(){
        return Collections.unmodifiableList(clientes);
    }
    public void agregarCliente(Cliente cliente){
        if(cliente == null){
            throw new IllegalArgumentException("el cliente no puede ser null");
        }
        
        for(Cliente c: clientes){
            if(c.getClave() == cliente.getClave()){
                throw new IllegalArgumentException("Ya existe un cliente con la clave: "+ cliente.getClave());
            }
        }
        clientes.add(cliente);
        marcarCambios();
    }
    public boolean estaVacio(){
        return clientes == null || clientes.isEmpty();
    }

    public  void ordenarPorClave() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}