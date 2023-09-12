
package automatagrafo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* Consideraciones a tener en cuenta:
   Los pesos de cada arista deben ser string, esto porque al momento
   de verificar una cadena asumimos esta como string.
*/
public class AutomataGrafo {
    //***********ATRIBUTOS*************
    private GrafoDispersoD grafo;
    private Object estadoInicial;
    private List<Object> estadosFinales;        
    //***********CONSTRUCTORES*************
    public AutomataGrafo(){
        grafo=new GrafoDispersoD();
        estadoInicial=new Object();
        estadosFinales=new ArrayList<Object>();
    }
    // ************MÉTODOS GET Y SET************

    public Object getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(Object estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public Object getEstadosFinales() {
        return estadosFinales;
    }

    public void setEstadosFinales(List<Object> estadosFinales) {
        this.estadosFinales = estadosFinales;
    }
    
    
    //***********MÉTODOS DE PROCESO*************
     
    // -- Agregar estado
    public void agregarEstado(Object estado){
        // -- Añadir vértice al grafo
        grafo.agregarVertice(estado);
    }
    // -- Agregar transición
    public void agregarTransicion(Object estado1,Object peso,Object estado2){
        // -- Añadir arista al grafo
        grafo.agregarArista(estado1, estado2, peso);
    }
    // -- Verificar cadena
    public boolean cadenaEsAceptada(Object cadena){
        // -- Definir un estado 
        Object estado=this.estadoInicial;
        // -- flag
        boolean flagAceptado=true;
        // -- Recuperar array de caracteres
        char[] caracteres=cadena.toString().toCharArray();
        // -- Procesar caracteres
        for (int i = 0; i < caracteres.length && flagAceptado; i++) {
            estado=grafo.verticeSgte(estado, Character.toString(caracteres[i]));
            if(estado==null){
                flagAceptado=false;
            }
        }
        // -- Verificar si es estado final
        if(flagAceptado){
            flagAceptado=estadosFinales.indexOf(estado)==-1?false:true;
        }
        return flagAceptado;
    }
}
