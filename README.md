# Architecture with Kotlin

Demo of architecture for Android written in Kotlin.

## Highlights

This demo uses:

+ `Kotlin 1.0.3`
+ `RxAndroid 1.2.1`
+ `Retrofit 2.0.2`
+ `Okhttp 3.3.1`
+ `Gson`

### Project build.gradle

```java
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:2.1.2'
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:1.0.3"
        classpath "org.jetbrains.kotlin:kotlin-android-extensions:1.0.3"
    }
}

allprojects {
    repositories {
        jcenter()
    }
}
```

### Module build.gradle

```java
apply plugin: 'com.android.application'
apply plugin: 'kotlin-android'
apply plugin: 'kotlin-android-extensions'

android {
    compileSdkVersion 22
    buildToolsVersion "24.0.0"

    defaultConfig {
        applicationId "com.siziksu.architecture"
        minSdkVersion 19
        targetSdkVersion 22
        versionCode 2219001
        versionName "0.0.1"
        testInstrumentationRunner test_instrumentation_runner
    }
    
    sourceSets {
        main.java.srcDirs += 'src/main/kotlin'
    }
}

dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    compile "com.android.support:appcompat-v7:22.2.1"
    compile "com.squareup.retrofit2:retrofit:2.0.2"
    compile "com.squareup.retrofit2:converter-gson:2.0.2"
    compile "com.squareup.okhttp3:okhttp:3.3.1"
    compile "com.squareup.okhttp3:logging-interceptor:3.3.1"
    compile "io.reactivex:rxjava:1.1.8"
    compile "io.reactivex:rxandroid:1.2.1"
    compile "org.jetbrains.kotlin:kotlin-stdlib:1.0.3"
    compile "org.jetbrains.anko:anko-sdk15:0.9"
    compile "org.jetbrains.anko:anko-appcompat-v7:0.9"
    compile "org.jetbrains.anko:anko-recyclerview-v7:0.9"
}
```

## Use Case

With this demo we will request to the [OpenWeatherMap](http://openweathermap.org/) service for the current temperature of Barcelona, Spain.

## RxAndroid

This demo uses RxAndroid.

```java

override fun getWeather(city: String) {
    Observable.create(subscriber(city))
              .observeOn(AndroidSchedulers.mainThread())
              .subscribeOn(Schedulers.newThread())
              .subscribe(
                    { response -> Log.d(Constants.TAG, "Temperature: " + response.main.temperature) },
                    { throwable -> Log.d(Constants.TAG, throwable.message, throwable) },
                    { Log.d(Constants.TAG, "Action successfully completed") }
              };
}

private fun subscriber(city: String): Observable.OnSubscribe<OpenWeather> {
    return Observable.OnSubscribe<OpenWeather> {
        subscriber ->
        try {
            val result = getWeatherData.city(city).useCache().cacheExpiryTime(EXPIRY_TIME).run()
            subscriber.onNext(result)
            subscriber.onCompleted()
        } catch (e: Exception) {
            subscriber.onError(e)
        }
    }
}
```

## Architecture

This demo uses a layered architecture.

+ User interface
+ Presenter
+ Domain
+ Data
+ Common

Applying the dependency rule in this direction:

```
User Interface -> Presenter -> Domain -> Data
```

All the layers has access to the `Common` layer which contains the `Model` and global objects. This simplifies a lot the amount of objects used.
The `Data` layer includes the `Cloud` and the `Persistence`.

## Layer communication

```
ui        -> presenter

presenter ^  return result
          -> domain

domain    -> data

data      -> data.database  ^  return response
          -> data.client    ^  return response
```

## License

    Copyright 2016 Esteban Latre

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
