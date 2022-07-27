plugins {
    id("android-app-convention")
}

dependencies {

    implementation(Libs.AndroidX.CORE)
    implementation(Libs.Compose.UI)
    implementation(Libs.Compose.MATERIAL)
    implementation(Libs.Compose.UI_PREVIEW)
    implementation(Libs.AndroidX.LIFECYCLE_RUNTIME)
    implementation(Libs.Compose.ACTIVITY)

    testImplementation(Libs.Test.JUNIT)
    androidTestImplementation(Libs.Test.ANDROID_JUNIT)
    androidTestImplementation(Libs.Test.ESPRESSO)
    androidTestImplementation(Libs.Test.COMPOSE_JUNIT)

    debugImplementation(Libs.Compose.TOOLING)
}