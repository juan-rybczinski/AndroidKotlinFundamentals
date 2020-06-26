Summary
============================================================

### To make items in a `RecyclerView` respond to clicks

- Create a listener class that takes a lambda and assigns it to an `onClick()` function.

```
class SleepNightListener(val clickListener: (sleepId: Long) -> Unit) {
   fun onClick(night: SleepNight) = clickListener(night.nightId)
}
```

- Set the click listener on the view.

```
android:onClick="@{() -> clickListener.onClick(sleep)}"
```

- Pass the click listener to the adapter constructor, into the view holder, and add it to the binding object.

```
class SleepNightAdapter(val clickListener: SleepNightListener):
       ListAdapter<DataItem, RecyclerView.ViewHolder>(SleepNightDiffCallback()
holder.bind(getItem(position)!!, clickListener)
binding.clickListener = clickListener
```

- In the fragment that shows the recycler view, where you create the adapter, define a click listener by passing a lambda to the adapter.

```
val adapter = SleepNightAdapter(SleepNightListener { nightId ->
      sleepTrackerViewModel.onSleepNightClicked(nightId)
})
```

- Implement the click handler in the view model. For clicks on list items, this commonly triggers navigation to a detail fragment.

### Major steps for adding a header

- Abstract the data in your list by creating a `DataItem` that can hold a header or data.
- Create a view holder with a layout for the header in the adapter.
- Update the adapter and its methods to use any kind of `RecyclerView.ViewHolder`.
- In `onCreateViewHolder()`, return the correct type of view holder for the data item.
- Update `SleepNightDiffCallback` to work with the `DataItem` class.
- Create a `addHeaderAndSubmitList()` function that uses coroutines to add the header to the dataset and then calls `submitList()`.
- Implement `GridLayoutManager.SpanSizeLookup()` to make only the header three spans wide.