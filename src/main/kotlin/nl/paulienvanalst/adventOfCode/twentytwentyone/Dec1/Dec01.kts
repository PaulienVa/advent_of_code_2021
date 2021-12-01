#!/usr/bin/env kscript

import nl.paulienvanalst.adventOfCode.twentytwentyone.utils.Utils

val reportInput = Utils.readInput().map { it.toInt() }
val nrOfInputs = reportInput.size

println("Working towards solution to part 1: reading $nrOfInputs lines of code")

val counting = reportInput.foldIndexed(0) {
    index, total, item -> if( index + 1 < nrOfInputs && reportInput[index + 1] > item) {
        total + 1
    } else {
        total
    }
}

println("Solution to part 1: $counting")

println("Working towards solution to part 2")
val threeMeasurements = reportInput.subList(0, nrOfInputs - 2).mapIndexed { index, item ->
    item + reportInput[index + 1] + reportInput[index + 2]
}

threeMeasurements.cou

val counting2 = threeMeasurements.foldIndexed(0) {
        index, total, item -> if(index + 1 < threeMeasurements.size && threeMeasurements[index + 1] > item) {
    total + 1
} else {
    total
}
}
println("Solution to part 2: $counting2")
