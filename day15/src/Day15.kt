

fun main() {
    val file = generateFile()

    println("Part 1: ${part1(file)}")
    println("Part 2: ${part2(file)}")
}

fun part1(file: List<Int>): Long {
    val saidNumbers: MutableList<Long> = mutableListOf()
    for (i in file) {
        saidNumbers.add(i.toLong())
    }
    var currentNumber = 0L
    for (i in file.size until 2020) {
        if (saidNumbers.contains(currentNumber)) {
            var lastIndex = 0
            lastIndex = saidNumbers.lastIndexOf(currentNumber)
            saidNumbers.add(currentNumber)
            currentNumber = (saidNumbers.size - 1 - lastIndex).toLong()
        } else {
            saidNumbers.add(currentNumber)
            currentNumber = 0L
        }
    }
    return saidNumbers.last()
}

fun part2(file: List<Int>): Long {
    val saidNumbers: MutableMap<Long, Int> = mutableMapOf()
    for (i in file.indices) {
        saidNumbers[file[i].toLong()] = i
    }
    var currentNumber = 0L
    for (i in file.size until 30000000) {
        if (saidNumbers[currentNumber] != null) {
            val maxIndex = saidNumbers[currentNumber]!!
            saidNumbers[currentNumber] = i
            currentNumber = i - maxIndex.toLong()
        } else {
            saidNumbers[currentNumber] = i
            currentNumber = 0L
        }
    }
    return saidNumbers.maxByOrNull { it.value }?.key ?: throw Exception()
}

fun generateFile(): List<Int> {
    return listOf(14, 8, 16, 0, 1, 17)
}
