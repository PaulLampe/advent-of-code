import java.io.File

fun main() {
    val file = generateFile()

    println("Part 1: ${part1(file)}")
    println("Part 2: ${part2(file)}")
}

fun part1(file: List<Pair<String, String>>): Long {
    var mask: String = "X"
    val mem: MutableMap<Int, Long> = mutableMapOf()
    file.forEach {
        if (it.first == "mask") {
            mask = it.second
        } else if (it.first == "mem") {
            val splitString = it.second.split(",")
            val unmasked = splitString[1].toInt().toString(2)
            val masked = unmasked.padStart(36, '0').toCharArray()
            for (i in mask.indices) {
                when (mask[i]) {
                    '0' -> masked[i] = '0'
                    '1' -> masked[i] = '1'
                }
            }
            mem[splitString[0].toInt()] = masked.concatToString().toLong(2)
        }
    }
    return mem.values.sum()
}

fun part2(file: List<Pair<String, String>>): Long {
    var mask: String = "X"
    val mem: MutableMap<Long, Long> = mutableMapOf()
    file.forEach {
        if (it.first == "mask") {
            mask = it.second
        } else if (it.first == "mem") {
            val splitString = it.second.split(",")
            val addressUnmasked = splitString[0].toInt().toString(2)
            val addressMasked = addressUnmasked.padStart(36, '0').toCharArray()
            for (i in mask.indices) {
                when (mask[i]) {
                    'X' -> addressMasked[i] = 'X'
                    '1' -> addressMasked[i] = '1'
                }
            }
            val addresses = getAddressesFromMask(addressMasked.concatToString())
            addresses.forEach {
                mem[it] = splitString[1].toLong()
            }
        }
    }
    return mem.values.sum()
}

fun getAddressesFromMask(mask: String): List<Long> {
    val list: MutableList<Long> = mutableListOf()
    if (mask.contains('X')) {
        list.addAll(getAddressesFromMask(mask.replaceFirst('X', '1')))
        list.addAll(getAddressesFromMask(mask.replaceFirst('X', '0')))
    } else {
        list.add(mask.toLong(2))
    }
    return list
}

fun generateFile(): List<Pair<String, String>> {
    val returnList: MutableList<Pair<String, String>> = mutableListOf()
    File("day14/src/input.txt").forEachLine {
        val splitString = it.split(Regex("\\[|] = | = "))
        if (splitString.size == 3) {
            returnList.add(Pair(splitString[0], "${splitString[1]},${splitString[2]}"))
        } else {
            returnList.add(Pair(splitString[0], splitString[1]))
        }
    }
    return returnList
}
