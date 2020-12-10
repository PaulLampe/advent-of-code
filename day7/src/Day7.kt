import java.io.File

fun main() {
    val file = generateFile()

    println("Part 1: ${part1(file)}")
    println("Part 1: ${part2(file)}")
}

fun part1(file: List<Pair<String, List<Pair<Int, String>>>>): Int {
    val bags: MutableList<String> = mutableListOf("shinygold")
    var lastSize = -1
    var bagSize = 0

    while (bagSize != lastSize) {
        file.forEach { currentBag ->
            val currentBagName = currentBag.first
            val containedBags = currentBag.second.map { it.second }
            if (!bags.contains(currentBagName)) {
                containedBags.forEach {
                    if (bags.contains(it) && !bags.contains(currentBagName)) {
                        bags.add(currentBagName)
                    }
                }
            }
        }
        println("foreach")
        lastSize = bagSize
        bagSize = bags.size
    }
    return bags.size - 1
}

fun part2(file: List<Pair<String, List<Pair<Int, String>>>>): Long {
    return getContainedBags(file, "shinygold")
}

fun getContainedBags(file: List<Pair<String, List<Pair<Int, String>>>>, name: String): Long {
    var containedBags = 0L
    val bag = file.find { it.first == name } ?: throw Exception("wrong name: $name")

    bag.second.forEach {
        containedBags += it.first * (getContainedBags(file, it.second) + 1)
    }
    return containedBags
}

fun generateFile(): List<Pair<String, List<Pair<Int, String>>>> {
    val file: MutableList<Pair<String, List<Pair<Int, String>>>> = mutableListOf()
    File("day7/src/input.txt").forEachLine {
        val splitString = it.split(Regex(" bag, | bags, | bag\\.| bags\\.| bags contain no other | bags contain ")).dropLast(1).map { it.replace(" ", "") }
        file.add(Pair(splitString[0], splitString.drop(1).map { Pair(it[0].toString().toInt(), it.drop(1)) }))
    }
    return file
}
