Summary
==============================

### Lifecycle Diagram

![img](https://codelabs.developers.google.com/codelabs/kotlin-android-training-lifecycles-logging/img/f6b25a71cec4e401.png)

![img](https://codelabs.developers.google.com/codelabs/kotlin-android-training-lifecycles-logging/img/dfde69e6a42d54b3.png)

### Application Class

> **Warning**: It might be tempting to add your own code to the `Application` class, because the class is created before all of your activities and can hold global state. But just as it's error-prone to make readable and writable static variables that are globally available, it's easy to abuse the `Application` class. Avoid putting any activity code in the `Application` class unless the code is really needed.

### onPause()

> Whatever code runs in `onPause()` blocks other things from displaying, so keep the code in `onPause()` lightweight. For example, if a phone call comes in, the code in `onPause()` may delay the incoming-call notification.