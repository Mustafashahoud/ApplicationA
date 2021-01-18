object Kotlin {
    const val standardLibrary = "1.4.21"
    const val coroutines = "1.3.9"
}

object BuildTypes {
    const val DEBUG = "debug"
    const val RELEASE = "release"
}

object AndroidSdk {
    const val min = 23
    const val compile = 30
    const val target = compile
    const val build = "30.0.2"
}

object AndroidClient {
    const val appId = "com.android.mustafa.applicationa"
    const val versionCode = 1
    const val versionName = "1.0"
    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
}

object BuildPlugins {

    object Versions {
        const val buildToolsVersion = "4.1.1"
        const val serialization = "1.4.30-M1"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.standardLibrary}"
    const val serializationClasspath = "org.jetbrains.kotlin:kotlin-serialization:${Versions.serialization}"
    const val serializationPlugin = "org.jetbrains.kotlin.plugin.serialization"
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinKapt = "kotlin-kapt"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
}

object Libraries {
    private object Versions {
        const val appCompat = "1.2.0"
        const val activity = "1.1.0"
        const val constraintLayout = "2.0.2"
        const val recyclerView = "1.1.0"
        const val localBroadcastManager = "1.0.0"
        const val cardView = "1.0.0"
        const val material = "1.1.0"
        const val lifecycle = "2.2.0"
        const val lifecycleExtensions = "2.1.0"
        const val annotations = "1.1.0"
        const val ktx = "1.3.2"
        const val glide = "4.11.0"
        const val retrofit = "2.9.0"
        const val okHttpLoggingInterceptor = "4.9.0"
        const val timber = "4.7.1"
        const val room = "2.2.6"


        const val dagger = "2.30.1"
        const val javaxAnnotations = "1.0"
        const val javaxInject = "1"
    }

    const val kotlinStdLib             = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Kotlin.standardLibrary}"
    const val kotlinCoroutines         = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Kotlin.coroutines}"
    const val kotlinCoroutinesAndroid  = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Kotlin.coroutines}"
    const val appCompat                = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val activity                 = "androidx.activity:activity-ktx:${Versions.activity}"
    const val constraintLayout         = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val ktxCore                  = "androidx.core:core-ktx:${Versions.ktx}"
    const val lifecycleCompiler        = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    const val viewModel                = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val liveData                 = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val lifecycleExtensions      = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExtensions}"
    const val cardView                 = "androidx.cardview:cardview:${Versions.cardView}"
    const val recyclerView             = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val localBroadcastManager    = "androidx.localbroadcastmanager:localbroadcastmanager:${Versions.localBroadcastManager}"
    const val material                 = "com.google.android.material:material:${Versions.material}"
    const val androidAnnotations       = "androidx.annotation:annotation:${Versions.annotations}"
    const val glide                    = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val dagger                   = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerAndroid            = "com.google.dagger:dagger-android:${Versions.dagger}"
    const val daggerAndroidSupport     = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    const val roomRuntime              = "androidx.room:room-runtime:${Versions.room}"
    const val roomKtx                  = "androidx.room:room-ktx:${Versions.room}"

    const val retrofit                 = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpLoggingInterceptor}"
    const val timber                   = "com.jakewharton.timber:timber:${Versions.timber}"

    const val daggerCompiler           = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val daggerAndroidProcessor   = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    const val roomCompiler             = "androidx.room:room-compiler:${Versions.room}"
    const val javaxAnnotation          = "javax.annotation:jsr250-api:${Versions.javaxAnnotations}"
    const val javaxInject              = "javax.inject:javax.inject:${Versions.javaxInject}"
}

object TestLibraries {
    private object Versions {
        const val junit4 = "4.13.1"
        const val mockk = "1.10.0"
        const val robolectric = "4.4"
        const val kluent = "1.14"
        const val testRunner = "1.1.0"
        const val espressoCore = "3.2.0"
        const val espressoIntents = "3.1.0"
        const val testExtensions = "1.1.1"
        const val testRules = "1.1.0"
    }

    const val junit4          = "junit:junit:${Versions.junit4}"
    const val mockk           = "io.mockk:mockk:${Versions.mockk}"
    const val robolectric     = "org.robolectric:robolectric:${Versions.robolectric}"
    const val kluent          = "org.amshove.kluent:kluent:${Versions.kluent}"
    const val testRunner      = "androidx.test:runner:${Versions.testRunner}"
    const val testRules       = "androidx.test:rules:${Versions.testRules}"
    const val espressoCore    = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    const val espressoIntents = "androidx.test.espresso:espresso-intents:${Versions.espressoIntents}"
    const val testExtJunit    = "androidx.test.ext:junit:${Versions.testExtensions}"
}

object DevLibraries {
    private object Versions {
        const val leakCanary = "2.5"
    }

    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
}