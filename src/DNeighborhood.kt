fun hammingDistance(strOne: String, StringTwo: String): Int {

    var hammingCount = 0
    strOne.filterIndexed { index, char ->
        if (StringTwo[index] != char) {
            hammingCount += 1
        }
        true
    }
    return hammingCount
}

fun neighbors(pattern: String, hammingDistance: Int): List<String> {
    if (hammingDistance == 0) {
        return listOf(pattern)
    }

    if (pattern.isEmpty()) {
        return listOf("")
    }

    if (pattern.length == 1) {
        return listOf("A", "C", "G", "T")
    }

    val neighborhood : MutableList<String> = arrayListOf()
    val suffixOfPattern = pattern.substring(1, pattern.length)
    val suffixNeighbors = neighbors(suffixOfPattern, hammingDistance)
    for (s in suffixNeighbors) {
        if (hammingDistance(suffixOfPattern, s) < hammingDistance) {
            for (n in listOf("A", "C", "G", "T")) {
                neighborhood.add("$n$s")
            }
        } else {
            neighborhood.add("${pattern[0]}$s")
        }
    }
    return neighborhood
}

fun main(){
    print(neighbors("TCATAAGCCA",3).joinToString("\n"))
}