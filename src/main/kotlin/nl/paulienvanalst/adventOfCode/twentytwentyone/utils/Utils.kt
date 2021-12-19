package nl.paulienvanalst.adventOfCode.twentytwentyone.utils

import java.io.File

object Utils {
    fun readInput() = File("./input.txt").inputStream().bufferedReader().readLines()
    fun readFileAsWhole() = File("./input.txt").inputStream().bufferedReader().readText()
    fun readFileReturnIntArray(delimiter: String) = File("./input.txt").inputStream().bufferedReader()
        .readText().split(delimiter).map { it.trim().toInt() }
}
