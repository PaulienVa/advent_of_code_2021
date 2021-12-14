#!/usr/bin/env kscript

import nl.paulienvanalst.adventOfCode.twentytwentyone.utils.Utils

val initialState = Utils.readFileAsWhole().split(",").map{ it.trim().toInt() }
//println(initialState)

val until80Days = evolution(initialState, 0).size

println("Initial amount: ${initialState.size}")

println("Solution to part 1: ${until80Days}")

println("Found")


println("Solution to part 2:")

println("Found")

fun evolution(state: List<Int>, day: Int) : List<Int> {
    if (day == 80) {
        return state
    }
    val nr0s = state.count { it == 0 }
    val nr8s = (1 .. nr0s).map { 8 }
    val newState = state.map { newState(it) }
    return evolution(newState + nr8s, day + 1)
}

fun newState(nrDays: Int) : Int {
    return if (nrDays == 0) {
       6
    } else {
        nrDays - 1
    }
}
