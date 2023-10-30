import java.util.*

class Ciudad(val nombre: String, var enlace: Ciudad? = null)

// Clase Ciudad para representar un nodo de la lista enlazada
class ListaCiudades {
    private var primeraCiudad: Ciudad? = null

    // Método para insertar una ciudad al principio de la lista
    fun insertarCabeza(ciudad: Ciudad) {
        ciudad.enlace = primeraCiudad
        primeraCiudad = ciudad
    }

    // Método para insertar una ciudad al final de la lista
    fun insertarCola(ciudad: Ciudad) {
        if (primeraCiudad == null) {
            primeraCiudad = ciudad
        } else {
            var actual = primeraCiudad
            while (actual?.enlace != null) {
                actual = actual.enlace
            }
            actual?.enlace = ciudad
        }
    }

    // Método para insertar una ciudad entre dos nodos existentes
    fun insertarEntre(ciudadNueva: Ciudad, ciudadAnterior: Ciudad) {
        var actual = primeraCiudad
        while (actual != null) {
            if (actual == ciudadAnterior) {
                ciudadNueva.enlace = actual.enlace
                actual.enlace = ciudadNueva
                break
            }
            actual = actual.enlace
        }
    }

    // Método para visualizar las ciudades en la lista
    fun visualizar() {
        var actual = primeraCiudad
        while (actual != null) {
            print("${actual.nombre} ")
            actual = actual.enlace
        }
        println()
    }
}

fun main() {
    val scanner = Scanner(System.`in`)
    val listaCiudades = ListaCiudades()

    while (true) {
        println("Opciones:")
        println("1. Insertar al principio")
        println("2. Insertar al final")
        println("3. Insertar entre dos nodos existentes")
        println("4. Visualizar ciudades")
        println("5. Salir")
        print("Selecciona una opción: ")

        when (scanner.nextInt()) {
            1 -> {
                print("Ingresa el nombre de la ciudad: ")
                val nombreCiudad = scanner.next()
                val nuevaCiudad = Ciudad(nombreCiudad)
                listaCiudades.insertarCabeza(nuevaCiudad)
            }
            2 -> {
                print("Ingresa el nombre de la ciudad: ")
                val nombreCiudad = scanner.next()
                val nuevaCiudad = Ciudad(nombreCiudad)
                listaCiudades.insertarCola(nuevaCiudad)
            }
            3 -> {
                print("Ingresa el nombre de la ciudad nueva: ")
                val nombreNuevaCiudad = scanner.next()
                print("Ingresa el nombre de la ciudad existente después de la cual deseas insertar la nueva ciudad: ")
                val nombreCiudadExistente = scanner.next()

                val nuevaCiudad = Ciudad(nombreNuevaCiudad)
                val ciudadAnterior = Ciudad(nombreCiudadExistente)

                listaCiudades.insertarEntre(nuevaCiudad, ciudadAnterior)
            }
            4 -> {
                println("Ciudades:")
                listaCiudades.visualizar()
            }
            5 -> {
                println("Saliendo del programa.")
                return
            }
            else -> {
                println("Opción no válida. Por favor, selecciona una opción válida.")
            }
        }
    }
}
