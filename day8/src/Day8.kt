import java.io.File

fun main() {
    val file = generateFile()

    println("Part 1: " + part1(file).first)
    println("Part 2: " + part2(file))
}

fun part1(file: List<Pair<String, Int>>): Pair<Int, List<Int>> {
    var multiple = false
    val indices = mutableListOf<Int>()
    var currentIndex = 0
    var accumulator = 0

    while (!multiple) {
        val command = file[currentIndex].first
        val value = file[currentIndex].second

        if (indices.contains(currentIndex)) {
            multiple = true
        } else {
            indices.add(currentIndex)
            multiple = currentIndex == file.lastIndex

            if (command == "jmp") {
                currentIndex += value
            } else if (command == "acc") {
                accumulator += value
                currentIndex++
            } else {
                currentIndex++
            }
        }
    }
    return Pair(accumulator, indices.toList())
}

fun part2(file: List<Pair<String, Int>>): Int {
    var terminated = false
    val changedIndices = mutableListOf<Int>()
    var thisRound = false
    var returnVal = 0
    while (!terminated) {
        val fileCopy = file.toMutableList()
        fileCopy.forEachIndexed { index, pair ->
            if ((pair.first == "jmp" || pair.first == "nop") && !changedIndices.contains(index) && !thisRound) {
                fileCopy[index] = Pair(if (pair.first == "jmp") "nop" else "jmp", fileCopy[index].second)
                thisRound = true
                changedIndices.add(index)
            }
        }
        if (fileCopy.mapIndexed { index, pair -> pair.first == file[index].first }.contains(false)) {
            val res = part1(fileCopy)
            terminated = res.second.contains(file.lastIndex)
            returnVal = res.first
        } else {
            terminated = true
        }
        thisRound = false
    }
    return returnVal
}

fun generateFile(): List<Pair<String, Int>> {
    val file: MutableList<Pair<String, Int>> = mutableListOf()
    File("day8/src/input.txt").forEachLine {
        val splitString = it.split(" ")
        file.add(Pair(splitString[0], splitString[1].toInt()))
    }
    return file
}
