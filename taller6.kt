import kotlin.random.Random // Importa la clase Random para generar números aleatorios

//La clase Nodo representa un nodo en la lista enlazada. Cada nodo contiene un valor 
//entero dato y una referencia al siguiente nodo enlace. Se proporciona un valor predeterminado null para enlace.
class Nodo(val dato: Int, var enlace: Nodo? = null)

//La clase Lista representa la lista enlazada y contiene un campo privado primero que apunta al primer nodo de la lista.
class Lista {
    private var primero: Nodo? = null // La lista comienza vacía, por lo que el primer nodo es null

    //Este método insertarCabezaLista permite insertar un nuevo nodo al principio de la lista. 
    //Crea un nuevo nodo con el valor proporcionado, enlaza este nuevo nodo al nodo actualmente en la cabeza de la lista y luego establece 
    //el nuevo nodo como el primer nodo de la lista. Luego, devuelve la instancia de la lista para que se puedan encadenar más llamadas a métodos.
    fun insertarCabezaLista(entrada: Int): Lista {
        val nuevo = Nodo(entrada) // Crea un nuevo nodo con el valor proporcionado
        nuevo.enlace = primero // Enlaza el nuevo nodo con el nodo actualmente en la cabeza de la lista
        primero = nuevo // Establece el nuevo nodo como el primer nodo de la lista
        return this // Devuelve la instancia de la lista (esto permite encadenar llamadas)
    }

    //El método visualizar recorre la lista y muestra sus elementos. Comienza desde el primer nodo (primero) y 
    //continúa avanzando a través de los nodos,
    // imprimiendo el valor de cada nodo. Cuando se imprimen 15 elementos, agrega un salto de línea para una mejor visualización.
    fun visualizar() {
        var n = primero // Comienza desde el primer nodo
        var k = 0
        while (n != null) { // Mientras no se llegue al final de la lista
            print("${n.dato} ") // Imprime el valor del nodo
            n = n.enlace // Avanza al siguiente nodo
            k++
            if (k % 15 == 0) { // Agrega un salto de línea cada 15 elementos para una mejor visualización
                println()
            }
        }
    }
}

//En la función main, se crea una instancia de la clase Random para generar números aleatorios y una instancia de la clase Lista. 
//Luego, se genera un número aleatorio k que determina cuántos elementos se insertarán en la lista (entre 1 y 54).
// A continuación, se insertan elementos aleatorios en la lista y se muestran los elementos generados en la lista.
fun main() {
    val r = Random // Crea una instancia de la clase Random para generar números aleatorios
    val lista = Lista() // Crea una instancia de la clase Lista

    val k = Math.abs(r.nextInt() % 55) // Genera un número aleatorio entre 0 y 54 para determinar cuántos elementos se insertarán en la lista

    // Se insertan elementos aleatorios en la lista
    for (i in k downTo 1) { // Desde k hasta 1
        val d = r.nextInt() % 99 // Genera un número aleatorio entre -98 y 98 para los elementos de la lista
        lista.insertarCabezaLista(d) // Inserta el elemento en la cabeza de la lista
    }

    // Recorre la lista para escribir sus elementos
    println("Elementos de la lista generados al azar")
    lista.visualizar() // Visualiza los elementos de la lista
}

    
