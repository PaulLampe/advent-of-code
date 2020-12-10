import java.io.File

fun main() {
    val file = generateFile()

    println("Part1: " + part1(file))
    println("Part2: " + part2(file))
}

fun part1(file: List<Long>): Long {
    for (i in 25 until file.size) {
        val currentNumber = file[i]
        val prevArray = file.slice(i - 25 until i)

        if (prevArray.contains(currentNumber)) {
            return currentNumber
        } else if (!makesNumber(prevArray, currentNumber)) {
            return currentNumber
        }
    }
    return -1
}

fun part2(file: List<Long>): Long {
    val contiguousSum = part1(file)
    for (i in file.indices) {
        var index = i - 1
        var contiguousResult = 0L
        val contiguousList = mutableListOf<Long>()
        while (index < file.size - 2 && contiguousResult < contiguousSum) {
            index++
            contiguousResult += file[index]
            contiguousList.add(file[index])
        }
        if (contiguousResult == contiguousSum) {
            return (contiguousList.minOrNull() ?: throw Exception()) + (contiguousList.maxOrNull() ?: throw Exception())
        }
    }
    return -1
}

fun makesNumber(list: List<Long>, number: Long): Boolean {
    for (i in 0 until list.size - 1) {
        for (j in i + 1 until list.size) {
            if (list[i] + list [j] == number)
                return true
        }
    }
    return false
}

fun generateFile(): List<Long> {
    val file: MutableList<Long> = mutableListOf()
    File("day9/src/input.txt").forEachLine {
        file.add(it.toLong())
    }
    return file
}