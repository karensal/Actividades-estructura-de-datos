import java.util.Scanner

// Clase Usuario
data class Usuario(
    var nombre: String,
    var correo: String,
    var contraseña: String,
    var edad: Int,
    var genero: String,
    var masaCorporal: Double,
    var altura: Double,
    var objetivos: String = ""
) {
    fun editarPerfil() {
        println("Editar perfil:")
        print("Nuevo nombre (Deja en blanco para mantener el actual): ")
        val nuevoNombre = readLine()
        if (nuevoNombre?.isNotBlank() == true) {
            nombre = nuevoNombre
        }

        print("Nueva contraseña (Deja en blanco para mantener la actual): ")
        val nuevaContraseña = readLine()
        if (nuevaContraseña?.isNotBlank() == true) {
            contraseña = nuevaContraseña
        }

        print("Nuevo peso (kg): ")
        val nuevoPeso = readLine()?.toDoubleOrNull()
        if (nuevoPeso != null) {
            masaCorporal = nuevoPeso
        }

        // Recalcula el IMC
        val imc = calcularIMC()
        println("Tu nuevo IMC es: $imc")
    }

    fun verPerfil() {
        println("Perfil de usuario:")
        println("Nombre: $nombre")
        println("Correo: $correo")
        println("Edad: $edad")
        println("Género: $genero")
        println("Masa Corporal: $masaCorporal kg")
        println("Altura: $altura m")
        val imc = calcularIMC()
        println("IMC: $imc")
    }

    fun calcularIMC(): Double {
        return masaCorporal / (altura * altura)
    }

    fun establecerObjetivos() {
        println("Establecer Objetivos:")
        print("Objetivos (por ejemplo: perder peso, ganar masa muscular, mantenerse en forma, etc.): ")
        val nuevosObjetivos = readLine()
        if (nuevosObjetivos?.isNotBlank() == true) {
            objetivos = nuevosObjetivos
            println("Objetivos actualizados exitosamente.")
        } else {
            println("No se han realizado cambios en los objetivos.")
        }
    }
}

// Clase Ejercicio
data class Ejercicio(
    var nombreEjercicio: String,
    var series: Int,
    var repeticiones: Int,
    var descanso: Int,
    var musculoTrabajado: String,
    var tiempoRealizacion: Int,
    var validador: Boolean = false
) {
    fun validar() {
        if (nombreEjercicio.isNotEmpty() && series > 0 && repeticiones > 0 && descanso >= 0 && musculoTrabajado.isNotEmpty() && tiempoRealizacion > 0) {
            validador = true
        }
    }

    fun validarEjercicioRealizado() {
        println("Validar ejercicio realizado:")
        print("Número de series realizadas: ")
        val seriesRealizadas = readLine()?.toIntOrNull() ?: 0
        print("Número de repeticiones realizadas: ")
        val repeticionesRealizadas = readLine()?.toIntOrNull() ?: 0

        if (seriesRealizadas == series && repeticionesRealizadas == repeticiones) {
            println("Has completado todas las series y repeticiones del ejercicio correctamente. ¡Buen trabajo!")
        } else {
            println("No has completado todas las series y repeticiones del ejercicio. Sigue trabajando en ello.")
        }
    }
}

// Clase Rutina
data class Rutina(
    var nombreRutina: String,
    var duracionTotal: Int,
    var listaActividades: MutableList<Ejercicio> = mutableListOf()
) {
    fun validarRutina(): Boolean {
        for (ejercicio in listaActividades) {
            if (!ejercicio.validador) {
                return false
            }
        }
        return true
    }

    fun agregarActividadFisica(ejercicio: Ejercicio) {
        listaActividades.add(ejercicio)
        duracionTotal += ejercicio.series * (ejercicio.repeticiones + ejercicio.descanso)
    }

    fun validarRutinaPorInput() {
        println("Validación de la rutina '$nombreRutina':")
        for (ejercicio in listaActividades) {
            print("¿Has completado el ejercicio '${ejercicio.nombreEjercicio}'? (Sí/No): ")
            val respuesta = readLine()?.trim()?.toLowerCase()
            if (respuesta == "si") {
                ejercicio.validador = true
            }
        }

        val rutinaValidada = validarRutina()
        if (rutinaValidada) {
            println("¡La rutina ha sido validada con éxito!")
        } else {
            println("La rutina no ha sido validada. Asegúrate de completar todos los ejercicios propuestos.")
        }
    }

    fun calcularDuracionTotal(): Int {
        var duracionTotal = 0
        for (ejercicio in listaActividades) {
            // Calcula la duración de cada ejercicio y agrégala a la duración total
            val duracionEjercicio = ejercicio.series * (ejercicio.repeticiones + ejercicio.descanso + ejercicio.tiempoRealizacion)
            duracionTotal += duracionEjercicio
        }
        return duracionTotal
    }
}

