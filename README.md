# Advent of Code 2025

Kotlin solutions for [Advent of Code 2025](https://adventofcode.com/2025).

## Quick Start

### Create a new day
```bash
kotlin new-day.main.kts <day>
# or
./gradlew newDay -Pday=<day>
```

### Run a day
```bash
./gradlew -p day01 run
```

### Test a day
```bash
./gradlew p day01 test
```

### Build all
```bash
./gradlew build
```

## Workflow

1. Create day: `kotlin new-day.main.kts 10`
2. Add test input to `day10/src/test/resources/input.txt`
3. Add puzzle input to `day10/src/main/resources/input.txt`
4. Implement `part1()` and `part2()` in `Day10.kt`
5. Test: `./gradlew :day10:test`
6. Run: `./gradlew :day10:run`
