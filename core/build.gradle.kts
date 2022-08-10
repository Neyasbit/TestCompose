plugins {
   id("android-app-convention")
}

dependencies {

    api(Libs.AndroidX.CORE)
    api(Libs.Compose.UI)
    api(Libs.Compose.MATERIAL)
    api(Libs.Compose.UI_PREVIEW)
    api(Libs.AndroidX.LIFECYCLE_RUNTIME)
    api(Libs.Compose.ACTIVITY)

    testApi(Libs.Test.JUNIT)
    androidTestApi(Libs.Test.ANDROID_JUNIT)
    androidTestApi(Libs.Test.ESPRESSO)
    androidTestApi(Libs.Test.COMPOSE_JUNIT)

    debugApi(Libs.Compose.TOOLING)
}