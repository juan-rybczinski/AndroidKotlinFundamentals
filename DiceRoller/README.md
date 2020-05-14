# Build your first app

### Android Jetpack:

- Android Jetpack is a collection of libraries, developed by Google, that offers backward-compatible classes and helpful functions for supporting older versions of Android. Jetpack replaces and expands on the set of libraries formerly known as the Android Support Library.
- Classes imported from the `androidx` package refer to the Jetpack libraries. Dependencies to Jetpack in your `build.gradle` file also start with `androidx`.

### Backward compatibility for vector drawables:

- Vector drawables are only natively supported in versions of Android higher than API 21. In older versions, Gradle generates PNG images for those drawables when your app is built.
- You can specify that the Android Support Library should be used for vector drawables in older API versions with the `vectorDrawables.useSupportLibrary = true` configuration parameter in the `build.gradle` file.
- Once you've enabled the support library for vector drawables, use the `app:srcCompat` attribute in the `` element (instead of `android:src`) to specify the vector drawable source for that image.

### The `app` namespace:

- The `app` namespace in your XML layout file is for attributes that come from either your custom code or from libraries, not from the core Android framework.