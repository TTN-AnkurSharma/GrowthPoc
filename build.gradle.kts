plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

subprojects {
    apply(plugin = "java")

    group = "com.growthpoc"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    // Use the JDK already configured for this project in IntelliJ.
    configure<JavaPluginExtension> {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
        }
    }
}
