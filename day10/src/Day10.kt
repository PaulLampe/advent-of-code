import java.io.File

fun main() {
    val file = generateFile()
    println("Part1:" + part1(file))
    println("Part2:" + part2(file))
}

fun part1(file: List<Int>): Int {
    var diff1 = 0
    var diff3 = 0
    for (i in 0 until file.lastIndex) {
        if (file[i + 1] - file[i] == 1) diff1++
        if (file[i + 1] - file[i] == 3) diff3++
    }
    return diff1 * diff3
}

fun part2(file: List<Int>): Long {
    val array = LongArray(file.size)
    array[0] = 1
    for (i in array.indices) {
        var currentIndex = i + 1
        while (currentIndex in array.indices && file[currentIndex] - file[i] < 4 && file[currentIndex] - file[i] > 0) {
            array[currentIndex] += array[i]
            currentIndex++
        }
    }
    return array.last()
}

fun generateFile(): List<Int> {
    val file: MutableList<Int> = mutableListOf()
    File("day10/src/input.txt").forEachLine {
        file.add(it.toInt())
    }
    file.add(0)
    file.add((file.maxOrNull() ?: 0) + 3)
    file.sort()
    return file
}
