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
