import java.io.File

fun main() {
    val file = generateFile()

    println("Part 1: " + part1(file))
    println("Part 2: " + part2(file))
}

fun part1(file: List<String>): Long {
    return countTrees(file, 3, 1)
}

fun part2(file: List<String>): Long {
    var treeCount = 1L
    listOf(Pair(1, 1), Pair(3, 1), Pair(5, 1), Pair(7, 1), Pair(1, 2)).forEach {
        treeCount *= countTrees(file, it.first, it.second)
    }
    return treeCount
}

fun countTrees(file: List<String>, right: Int, down: Int): Long {
    val length = file.first().length
    var x = 0
    var y = 0
    var treeCount = 0L
    file.forEach {
        if (y % down == 0) {
            if (it.toCharArray()[x] == '#')
                treeCount++
            x = (x + right) % length
        }
        y ++
    }
    return treeCount
}

fun generateFile(): List<String> {
    return File("day3/src/input.txt").readLines()
}
