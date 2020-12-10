import java.io.File

fun main() {
    val file = generateFile()

    println("Part 1: " + part1(file))
    println("Part 2: " + part2(file))
}

fun part1(file: List<Map<String, String>>): Int {
    var validCount = 0
    file.forEach {
        if (it.keys.containsAll(listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"))) {
            validCount++
        }
    }
    return validCount
}

fun part2(file: List<Map<String, String>>): Int {
    var validCount = 0
    file.forEach {
        if (it.keys.containsAll(listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"))) {
            if (it["byr"]?.toInt() !in 1920..2002) {
                return@forEach
            }
            if (it["iyr"]?.toInt() !in 2010..2020) {
                return@forEach
            }
            if (it["eyr"]?.toInt() !in 2020..2030) {
                return@forEach
            }
            if (!(((it["hgt"]?.endsWith("cm") == true && (it["hgt"]?.dropLast(2)?.toInt() in 150..193)) || (it["hgt"]?.endsWith("in") == true) && (it["hgt"]?.dropLast(2)?.toInt() in 59..76)))) {
                return@forEach
            }
            if (!(it["hcl"]?.startsWith("#") == true && it["hcl"]?.drop(1)?.matches(Regex("[0-9a-f]{6}")) == true)) {
                return@forEach
            }
            if (it["ecl"] !in listOf("amb", "blu", "grn", "gry", "brn", "hzl", "oth")) {
                return@forEach
            }
            if (!(it["pid"]?.length == 9 && it["pid"]?.toInt() !== null)) {
                return@forEach
            }
            validCount++
        }
    }
    return validCount
}

fun validatePassport(passport: Map<String, String>): Boolean {
    if (passport.keys.containsAll(listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"))) {
        try {
            if (passport["byr"]?.toInt() !in 1920..2002) {
                println("byr: " + passport["byr"])
                return false
            }
            if (passport["iyr"]?.toInt() !in 2010..2020) {
                println("iyr")
                return false
            }
            if (passport["eyr"]?.toInt() !in 2020..2030) {
                println("eyr")
                return false
            }
            if (!(((passport["hgt"]?.endsWith("cm") == true && (passport["hgt"]?.slice(IntRange(0, passport["hgt"]!!.length - 3))?.toInt() in 150..193)) || (passport["hgt"]?.endsWith("in") == true) && (passport["hgt"]?.slice(IntRange(0, passport["hgt"]!!.length - 3))?.toInt() in 59..76)))) {
                println("hgt:" + passport["hgt"])
                println("parsed value:" + passport["hgt"]?.slice(IntRange(0, passport["hgt"]!!.length - 3))?.toInt())
                return false
            }
            if (!(passport["hcl"]?.startsWith("#") == true && passport["hcl"]!!.slice(IntRange(1, passport["hcl"]!!.length-1)).matches(Regex("[0-9a-f]{6}")))) {
                println("hcl")
                return false
            }
            if (passport["ecl"] !in listOf("amb", "blu", "grn", "gry", "brn", "hzl", "oth")) {
                println("ecl")
                return false
            }
            if (!(passport["pid"]?.length == 9 && passport["pid"]?.toInt() !== null)) {
                println("pid")
                return false
            }
            return true
        } catch (e: Exception) {
            println("error:" + e.localizedMessage)
        }
    }
    return false
}

fun generateFile(): List<Map<String, String>> {
    val file: MutableList<Map<String, String>> = mutableListOf()
    var currentPassport: MutableMap<String, String> = mutableMapOf()

    File("day4/src/input.txt").forEachLine {
        if (it.isEmpty()) {
            file.add(currentPassport)
            currentPassport = mutableMapOf()
        } else {
            val splitString = it.split(":", " ")
            for (i in splitString.indices step 2) {
                currentPassport[splitString[i]] = splitString[i + 1]
            }
        }
    }
    file.add(currentPassport)
    return file
}
