plugins {
    kotlin("jvm") version "1.3.61"
    id("idea")

}

description = "Karate template"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation("com.intuit.karate", "karate-apache", "0.9.4")
    implementation("com.intuit.karate", "karate-junit5", "0.9.4")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.5.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.5.2")

    testImplementation("com.github.tomakehurst:wiremock-jre8:2.25.1")
    testImplementation("net.masterthought:cucumber-reporting:5.0.1")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

sourceSets {
    test {
        resources {
            srcDirs("src/test/kotlin")
            exclude("**/*.kt")
        }
    }
}


tasks {
    task<Test>("e2e") {
        useJUnitPlatform() // enable junit5 platform
        group = "verification"
        description = "Runs all @E2E tests"

        systemProperty("karate.baseUrl", "https://trello.com/1/")
        systemProperty("karate.config.dir", "src/test/resources")

        System.getenv("KARATE_DIRECTORY_SUFFIX")?.let { directorySuffix ->
            reports.html.outputLocation.set(File("build/reports/tests/e2e-$directorySuffix"))

            reports.junitXml.outputLocation.set(File("build/test-results/e2e-$directorySuffix"))
            binaryResultsDirectory.set(File("build/test-results/e2e-$directorySuffix/binary"))
        }

        testLogging {
            events("passed", "skipped", "failed")
        }

        outputs.upToDateWhen { false }
    }
}
