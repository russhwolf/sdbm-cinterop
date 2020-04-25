import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    java
    kotlin("multiplatform") version "1.3.72"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    macosX64()
    linuxX64()
    mingwX64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
    }

    targets.withType<KotlinNativeTarget>().all {
        compilations.getByName("main") {
            binaries {
                executable {
                    entryPoint = "sdbm.main"
                }
            }
            cinterops.create("sdbm") {
//                includeDirs(file("src/sdbm_rev1").absolutePath)
                includeDirs(file("src/sdbm_rev2").absolutePath)
//                includeDirs(file("src/sdbm_rev3").absolutePath)
            }
        }
    }
}
