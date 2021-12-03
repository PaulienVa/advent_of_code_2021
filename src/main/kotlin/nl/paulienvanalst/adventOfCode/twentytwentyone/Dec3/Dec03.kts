#!/usr/bin/env kscript

import nl.paulienvanalst.adventOfCode.twentytwentyone.utils.Utils

val reportInput = Utils.readInput()
val nrOfInputs = reportInput.size

println("Working towards solution to part 1: reading $nrOfInputs lines of code")
val gammaRate = reportInput.findGammaRate { mostCommon() }

val gammaDecimal = gammaRate.joinToString(separator = "").toInt(2)
println("What is the gamma rate (Decimal) $gammaDecimal")

val epsilonRate = gammaRate.convert()
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

fun List<List<Int>>.transpose() : List<IntArray> {
    val transposedArray = List(this[0].size){ IntArray(this.size) }
    for(y in 0 until this[0].size) {
        for (x in this.indices) {
            transposedArray[y][x] = this[x][y]
        }
    }
    return transposedArray
}

fun List<Int>.convert(): List<Int> {
    return this.map {
        return@map if(it ==  0) {
            1
        } else  if (it == 2) {
            2
        } else {
            0
        }
    }
}

fun List<String>.findGammaRate(fn: IntArray.() -> Int): List<Int> {
    return this.map { it.chunked(1).map { char -> char.toInt() } }
        .transpose()
        .map { fn(it) }
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

fun co2ScrubberRating(input: List<String>, indexLessCommon: Int) : List<String> {
    val bitZero = "0"
    val epsilonRate = input.findGammaRate { mostCommonOrEqual() }.convert()
    return findRating(input, indexLessCommon, epsilonRate, bitZero) { a: List<String>, b : Int -> co2ScrubberRating(a, b)}
}

fun oxygenRating(input: List<String>, indexMostCommon: Int) : List<String> {
    val bitOne = "1"
    val gammaRate = input.findGammaRate { mostCommonOrEqual() }
    return findRating(input, indexMostCommon, gammaRate, bitOne) { a: List<String>, b : Int -> oxygenRating(a, b)}
}

fun findRating(
    input: List<String>,
    index: Int,
    rate: List<Int>,
    defaultBit: String,
    function: (List<String>, Int) -> List<String>
) = if (input.size == 1 || index == 12) {
    input
} else if (rate[index] == 2) {
    function(
        input.filter { "${it[index]}" == defaultBit },
        index + 1,
    )
} else {
    function(
        input.filter { "${it[index]}" == "${rate[index]}" },
        index + 1,
    )
}
