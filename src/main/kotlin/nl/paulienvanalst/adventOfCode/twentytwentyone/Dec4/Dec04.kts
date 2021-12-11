#!/usr/bin/env kscript

import nl.paulienvanalst.adventOfCode.twentytwentyone.utils.Utils
import java.lang.IllegalArgumentException
import java.util.*

val input = Utils.readFileAsWhole()

val text = input.split("\n\n")

val numbers = text[0].split(",").map { it.toInt() }

val boards = text.subList(1, text.size)
    .map { board -> board.split("\n") }
    .map { it.map { row -> row.split(" ").filter { el -> el.isNotEmpty() }.map { nr -> nr.toInt() } } }

val (draws, index) = findWinningBoard(false, 4, numbers, boards)

val sumUnMarked = boards[index].flatten().filter { it !in draws }.sum()

println("Solution to part 1: ${sumUnMarked * draws.last() }")

val (draws2, index2) = findLastWinningBoard(4, listOf(), numbers, boards)
val sumUnMarked2 = boards[index2].flatten().filter { it !in draws2 }.sum()
println("Solution to part 2: ${sumUnMarked2 * draws2.last() }")

println("Found")

fun rowIsWin(row: List<Int>, draws: List<Int>) = row.all { el -> el in draws }

fun List<List<Int>>.transpose() : List<List<Int>> {
    val transposedArray = List(this[0].size){ IntArray(this.size) }
    for(y in 0 until this[0].size) {
        for (x in this.indices) {
            transposedArray[y][x] = this[x][y]
        }
    }
    return transposedArray.map{ it.toList() }
}

fun isBoardWinning(
    board: List<List<Int>>,
    draws: List<Int>
) : Boolean {
    val anyRowWinning = board.filter { it.isNotEmpty() }.any { row -> rowIsWin(row, draws) }
    val anyColumnWinning = board.filter { it.isNotEmpty() }.transpose().any { row -> rowIsWin(row, draws) }
    return anyRowWinning || anyColumnWinning
}

fun findWinningBoard(isBoardFound: Boolean, indexForDraft: Int, numbers: List<Int>, boards: List<List<List<Int>>>): Pair<List<Int>, Int> {
    val previousDraws = numbers.take(indexForDraft - 1)
    if(isBoardFound) {
        val indexOfBoard  = boards.indexOfFirst { board : List<List<Int>> -> isBoardWinning(board, previousDraws) }
        return previousDraws to indexOfBoard
    }
    val isBoardFound1 = boards.any { board -> isBoardWinning(board, numbers.take(indexForDraft)) }
    return findWinningBoard(isBoardFound1, indexForDraft + 1, numbers, boards)
}

fun findLastWinningBoard(indexForDraft: Int, wins: List<Int>, numbers: List<Int>, boards: List<List<List<Int>>>): Pair<List<Int>, Int> {
    val draws = numbers.take(indexForDraft)
    if(wins.size == boards.size) {
        return numbers.take(indexForDraft -  1) to wins.last()
    }
    val winningIdices = boards.withIndex().filter { board -> board.index !in wins && isBoardWinning(board.value, draws) }.map { it.index }
    return findLastWinningBoard(indexForDraft + 1, wins + winningIdices, numbers, boards)
}
