import java.io.File

fun main() {
    val file = generateFile()

    println("Part 1: " + part1(file))
    println("Part 2: " + part2(file))
}

fun part1(file: List<String>): Int {
    var validCount = 0
    file.forEach {
        val splitString = it.split("-", ": ", " ")
        val min = splitString[0].toInt()
        val max = splitString[1].toInt()
        val char = splitString[2].toCharArray().first()
        val word = splitString[3].toCharArray()
        val occurrences = word.filter { it == char }.size

        if(occurrences in min..max)
            validCount++
    }
    return validCount
}

fun part2(file: List<String>): Int {
    var validCount = 0
    file.forEach {
        val splitString = it.split("-", ": ", " ")
        val min = splitString[0].toInt() - 1
        val max = splitString[1].toInt() - 1
        val char = splitString[2].toCharArray().first()
        val word = splitString[3].toCharArray()

        if((word[min] == char) xor (char == word[max]))
            validCount++
    }
    return validCount
}

fun generateFile(): List<String> {
    return File("day2/src/input.txt").readLines()
}
