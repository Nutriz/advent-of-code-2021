
fun main() {

    fun fishesSimpleWay(input: MutableList<Int>, days: Int): MutableList<Int> {
        var fishes = input
        for (day in 1..days) {
            var toCreate = 0
            fishes = fishes.map {
                if (it == 0) {
                    toCreate++
                    6
                } else {
                    it - 1
                }
            }.toMutableList()
            (0 until toCreate).forEach {
                fishes += 8
            }
        }
        return fishes
    }

    fun fishesOptimizedWay(input: MutableList<Int>, days: Int): Long {
        var fishCounter = HashMap<Int, Long>(9)

        // initial count
        (0..8).forEach { index ->
            fishCounter[index] = (fishCounter[index] ?: 0) + input.count { it == index }.toLong()
        }

        (1..days).forEach { day ->
            val newFishCount = HashMap<Int, Long>(9)
            fishCounter.forEach { (index, count) ->
                if (index == 0) {
                    newFishCount[6] = count
                    newFishCount[8] = count
                } else {
                    newFishCount[index - 1] = (newFishCount[index - 1] ?: 0) + count
                }
            }
            fishCounter = newFishCount
        }
        return fishCounter.values.sum()
    }

    fun part1(input: String): Long {
        val fishes = input.split(",").map { it.toInt() }.toMutableList()
        return fishesOptimizedWay(fishes, 80)
    }

    fun part2(input: String): Long {
        val fishes = input.split(",").map { it.toInt() }.toMutableList()
        return fishesOptimizedWay(fishes, 256)
    }

    // test if implementation meets criteria from the description, like:
    check("part1", part1(testInput), 5934)
    check("part2", part2(testInput), 26984457539)

    measureAndLog {
        val result = part1(realInput)
        println("part1: $result")
    }
    measureAndLog {
        val result = part2(realInput)
        println("part2: $result")
    }
}

private const val testInput =
    "3,4,3,1,2"

private const val realInput =
    "1,4,1,1,1,1,1,1,1,4,3,1,1,3,5,1,5,3,2,1,1,2,3,1,1,5,3,1,5,1,1,2,1,2,1,1,3,1,5,1,1,1,3,1,1,1,1,1,1,4,5,3,1,1,1,1,1,1,2,1,1,1,1,4,4,4,1,1,1,1,5,1,2,4,1,1,4,1,2,1,1,1,2,1,5,1,1,1,3,4,1,1,1,3,2,1,1,1,4,1,1,1,5,1,1,4,1,1,2,1,4,1,1,1,3,1,1,1,1,1,3,1,3,1,1,2,1,4,1,1,1,1,3,1,1,1,1,1,1,2,1,3,1,1,1,1,4,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,5,1,1,1,2,2,1,1,3,5,1,1,1,1,3,1,3,3,1,1,1,1,3,5,2,1,1,1,1,5,1,1,1,1,1,1,1,2,1,2,1,1,1,2,1,1,1,1,1,2,1,1,1,1,1,5,1,4,3,3,1,3,4,1,1,1,1,1,1,1,1,1,1,4,3,5,1,1,1,1,1,1,1,1,1,1,1,1,1,5,2,1,4,1,1,1,1,1,1,1,1,1,1,1,1,1,5,1,1,1,1,1,1,1,1,2,1,4,4,1,1,1,1,1,1,1,5,1,1,2,5,1,1,4,1,3,1,1"