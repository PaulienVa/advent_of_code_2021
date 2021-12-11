#!/usr/bin/env kscript

package nl.paulienvanalst.adventOfCode.twentytwentyone.Dec5

import nl.paulienvanalst.adventOfCode.twentytwentyone.utils.Utils
import kotlin.math.abs

data class Position(val x: Int, val  y: Int) {
    companion object {
        fun fromString(string: String) : Position {
            val splittedString = string.split(",")
            return Position(splittedString[0].trim().toInt(), splittedString[1].trim().toInt())
        }
    }
}

data class Movement(val start: Position, val end: Position) {
    companion object {
        fun fromString(string: String) : Movement {
            val splittedString = string.split("->")
            return Movement(Position.fromString(splittedString[0]), Position.fromString(splittedString[1]))
        }
    }

    fun horizontalOrVertial() = start.x == end.x || start.y == end.y

    fun coveredPoints() : List<Position> {
        if (start.x == end.x) {
            val startRange = minOf(start.y, end.y) - 1
            val endRange = maxOf(start.y,  end.y)
            return (startRange until endRange).map { Position(start.x, it + 1) }
        } else {
            val startRange = minOf(start.x, end.x) - 1
            val endRange = maxOf(start.x,  end.x)
            return (startRange until endRange).map { Position(it + 1, start.y) }
        }
    }

    fun coveredPointsDiagonal() : List<Position> {
        return (0..maxOf(abs(start.x - end.x), abs(start.y - end.y))).map { delta ->
            val x = if (end.x > start.x) start.x + delta else if (end.x < start.x) start.x - delta else start.x
            val y = if (end.y > start.y) start.y + delta else if (end.y < start.y) start.y - delta else start.y

            Position(x, y)
        }
    }

}

fun List<Position>.countMoreThanTwoTimesCovered() = this.groupBy { it }.count { it.value.size > 1 }

val input = Utils.readInput()

println("Total input lines ${input.size}")

val movements = input.map { Movement.fromString(it) }
val onlyHorizontalOrVertical = movements.filter { it.horizontalOrVertial() }.flatMap { it.coveredPoints() }.countMoreThanTwoTimesCovered()

println("Solution to part 1: ${onlyHorizontalOrVertical}")

val diagonals            = movements.filterNot { it.horizontalOrVertial() }.flatMap { it.coveredPointsDiagonal() }
val horizontalOrVertical = movements.filter { it.horizontalOrVertial() }.flatMap { it.coveredPoints() }

val all =  (diagonals + horizontalOrVertical).countMoreThanTwoTimesCovered()

println("Solution to part 2:  ${all}")
