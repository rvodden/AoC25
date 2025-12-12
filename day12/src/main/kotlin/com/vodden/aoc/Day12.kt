package com.vodden.aoc

class Day12 {
    val input: String by lazy {
        requireNotNull(object {}.javaClass.getResource("/input.txt")?.readText(),
            { "Could not open input.txt resource." })
    }

    fun part1(): Int {
        val chunks = input.split("\n\n")

        val presents = chunks.take(6).map { it.lines().drop(1).withIndex().flatMap { (y, line) ->
            line.withIndex().mapNotNull { (x, c) -> when(c) {
                '#' -> x to y
                else ->  null
            } }.toSet()
        } }

        val trees = chunks.drop(6).flatMap { c -> c.lines().map { it.split(":") } }

        val areas = trees.map{ tree -> 
            tree.first().split("x").map {
                it.toInt()
            }
        }.map { 
            val xs = it[0]
            val ys = it[1] 
            List(xs) {}.indices.flatMap { x ->
                List(ys) {}.indices.map { y -> x to y }
            }
        }
        
        val targets = trees.map{ it.last().trim().split(" ").map(String::toInt) }

        return 0
    }

    fun part2(): Int {
        // TODO: Implement part 2
        return 0
    }
}

fun main() {
    val day = Day12()
    println("Part 1: ${day.part1()}")
    println("Part 2: ${day.part2()}")
}