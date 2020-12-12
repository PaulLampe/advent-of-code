import java.io.File
import kotlin.math.absoluteValue

fun main() {
    val file = generateFile()

    println("Part 1: ${part1(file)}")
    println("Part 2: ${part2(file)}")
}

enum class Direction { NORTH, EAST, SOUTH, WEST }

fun part1(file: List<Pair<Char, Int>>): Int {
    var direction: Direction = Direction.EAST
    var x = 0
    var y = 0
    file.forEach {
        when (it.first) {
            'N' -> {
                y += it.second
            }
            'S' -> {
                y -= it.second
            }
            'E' -> {
                x += it.second
            }
            'W' -> {
                x -= it.second
            }
            'R' -> {
                direction = Direction.values()[(direction.ordinal + it.second / 90) % 4]
            }
            'L' -> {
                direction = Direction.values()[((direction.ordinal - it.second / 90) + 4) % 4]
            }
            'F' -> {
                when (direction) {
                    Direction.NORTH -> {
                        y += it.second
                    }
                    Direction.SOUTH -> {
                        y -= it.second
                    }
                    Direction.EAST -> {
                        x += it.second
                    }
                    Direction.WEST -> {
                        x -= it.second
                    }
                }
            }
        }
    }
    return x.absoluteValue + y.absoluteValue
}

fun part2(file: List<Pair<Char, Int>>): Int {
    var wayX = 10
    var wayY = 1
    var x = 0
    var y = 0
    file.forEach {
        when (it.first) {
            'N' -> {
                wayY += it.second
            }
            'S' -> {
                wayY -= it.second
            }
            'E' -> {
                wayX += it.second
            }
            'W' -> {
                wayX -= it.second
            }
            'R' -> {
                val pos = rotateCoordinatesRight(it.second / 90, wayX, wayY)
                wayX = pos.first
                wayY = pos.second
            }
            'L' -> {
                val pos = rotateCoordinatesRight(-it.second / 90, wayX, wayY)
                wayX = pos.first
                wayY = pos.second
            }
            'F' -> {
                x += wayX * it.second
                y += wayY * it.second
            }
        }
    }
    return x.absoluteValue + y.absoluteValue
}

fun rotateCoordinatesRight(deg: Int, x: Int, y: Int): Pair<Int, Int> {
    val mult = if (deg < 0) -1 else 1
    var x = x
    var y = y
    for (i in 1..deg.absoluteValue) {
        val newX = mult * y
        y = mult * -x
        x = newX
    }
    return Pair(x, y)
}

fun generateFile(): List<Pair<Char, Int>> {
    return File("day12/src/input.txt").readLines().map {
        Pair(it.first(), it.drop(1).toInt())
    }
}
