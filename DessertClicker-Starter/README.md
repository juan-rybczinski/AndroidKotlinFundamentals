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

