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