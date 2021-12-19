#!/usr/bin/env kscript

import nl.paulienvanalst.adventOfCode.twentytwentyone.utils.Utils

val initialState = LongArray(9).apply {
    Utils.readFileAsWhole().split(",").map{ it.trim().toInt() }.forEach { this[it] += 1L }
}
//println(initialState)

val until80Days = evolution(initialState.asSequence(), 0, 80).count()

println("Initial amount: ${initialState.size}")

println("Solution to part 1: ${until80Days}")
performingEvolutions(256)
val until256Days = this.initialState.sum()

println("Solution to part 2: $until256Days")

fun performingEvolutions(nrDays: Int) {
    repeat(nrDays) {
        initialState.evolute()
        initialState[6] += initialState[8]
    }
}

fun LongArray.evolute() {
    val nr0s = this[0]
    this.copyInto(this, startIndex = 1)
    this[this.lastIndex] = nr0s
}

fun evolution(state: Sequence<Long>, day: Int, nrDays: Int) : Sequence<Long> {
    if (day == nrDays) {
        return state
    }
    val nr0s = state.count { it == 0L }
    val nr8s = (1 .. nr0s).map { 8L }
    val newState = state.map { newState(it) }
    return evolution(newState + nr8s, day + 1, nrDays)
}

fun newState(nrDays: Long) : Long {
    return if (nrDays == 0L) {
       6
    } else {
        nrDays - 1
    }
}
