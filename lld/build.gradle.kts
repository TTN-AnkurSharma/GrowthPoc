plugins {
    application
}

dependencies {
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
}

application {
    mainClass.set("com.growthpoc.solid.Main")
}
