import java.io.File

fun main() {
    val file = generateFile()

    println("Part 1 : ${part1(file)}")
    println("Part 2 : ${part2(file)}")
}

fun part1(file: List<Int>): Int {
    file.forEachIndexed { index, i ->
        file.forEach {
            if (i != it && it + i == 2020)
                return it * i
        }
    }
    return -1
}

fun part2(file: List<Int>): Int {
    file.forEach { val1 ->
        file.forEach { val2 ->
            file.forEach { val3 ->
                if (val1 != val2 && val2 != val3 && val1 + val2 + val3 == 2020)
                    return val1 * val2 * val3
            }
        }
    }
    return -1
}

fun generateFile(): List<Int> {
    val file = mutableListOf<Int>()
    File("day1/src/input.txt").forEachLine {
        file.add(it.toInt())
    }
    return file
}
