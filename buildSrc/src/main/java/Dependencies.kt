object BuildConfigVersions {
    const val compileSdkVersion = 32
    const val minSdkVersion = 25
    const val targetSdkVersion = 32
    const val versionCode = 1
    const val versionName = "1.0"
}

object ClasspathVersions {
    const val gradleVersion = "7.2.0"
    const val kotlinGradleVersion = "1.6.21"
    const val hiltGradleVersion = "2.40.5"
    const val safeArgsVersion = "2.4.2"
}

object ClassPaths {
    const val gradlePath = "com.android.tools.build:gradle:${ClasspathVersions.gradleVersion}"
    const val kotlinGradlePath = "org.jetbrains.kotlin:kotlin-gradle-plugin:${ClasspathVersions.kotlinGradleVersion}"
    const val hiltPath = "com.google.dagger:hilt-android-gradle-plugin:${ClasspathVersions.hiltGradleVersion}"
    const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${ClasspathVersions.safeArgsVersion}"
}

object Versions {
    //kotlin
    const val coroutinesVersion = "1.6.2"

    //androidx libs
    const val coreKtxVersion = "1.7.0"
    const val appCompatVersion = "1.4.2"
    const val materialVersion = "1.6.1"
    const val constraintLayoutVersion = "2.1.4"
    const val liveDataVersion = "2.4.1"
    const val viewModelVersion = "2.4.1"
    const val lifecycleExtVersion = "2.2.0"
    const val swipeRefreshLayoutVersion = "1.1.0"
    const val activityKtxVersion = "1.4.0"
    const val navigationFragmentKtxVersion = "2.3.5"
    const val navigationUiKtxVersion = "2.3.5"

    //retrofit
    const val retrofitVersion = "2.9.0"
    const val gsonConverterFactoryVersion = "2.9.0"
    const val okhttpVersion = "4.9.0"
    const val okhttpLoggingInterceptorVersion = "4.8.0"

    //hilt
    const val hiltAndroidVersion = "2.40.5"
    const val hiltCompilerVersion = "2.40.5"
    const val hiltViewModelVersion = "1.0.0-alpha03"
    const val hiltAndroidXCompilerVersion = "1.0.0"

    //test libs
    const val junitVersion = "4.13.2"
    const val androidXCoreTest = "2.1.0"
    const val testCoroutinesVersion = "1.5.2"
    const val mockitoCoreVersion = "3.11.0"

    //android test libs
    const val uiJunitVersion = "1.1.3"
    const val espresso = "3.4.0"

    //miscellaneous
    const val glideVersion = "4.13.0"
    const val toastyVersion = "1.5.2"
    const val tempoVersion = "0.7.0"
}

object Deps {

    //material
    const val material =  "com.google.android.material:material:${Versions.materialVersion}"

    //androidx
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val constraintLayout =  "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    const val liveData =  "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.liveDataVersion}"
    const val viewModel =  "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModelVersion}"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExtVersion}"
    const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayoutVersion}"
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigationFragmentKtxVersion}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigationUiKtxVersion}"
    const val activityKtx ="androidx.activity:activity-ktx:${Versions.activityKtxVersion}"

    //kotlin
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}"

    //Retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.gsonConverterFactoryVersion}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttpVersion}"
    const val okhttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpLoggingInterceptorVersion}"

    //hilt
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hiltAndroidVersion}"
    const val hiltACompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltCompilerVersion}"
    const val hiltAndroidXCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltAndroidXCompilerVersion}"
    const val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltViewModelVersion}"

    //miscellaneous
    const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
    const val glideAnnotationProcessor = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"
    const val toasty = "com.github.GrenderG:Toasty:${Versions.toastyVersion}"
    const val tempo = "com.github.cesarferreira:tempo:${Versions.tempoVersion}"

    //testing
    const val junit = "junit:junit:${Versions.junitVersion}"
    const val mockitoCore = "org.mockito:mockito-core:${Versions.mockitoCoreVersion}"
    const val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.testCoroutinesVersion}"
    const val androidXCoreTest = "androidx.arch.core:core-testing:${Versions.androidXCoreTest}"
    const val uiTestJunit= "androidx.test.ext:junit:${Versions.uiJunitVersion}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}