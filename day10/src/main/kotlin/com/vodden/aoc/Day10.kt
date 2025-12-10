package com.vodden.aoc

typealias Target = List<Boolean>
typealias Button = List<Int>

class Day10 {
    val input: String by lazy {
        requireNotNull(object {}.javaClass.getResource("/input.txt")?.readText(),
            { "Could not open input.txt resource." })
    }
    
    val targets by lazy { 
        input.lines().flatMap { line -> line.split(" ").take(1)}.map { 
            line -> line.mapNotNull {
                when(it) {
                    '.' -> false
                    '#' -> true
                    else -> null
                }
            }
        }
    }
    
    val buttons by lazy {
        input.lines().map { line -> line.split(" ").drop(1).dropLast(1).map { 
            it.drop(1).dropLast(1).split(",").map { it.toInt() }
        }}
    }

    val machines by lazy {
        targets.zip(buttons)
    }

    fun part1(): Int {
        println(machines)
        return 0
    }

    fun part2(): Int {
        // TODO: Implement part 2
        return 0
    }
}

fun main() {
    val day = Day10()
    println("Part 1: ${day.part1()}")
    println("Part 2: ${day.part2()}")
}