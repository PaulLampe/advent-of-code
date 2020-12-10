import java.io.File

fun main() {
    val file = generateFile()

    println("Part 1: " + part1(file))
    println("Part 2: " + part2(file))
}

fun part1(file: List<String>): Int {
    var count = 0
    var currentAnswers = mutableListOf<Char>()
    file.forEach {
        if (!it.isEmpty())
            it.toCharArray().forEach { if (!currentAnswers.contains(it)) currentAnswers.add(it) }
        else {
            count += currentAnswers.size
            currentAnswers = mutableListOf()
        }
    }
    return count + currentAnswers.size
}

fun part2(file: List<String>): Int {
    var count = 0
    var commonAnswers = listOf<Char>(' ')
    var lineAnswers: List<Char>
    file.forEach {
        if (it.isEmpty()) {
            count += commonAnswers.size
            commonAnswers = listOf(' ')
            lineAnswers = listOf()
        } else {
            lineAnswers = it.toCharArray().toList()
            commonAnswers = if (commonAnswers.contains(' ')) {
                lineAnswers
            } else {
                lineAnswers.filter { commonAnswers.contains(it) }
            }
        }
        lineAnswers = listOf()
    }
    return count + commonAnswers.size
}

fun generateFile(): List<String> {
    return File("day6/src/input.txt").readLines()
}
