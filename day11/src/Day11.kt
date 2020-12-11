import java.io.File

fun main() {
    val file = generateFile()

    println("Part 1: ${part1(file)}")
    println("Part 2: ${part2(file)}")
}

fun part1(inputFile: Array<CharArray>): Int {
    var file = inputFile
    var newSeatOccupancies: Array<CharArray> = Array(file.size) { CharArray(file.first().size) { ' ' } }
    var oldSum = -1
    var newSum = 0
    while (oldSum != newSum) {
        file.forEachIndexed { y, chars ->
            chars.forEachIndexed { x, char ->
                val adjacentOccupied = occupiedAdjacentSeats(file, x, y, chars.size, file.size)
                if (char == 'L' && adjacentOccupied == 0) {
                    newSeatOccupancies[y][x] = '#'
                } else if (char == '#' && adjacentOccupied > 3) {
                    newSeatOccupancies[y][x] = 'L'
                } else {
                    newSeatOccupancies[y][x] = char
                }
            }
        }
        oldSum = newSum
        newSum = newSeatOccupancies.sumBy { it.sumBy { if (it == '#') 1 else 0 } }
        file = newSeatOccupancies
        newSeatOccupancies = Array(file.size) { CharArray(file.first().size) { ' ' } }
    }
    return newSum
}

fun part2(inputFile: Array<CharArray>): Int {
    var file = inputFile
    var newSeatOccupancies: Array<CharArray> = Array(file.size) { CharArray(file.first().size) { ' ' } }
    var oldSum = -1
    var newSum = 0
    while (oldSum != newSum) {
        file.forEachIndexed { y, chars ->
            chars.forEachIndexed { x, char ->
                val adjacentOccupied = occupiedVisibleSeats(file, x, y, chars.size, file.size)
                if (char == 'L' && adjacentOccupied == 0) {
                    newSeatOccupancies[y][x] = '#'
                } else if (char == '#' && adjacentOccupied > 4) {
                    newSeatOccupancies[y][x] = 'L'
                } else {
                    newSeatOccupancies[y][x] = char
                }
            }
        }
        oldSum = newSum
        newSum = newSeatOccupancies.sumBy { it.sumBy { if (it == '#') 1 else 0 } }
        file = newSeatOccupancies
        newSeatOccupancies = Array(file.size) { CharArray(file.first().size) { ' ' } }
    }
    return newSum
}

fun occupiedAdjacentSeats(file: Array<CharArray>, x: Int, y: Int, xDim: Int, yDim: Int): Int {
    val validCombinations = mutableListOf<Pair<Int, Int>>()
    setOf(x - 1, x, x + 1).forEach { newX ->
        setOf(y - 1, y, y + 1).forEach { newY ->
            if (newX in 0 until xDim && newY in 0 until yDim && (newX != x || newY != y)) {
                validCombinations.add(Pair(newX, newY))
            }
        }
    }
    return validCombinations.map { if (file[it.second][it.first] == '#') 1 else 0 }.sum()
}

fun occupiedVisibleSeats(file: Array<CharArray>, x: Int, y: Int, xDim: Int, yDim: Int): Int {
    var counter = 0
    setOf(-1, 0, 1).forEach { deltaX ->
        setOf(-1, 0, 1).forEach { deltaY ->
            if (deltaX != 0 || deltaY != 0) {
                var newX = x + deltaX
                var newY = y + deltaY
                while (newX in 0 until xDim && newY in 0 until yDim) {
                    if (file[newY][newX] == '#') {
                        counter++
                        newX = -1
                    } else if(file[newY][newX] == 'L') {
                        newX = -1
                    } else {
                        newX += deltaX
                        newY += deltaY
                    }
                }
            }
        }
    }
    return counter
}

fun generateFile(): Array<CharArray> {
    val file = mutableListOf<CharArray>()
    File("day11/src/input.txt").forEachLine {
        file.add(it.toCharArray())
    }
    return file.toTypedArray()
}