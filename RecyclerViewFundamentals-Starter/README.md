Summary
============================================================

### RecyclerView

- Displaying a list or grid of data is one of the most common UI tasks in Android. `RecyclerView` is designed to be efficient even when displaying extremely large lists.
- `RecyclerView` does only the work necessary to process or draw items that are currently visible on the screen.
- When an item scrolls off the screen, its views are recycled. That means the item is filled with new content that scrolls onto the screen.
- The [adapter pattern](https://en.wikipedia.org/wiki/Adapter_pattern) in software engineering helps an object work together with another API. `RecyclerView` uses an adapter to transform app data into something it can display, without the need for changing how the app stores and processes data.

To display your data in a `RecyclerView`, you need the following parts:

- **RecyclerView
  **To create an instance of [`RecyclerView`](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView), define a `<RecyclerView>` element in the layout file.

- **LayoutManager
  **A `RecyclerView` uses a `LayoutManager` to organize the layout of the items in the `RecyclerView`, such as laying them out in a grid or in a linear list.

  In the `<RecyclerView>` in the layout file, set the `app:layoutManager` attribute to the layout manager (such as `LinearLayoutManager` or `GridLayoutManager`).

  You can also set the `LayoutManager` for a `RecyclerView` programmatically. (This technique is covered in a later codelab.)

- **Layout for each item
  **Create a layout for one item of data in an XML layout file.

- **Adapter
  **Create an adapter that prepares the data and how it will be displayed in a `ViewHolder`. Associate the adapter with the `RecyclerView`.

  When `RecyclerView` runs, it will use the adapter to figure out how to display the data on the screen.

  The adapter requires you to implement the following methods:
  – `getItemCount()` to return the number of items.
  – `onCreateViewHolder()` to return the `ViewHolder` for an item in the list.
  – `onBindViewHolder()` to adapt the data to the views for an item in the list.

  

- **ViewHolder
  **A `ViewHolder` contains the view information for displaying one item from the item's layout.

- The `onBindViewHolder()` method in the adapter adapts the data to the views. You always override this method. Typically, `onBindViewHolder()` inflates the layout for an item, and puts the data in the views in the layout.

- Because the `RecyclerView` knows nothing about the data, the `Adapter` needs to inform the `RecyclerView` when that data changes. Use `notifyDataSetChanged()`to notify the `Adapter` that the data has changed.

![img](https://codelabs.developers.google.com/codelabs/kotlin-android-training-diffutil-databinding/img/6c32c918f96efb46.png)

- From user input, the app creates a list of `SleepNight` objects. Each `SleepNight` object represents a single night of sleep, its duration, and quality.
- The `SleepNightAdapter` adapts the list of `SleepNight` objects into something `RecyclerView` can use and display.
- The `SleepNightAdapter` adapter produces `ViewHolders` that contain the views, data, and meta information for the recycler view to display the data.
- `RecyclerView` uses the `SleepNightAdapter` to determine how many items there are to display (`getItemCount()`). `RecyclerView` uses `onCreateViewHolder()` and `onBindViewHolder()` to get view holders bound to data for displaying.

`DiffUtil`:

- `RecyclerView` has a class called `DiffUtil` which is for calculating the differences between two lists.
- `DiffUtil` has a class called `ItemCallBack` that you extend in order to figure out the difference between two lists.
- In the `ItemCallback` class, you must override the `areItemsTheSame()` and `areContentsTheSame()` methods.

`ListAdapter`:

- To get some list management for free, you can use the `ListAdapter` class instead of `RecyclerView.Adapter`. However, if you use `ListAdapter` you have to write your own adapter for other layouts, which is why this codelab shows you how to do it.
- To open the intention menu in Android Studio, place the cursor on any item of code and press `Alt+Enter` (`Option+Enter` on a Mac). This menu is particularly helpful for refactoring code and creating stubs for implementing methods. The menu is context-sensitive, so you need to place cursor exactly to get the correct menu.

Data binding:

- Use data binding in the item layout to bind data to the views.

Binding adapters:

- You previously used `Transformations` to create strings from data. If you need to bind data of different or complex types, provide binding adapters to help data binding use them.
- To declare a binding adapter, define a method that takes an item and a view, and annotate the method with `@BindingAdapter`. In Kotlin, you can write the binding adapter as an extension function on the `View`. Pass in the name of the property that the adapter adapts. For example:

```
@BindingAdapter("sleepDurationFormatted")
```

- In the XML layout, set an `app` property with the same name as the binding adapter. Pass in a variable with the data. For example:

```
.app:sleepDurationFormatted="@{sleep}"
```