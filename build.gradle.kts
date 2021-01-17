// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    kotlin("android") version "1.4.20" apply false
}
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath (BuildPlugins.androidGradlePlugin)
        classpath (BuildPlugins.kotlinGradlePlugin)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

//task clean(type: Delete) {
//    delete rootProject.buildDir
//}