fun main() {
    val usuarios = mutableListOf<Usuario>() //implementacion de la lista enlazada
    var menuPrincipal = 0
    var menuSecundario = 0
    var usuario: Usuario? = null

    while (menuPrincipal != 3) {
        println("Menú Principal:")
        println("1. Registrarse")
        println("2. Ingresar")
        println("3. Salir")
        print("Selecciona una opción del menú principal: ")
        menuPrincipal = readLine()?.toIntOrNull() ?: 0

        when (menuPrincipal) {
            1 -> {
                // Registrar usuario
                print("Nombre: ")
                val nombre = readLine() ?: ""
                print("Correo: ")
                val correo = readLine() ?: ""
                print("Contraseña: ")
                val contraseña = readLine() ?: ""
                print("Edad: ")
                val edad = readLine()?.toIntOrNull() ?: 0
                print("Género: ")
                val genero = readLine() ?: ""
                print("Masa Corporal (kg): ")
                val masaCorporal = readLine()?.toDoubleOrNull() ?: 0.0
                print("Altura (m): ")
                val altura = readLine()?.toDoubleOrNull() ?: 0.0

                val nuevoUsuario = Usuario(nombre, correo, contraseña, edad, genero, masaCorporal, altura)
                usuarios.add(nuevoUsuario)

                println("Usuario registrado exitosamente.")
            }
            2 -> {
                // Ingresar usuario
                print("Correo: ")
                val correo = readLine() ?: ""
                print("Contraseña: ")
                val contraseña = readLine() ?: ""

                usuario = usuarios.find { it.correo == correo && it.contraseña == contraseña }

                if (usuario != null) {
                    println("Bienvenido, ${usuario.nombre}!")

                    while (menuSecundario != 6) {
                        println("Menú Secundario:")
                        println("1. Editar perfil")
                        println("2. Ver perfil")
                        println("3. Establecer objetivos")
                        println("4. Ingresar rutina")
                        println("5. Volver al Menú Principal")
                        print("Selecciona una opción del menú secundario: ")
                        menuSecundario = readLine()?.toIntOrNull() ?: 0

                        when (menuSecundario) {
                            1 -> {
                                // Editar perfil del usuario actual
                                usuario.editarPerfil()
                            }
                            2 -> {
                                // Ver perfil del usuario actual
                                usuario.verPerfil()
                            }
                            3 -> {
                                // Establecer objetivos
                                usuario.establecerObjetivos()
                            }
                            4 -> {
                                // Ingresar rutina
                                val nuevaRutina = Rutina("Mi Rutina", 0)
                                while (true) {
                                    val nuevoEjercicio = Ejercicio("", 0, 0, 0, "", 0)
                                    println("Ingresar los datos del ejercicio:")
                                    print("Nombre del ejercicio (Deja en blanco para finalizar): ")
                                    val nombreEjercicio = readLine()
                                    if (nombreEjercicio.isNullOrBlank()) {
                                        break
                                    }
                                    nuevoEjercicio.nombreEjercicio = nombreEjercicio
                                    print("Número de series: ")
                                    nuevoEjercicio.series = readLine()?.toIntOrNull() ?: 0
                                    print("Número de repeticiones: ")
                                    nuevoEjercicio.repeticiones = readLine()?.toIntOrNull() ?: 0
                                    print("Tiempo de descanso (segundos): ")
                                    nuevoEjercicio.descanso = readLine()?.toIntOrNull() ?: 0
                                    print("Músculo trabajado: ")
                                    nuevoEjercicio.musculoTrabajado = readLine() ?: ""
                                    print("Tiempo de realización (segundos): ")
                                    nuevoEjercicio.tiempoRealizacion = readLine()?.toIntOrNull() ?: 0
                                    nuevoEjercicio.validar()

                                    // Agregar el ejercicio a la rutina
                                    nuevaRutina.agregarActividadFisica(nuevoEjercicio)
                                }
                            }
                            5 -> {
                                println("Volviendo al Menú Principal.")
                            }
                            else -> println("Opción no válida en el Menú Secundario.")
                        }
                    }
                    menuSecundario = 0 // Reiniciar el menú secundario para futuros usos.
                }
            }
            3 -> {
                // Salir
                println("Saliendo del programa.")
            }
            else -> println("Opción no válida en el Menú Principal.")
        }
    }
}
