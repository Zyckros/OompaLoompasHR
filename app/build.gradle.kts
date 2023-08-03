@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    kotlin("kapt")
    alias(libs.plugins.safeargs)
}

android {
    namespace = "com.example.oompaloompashr"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.oompaloompashr"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding {viewBinding = true}
        dataBinding {dataBinding = true}
    }

}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.legacy.support.v4)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.room.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

    //Retrofit - http client library.
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    kapt("androidx.room:room-compiler:2.5.2")
    implementation("androidx.room:room-runtime:2.5.2")


    implementation( "androidx.navigation:navigation-fragment-ktx:2.6.0")
    implementation ("androidx.navigation:navigation-ui-ktx:2.6.0")


    //Paging - paging library
    implementation(libs.paging)
    implementation("androidx.databinding:databinding-ktx:8.1.0")
    runtimeOnly("androidx.room:room-paging:2.5.2")
    implementation ("androidx.paging:paging-runtime-ktx:3.1.0")
    implementation("androidx.room:room-paging:2.5.2")
    implementation("com.github.bumptech.glide:glide:4.15.1")


}
