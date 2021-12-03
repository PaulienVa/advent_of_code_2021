#!/usr/bin/env kscript

import nl.paulienvanalst.adventOfCode.twentytwentyone.utils.Utils

val reportInput = Utils.readInput()
val nrOfInputs = reportInput.size

println("Working towards solution to part 1: reading $nrOfInputs lines of code")
val gammaRate = reportInput
    .map { it.chunked(1).map { char -> char.toInt() } }
    .transform()
    .map { it.mostCommon() }

val gammaDecimal = gammaRate.joinToString(separator = "").toInt(2)
println("What is the gamma rate (Decimal) $gammaDecimal")

val epsilonRate = gammaRate.map {
    return@map if(it ==  0) {
        1
    } else {
        0
    }
}
val epsilonDecimal = epsilonRate.joinToString(separator = "").toInt(2)
println("What is the epsilon rate (Decimal): $epsilonDecimal")

val solution = gammaDecimal  * epsilonDecimal
println("Solution to part 1: $solution")

println("Working towards solution to part 2")

val oxygenRating = oxygenRating(reportInput, 0)[0].toInt(2)
println("What is the oxygen rating $oxygenRating")

val co2ScrubberRating = co2ScrubberRating(reportInput, 0)[0].toInt(2)
println("What is the co2Scrubber rating $co2ScrubberRating")

val solution2 = oxygenRating * co2ScrubberRating
println("Solution to part 2: $solution2")

fun List<List<Int>>.transform() : List<IntArray> {
    val transformedArray = List(this[0].size){ IntArray(this.size) }
    for(y in 0 until this[0].size) {
        for (x in this.indices) {
            transformedArray[y][x] = this[x][y]
        }
    }
    return transformedArray
}

fun IntArray.mostCommon(): Int {
    val nrOnes = this.count { it == 1 }
    val nrZeros = this.count { it == 0 }
    return if (nrZeros > nrOnes ) {
        0
    } else {
        1
    }
}

fun IntArray.mostCommonOrEqual(): Int {
    val nrOnes = this.count { it == 1 }
    val nrZeros = this.count { it == 0 }
    return if (nrZeros == nrOnes ) {
        2
    } else if (nrZeros > nrOnes) {
        0
    } else{
        1
    }
}

fun IntArray.lessCommonOrEqual(): Int {
    val nrOnes = this.count { it == 1 }
    val nrZeros = this.count { it == 0 }
    return if (nrZeros == nrOnes ) {
        2
    } else if (nrZeros < nrOnes) {
        0
    } else{
        1
    }
}

fun co2ScrubberRating(input: List<String>, indexLessCommon: Int) : List<String> {
    val bitZero = "0"
    val epsilonRate = input
        .map { it.chunked(1).map { char -> char.toInt() } }
        .transform()
        .map { it.lessCommonOrEqual() }

    return if (input.size == 1 || indexLessCommon == 12) {
        input
    } else if(epsilonRate[indexLessCommon] == 2) {
        co2ScrubberRating(
            input.filter { "${it[indexLessCommon]}" == bitZero },
            indexLessCommon + 1,
        )
    } else {
        co2ScrubberRating(
            input.filter { "${it[indexLessCommon]}" == "${epsilonRate[indexLessCommon]}"},
            indexLessCommon + 1,
        )
    }
}

fun oxygenRating(input: List<String>, indexMostCommon: Int) : List<String> {
    val bitOne = "1"
    val gammaRate = input
        .map { it.chunked(1).map { char -> char.toInt() } }
        .transform()
        .map { it.mostCommonOrEqual() }

    return if (input.size == 1 || indexMostCommon == 12) {
        input
    } else if(gammaRate[indexMostCommon] == 2) {
        oxygenRating(
            input.filter { "${it[indexMostCommon]}" == bitOne },
            indexMostCommon + 1,
        )
    } else {
        oxygenRating(
            input.filter { "${it[indexMostCommon]}" == "${gammaRate[indexMostCommon]}"},
            indexMostCommon + 1,
        )
    }
}
