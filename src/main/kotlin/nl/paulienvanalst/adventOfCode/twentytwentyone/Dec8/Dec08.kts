#!/usr/bin/env kscript

package nl.paulienvanalst.adventOfCode.twentytwentyone.Dec8

import nl.paulienvanalst.adventOfCode.twentytwentyone.utils.Utils

val uniqueCombinations = listOf(2, 3, 4, 7)

val input = Utils.readInput().map { it.split(("|"))}.map { it[0] to it[1] }

val outputValues = input.map { it.second }

val appearances = outputValues.flatMap { it.trim().split(" ") }.count { it.length in uniqueCombinations }

println("Solution to part 1: ${appearances}")
