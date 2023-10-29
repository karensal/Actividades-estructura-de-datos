fun main() {
    // Definir las dimensiones del array tridimensional
    val años = 3
    val regiones = 4
    val categorias = 5

    // Crear un array tridimensional para almacenar los datos de ventas
    val ventas = Array(años) { Array(regiones) { IntArray(categorias) } }

    // Permitir al usuario ingresar los datos de ventas
    for (año in 0 until años) {
        for (region in 0 until regiones) {
            for (categoria in 0 until categorias) {
                print("Ingrese las ventas en COP para el año ${año + 2020}, región ${region + 1}, categoría de productos ${categoria + 1}: ")
                ventas[año][region][categoria] = readLine()?.toIntOrNull() ?: 0
            }
        }
    }

    // Mostrar las ventas totales en un año específico
    print("Ingrese el año para ver las ventas totales (2020, 2021 o 2022): ")
    val añoSeleccionado = (readLine()?.toIntOrNull() ?: 0)

    if (añoSeleccionado in 2020..2022) {
        val añoIndex = añoSeleccionado - 2020
        val ventasTotalesAño = ventas[añoIndex]
            .flatMap { it.toList() }
            .sum()
        println("Ventas totales en $añoSeleccionado: $ventasTotalesAño COP")
    } else {
        println("Año no válido.")
    }

    // Calcular el crecimiento de ventas en una región específica a lo largo de varios años
    print("Ingrese la región para calcular el crecimiento de ventas (1, 2, 3 o 4): ")
    val regionSeleccionada = (readLine()?.toIntOrNull() ?: 0)

    if (regionSeleccionada in 1..4) {
        val regionIndex = regionSeleccionada - 1
        val ventasPorAño = ventas.map { it[regionIndex].sum() }
        for (i in 1 until ventasPorAño.size) {
            val crecimiento = ventasPorAño[i] - ventasPorAño[i - 1]
            println("Crecimiento de ventas en la región $regionSeleccionada entre ${i + 2020} y ${i + 2019}: $crecimiento COP")
        }
    } else {
        println("Región no válida.")
    }

    // Identificar la categoría de productos más vendida en una región en un año específico
    print("Ingrese el año para identificar la categoría de productos más vendida (2020, 2021 o 2022): ")
    val añoIdentificar = (readLine()?.toIntOrNull() ?: 0)

    if (añoIdentificar in 2020..2022) {
        val añoIndex = añoIdentificar - 2020
        print("Ingrese la región (1, 2, 3 o 4) para identificar la categoría más vendida: ")
        val regionIdentificar = (readLine()?.toIntOrNull() ?: 0)

        if (regionIdentificar in 1..4) {
            val regionIndex = regionIdentificar - 1
            val categoriaMasVendida = ventas[añoIndex][regionIndex].indexOfMax()
            println("En el año $añoIdentificar, en la región $regionIdentificar, la categoría de productos más vendida es la ${categoriaMasVendida + 1}.")
        } else {
            println("Región no válida.")
        }
    } else {
        println("Año no válido.")
    }
}

fun IntArray.indexOfMax(): Int {
    var maxIndex = 0
    for (i in 1 until this.size) {
        if (this[i] > this[maxIndex]) {
            maxIndex = i
        }
    }
    return maxIndex
}
