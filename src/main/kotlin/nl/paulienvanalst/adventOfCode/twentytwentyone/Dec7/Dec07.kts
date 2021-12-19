#!/usr/bin/env kscript

package nl.paulienvanalst.adventOfCode.twentytwentyone.Dec7

import nl.paulienvanalst.adventOfCode.twentytwentyone.utils.Utils
import kotlin.math.absoluteValue

val input = Utils.readFileReturnIntArray(",")

val horizontalPositionByFrequency = input.groupingBy { it }.eachCount()

val crabFrequencies = horizontalPositionByFrequency.keys

val lessCommonPosition = crabFrequencies.minOf { it }
val mostCommonPostion = crabFrequencies.maxOf { it }

fun IntRange.minimalFuel(fuel: (Int, Int, Int) -> Int) = this.minOf {
        finalPosition -> horizontalPositionByFrequency.map { (position, freq) -> fuel(position, finalPosition, freq) }.sum()
}

fun fuelPart1(position: Int, finalPosition: Int, freq: Int) =
    (position - finalPosition).absoluteValue * freq

fun fuelPart2(position: Int, finalPosition: Int, freq: Int) = (minOf(position, finalPosition) until maxOf(
    position,
    finalPosition
)).sumOf { it - minOf(position, finalPosition) + 1 } *  freq

val minimalFuel1 = (lessCommonPosition .. mostCommonPostion).minimalFuel { position, finalPosition, freq ->
    fuelPart1(
        position,
        finalPosition,
        freq
    )
}

println("Solution to part 1: $minimalFuel1")

val testFuel2 =  fuelPart2(16, 5, 1)

println("Test: ${(minOf(5, 16) .. maxOf(5, 16)).map {it - minOf(5,16)} + 1 }")
println("Test: $testFuel2")

val minimalFuel2 = (lessCommonPosition .. mostCommonPostion).minimalFuel { position, finalPosition, freq ->
    fuelPart2(
        position,
        finalPosition,
        freq
    )
}

println("Solution to part 2: $minimalFuel2")
