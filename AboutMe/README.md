# Summary

### dp vs sp

The *dp* abbreviation stands for *density-independent*. If you want a UI element to look the same size on screens with different densities, use dp as your unit of measurement. When specifying text size, however, always use sp (scalable pixels).

## Data Binding

![img](https://codelabs.developers.google.com/codelabs/kotlin-android-training-data-binding-basics/img/204bd94c4dd5dd37.jpeg)

Data binding has the following benefits:

- Code is shorter, easier to read, and easier to maintain than code that uses `findByView()`.
- Data and views are clearly separated. This benefit of data binding becomes increasingly important later in this course.
- The Android system only traverses the view hierarchy once to get each view, and it happens during app startup, not at runtime when the user is interacting with the app.
- You get [type safety](https://en.wikipedia.org/wiki/Type_safety) for accessing views. (*Type safety* means that the compiler validates types while compiling, and it throws an error if you try to assign the wrong type to a variable.)