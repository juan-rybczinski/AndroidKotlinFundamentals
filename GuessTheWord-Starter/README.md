Summary
==================================

### Android App Architecture

App architecture is a way of designing your apps' classes, and the relationships between them, such that the code is organized, performs well in particular scenarios, and is easy to work with. In this set of four codelabs, the improvements that you make to the GuessTheWord app follow the [Android app architecture](https://developer.android.com/jetpack/docs/guide) guidelines, and you use [Android Architecture Components](https://developer.android.com/jetpack/#architecture-components). The Android app architecture is similar to the [MVVM](https://en.wikipedia.org/wiki/Model–view–viewmodel) (model-view-viewmodel) architectural pattern.

#### UI controller

A *UI controller* is a UI-based class such as `Activity` or `Fragment`. A UI controller should only contain logic that handles UI and operating-system interactions such as displaying views and capturing user input. Don't put decision-making logic, such as logic that determines the text to display, into the UI controller.

#### ViewModel

A [`ViewModel`](https://developer.android.com/reference/android/arch/lifecycle/ViewModel) holds data to be displayed in a fragment or activity associated with the `ViewModel`. A `ViewModel` can do simple calculations and transformations on data to prepare the data to be displayed by the UI controller. In this architecture, the `ViewModel` performs the decision-making.

#### ViewModelFactory

A [`ViewModelFactory`](https://developer.android.com/reference/android/arch/lifecycle/ViewModelProvider.Factory) instantiates `ViewModel` objects, with or without constructor parameters.

![img](https://codelabs.developers.google.com/codelabs/kotlin-android-training-view-model/img/d115344705100cf1.png)

#### ViewModelProvider

During configuration changes such as screen rotations, UI controllers such as fragments are re-created. However, `ViewModel` instances survive. If you create the `ViewModel` instance using the `ViewModel` class, a new object is created every time the fragment is re-created. Instead, create the `ViewModel` instance using a [`ViewModelProvider`](https://developer.android.com/reference/android/arch/lifecycle/ViewModelProvider).

![img](https://codelabs.developers.google.com/codelabs/kotlin-android-training-view-model/img/4b1c6b4b4c62a8ef.png)

**Important:** Always use [`ViewModelProvider`](https://developer.android.com/reference/android/arch/lifecycle/ViewModelProvider) to create `ViewModel` objects rather than directly instantiating an instance of `ViewModel`.

How `ViewModelProvider` works:

- `ViewModelProvider` returns an existing `ViewModel` if one exists, or it creates a new one if it does not already exist.
- `ViewModelProvider` creates a `ViewModel` instance in association with the given scope (an activity or a fragment).
- The created `ViewModel` is retained as long as the scope is alive. For example, if the scope is a fragment, the `ViewModel` is retained until the fragment is detached.

Initialize the `ViewModel`, using the [`ViewModelProviders.of()`](https://developer.android.com/reference/android/arch/lifecycle/ViewModelProviders.html#of) method to create a `ViewModelProvider`.

