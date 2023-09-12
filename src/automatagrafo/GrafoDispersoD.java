
package automatagrafo;

/**
 *
 * @author Jefferson Lennart Campos Segovia
 */

import java.util.ArrayList;
import java.util.Collections;
//********Clase Nodo*************
class Nodo{
    //********ATRIBUTOS*********
    public Object vertice;
    public Object peso;  
    //********CONSTRUCTORES*********    
    public Nodo(Object vertice, Object peso) {
        this.vertice = vertice;
        this.peso = peso;        
    }  
}
// ********Clase Proceso***********
/* Para poder realizar un proceso como contar o recuperar dato dentro 
    de una expresión lambda*/
class Proceso{    
    public Object objeto;
    public int contador; 
}
public class GrafoDispersoD {
    //*********ATRIBUTOS**************
    private ArrayList<ArrayList<Nodo>> listasDeAdyacencia;
    private ArrayList verticesIniciales;
    //*********CONSTRUCTOR**************
    public GrafoDispersoD(){
        listasDeAdyacencia=new ArrayList<ArrayList<Nodo>>();
        verticesIniciales=new ArrayList();
    }
    //*********MÉTODOS DE PROCESO**************
    // -- Agregar Vértice
    public void agregarVertice(Object vertice){
            verticesIniciales.add(vertice);  
            ArrayList<Nodo> listaAdyacencia=new ArrayList<Nodo>();
            listasDeAdyacencia.add(listaAdyacencia);
    }   
    // -- Agregar arista
    public boolean agregarArista(Object v1,Object v2,Object peso){
        // -- Recuperar índice de v1 y v2
        int i=verticesIniciales.indexOf(v1);
        int j=verticesIniciales.indexOf(v2);
        // -- Verificar que vértices existan
        if (i!=-1 && j!=-1) {
            // -- Crear nodo
            Nodo nodo=new Nodo(v2,peso);
            listasDeAdyacencia.get(i).add(nodo);       
            return true;
        }
        else{
            return false;
        }
    }
    // -- Verificar si existe arista
    public boolean existeArista(Object v1,Object v2){
        // -- Recuperar indices de vértices
        int i=verticesIniciales.indexOf(v1);
        int j=verticesIniciales.indexOf(v2);
        // -- Verificar que vértices existan
        if (i!=-1 && j!=-1) {
            // -- Crear objeto proceso
            Proceso flagEncontrado=new Proceso();
            flagEncontrado.objeto=false;
            listasDeAdyacencia.get(i).forEach((Nodo nodo)->
                    {
                        if(nodo.vertice.equals(v2)){
                            flagEncontrado.objeto=true;
                        }
                    });
            // -- Devolver valor
            return (boolean)flagEncontrado.objeto;
        }
        else{
            return false;
        }
    }
    // -- Eliminar arista
    public boolean eliminarArista(Object v1,Object v2,Object peso){
        // -- Recuperar indices de vértices
        int i=verticesIniciales.indexOf(v1);
        int j=verticesIniciales.indexOf(v2);
        // -- Verificar que vértices existan
        if(i!=-1 && j!=-1){ 
            // -- Crear objeto proceso
            Proceso objetoAux=new Proceso();            
            // -- procesar lista adyacencia del vértice v1
            listasDeAdyacencia.get(i).forEach((Nodo nodo)->
                {
                    if(nodo.vertice.equals(v2) && nodo.peso.equals(peso)){                        
                        objetoAux.objeto=nodo;
                    }
                });
            listasDeAdyacencia.get(i).remove(objetoAux.objeto);
            return objetoAux==null?false:true;
        }
        else{
            return false;
        }
    }
    // -- Vecindad de un vértice
    public ArrayList vecindadIncidente(Object v){
        // -- Crear lista para almacenar nodos
        ArrayList vecindad=new ArrayList();
        // -- Recuperar índice del vértice si existe
        int i=verticesIniciales.indexOf(v);
        if (i!=-1) {
            // -- procesar lista adyacencia del vértice
            listasDeAdyacencia.get(i).forEach((Nodo nodo)->
                {
                    vecindad.add(nodo.vertice);
                });
        }
        // -- Retonar vecindad
        return vecindad;        
    }
    // -- Grado entrante de un vértice
    public int gradoEntrante(Object vertice){
        // -- Recuperar índice de vértice
        int i=verticesIniciales.indexOf(vertice);
        // -- Verificar que vértice exista
        if (i!=-1) {
            // -- Para almacenar todos los vértices
            ArrayList aux=new ArrayList();
            // -- Procesar la lista de vértices
            verticesIniciales.forEach((Object v)->
                    {
                        aux.addAll(vecindadIncidente(v));
                    }                
                    );
            // -- Retornar número vértices repetidos        
            return Collections.frequency(aux, vertice);
        }
        else{
            return -1;
        }
    }
    // -- Grado saliente de un vértice
    public int gradoSaliente(Object vertice){
        // -- Recuperar índice de vértice
        int i=verticesIniciales.indexOf(vertice);
        // -- Verificar que vértice exista
        if (i!=-1) {
            return vecindadIncidente(vertice).size();
        } else {
            return -1;
        }        
    }
    // -- Nro de aristas
    public int nroAristas(){
        // -- Contador
        int n=0;
        // -- Procesar lista de vértices
        for (int i = 0; i < verticesIniciales.size(); i++) {
            n+=listasDeAdyacencia.get(i).size();
        }
        // -- Retornar nro aristas
        return n;
    }
    // -- Recuperar peso de una arista
    public ArrayList pesoArista(Object v1,Object v2){
        // -- Para almacenar aristas si es multigrafo
        ArrayList pesos=new ArrayList();
        // -- Recuperar indices de vértices
        int i=verticesIniciales.indexOf(v1);
        int j=verticesIniciales.indexOf(v2);
        // -- Verificar que vértices existan
        if(i!=-1 && j!=-1){             
            // -- procesar lista adyacencia del vértice v1
            listasDeAdyacencia.get(i).forEach((Nodo nodo)->
                {
                    if(nodo.vertice.equals(v2)){                        
                        pesos.add(nodo.peso);
                    }
                });            
            return pesos;
        }
        else{
            return null;
        }
    }
    // -- Determinar vértice incidente
    public Object verticeSgte(Object v,Object peso){
        // -- Crear objeto proceso
        Proceso verticeSgte=new Proceso();    
        // -- Recuperar índice de vértice
        int i=verticesIniciales.indexOf(v);
        // -- Verificar que el vértice exista
        if(i!=-1){             
            // -- procesar lista adyacencia del vértice v
            listasDeAdyacencia.get(i).forEach((Nodo nodo)->
                {
                    if(nodo.peso.equals(peso)){                     
                        verticeSgte.objeto=nodo.vertice;
                    }
                });            
            return verticeSgte.objeto;
        }
        else{
            return null;
        }
    }
}
