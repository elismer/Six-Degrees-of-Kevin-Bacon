/**************************************************
 * Libreria utilizada para resolver los problemas *
 * planteados en el Trabajo Practico              *
 * @author Bongiovanni Elismer                    *
 * @version 2.0 6/12/2018                         *
 *************************************************/
package app;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Grafo<V> {

  private Map<V, Set<V>> lAdy = new HashMap<>();
  private Map<V, V> recBfs = new HashMap<>();

  public Grafo() {
  }

  public boolean contieneVertice(V v) {
    return lAdy.containsKey(v);
  }

  public void agregarVertice(V v) {
    lAdy.put(v, new HashSet<V>());
  }

  public Set<V> keySet() {
    return lAdy.keySet();
  }

  public void agregarArista(V v, V w) {
    lAdy.get(v).add(w);
    lAdy.get(w).add(v);
  }

  public Set<V> obtenerAdyacentes(V v) {
    return lAdy.get(v);
  }

  public V origen(V v) {
    return recBfs.get(v);// Esta nos retorna el origen del vertice desde un recorrido bsf
  }

  public void clear() {
    recBfs.clear();// Con esta funcion limpiamos el Map anterior, para ahorrar memoria
  }

  public int cantVertices() {
    return lAdy.keySet().size();
  }

  /***********************************************************************************
   * Recorrido Breadth First Search *
   * 
   * @param x     verteci origen. *
   * @param y     vertice destino. *
   * @param marca conjunto ulitizado como marca del recorrido. *
   * @param adya  cola utilizada para colocar los adyacentes que van a ser
   *              recorridos *
   * @pre !g.isEmpty() ^ x=a1 ^ y=b1. *
   * @post recBfs= grafo direccional donde podemos saber como llegar de x a y, *
   *       simpre y cunado exista una forma de llegar a y. *
   **********************************************************************************/
  public boolean bfs(V x, V y) {// recorrido en capas, iterativo usando cola
    ConcurrentLinkedQueue<V> adya = new ConcurrentLinkedQueue<V>();
    HashSet<V> marca = new HashSet<V>();
    V llegada = y;
    V v1 = x; // Asignamos el vertice de partida
    adya.add(v1);// metemos el vertice en la cola
    marca.add(v1);// lo marcamos
    while (!adya.isEmpty()) {// hasta que la cola sea vacia
      V v2 = (V) adya.remove();// removemos la salida
      for (V v3 : obtenerAdyacentes(v2)) {// Tratamos el vertice
        if (llegada.equals(v3)) {
          recBfs.put(v3, v2);
          return true;
        }
      }
      for (V v3 : obtenerAdyacentes(v2)) {// Ponesmos en la cola a todos los adyacentes del orinen
        if (v3 != null && !marca.contains(v3)) {
          marca.add(v3);// y los marcamos
          adya.add(v3);
          recBfs.put(v3, v2);
        }
      }
    }
    return false;
  }

}
