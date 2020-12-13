import java.io.File

fun main() {
    val file = generateFile()

    println("Part 1: ${part1(file)}")
    println("Part 2: ${part2(file)}")
}

fun part1(file: Pair<Int, List<String>>): Int {
    val arrival = file.first
    var wait = -1
    var busId = 0
    file.second.filter { it != "x" }.map { it.toInt() }.forEach {
        var busArrival = 0
        while (busArrival < arrival) {
            busArrival += it
        }
        if (busArrival - arrival < wait || wait < 0) {
            wait = busArrival - arrival
            busId = it
        }
    }
    return busId * wait
}

fun part2(file: Pair<Int, List<String>>): Long {
    var timestamp = 1L
    var addValue = 1L
    file.second.map { if (it != "x") it.toLong() else 0L }.forEachIndexed { index, l ->
        if(l != 0L) {
            while ((timestamp + index) % l != 0L) {
                timestamp += addValue
                println(timestamp)
            }
            addValue = lcm(addValue, l)
        }
    }
    return timestamp
}

fun lcm(long1: Long, long2: Long):Long {
    var gcd = 1

    var i = 1
    while (i <= long1 && i <= long2) {
        // Checks if i is factor of both integers
        if (long1 % i == 0L && long2 % i == 0L)
            gcd = i
        ++i
    }

    return long1*long2/gcd
}

fun generateFile(): Pair<Int, List<String>> {
    val lines = File("day13/src/input.txt").readLines()
    val arrival = lines.first().toInt()
    val busDepartures = lines[1].split(",")
    return Pair(arrival, busDepartures)
}
