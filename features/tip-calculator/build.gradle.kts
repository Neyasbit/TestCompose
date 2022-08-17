plugins {
    id("android-app-convention")
}


dependencies {
    api(project(Modules.Base.CORE))
    implementation(Libs.Arrow.CORE)

    testImplementation(Libs.Test.JUNIT)
    testImplementation(Libs.Test.ANDROID_JUNIT)
    testImplementation(Libs.Test.COROUTINES)

    androidTestApi(Libs.Test.ANDROID_JUNIT)
    androidTestApi(Libs.Test.ESPRESSO)
    androidTestApi(Libs.Test.COMPOSE_JUNIT)

    debugApi(Libs.Compose.TOOLING)
}