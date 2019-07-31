package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CargarDatos {

  public static Grafo<String> cargarDatos(String filename, String delimiter) {

    Grafo<String> grafo = new Grafo<>();
    // Primera pasada para agregar las peliculas y los actores como vertices del
    // grafo
    try {
      File file = new File(filename);
      BufferedReader br = new BufferedReader(new FileReader(file));
      String st = null;
      while ((st = br.readLine()) != null) {
        String[] values = st.split(delimiter);
        for (int i = 0; i < values.length; i++) {
          if (!grafo.contieneVertice(values[i]))
            grafo.agregarVertice(values[i]);
        }
      }
      br.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("Error al leer el archivo: " + filename);
    }

    // Segunda pasada para agregar aristas entre actores y peliculas en las que
    // trabajaron
    try {
      File file = new File(filename);
      BufferedReader br = new BufferedReader(new FileReader(file));
      String st = null;
      while ((st = br.readLine()) != null) {
        String[] values = st.split(delimiter);
        for (int i = 1; i < values.length; i++) {
          grafo.agregarArista(values[0], values[i]);
        }
      }
      br.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("Error al leer el archivo: " + filename);
    }

    return grafo;
  }

  // Ejemplo de uso:
  // java tp2.CargarDatos "../resources/movies.txt"
  // Ingrese un nombre:
  // Bacon, Kevin

}
