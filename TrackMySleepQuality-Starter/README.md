Summary
==================================

![img](https://codelabs.developers.google.com/codelabs/kotlin-android-training-room-database/img/c4a598be115aa77a.png)

- Define your tables as data classes annotated with `@Entity`. Define properties annotated with `@ColumnInfo` as columns in the tables.
- Define a data access object (DAO) as an interface annotated with `@Dao`. The DAO maps Kotlin functions to database queries.
- Use annotations to define `@Insert`, `@Delete`, and `@Update` functions.
- Use the `@Query` annotation with an SQLite query string as a parameter for any other queries.
- Create an abstract class that has a `getInstance()` function that returns a database.
- Use instrumented tests to test that your database and DAO are working as expected. You can use the provided tests as a template.

### General Process of Getting Room Database

- Create a `public abstract` class that `extends RoomDatabase`. This class is to act as a database holder. The class is abstract, because `Room` creates the implementation for you.
- Annotate the class with `@Database`. In the arguments, declare the entities for the database and set the version number.
- Inside a `companion` object, define an abstract method or property that returns a `SleepDatabaseDao`. `Room` will generate the body for you.
- You only need one instance of the `Room` database for the whole app, so make the `RoomDatabase` a singleton.
- Use `Room`'s database builder to create the database only if the database doesn't exist. Otherwise, return the existing database.

#### Annotate `INSTANCE` with `@Volatile`

The value of a volatile variable will never be cached, and all writes and reads will be done to and from the main memory. This helps make sure the value of `INSTANCE` is always up-to-date and the same to all execution threads. It means that changes made by one thread to `INSTANCE` are visible to all other threads immediately, and you don't get a situation where, say, two threads each update the same entity in a cache, which would create a problem.

#### Wrapping the code to get the database into `synchronized`

Multiple threads can potentially ask for a database instance at the same time, resulting in two databases instead of one. This problem is not likely to happen in this sample app, but it's possible for a more complex app. Wrapping the code to get the database into `synchronized` means that only one thread of execution at a time can enter this block of code, which makes sure the database only gets initialized once.

#### Migration Strategy

Normally, you would have to provide a [migration](https://medium.com/androiddevelopers/understanding-migrations-with-room-f01e04b07929) object with a migration strategy for when the schema changes. A *migration object* is an object that defines how you take all rows with the old schema and convert them to rows in the new schema, so that no data is lost.

### Quick run-through of the Testing code

- `SleepDabaseTest` is a test class**.**
- The `@RunWith` annotation identifies the test runner, which is the program that sets up and executes the tests.
- During setup, the function annotated with `@Before` is executed, and it creates an in-memory `SleepDatabase` with the `SleepDatabaseDao`. "In-memory" means that this database is not saved on the file system and will be deleted after the tests run.
- Also when building the in-memory database, the code calls another test-specific method, `allowMainThreadQueries`. By default, you get an error if you try to run queries on the main thread. This method allows you to run tests on the main thread, which you should only do during testing.
- In a test method annotated with `@Test`, you create, insert, and retrieve a `SleepNight`, and assert that they are the same. If anything goes wrong, throw an exception. In a real test, you would have multiple `@Test` methods.
- When testing is done, the function annotated with `@After` executes to close the database.

### `<merge>`

The `merge` tag can be used to eliminate redundant layouts when including layouts, and it's a good idea to use it. An example of a redundant layout would be ConstraintLayout > LinearLayout > TextView, where the system might be able to eliminate the LinearLayout. This kind of optimization can simplify the view hierarchy and improve app performance.

### Coroutine

In Kotlin, coroutines are the way to handle long-running tasks elegantly and efficiently. Kotlin coroutines let you convert callback-based code to sequential code. Code written sequentially is typically easier to read and can even use language features such as exceptions. In the end, coroutines and callbacks do the same thing: they wait until a result is available from a long-running task and continue execution.

![img](https://codelabs.developers.google.com/codelabs/kotlin-android-training-coroutines-and-room/img/952b19bd8601a7a5.png)

**Coroutines are asynchronous.**

A coroutine runs independently from the main execution steps of your program. This could be in parallel or on a separate processor. It could also be that while the rest of the app is waiting for input, you sneak in a bit of processing. One of the important aspects of async is that you cannot expect that the result is available, until you explicitly wait for it.

For example, let's say you have a question that requires research, and you ask a colleague to find the answer. They go off and work on it, which is like they're doing the work "asynchronously" and "on a separate thread." You can continue to do other work that doesn't depend on the answer, until your colleague comes back and tells you what the answer is.

**Coroutines are non-blocking.**

*Non-blocking* means that a coroutine does not block the main or UI thread. So with coroutines, users always have the smoothest possible experience, because the UI interaction always has priority.

**Coroutines use suspend functions to make asynchronous code sequential.**

The keyword `suspend` is Kotlin's way of marking a function, or function type, as being available to coroutines. When a coroutine calls a function marked with `suspend`, instead of blocking until the function returns like a normal function call, the coroutine suspends execution until the result is ready. Then the coroutine resumes where it left off, with the result.

While the coroutine is suspended and waiting for a result, it unblocks the thread that it's running on. That way, other functions or coroutines can run.

The `suspend` keyword doesn't specify the thread that the code runs on. A suspend function may run on a background thread, or on the main thread.

> **Tip:** The difference between *blocking* and *suspending* is that if a thread is blocked, no other work happens. If the thread is suspended, other work happens until the result is available.

![img](https://codelabs.developers.google.com/codelabs/kotlin-android-training-coroutines-and-room/img/ce77d98e12909f3e.png)

**Job**: Basically, a job is anything that can be canceled. Every coroutine has a job, and you can use the job to cancel the coroutine. Jobs can be arranged into parent-child hierarchies. Canceling a parent job immediately cancels all the job's children, which is a lot more convenient than canceling each coroutine manually.

**Dispatcher:** The dispatcher sends off coroutines to run on various threads. For example, `Dispatcher.Main` runs tasks on the main thread, and `Dispatcher.IO` offloads blocking I/O tasks to a shared pool of threads.

**Scope:** A coroutine's *scope* defines the context in which the coroutine runs. A scope combines information about a coroutine's job and dispatcher. Scopes keep track of coroutines. When you launch a coroutine, it's "in a scope," which means that you've indicated which scope will keep track of the coroutine.

### Coroutine Pattern Example

1. Launch a coroutine that runs on the main or UI thread, because the result affects the UI.
2. Call a suspend function to do the long-running work, so that you don't block the UI thread while waiting for the result.
3. The long-running work has nothing to do with the UI. Switch to the I/O context, so that the work can run in a thread pool that's optimized and set aside for these kinds of operations.
4. Then call the database function to do the work.

```kotlin
fun someWorkNeedsToBeDone {
   uiScope.launch {
        suspendFunction()
   }
}

suspend fun suspendFunction() {
   withContext(Dispatchers.IO) {
       longrunningWork()
   }
}
```

> Use a `Transformations` map to create a string from a `LiveData` object every time the object changes.

> [CDATA](http://www.w3.org/TR/REC-xml/#sec-cdata-sect) stands for [*character data*](http://www.w3.org/TR/REC-xml/#dt-chardata). CDATA means that the data in between these strings includes data that could be interpreted as XML markup, but should not be.

> ### Tip: Setting the appearance of a disabled View
>
> The `enabled` attribute is not the same as the `visibility` attribute. The `enabled` attribute only determines whether the `View` is enabled, not whether the `View` is visible.
>
> The meaning of "enabled" varies by the subclass. A user can edit the text in an enabled `EditText`, but not in a disabled `EditText`. A user can tap an enabled `Button`, but can't tap a disabled one.
>
> A default style is applied to a disabled `View` to visually represent that the `View` is not active.
>
> However, if the `View` has a `background` attribute or a `textColor` attribute, the values of those attributes are used when the `View` is displayed, *even if the* *`View`* *is disabled.*
>
> To define which colors to use for enabled and disabled states, use a [`ColorStateList`](https://developer.android.com/guide/topics/resources/color-list-resource) for the text color and a [`StateListDrawable`](https://developer.android.com/guide/topics/resources/drawable-resource#StateList) for the background color.

### Pattern

- Create a `ViewModel` and a `ViewModelFactory` and set up a data source.
- Trigger navigation. To separate concerns, put the click handler in the view model and the navigation in the fragment.
- Use encapsulation with `LiveData` to track and respond to state changes.
- Use transformations with `LiveData`.
- Create a singleton database.
- Set up coroutines for database operations.