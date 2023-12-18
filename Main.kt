import kotlin.random.Random

data class Train(val direction: String, val wagons: List<Wagon>)

data class Wagon(val capacity: Int, val passengers: List<String>)

fun main() {
    val cities = listOf("Барнаул", "Бийск", "Новосибирск", "Омск", "Томск", "Кемерово", "Иркутск", "Красноярск", "Новокузнецк", "Братск", "Ачинск", "Курган", "Тюмень", "Сургут", "Ханты-Мансийск")
    var train: Train? = null

    while (true) {
        println("Выберите действие:")
        println("1. Создать направление")
        println("2. Продать билеты")
        println("3. Сформировать поезд")
        println("4. Отправить поезд")
        println("EXIT. Завершить работу")

        when (readLine()?.toUpperCase()) {
            "1" -> {
                val startCity = cities.random()
                var endCity = cities.random()
                while (startCity == endCity) {
                    endCity = cities.random()
                }
                val direction = "$startCity - $endCity"
                train = Train(direction, emptyList())
                println("Создано направление: $direction")
            }
            "2" -> {
                if (train == null) {
                    println("Сначала создайте направление")
                } else {
                    val passengersCount = Random.nextInt(5, 202)
                    println("Продано билетов: $passengersCount")
                }
            }
            "3" -> {
                if (train == null) {
                    println("Сначала создайте направление")
                } else {
                    val wagons = mutableListOf<Wagon>()
                    var passengersLeft = train?.wagons?.sumBy { it.capacity } ?: 0

                    while (passengersLeft > 0) {
                        val wagonCapacity = Random.nextInt(5, 26)
                        val passengersInWagon = minOf(wagonCapacity, passengersLeft)
                        val passengers = List(passengersInWagon) { "Пассажир${it + 1}" }
                        wagons.add(Wagon(wagonCapacity, passengers))
                        passengersLeft -= passengersInWagon
                    }

                    train = train?.copy(wagons = wagons)
                    println("Поезд сформирован")
                }
            }
            "4" -> {
                if (train == null) {
                    println("Сначала создайте направление")
                } else {
                    println("Поезд ${train?.direction} отправлен.")
                    train?.wagons?.forEachIndexed { index, wagon ->
                        println("Вагон ${index + 1}: вместимость - ${wagon.capacity}, пассажиры - ${wagon.passengers.joinToString()}")
                    }
                    train = null
                }
            }
            "EXIT" -> {
                println("Работа программы завершена.")
                return
            }
            else -> println("Неверная команда. Попробуйте еще раз.")
        }
    }
}
