#!/usr/bin/env kscript

import nl.paulienvanalst.adventOfCode.twentytwentyone.utils.Utils

val reportInput = Utils.readInput().map { it.toInt() }
val nrOfInputs = reportInput.size

println("Working towards solution to part 1: reading $nrOfInputs lines of code")

val counting = reportInput.zipWithNext().count { (a, b) -> b > a }

println("Solution to part 1: $counting")

println("Working towards solution to part 2")

val counting2 = reportInput.windowed(3) { it.sum() }.zipWithNext().count { (a, b) -> b > a }

println("Solution to part 2: $counting2")
