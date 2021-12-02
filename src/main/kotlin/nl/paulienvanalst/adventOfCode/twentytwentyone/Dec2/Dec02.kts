#!/usr/bin/env kscript

import nl.paulienvanalst.adventOfCode.twentytwentyone.utils.Utils

val reportInput = Utils.readInput()
val nrOfInputs = reportInput.size

println("Working towards solution to part 1: reading $nrOfInputs lines of code")

val finalPosition = reportInput.map { it.split(" ") }.foldRight(Pair(0L, 0L)) {
    movement, acc -> acc.addMovement(movement)
}

val solution = finalPosition.horizontal() * finalPosition.depth()

println("Solution to part 1: $solution")

println("Working towards solution to part 2")

val finalPosition2 = Utils.readInput().map { it.split(" ") }.fold(Pair(Pair(0L, 0L), 0L)) {
        acc, movement -> acc.addMovement2(movement)
}

val solution2 = finalPosition2.position().horizontal() * finalPosition2.position().depth()

println("Solution to part 2: $solution2")

typealias Position = Pair<Long, Long>
fun Position.horizontal() = this.first
fun Position.depth() = this.second

typealias PositionAndAim = Pair<Position, Long>
fun PositionAndAim.position() = this.first
fun PositionAndAim.aim() = this.second

fun Pair<Long, Long>.addMovement(movement: List<String>) : Position {
    val x = movement[1].toLong()
    return when(movement[0]) {
        "forward" -> this.horizontal() + x to this.depth()
        "down"    -> this.horizontal() to this.depth() + x
        "up"      -> this.horizontal() to this.depth() - x
        else -> this
    }
}

fun Pair<Position, Long>.addMovement2(movement: List<String>) : PositionAndAim {
    val x = movement[1].toLong()
    return when(movement[0]) {
        "down" -> this.position() to this.aim() + x
        "up" -> this.position() to this.aim() - x
        "forward" -> {
            val newPostion = this.position().horizontal() + x to this.position().depth() + this.aim() * x
            newPostion to this.aim()
        }
        else -> this
    }
}
