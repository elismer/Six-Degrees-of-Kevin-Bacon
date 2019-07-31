/**************************************************
 * Libreria utilizada para resolver los problemas *
 * planteados en el Trabajo Practico              *
 * @author Bongiovanni Elismer                    *
 * @version 2.0 6/12/2018                         *
 *************************************************/
package app;

import java.util.LinkedList;
import java.util.Set;

public class Funciones {
  /********************************************************************************
   * \ Devulve las peliculas en donde los actores trabajaron juntos *
   * 
   * @param g           grafo del programa. *
   * @param actor1      verteci que representa a un actor en el grafo. *
   * @param actor2      verteci que representa a un actor en el grafo. *
   * @param pelisActor1 adyacentes del vertice de actor1. *
   * @param pelisActor2 adyacentes del vertice de actor2. *
   * @param juntos      lista encadenada donde se almacenan las pelícualas donde *
   *                    trabajaron juntos. *
   * @pre g posee por lo menos dos elementos, actor1=a1 actor2=a2. *
   * @return juntos cargado con las peliculas en la que los actores estan juntos *
   * @post juntos posee la intersección de los adyacentes de actor1 y actor2 * \
   ********************************************************************************/
  public static LinkedList<String> mismaPelicula(Grafo<String> g, String actor1, String actor2)
      throws NullPointerException {
    Set<String> pelisActor1 = g.obtenerAdyacentes(actor1);// Obtenesmos las peliculas del actor1
    Set<String> pelisActor2 = g.obtenerAdyacentes(actor2);// Obtenemos las peliculas del actor2
    LinkedList<String> juntos = new LinkedList<String>();
    for (String peli : pelisActor1) {// Revisa que al menos una de las peliculas del actor 1
      if (pelisActor2.contains(peli)) {// este en el conjunto de peliculas del actor 2
        juntos.add(peli);// Si la pelicula existe almenos una la devuelve como resultado
      }
    }
    return juntos;// Sino devuelve null
  }

  /*******************************************************************************
   * \ Devulve las peliculas en donde los actores trabajaron juntos *
   * 
   * @param g      grafo del programa. *
   * @param x      verteci que representa a un actor en el grafo. *
   * @param i      variable usada para saber cunando hay un actor para agragar *
   *               al camino. *
   * @param aux    variable auxiliar usada para recorrer el recorrido del bfs. *
   * @param camino lista encadenada donde se almacenan los actores que * concectan
   *               a Kevin Bacon *
   * @pre !g.isEmpty() ^ x=a1. *
   * @return true en caso de que el camino exista, de lo contrario devuelve false
   *         *
   * @post camino tiene el camino que lleva de x a Kevin Bacon. * \
   *******************************************************************************/
  public static LinkedList<String> numeroDeBacon(Grafo<String> g, String x) {// recorrido en capas, iterativo usando
                                                                             // cola
    try {
      g.bfs("Bacon, Kevin", x);// Recorremos el grafo de Bacon a x, para poder tener los origenes de x hasta
                               // Bacon
      LinkedList<String> camino = new LinkedList<String>();// creamos una lista donde se va almacenar el camino a Bacon
      camino.add(x);// Aniadimos x
      String aux = (String) g.origen(x);// Despues el origen de x
      int i = 0;// inicializamos i en 0
      while (!aux.equals("Bacon, Kevin")) {
        aux = (String) g.origen(aux);// damos a aux su origen, la primera vez que entre va a ser un actor
        if (i % 2 == 0)// Si i%2==0 es porque es actor.
          camino.add(aux);// aniadimos el actor al camino
        i++;// aumentamos i
      }
      g.clear();// una vez terminado el camino limpiamos el recorrido del bfs.
      return camino;// retornamos el camino a Bacon
    } catch (NullPointerException e) {// Exepcion lanzada cuando no puede encontar x
      System.out.println("No encontrado");
      System.out.println(e);
      return null;
    }
  }

}