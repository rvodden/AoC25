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

    fun solve(target: List<Boolean>, buttons: List<Button>): Int {
        if (target.none()) return 0

        // create queue of state, unpressedButton pairs
        val queue = ArrayDeque<Pair<Target, Set<Int>>>()

        queue.add( List(size = target.size) { false }  to (0..<buttons.size).toSet() )

        while (queue.isNotEmpty()) {
            val (state, unpressedButtonIndexes) = queue.removeFirst()

            unpressedButtonIndexes.forEach { buttonIndex ->
                val button = buttons[buttonIndex]
                val newState = state.withIndex().map { (index, value) ->
                    if (button.contains(index)) !value else value
                }
                if (newState == target) return buttons.size - unpressedButtonIndexes.size + 1

                queue.add(newState to (unpressedButtonIndexes - buttonIndex))
            }
        }

        throw IllegalStateException("No solution found")
    }

    fun part1(): Int {
        return machines.map { (target, buttons) ->
            solve(target, buttons)
        }.sum()
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