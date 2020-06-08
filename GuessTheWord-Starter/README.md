Summary
==================================

### Android App Architecture

App architecture is a way of designing your apps' classes, and the relationships between them, such that the code is organized, performs well in particular scenarios, and is easy to work with. In this set of four codelabs, the improvements that you make to the GuessTheWord app follow the [Android app architecture](https://developer.android.com/jetpack/docs/guide) guidelines, and you use [Android Architecture Components](https://developer.android.com/jetpack/#architecture-components). The Android app architecture is similar to the [MVVM](https://en.wikipedia.org/wiki/Model–view–viewmodel) (model-view-viewmodel) architectural pattern.

### UI controller

A *UI controller* is a UI-based class such as `Activity` or `Fragment`. A UI controller should only contain logic that handles UI and operating-system interactions such as displaying views and capturing user input. Don't put decision-making logic, such as logic that determines the text to display, into the UI controller.

### ViewModel

A [`ViewModel`](https://developer.android.com/reference/android/arch/lifecycle/ViewModel) holds data to be displayed in a fragment or activity associated with the `ViewModel`. A `ViewModel` can do simple calculations and transformations on data to prepare the data to be displayed by the UI controller. In this architecture, the `ViewModel` performs the decision-making.

### ViewModelFactory

A [`ViewModelFactory`](https://developer.android.com/reference/android/arch/lifecycle/ViewModelProvider.Factory) instantiates `ViewModel` objects, with or without constructor parameters.

![img](https://codelabs.developers.google.com/codelabs/kotlin-android-training-view-model/img/d115344705100cf1.png)

### ViewModelProvider

During configuration changes such as screen rotations, UI controllers such as fragments are re-created. However, `ViewModel` instances survive. If you create the `ViewModel` instance using the `ViewModel` class, a new object is created every time the fragment is re-created. Instead, create the `ViewModel` instance using a [`ViewModelProvider`](https://developer.android.com/reference/android/arch/lifecycle/ViewModelProvider).

![img](https://codelabs.developers.google.com/codelabs/kotlin-android-training-view-model/img/4b1c6b4b4c62a8ef.png)

**Important:** Always use [`ViewModelProvider`](https://developer.android.com/reference/android/arch/lifecycle/ViewModelProvider) to create `ViewModel` objects rather than directly instantiating an instance of `ViewModel`.

How `ViewModelProvider` works:

- `ViewModelProvider` returns an existing `ViewModel` if one exists, or it creates a new one if it does not already exist.
- `ViewModelProvider` creates a `ViewModel` instance in association with the given scope (an activity or a fragment).
- The created `ViewModel` is retained as long as the scope is alive. For example, if the scope is a fragment, the `ViewModel` is retained until the fragment is detached.

Initialize the `ViewModel`, using the [`ViewModelProviders.of()`](https://developer.android.com/reference/android/arch/lifecycle/ViewModelProviders.html#of) method to create a `ViewModelProvider`.

- The `ViewModel` should never contain references to fragments, activities, or views, because activities, fragments, and views do not survive configuration changes.

![img](https://codelabs.developers.google.com/codelabs/kotlin-android-training-view-model/img/86c9c22e398e0642.png)

![img](https://codelabs.developers.google.com/codelabs/kotlin-android-training-view-model/img/6451748b74d3b82c.png)

### Compare UI controllers and ViewModel

https://codelabs.developers.google.com/codelabs/kotlin-android-training-view-model/#9

### Why use viewLifecycleOwner?

> Fragment views get destroyed when a user navigates away from a fragment, even though the fragment itself is not destroyed. This essentially creates two lifecycles, the lifecycle of the fragment, and the lifecycle of the fragment's view. Referring to the fragment's lifecycle instead of the fragment view's lifecycle can cause [subtle bugs](https://www.youtube.com/watch?v=pErTyQpA390&feature=youtu.be&t=349) when updating the fragment's view. Therefore, when setting up observers that affect the fragment's view you should:
>
> 1. Set up the observers in `onCreateView()`
> 2. Pass in `viewLifecycleOwner` to observers

### LiveData

- [`LiveData`](https://developer.android.com/topic/libraries/architecture/livedata) is an observable data holder class that is lifecycle-aware, one of the [Android Architecture Components](https://developer.android.com/topic/libraries/architecture).
- You can use `LiveData` to enable your UI to update automatically when the data updates.
- `LiveData` is observable, which means that an observer like an activity or an fragment can be notified when the data held by the `LiveData` object changes.
- `LiveData` holds data; it is a wrapper that can be used with any data.
- `LiveData` is lifecycle-aware, meaning that it only updates observers that are in an active lifecycle state such as [`STARTED`](https://developer.android.com/reference/android/arch/lifecycle/Lifecycle.State.html#STARTED) or [`RESUMED`](https://developer.android.com/reference/android/arch/lifecycle/Lifecycle.State.html#RESUMED).

### To encapsulate LiveData

- The `LiveData` inside the `ViewModel` should be editable. Outside the `ViewModel`, the `LiveData` should be readable. This can be implemented using a Kotlin [backing property](https://kotlinlang.org/docs/reference/properties.html#backing-properties).
- A Kotlin backing property allows you to return something from a getter other than the exact object.
- To encapsulate the `LiveData`, use `private` `MutableLiveData` inside the `ViewModel` and return a `LiveData` backing property outside the `ViewModel`.

### Observable LiveData

- `LiveData` follows an observer pattern. The "observable" is the `LiveData` object, and the observers are the methods in the UI controllers, like fragments. Whenever the data wrapped inside `LiveData` changes, the observer methods in the UI controllers are notified.
- To make the `LiveData` observable, attach an observer object to the `LiveData` reference in the observers (such as activities and fragments) using the [`observe()`](https://developer.android.com/reference/android/arch/lifecycle/LiveData.html#observe(android.arch.lifecycle.LifecycleOwner, android.arch.lifecycle.Observer)) method.
- This `LiveData` observer pattern can be used to communicate from the `ViewModel` to the UI controllers.

### Integration with Data Binding

- The Data Binding Library works seamlessly with Android Architecture Components like `ViewModel` and `LiveData`.
- The layouts in your app can bind to the data in the Architecture Components, which already help you manage the UI controller's lifecycle and notify about changes in the data.

## ViewModel data binding

- You can associate a [`ViewModel`](https://developer.android.com/reference/android/arch/lifecycle/ViewModel) with a layout by using data binding.
- `ViewModel` objects hold the UI data. By passing `ViewModel` objects into the data binding, you can automate some of the communication between the views and the `ViewModel` objects.

How to associate a `ViewModel` with a layout:

- In the layout file, add a data-binding variable of the type `ViewModel`.

```xml
   <data>
       <variable
           name="gameViewModel"
           type="com.example.android.guesstheword.screens.game.GameViewModel" />
   </data>
```

- In the `GameFragment` file, pass the `GameViewModel` into the data binding.

```kotlin
binding.gameViewModel = viewModel
```

### Listener bindings

- [*Listener bindings*](https://developer.android.com/topic/libraries/data-binding/expressions#listener_bindings) are binding expressions in the layout that run when click events such as `onClick()` are triggered.
- Listener bindings are written as lambda expressions.
- Using listener bindings, you replace the click listeners in the UI controllers with listener bindings in the layout file.
- Data binding creates a listener and sets the listener on the view.

```xml
 android:onClick="@{() -> gameViewModel.onSkip()}"
```

## Adding LiveData to data binding

- [`LiveData`](https://developer.android.com/reference/android/arch/lifecycle/LiveData) objects can be used as a data-binding source to automatically notify the UI about changes in the data.
- You can bind the view directly to the `LiveData` object in the `ViewModel`. When the `LiveData` in the `ViewModel` changes, the views in the layout can be automatically updated, without the observer methods in the UI controllers.

```xml
android:text="@{gameViewModel.word}"
```

- To make the `LiveData` data binding work, set the current activity (the UI controller) as the lifecycle owner of the `binding` variable in the UI controller.

```kotlin
binding.lifecycleOwner = this
```

## String formatting with data binding

- Using data binding, you can format a string resource with placeholders like `%s` for strings and `%d` for integers.
- To update the `text` attribute of the view, pass in the `LiveData` object as an argument to the formatting string.

```xml
 android:text="@{@string/quote_format(gameViewModel.word)}"
```