
package automatagrafo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pruebas {

    public static void main(String[] args) {
       // -- Inicializar autómata 
       AutomataGrafo A=new AutomataGrafo();
       // -- Agregar estados
       A.agregarEstado("q0");
       A.agregarEstado("q1");
       A.agregarEstado("q2");
       A.agregarEstado("q3");
       // -- Agregar transiciones
       A.agregarTransicion("q0", "1", "q1");
       A.agregarTransicion("q1", "0", "q1");
       A.agregarTransicion("q1", "1", "q3");
       A.agregarTransicion("q3", "1", "q1");
       A.agregarTransicion("q3", "0", "q2");
       // -- Definir estado inicial y final
       A.setEstadoInicial("q0");
       A.setEstadosFinales(Arrays.asList("q3"));       
       // -- Verificar distintas cadenas
        System.out.println(A.cadenaEsAceptada("10000001"));
        System.out.println(A.cadenaEsAceptada("10011"));
        System.out.println(A.cadenaEsAceptada("100001011"));                
    }
    
}
