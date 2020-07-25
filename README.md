# My-Contacts-Note

## Introduction
An Android application to keep contacts detailes note.

## Architecture
* Model View ViewModel

<p align="center"><a><img src="https://developer.android.com/topic/libraries/architecture/images/final-architecture.png" width="500"></a></p>

* A single-activity architecture.
* [Navigation Components](https://developer.android.com/guide/navigation) .

## Used Android Jetpack :
Android Jetpack
<p align="center"><a><img src="https://www.xda-developers.com/files/2018/05/Android-Jetpack-1024x517.png" width="400"></a></p>
 
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) provides a way to create and retrieve objects. It typically stores the state of a view’s data and communicates with other components.

* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)  notifies Observer objects when the lifecycle state changes.

* [Navigation Component](https://developer.android.com/guide/navigation) to handle all navigations and also passing of data between destinations.

* [Data Binding](https://developer.android.com/topic/libraries/data-binding/) to declaratively bind UI components in layouts to data sources.

* [Room](https://developer.android.com/topic/libraries/architecture/room) persistence library which provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.

#

 [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) for managing background threads and reducing needs for callbacks.
 
 [Timber](https://github.com/JakeWharton/timber) - a logger with a small, extensible API which provides utility on top of Android's normal Log class.

#### Testing
* Unit Testing- JUnit
