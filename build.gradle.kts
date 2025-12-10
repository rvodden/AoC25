plugins {
    alias(libs.plugins.kotlin.jvm) apply false
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "application")

    repositories {
        mavenCentral()
    }

    configure<org.gradle.api.plugins.JavaPluginExtension> {
        toolchain {
            languageVersion = JavaLanguageVersion.of(21)
        }
    }

    dependencies {
        "testImplementation"(rootProject.libs.junit.jupiter)
        "testRuntimeOnly"("org.junit.platform:junit-platform-launcher")
    }

    tasks.named<Test>("test") {
        useJUnitPlatform()
    }
}

tasks.register<Exec>("newDay") {
    group = "advent of code"
    description = "Create a new day module"

    val dayNum = providers.gradleProperty("day")
        .orElse(provider { throw GradleException("Please specify day number: ./gradlew newDay -Pday=10") })

    commandLine("kotlin", "new-day.main.kts", dayNum.get())
}
