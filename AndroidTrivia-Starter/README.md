Summary
============================

### Check Latest Navigation Version

https://developer.android.com/jetpack/androidx/releases/navigation#declaring_dependencies

### Navigation Host Fragment

A *navigation host fragment* acts as a host for the fragments in a navigation graph. The navigation host fragment is usually named [`NavHostFragment`](https://developer.android.com/reference/androidx/navigation/fragment/NavHostFragment).

### Options Menu

> **Tip:** Make sure that the `ID` of the menu item that you just added is exactly the same as the `ID` of the `AboutFragment` that you added in the navigation graph. This will make the code for the `onClick` handler much simpler.

### Safe Args

- To help catch errors caused by missing keys or mis-matched types when you pass data from one fragment to another, use a Gradle plugin called [*Safe Args*](https://developer.android.com/topic/libraries/architecture/navigation/navigation-pass-data#Safe-args).
- For each fragment in your app, the Safe Args plugin generates a corresponding `NavDirection` class. You add the `NavDirection` class to the fragment code, then use the class to pass arguments between the fragment and other fragments.
- The `NavDirection` classes represent navigation from all the app's actions.

### Implicit intents

An [`Intent`](https://developer.android.com/reference/android/content/Intent) is a simple message object that's used to communicate between Android components. With an [*implicit intent*](https://developer.android.com/guide/components/intents-common), you initiate an activity without knowing which app or activity will handle the task. For example, if you want your app to take a photo, you typically don't care which app or activity performs the task. When multiple Android apps can handle the same implicit intent, Android shows the user a chooser, so that the user can select an app to handle the request.

Each implicit intent must have an [`ACTION`](https://developer.android.com/reference/android/content/Intent#standard-activity-actions) that describes the type of thing that is to be done. Common actions, such as `ACTION_VIEW`, `ACTION_EDIT`, and `ACTION_DIAL`, are defined in the `Intent` class.

For more about implicit intents, see [Sending the User to Another App](https://developer.android.com/training/basics/intents/sending).

- An [*implicit intent*](https://developer.android.com/training/basics/intents/sending) declares an action that your app wants some other app (such as a camera app or email app) to perform on its behalf.
- If several Android apps could handle an implicit intent, Android shows the user a chooser. For example, when the user taps the **share** icon in the AndroidTrivia app, the user can select which app they want to use to share their game results.
- To build an intent, you declare an action to perform, for example [`ACTION_SEND`](https://developer.android.com/reference/android/content/Intent.html#ACTION_SEND).
- Several [`Intent()`](https://developer.android.com/reference/android/content/Intent.html#public-constructors_1) constructors are available to help you build intents.

### Intent resolves to an acitivity

To make sure the `Intent` resolves to an `Activity`, check with the Android package manager ([`PackageManager`](https://developer.android.com/reference/android/content/pm/PackageManager)), which keeps track of the apps and activities installed on the device. Use the activity's `packageManager` property to gain access to the package manager, and call [`resolveActivity()`](https://developer.android.com/reference/android/content/pm/PackageManager.html#resolveActivity(android.content.Intent, int)).

### Sharing functionality

- In the case of sharing your success with your friends, the `Intent` action would be `Intent.ACTION_SEND.`
- To add an options menu to a fragment, set the [`setHasOptionsMenu()`](https://developer.android.com/reference/android/support/v4/app/Fragment#sethasoptionsmenu) method to `true` in the fragment code.
- In the fragment code, override the `onCreateOptionsMenu()` method to inflate the menu.
- Override the `onOptionsItemSelected()` to use `startActivity()` to send the `Intent` to other apps that can handle it.

