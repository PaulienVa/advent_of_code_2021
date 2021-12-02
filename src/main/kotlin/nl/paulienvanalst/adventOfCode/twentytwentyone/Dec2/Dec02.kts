#!/usr/bin/env kscript

import nl.paulienvanalst.adventOfCode.twentytwentyone.utils.Utils

val reportInput = Utils.readInput()
val nrOfInputs = reportInput.size

println("Working towards solution to part 1: reading $nrOfInputs lines of code")

val finalPosition = reportInput.map { it.split(" ") }.foldRight(Pair(0,0)) {
    movement, acc -> acc.addMovement(movement)
}

val solution = finalPosition.horizontal() * finalPosition.depth()

println("Solution to part 1: $solution")

println("Working towards solution to part 2")

//val counting2 = reportInput.windowed(3) { it.sum() }.zipWithNext().count { (a, b) -> b > a }

//println("Solution to part 2: $counting2")

typealias Position = Pair<Int, Int>
fun Position.horizontal() = this.first
fun Position.depth() = this.second

fun Pair<Int, Int>.addMovement(movement: List<String>) = when(movement[0]) {
    "forward" -> this.horizontal() + movement[1].toInt() to this.depth()
    "down"    -> this.horizontal() to this.depth() + movement[1].toInt()
    "up"      -> this.horizontal() to this.depth() - movement[1].toInt()
    else -> this
}
