import java.io.File

fun main() {
    val file = generateFile()

    println("Part 1: " + part1(file))
    println("Part 2: " + part2(file))
}

fun part1(file: List<Int>): Int {
    return file.maxOrNull() ?: -1
}

fun part2(file: List<Int>): Int {
    val sortedFile = file.sorted().drop(200).dropLast(200)
    sortedFile.forEachIndexed { index, i ->
       if(sortedFile[index + 1] - i > 1)
            return i + 1
    }
    return -1
}

fun generateFile(): List<Int> {
    val file = mutableListOf<Int>()
    File("day5/src/input.txt").forEachLine {
        val row = it.slice(0..6).replace('B', '1').replace('F', '0').toInt(2)
        val col = it.slice(7..9).replace('R', '1').replace('L', '0').toInt(2)
        file.add(row * 8 + col)
    }
    return file
}
