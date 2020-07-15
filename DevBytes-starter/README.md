Summary
======================

## Several way to store structured data on a device file system

| **Caching technique**                                        | **Uses**                                                     |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| [`Retrofit`](http://square.github.io/retrofit/) is a networking library used to implement a type-safe REST client for Android. You can configure Retrofit to store a copy of every network result locally. | Good solution for simple requests and responses, infrequent network calls, or small datasets. |
| You can use `SharedPreferences` to store key-value pairs.    | Good solution for a small number of keys and simple values. You can't use this technique to store large amounts of structured data. |
| You can access the app's internal storage directory and save data files in it. Your app's package name specifies the app's internal storage directory, which is in a special location in the Android file system. This directory is private to your app, and it is cleared when your app is uninstalled. | Good solution if you have specific needs that a file system can solve—for example, if you need to save media files or data files and you have to manage the files yourself. You can't use this technique to store complex and structured data. |
| You can cache data using [`Room`](https://developer.android.com/topic/libraries/architecture/room), which is an SQLite object-mapping library that provides an abstraction layer over SQLite. | Recommended solution for complex and structured data, because the best way to store structured data on a device's file system is in a local SQLite database. |

## The repository pattern

The *repository pattern* is a design pattern that isolates data sources from the rest of the app.

A *repository* mediates between data sources (such as persistent models, web services, and caches) and the rest of the app. The diagram below shows how app components such as activities that use `LiveData` might interact with data sources by way of a repository.

![img](https://codelabs.developers.google.com/codelabs/kotlin-android-training-repository/img/69021c8142d29198.png)

To implement a repository, you use a *repository class*, such as the `VideosRepository` class that you create in the next task. The repository class isolates the data sources from the rest of the app and provides a clean API for data access to the rest of the app. Using a repository class is a recommended best practice for code separation and architecture.

## Advantages of using a repository

A repository module handles data operations and allows you to use multiple backends. In a typical real-world app, the repository implements the logic for deciding whether to fetch data from a network or use results that are cached in a local database. This helps make your code modular and testable. You can easily mock up the repository and test the rest of the code.