import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.math.abs

fun String.resolve(): List<Pair<Int, Int>> =
    this.lines().map {
        val numbers = it.split("   ")
        numbers[0].toInt() to numbers[1].toInt()
    }

fun List<Pair<Int, Int>>.findDistances(): Int {
    val firstList = mapIndexed { pos, dis -> (pos to dis.first) }.sortedBy { pair -> pair.second }
    val secondList = mapIndexed { pos, dis -> (pos to dis.second) }.sortedBy { pair -> pair.second }

    return firstList.mapIndexed { num, pos -> abs(pos.second - secondList[num].second) }.sum()
}

fun List<Pair<Int, Int>>.findSimilarities(): Int {
    return map { it.first }.sumOf { find -> find * map { it.second }.filter { it == find }.size }

}

fun main() {
    val pairs = Path("src/Day1_input.txt").readText().trim().resolve()
    println("Total Distance is: ${pairs.findDistances()}")
    println("Similarities sum are: ${pairs.findSimilarities()}")
}
