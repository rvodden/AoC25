package com.vodden.aoc

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class Day12Test {
    @Test
    fun testPartOne() {
        val day = Day12()
        assertEquals(2, day.part1())
    }
    
    @Test
    fun testPartTwo() {
        val day = Day12()
        assertEquals(0, day.part2())
    }
}