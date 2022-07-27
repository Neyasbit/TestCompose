object Libs {

    object BuildPlugins {
        const val GRADLE = "com.android.tools.build:gradle:7.0.4"
        const val KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10"
    }
    @Suppress("MemberVisibilityCanBePrivate")
    object Detekt {
        const val VERSION = "1.19.0"
        const val BASE = "io.gitlab.arturbosch.detekt"
    }

    object AndroidX {
        private const val VERSION = "1.8.0"
        const val CORE = "androidx.core:core-ktx:$VERSION"
        const val LIFECYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:2.5.0"
    }

    object Compose {
        private const val VERSION = "1.1.0"
        const val UI = "androidx.compose.ui:ui:$VERSION"
        const val MATERIAL = "androidx.compose.material:material:$VERSION"
        const val UI_PREVIEW = "androidx.compose.ui:ui-tooling-preview:$VERSION"
        const val ACTIVITY = "androidx.activity:activity-compose:1.5.0"
        const val TOOLING = "androidx.compose.ui:ui-tooling:$VERSION"
    }

    object Test {
        const val JUNIT = "junit:junit:4.13.2"
        const val ANDROID_JUNIT = "androidx.test.ext:junit:1.1.3"
        const val ESPRESSO = "androidx.test.espresso:espresso-core:3.4.0"
        const val COMPOSE_JUNIT = "androidx.compose.ui:ui-test-junit4:1.1.1"
    }
}