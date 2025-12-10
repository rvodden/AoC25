#!/usr/bin/env kotlin

import java.io.File
import kotlin.system.exitProcess

// Get day number from command line
val dayNum = args.firstOrNull()?.toIntOrNull() ?: run {
    println("Usage: kotlin new-day.main.kts <day_number>")
    println("Example: kotlin new-day.main.kts 10")
    exitProcess(1)
}

val dayDir = "day%02d".format(dayNum)
val dayClass = "Day%02d".format(dayNum)

// Check if directory already exists
if (File(dayDir).exists()) {
    println("Error: $dayDir already exists!")
    exitProcess(1)
}

println("Creating $dayDir...")

// Create directory structure
listOf(
    "$dayDir/src/main/kotlin/com/vodden/aoc",
    "$dayDir/src/main/resources",
    "$dayDir/src/test/kotlin/com/vodden/aoc",
    "$dayDir/src/test/resources"
).forEach { File(it).mkdirs() }

// Create build.gradle.kts
File("$dayDir/build.gradle.kts").writeText("""
application {
    mainClass = "com.vodden.aoc.${dayClass}Kt"
}
""".trimIndent())

// Create main Kotlin file
File("$dayDir/src/main/kotlin/com/vodden/aoc/$dayClass.kt").writeText("""
package com.vodden.aoc

class $dayClass {
    val input: String by lazy {
        requireNotNull(object {}.javaClass.getResource("/input.txt")?.readText(),
            { "Could not open input.txt resource." })
    }

    fun part1(): Int {
        // TODO: Implement part 1
        return 0
    }

    fun part2(): Int {
        // TODO: Implement part 2
        return 0
    }
}

fun main() {
    val day = $dayClass()
    println("Part 1: ${'$'}{day.part1()}")
    println("Part 2: ${'$'}{day.part2()}")
}
""".trimIndent())

// Create test file
File("$dayDir/src/test/kotlin/com/vodden/aoc/${dayClass}Test.kt").writeText("""
package com.vodden.aoc

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class ${dayClass}Test {
    @Test
    fun testPartOne() {
        val day = $dayClass()
        // TODO: Add expected values
        // assertEquals(expectedPart1, day.part1())
        // assertEquals(expectedPart2, day.part2())
    }
}
""".trimIndent())

// Create empty resource files
File("$dayDir/src/main/resources/input.txt").createNewFile()
File("$dayDir/src/test/resources/input.txt").createNewFile()

// Add to settings.gradle.kts if not already present
val settingsFile = File("settings.gradle.kts")
val settingsContent = settingsFile.readText()

if (!settingsContent.contains("include(\"$dayDir\")")) {
    // Ensure there's a newline at the end
    val updatedContent = if (settingsContent.endsWith("\n")) {
        settingsContent
    } else {
        settingsContent + "\n"
    }

    settingsFile.writeText(updatedContent + "include(\"$dayDir\")\n")
    println("Added $dayDir to settings.gradle.kts")
}

println()
println("✓ Created $dayDir successfully!")
println()
println("Next steps:")
println("  1. Add your puzzle input to $dayDir/src/main/resources/input.txt")
println("  2. Add test input to $dayDir/src/test/resources/input.txt")
println("  3. Implement part1() and part2() in $dayDir/src/main/kotlin/com/vodden/aoc/$dayClass.kt")
println("  4. Run with: ./gradlew :$dayDir:run")
println("  5. Test with: ./gradlew :$dayDir:test")
