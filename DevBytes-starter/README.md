Summary
======================

[Caching](https://searchstorage.techtarget.com/definition/cache) is a process of storing data fetched from a network on a device's storage. Caching lets your app access data when the device is offline, or if your app needs to access the same data again.

## Seperation of concerns

When you design an offline cache, it's a best practice to separate the app's network, domain, and database objects. This strategy is an example of [separation of concerns](https://en.wikipedia.org/wiki/Separation_of_concerns).

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

## WorkManager

[`WorkManager`](https://developer.android.com/arch/work) is one of the [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/) and part of [Android Jetpack](http://d.android.com/jetpack). `WorkManager` is for background work that's deferrable and requires guaranteed execution:

- *Deferrable* means that the work is not required to run immediately. For example, sending analytical data to the server or syncing the database in the background is work that can be deferred.
- *Guaranteed execution* means that the task will run even if the app exits or the device restarts.

While `WorkManager` runs background work, it takes care of compatibility issues and best practices for battery and system health. `WorkManager` offers compatibility back to API level 14. `WorkManager` chooses an appropriate way to schedule a background task, depending on the device API level. It might use [`JobScheduler`](https://developer.android.com/reference/android/app/job/JobScheduler) (on API 23 and higher) or a combination of [`AlarmManager`](https://developer.android.com/reference/android/app/AlarmManager) and [`BroadcastReceiver`](https://developer.android.com/reference/android/app/BroadcastReceiver).![img](https://codelabs.developers.google.com/codelabs/kotlin-android-training-work-manager/img/e04f53ac665e07c9.png)

`WorkManager` also lets you set criteria on when the background task runs. For example, you might want the task to run only when the battery status, network status, or charge state meet certain criteria. You learn how to set constraints later in this codelab.

> **Note:**
>
> - `WorkManager` is not intended for in-process background work that can be terminated safely if the app process is killed.
> - `WorkManager` is not intended for tasks that require immediate execution.

### WorkManager Library

- [`Worker`](https://developer.android.com/reference/androidx/work/Worker.html)
  This class is where you define the actual work (the task) to run in the background. You extend this class and override the [`doWork()`](https://developer.android.com/reference/androidx/work/Worker.html#doWork()) method. The `doWork()` method is where you put code to be performed in the background, such as syncing data with the server or processing images.
  - The `doWork()` method inside the `Worker` class is called on a background thread. The method performs work synchronously, and should return a [`ListenableWorker.Result`](https://developer.android.com/reference/androidx/work/ListenableWorker.Result.html) object. The Android system gives a `Worker` a maximum of 10 minutes to finish its execution and return a `ListenableWorker.Result` object. After this time has expired, the system forcefully stops the `Worker`.
  - To create a `ListenableWorker.Result` object, call one of the following static methods to indicate the completion status of the background work:
    - [`Result.success()`](https://developer.android.com/reference/androidx/work/ListenableWorker.Result.html#success())—work completed successfully.
    - [`Result.failure()`](https://developer.android.com/reference/androidx/work/ListenableWorker.Result.html#failure())—work completed with a permanent failure.
    - [`Result.retry()`](https://developer.android.com/reference/androidx/work/ListenableWorker.Result.html#retry())—work encountered a transient failure and should be retried.
- [`WorkRequest`](https://developer.android.com/reference/androidx/work/WorkRequest.html)
  This class represents a request to run the worker in background. Use `WorkRequest` to configure how and when to run the worker task, with the help of [`Constraints`](https://developer.android.com/reference/androidx/work/Constraints.html) such as device plugged in or Wi-Fi connected.
- [`WorkManager`](https://developer.android.com/reference/androidx/work/WorkManager.html)
  This class schedules and runs your `WorkRequest`. `WorkManager` schedules work requests in a way that spreads out the load on system resources, while honoring the constraints that you specify.

