#!/usr/bin/env kscript

package nl.paulienvanalst.adventOfCode.twentytwentyone.Dec7

import nl.paulienvanalst.adventOfCode.twentytwentyone.utils.Utils
import kotlin.math.absoluteValue

val input = Utils.readFileReturnIntArray(",")

val horizontalPositionByFrequency = input.groupingBy { it }.eachCount()

val crabFrequencies = horizontalPositionByFrequency.keys

val lessCommonPosition = crabFrequencies.minOf { it }
val mostCommonPostion = crabFrequencies.maxOf { it }

val minimalFuel = (lessCommonPosition .. mostCommonPostion).minOf {
    finalPosition -> horizontalPositionByFrequency.map { (position, freq) -> (position  - finalPosition).absoluteValue * freq }.sum()
}

println("Solution to part 1: $minimalFuel")

println("Found")


println("Solution to part 2:")

println("Found")


