package app;

import java.util.LinkedList;
import java.util.Scanner;

public class App {
  public final static void clearConsole() {
    try {
      final String os = System.getProperty("os.name");

      if (os.contains("Windows")) {
        Runtime.getRuntime().exec("cls");
      } else {
        Runtime.getRuntime().exec("clear");
      }
    } catch (final Exception e) {
      System.out.println(e);
    }
  }

  public static void main(String[] args) {
    String filename = "resources/movies.txt";
    String delimiter = "/";
    Grafo<String> grafo = CargarDatos.cargarDatos(filename, delimiter);
    Scanner scanner = new Scanner(System.in);
    int opcion = -1;
    while (opcion != 0) { // mientras la opcion no sea 'salir'

      clearConsole();// limpia pantalla
      System.out.flush(); // limpia panalla
      try {// interfaz para acceder a las funciones propuestas por el Tp
        System.out.println("Cantidad de vertices: " + grafo.cantVertices());
        System.out.println("Six Degrees of Kevin Bacon: ");
        System.out.println(" ");
        System.out.println("1- Acturaon Juntos");
        System.out.println("2- Niveles de Kevin Bacon");
        System.out.println("3- Consultar Actor o Pelicula");
        System.out.println("4- TODOS LOS NUMERON DE BACON");
        System.out.println("0- Salir");

        System.out.println(" ");
        opcion = Integer.parseInt(scanner.nextLine());
        scanner.close();
        String actor1 = new String();// Variables usadas para el ingreso
        String actor2 = new String();// de los actores

        switch (opcion) {

        case 1:
          System.out.println();
          System.out.println("Elija un actor de nuesta base de datos de la siguiente forma: Apellido, Nombre");
          actor1 = scanner.nextLine();// Obtenemos el primer actor
          System.out.println("Elija otro actor de la misma forma");
          actor2 = scanner.nextLine();// Despues el segundo
          LinkedList<String> peliculasJuntos = Funciones.mismaPelicula(grafo, actor1, actor2);// Devolvemos el
                                                                                              // listado de
                                                                                              // pelicualas
                                                                                              // juntos
          System.out.println();
          if (!peliculasJuntos.isEmpty()) {// Si no es vacio lo imprimimos
            System.out.println(actor1 + " y " + actor2 + " trabajaron juntos en: ");
            for (String st : peliculasJuntos) {
              System.out.println("      " + st);
            }
          } else {// Sino ponemos este cartel
            System.out.println(actor1 + " y " + actor2 + " no trabajaron juntos por ahora");
          }
          scanner.nextLine();
          break;

        case 2:
          System.out.println();
          System.out.println("Elija un actor de nuesta base de datos de la siguiente forma: Apellido, Nombre");
          actor1 = scanner.nextLine();// elegimos el actor
          LinkedList<String> camino = Funciones.numeroDeBacon(grafo, actor1);// devolvemos el camino
          System.out.println();
          System.out.println("El camino a Kevin Bacon es:");
          for (int i = 0; i < camino.size() - 1; i++) {// Lo imprimimos
            System.out.print(camino.get(i) + " ");
            System.out.print("-> ");
          }
          System.out.println(camino.getLast());
          System.out.println("Su numero es: " + (camino.size() - 1));// Calculamos el nivel desde el largo de
                                                                     // la lista
          grafo.clear();// limpiamos el grafo
          scanner.nextLine();

          break;
        case 3:
          System.out.println();
          System.out.println("Ingrese un nombre de actor o película.");
          System.out.println("Para actores (Apellido, Nombre), para películas (Nombre De La Película (Año) :");
          String source = scanner.nextLine();
          if (grafo.contieneVertice(source)) {
            for (String v : grafo.obtenerAdyacentes(source)) {
              System.out.println("   " + v);
            }
          } else {
            System.out.println("La entrada no existe");
          }
          scanner.nextLine();

          break;
        case 4:// Funcion a parte que calcula el camino maximo y el mismo
          System.out.println();
          LinkedList<String> camino2 = new LinkedList<String>();
          LinkedList<String> caminoMaximo = new LinkedList<String>();
          LinkedList<String> caminoMinimo = new LinkedList<String>();
          for (String actor : grafo.keySet()) {
            try {
              camino2 = Funciones.numeroDeBacon(grafo, actor);
              if (camino2.size() > caminoMaximo.size()) {
                caminoMaximo = camino2;
              } else if (camino2.size() < caminoMinimo.size()) {
                caminoMinimo = camino2;
              }
            } catch (Exception e) {
              System.out.println("Error: " + e);
            }
          }
          System.out.println();
          System.out.println("El camino minimo a Kevin Bacon es:");
          for (int i = 0; i < caminoMinimo.size() - 1; i++) {
            System.out.print(caminoMinimo.get(i) + " ");
            System.out.print("-> ");
          }
          System.out.println(caminoMinimo.getLast());
          System.out.println("Su numero es: " + (caminoMinimo.size() - 1));

          System.out.println("El camino maximo a Kevin Bacon es:");
          for (int i = 0; i < caminoMaximo.size() - 1; i++) {
            System.out.print(caminoMaximo.get(i) + " ");
            System.out.print("-> ");
          }
          System.out.println(caminoMaximo.getLast());
          System.out.println("Su numero es: " + (caminoMaximo.size() - 1));
          scanner.nextLine();
          break;

        case 0:

          clearConsole();// limpia panalla

          break;

        default:

          System.out.print("Ingrese una opcion valida");
          scanner.nextLine(); // lee una tecla para continuar

          break;

        }
      } catch (Exception e) {
        System.out.println("Caracter invalido");
        scanner.nextLine(); // lee una tecla para continuar
      }
    }
  }
}