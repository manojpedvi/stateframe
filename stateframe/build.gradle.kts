plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.plugin.compose")
    id("maven-publish")
}

android {
    namespace = "com.manojpedvi.stateframe"
    compileSdk = 37

    defaultConfig {
        minSdk = 23
        consumerProguardFiles("consumer-rules.pro")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    publishing {
        singleVariant("release")
    }
}

dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2026.06.01")

    api(composeBom)
    api("androidx.compose.foundation:foundation")
    api("androidx.compose.material3:material3")
    api("androidx.compose.material:material-icons-core")
    api("androidx.compose.ui:ui")

    debugApi("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.ui:ui-tooling-preview")
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.manojpedvi"
            artifactId = "stateframe"
            version = "0.1.0"

            afterEvaluate {
                from(components["release"])
            }

            pom {
                name.set("StateFrame")
                description.set("Reusable Jetpack Compose state renderer for loading, empty, error, and content UI.")
            }
        }
    }
}
