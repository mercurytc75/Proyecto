/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ordenamiento;

import java.util.List;

/**
 *
 * @author ricoy
 */
public class BubbleSort {
    /**
     * oordena una lista de elementos que implementan Comparable.
     * Maneja lista null o de tamaño 0/1 de forma segura.
     */
    
    public static <T extends Comparable<T>> void ordenar(List<T> lista){
        if(lista == null || lista.size() < 2){
            return; // nada que ordenar
        }
        boolean intercambio;
        int n = lista.size();
        
        do{
            intercambio = false;
            for(int i = 0; i < n - 1; i++){
                T actual = lista.get(i);
                T siguiente = lista.get(i+1);
                
                if(actual.compareTo(siguiente) > 0){
                    // intercambio
                    lista.set(i, siguiente);
                    lista.set(i+1, actual);
                    intercambio = true;
                }
            }
            n--;// optimixa: el ultimo ya esta en su posicion
        }while(intercambio);
    }
}
