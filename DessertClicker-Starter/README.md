Summary
==============================

### Lifecycle Diagram

![img](https://codelabs.developers.google.com/codelabs/kotlin-android-training-lifecycles-logging/img/f6b25a71cec4e401.png)

![img](https://codelabs.developers.google.com/codelabs/kotlin-android-training-complex-lifecycle/img/c259ab6beca0ca88.png)

![img](https://codelabs.developers.google.com/codelabs/kotlin-android-training-lifecycles-logging/img/dfde69e6a42d54b3.png)

- `onAttach()`: Called when the fragment is associated with its owner activity.
- `onCreate()`: Similarly to `onCreate()` for the activity, `onCreate()` for the fragment is called to do initial fragment creation (other than layout).
- `onCreateView()`: Called to inflate the fragment's layout.
- `onActivityCreated()`: Called when the owner activity's `onCreate()` is complete. Your fragment will not be able to access the activity until this method is called.
- `onStart()`: Called when the fragment becomes visible; parallel to the activity's `onStart()`.
- `onResume()`: Called when the fragment gains the user focus; parallel to the activity's `onResume()`.
- `onPause()`: Called when the fragment loses the user focus; parallel to the activity's `onPause()`.
- `onStop()`: Called when the fragment is no longer visible on screen; parallel to the activity's `onStop()`.
- `onDestroyView()`: Called when the fragment's view is no longer needed, to clean up the resources associated with that view.

### Logging with Timber

- Generates the log tag for you based on the class name.
- Helps avoid showing logs in a release version of your Android app.
- Allows for integration with crash reporting libraries.

### Application Class

- `Application` is a base class that contains global application state for your entire app. There is a default `Application` class that is used by Android if you don't specify one. You can create your own `Application` subclass to initialize app-wide libraries such as `Timber`.
- Add your custom `Application` class to your app by adding the `android:name` attribute to the `<application>` element in the Android manifest. Do not forget to do this!

> **Warning**: It might be tempting to add your own code to the `Application` class, because the class is created before all of your activities and can hold global state. But just as it's error-prone to make readable and writable static variables that are globally available, it's easy to abuse the `Application` class. Avoid putting any activity code in the `Application` class unless the code is really needed.

### onPause()

> Whatever code runs in `onPause()` blocks other things from displaying, so keep the code in `onPause()` lightweight. For example, if a phone call comes in, the code in `onPause()` may delay the incoming-call notification.

### Add ADB to execution path

https://developer.android.com/courses/extras/utilities

### Process shutdown by ADB

```
adb shell am kill com.example.android.dessertclicker
```

### **Lifecycle library**

- Use the Android lifecycle library to shift lifecycle control from the activity or fragment to the actual component that needs to be lifecycle-aware.
- Lifecycle *owners* are components that have (and thus "own") lifecycles, including `Activity` and `Fragment`. Lifecycle owners implement the `LifecycleOwner` interface.
- Lifecycle *observers* pay attention to the current lifecycle state and perform tasks when the lifecycle changes. Lifecycle observers implement the `LifecycleObserver` interface.
- `Lifecycle` objects contain the actual lifecycle states, and they trigger events when the lifecycle changes.

To create a lifecycle-aware class:

- Implement the `LifecycleObserver` interface in classes that need to be lifecycle-aware.

- Initialize a lifecycle observer class with the lifecycle object from the activity or fragment.

- In the lifecycle observer class, annotate lifecycle-aware methods with the lifecycle state change they are interested in.

  For example, the `@OnLifecycleEvent(Lifecycle.Event.ON_START)`annotation indicates that the method is watching the `onStart` lifecycle event.

### **Preserving activity and fragment state**

- When your app goes into the background, just after `onStop()` is called, app data is saved to a bundle. Some app data, such as the contents of an `EditText`, is automatically saved for you.
- The bundle is an instance of `Bundle`, which is a collection of keys and values. The keys are always strings.
- Use the `onSaveInstanceState()` callback to save other data to the bundle that you want to retain, even if the app was automatically shut down. To put data into the bundle, use the bundle methods that start with `put`, such as `putInt()`.
- You can get data back out of the bundle in the `onRestoreInstanceState()` method, or more commonly in `onCreate()`. The `onCreate()` method has a `savedInstanceState` parameter that holds the bundle.
- If the `savedInstanceState` variable contains `null`, the activity was started without a state bundle and there is no state data to retrieve.
- To retrieve data from the bundle with a key, use the `Bundle` methods that start with `get`, such as `getInt()`.

### **Configuration changes**

- A *configuration change* happens when the state of the device changes so radically that the easiest way for the system to resolve the change is to shut down and rebuild the activity.
- The most common example of a configuration change is when the user rotates the device from portrait to landscape mode, or from landscape to portrait mode. A configuration change can also occur when the device language changes or a hardware keyboard is plugged in.
- When a configuration change occurs, Android invokes all the activity lifecycle's shutdown callbacks. Then Android restarts the activity from scratch, running all the lifecycle startup callbacks.
- When Android shuts down an app because of a configuration change, it restarts the activity with the state bundle that is available to `onCreate()`.
- As with process shutdown, save your app's state to the bundle in `onSaveInstanceState()`.