/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ordenamiento;

/**
 *
 * @author ricoy
 */
public class Cliente implements Comparable<Cliente> {
    private int clave;
    private String nombre;
    private double saldo;
    private String fechaCreacion;
    private String fechaUltimoMovimiento;

    public Cliente(int clave, String nombre, double saldo, String fechaCreacion, String fechaUltimoMovimiento) {
        this.clave = clave;
        this.nombre = nombre;
        this.saldo = saldo;
        this.fechaCreacion = fechaCreacion;
        this.fechaUltimoMovimiento = fechaUltimoMovimiento;
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaUltimoMovimiento() {
        return fechaUltimoMovimiento;
    }

    public void setFechaUltimoMovimiento(String fechaUltimoMovimiento) {
        this.fechaUltimoMovimiento = fechaUltimoMovimiento;
    }

    
    @Override
    public String toString() {
        return "Cliente{" + 
                "clave=" + clave + 
                ", nombre=" + nombre + 
                ", saldo=" + saldo +
                ", fechaCreacion=" + fechaCreacion + 
                ", fechaUltimoMovimiento=" + fechaUltimoMovimiento + '}';
    }
    public String toCsvline(){
        return clave +","+ nombre +","+ saldo +"," + fechaCreacion + "," +fechaUltimoMovimiento;
    }
    @Override
    public int compareTo(Cliente o) {
        if (o == null) {
            throw new NullPointerException("No se puede comparar con null");
        }
        return Integer.compare(this.clave, o.clave);
    }

}
